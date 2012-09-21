package com.cisco.pmonitor.dao.utils;

public final class Constants {

	
	public static final String PM_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String EASYUI_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
	
	public static final String PM_USER_SESSION = "PM_USER_SESSION";
	
	public static final int SESSION_OUT_TIME = 1800;
	
	public static final int SYSTEM_USER_ROLE = 1;
	public static final int NON_SYSTEM_USER_ROLE = 0;
	
	public static final int SYSTEM_USER_GROUP_ROLE = 1;
	public static final int NON_SYSTEM_USER_GROUP_ROLE = 0;
	
	public static final String SYSTEM_ROLE_VIEW = "Administrator";
	public static final String NON_SYSTEM_ROLE_VIEW = "User";
	
	public static final int IDLE_EQUIPMENT_STATUS = 0;
	public static final int RESERVE_EQUIPMENT_STATUS = 1;
	
	public static final int PENDING_STATUS = 0;
	public static final int RESERVED_STATUS = 1;
	public static final int OVERDUE_STATUS = -1;
	
	/**
	 * Four power cycler types
	 */
	
	public static final int DTR_POWERCYCLER = 1;
	public static final int DUALCOMM_POWERCYCLER = 2;
	public static final int APC_POWERCYCLER = 3;
	public static final int UBR10KLC_POWERCYCLER = 4;
	
	/**
	 * Two power status
	 */
	public static final int POWER_ON = 1;
	public static final int POWER_OFF = 0;
	
	/**
	 * Three communication protocols
	 */
	public static final int SNMP_PROTOCOL = 1;
	public static final int TELNET_PROTOCOL = 2;
	public static final int SOCKET_PROTOCOL = 3;
	
	/**
	 * DualComm powercycler oid information
	 */
	public static final String DUALCOMM_OUTLET_NUMS_OID = "1.3.6.1.4.1.14300.1.2.1.1";
	public static final String DUALCOMM_OUTLET_INDEX_OID = "1.3.6.1.4.1.14300.1.2.1.2.1.1.";
	public static final String DUALCOMM_OUTLET_LABEL_OID = "1.3.6.1.4.1.14300.1.2.1.2.1.2.";
	public static final String DUALCOMM_OUTLET_STATUS_OID = "1.3.6.1.4.1.14300.1.2.1.2.1.3.";
	
	/**
	 * APC powercycler oid information
	 */
	public static final String APC_OUTLET_NUMS_OID = "1.3.6.1.4.1.318.1.1.12.3.1.4.0";
	public static final String APC_OUTLET_INDEX_OID = "1.3.6.1.4.1.318.1.1.12.3.3.1.1.1.";
	public static final String APC_OUTLET_LABEL_OID = "1.3.6.1.4.1.318.1.1.12.3.3.1.1.2.";
	public static final String APC_OUTLET_STATUS_OID = "1.3.6.1.4.1.318.1.1.12.3.3.1.1.4.";
	
	/**
	 * DualComm power on/off status code
	 */
	public static final int DUALCOMM_ON_STATUS = 2;
	public static final int DUALCOMM_OFF_STATUS = 1;
	
	/**
	 * APC power on/off status code
	 */
	public static final int APC_ON_STATUS = 1;
	public static final int APC_OFF_STATUS = 2;
	
}
