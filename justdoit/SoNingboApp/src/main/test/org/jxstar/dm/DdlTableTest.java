/*
 * DdlTableTest.java 2010-12-17
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.dm;


import org.jxstar.dm.DmException;
import org.jxstar.dm.ddl.MysqlDdlTable;
import org.jxstar.dm.ddl.OracleDdlTable;
import org.jxstar.test.AbstractTest;

/**
 * ORACLE数据库对象创建类测试
 *
 * @author TonyTan
 * @version 1.0, 2010-12-17
 */
public class DdlTableTest extends AbstractTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DdlTableTest.oracleTest();
	}

	public static void oracleTest() {
		OracleDdlTable oracle = new OracleDdlTable();
		try {
			//oracle.create("jxstar2");
			oracle.modify("jxstar3");
		} catch (DmException e) {
			e.printStackTrace();
		}
	}
	
	public static void mysqlTest() {
		MysqlDdlTable mysql = new MysqlDdlTable();
		try {
			mysql.create("test_table");
		} catch (DmException e) {
			e.printStackTrace();
		}
	}
}
