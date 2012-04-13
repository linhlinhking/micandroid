/*
 * FileUtilTest.java 2009-10-25
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.util;

import org.jxstar.util.FileUtil;


/**
 * 
 *
 * @author TonyTan
 * @version 1.0, 2009-10-25
 */
public class FileUtilTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "d:/aaaa/cc/dd/ee/aa/ff/bb.txt";
		
		String name = FileUtil.getFileName(path);
		System.out.println("================name=" + name);
	}

	public static void saveUtf8() {
		String fileName = "d:/aaaa/cc/dd/ee/aa/ff/bb.txt";
		String  content = "aaaaaaaaaaaaaaaa";
		
		FileUtil.saveFileUtf8(fileName, content);
	}
}
