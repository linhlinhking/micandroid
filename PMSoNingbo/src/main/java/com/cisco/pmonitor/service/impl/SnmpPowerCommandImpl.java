package com.cisco.pmonitor.service.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import com.cisco.pmonitor.dao.dataobject.PowerCyclerDo;
import com.cisco.pmonitor.dao.utils.Constants;
import com.cisco.pmonitor.service.IPowerCommand;
import com.cisco.pmonitor.service.exception.ServiceException;

public class SnmpPowerCommandImpl implements IPowerCommand{

	protected volatile int counter;//verify thread complete
	
	@Override
	public Map<Integer, Boolean> execute(PowerCyclerDo pco,
			final Map<Integer, Boolean> outletMap, int state)throws ServiceException {
		if(null == pco || null == outletMap) {
			return null;
		}
		String ip = pco.getHost();
		ip = "udp:" + ip.trim() + "/161";
		try {
			Address targetAddress = GenericAddress.parse(ip);
			TransportMapping transport = new DefaultUdpTransportMapping();
			final Snmp snmp = new Snmp(transport);
			snmp.listen();
			
			final CommunityTarget target = new CommunityTarget();
			target.setCommunity(new OctetString("public"));
			target.setAddress(targetAddress);
			target.setRetries(2);
			target.setTimeout(1500);
			target.setVersion(SnmpConstants.version2c);
			
			for(final Iterator<Integer> it = outletMap.keySet().iterator();
					it.hasNext();) {
				final PDU pdu = new PDU();
				String oid = "";
				if(pco.getType() == Constants.DUALCOMM_POWERCYCLER) {
					oid = Constants.DUALCOMM_OUTLET_STATUS_OID + it.next();
				}
				if(pco.getType() == Constants.APC_POWERCYCLER) {
					oid = Constants.APC_OUTLET_STATUS_OID + it.next();
				}
				pdu.add(new VariableBinding(new OID(oid), 
						new Integer32(state)));
				pdu.setType(PDU.SET);
				
				final int outlet = it.next();
				new Thread(new Runnable(){
					@Override
					public void run() {
						ResponseEvent event;
						try {
							event = snmp.send(pdu, target);
							outletMap.put(outlet, event.getResponse() != null 
									&& event.getResponse().getErrorStatus() == 0);
						} catch (IOException e) {
							outletMap.put(outlet, false);
						}
						counter ++;
					}
					
				}).start();
			}
			while(counter < outletMap.keySet().size()) {
				Thread.sleep(10);
			}
			counter = 0;
		} catch (Exception e) {
			throw new ServiceException("SnmpPowerCommandImpl.execute", e);
		}
		return outletMap;
	}

}
