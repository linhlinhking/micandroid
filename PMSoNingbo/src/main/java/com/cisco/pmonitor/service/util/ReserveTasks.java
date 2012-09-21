package com.cisco.pmonitor.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.cisco.pmonitor.dao.dataobject.EquipmentDo;
import com.cisco.pmonitor.dao.dataobject.ReserveDo;
import com.cisco.pmonitor.dao.query.ReserveQuery;
import com.cisco.pmonitor.dao.utils.Constants;
import com.cisco.pmonitor.service.IEquipmentService;
import com.cisco.pmonitor.service.IReserveService;
import com.cisco.pmonitor.service.exception.ServiceException;

/**
 * An auto complete reservation operation program,
 * it will check the system once a day. 
 * @author shuaizha
 *
 */
public class ReserveTasks {

	private static final int RESERVE_PERIOD = 0x5265C00;
	private IEquipmentService equipmentService;
	private IReserveService reserveService;
	public void init() {
		Timer timer = new Timer("reserveTimer", true);
		timer.schedule(new ReserveTask(), new Date(), RESERVE_PERIOD);
	}
	
	//TODO every update status operation needs email to the reserver or  need to consider to mail to the overdue reserver
	private class ReserveTask extends TimerTask {

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			Result<List<EquipmentDo>> eRs;
			Result<Map<String, Object>> rMap;
			Result<ReserveDo> rRs;
			Result<Integer> iRs;
			try {
				eRs = equipmentService.loadAllEquipments();
				if(eRs.isSuccess()) {
					List<EquipmentDo> eList = eRs.getDefaultModel();
					if(null != eList && eList.size() > 0) {
						for(EquipmentDo eDo : eList) {
							ReserveQuery query = new ReserveQuery();
							query.setSort("START_TIME");
							query.setOrder("asc");
							query.setEquipmentId(eDo.getId());
							rMap = reserveService.loadReservesByQuery(query);
							if(rMap.isSuccess()) {
								List<ReserveQuery> rList = (List<ReserveQuery>) rMap.getDefaultModel().get("rows");
								if(null != rList && rList.size() > 0) {
									for(int i = 0;i < rList.size(); i ++) {
										ReserveQuery rq = rList.get(i);
										if(rq.getStatus() == Constants.RESERVED_STATUS) {
											//firstly, we need to check the time of reserve status.
											String time = rq.getEndTime();
											long endTime = strDate2long(time, Constants.PM_DATE_FORMAT);
											long now = System.currentTimeMillis();
											if(endTime < now) {
//												System.out.println("The reserved record is overdue....");
												rRs = reserveService.findReserveById(rq.getId());
												if(rRs.isSuccess()) {
													ReserveDo reserve = rRs.getDefaultModel();
													reserve.setStatus(Constants.OVERDUE_STATUS);
													iRs = reserveService.updateReserve(reserve);
													if(iRs.isSuccess()) {
														if(rList.size() > 1 && i < rList.size() - 1) {
															//update the next record status when the start time is earlier than now time.
															ReserveQuery next = rList.get(i + 1);
															if(strDate2long(next.getStartTime(), Constants.PM_DATE_FORMAT) <= now) {
																rRs = reserveService.findReserveById(next.getId());
																if(rRs.isSuccess()) {
																	ReserveDo rDo = rRs.getDefaultModel();
																	rDo.setStatus(Constants.RESERVED_STATUS);
																	reserveService.updateReserve(reserve);
																	break;
																	
																}
															}
															else {
																eDo.setStatus(Constants.IDLE_EQUIPMENT_STATUS);
																equipmentService.updateEquipment(eDo);
																break;
															}
														}
														else {
															//if only one reserve record, update the status of equipment is idle.
															eDo.setStatus(Constants.IDLE_EQUIPMENT_STATUS);
															equipmentService.updateEquipment(eDo);
															break;
														}
													}
												}
											}
											else {
												break;
											}
										}
										else if(rq.getStatus() == Constants.PENDING_STATUS){
											long idleTime = strDate2long(rq.getStartTime(), Constants.PM_DATE_FORMAT);
											long idleNow = System.currentTimeMillis();
											if(idleTime < idleNow) {
												//update the reserve status
												Result<ReserveDo> idleRs = reserveService.findReserveById(rq.getId());
												if(idleRs.isSuccess()) {
													ReserveDo idleR = idleRs.getDefaultModel();
													idleR.setStatus(Constants.RESERVED_STATUS);
													Result<Integer> idleIRs = reserveService.updateReserve(idleR);
													if(idleIRs.isSuccess()) {
														//update the equipment
														eDo.setStatus(Constants.RESERVE_EQUIPMENT_STATUS);
														equipmentService.updateEquipment(eDo);
														break;
													}
												}
											}
											else {
												break;
											}
											
										}
									}
								}
							}
						}
					}
				}
			} catch (ServiceException e) {
			}
		}
		
	}


	public void setEquipmentService(IEquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}


	public void setReserveService(IReserveService reserveService) {
		this.reserveService = reserveService;
	}
	
	private long strDate2long(String time, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
		}
		return date.getTime();
	}
}
