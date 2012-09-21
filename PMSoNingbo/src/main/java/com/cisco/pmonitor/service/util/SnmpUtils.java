package com.cisco.pmonitor.service.util;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 * The SnmpUtils class provide some method to handle the snmp protocol request.
 * @author shuaizha
 * @date 2012-3-5
 */
public final class SnmpUtils {

	
	public static void main(String[] args) throws Exception {
		Address targetAddress = GenericAddress.parse("udp:10.74.59.77/161");
		
		TransportMapping transport = new DefaultUdpTransportMapping();
		Snmp snmp = new Snmp(transport);
		snmp.listen();
		
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString("public"));
		target.setAddress(targetAddress);
		target.setRetries(2);
		target.setTimeout(1500);
		target.setVersion(SnmpConstants.version2c);
		
		PDU pdu = new PDU();
		
		
		pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.14300.1.2.1.2.1.4.2")));
		pdu.setType(PDU.GET);
		
		ResponseEvent event = snmp.send(pdu, target);
		
		if(null != event) {
			System.out.println(event.getResponse().get(0).getVariable().toString());
		}
		snmp.close();
		
	}
}
