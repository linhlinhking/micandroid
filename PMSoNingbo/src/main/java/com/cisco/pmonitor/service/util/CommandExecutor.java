package com.cisco.pmonitor.service.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The tool provides the method to invoke the system command or the script.
 * @author shuaizha
 * @date 2012-3-15
 */
public final class CommandExecutor {
	
	/**
	 * set the path of the script file.
	 */
	public static final String SCRIPT_DIR = System.getProperty("user.dir");

	private CommandExecutor() {}
	
	/**
	 * create a command executor instance.
	 * @return
	 */
	public static CommandExecutor createCommandExecutor() {
		return new CommandExecutor();
	}
	
	/**
	 * invoke the command.
	 * @param command
	 * @return
	 */
	public synchronized String invoke(String ...command) {
		ProcessBuilder pb = new ProcessBuilder(command);
		//set the default directory of the script file.
		pb.directory(new File(SCRIPT_DIR));
		
		pb.redirectErrorStream(true);
		
		Process proc = null;
		BufferedReader br = null;
		InputStream is = null;
		StringBuffer msg = new StringBuffer();
		try {
			proc = pb.start();
			is = proc.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String tmp = "";
			while(null != (tmp = br.readLine())) {
				msg.append(tmp);
				msg.append(System.getProperty("line.separator"));
			}
		} catch (IOException e) {
		}
		return msg.toString();
	}
	
	/**
	 * verify the result of the invoking command.
	 * @param pattern
	 * @param command
	 * @return
	 */
	public boolean execute(String pattern, String ...command) {
		String target = invoke(command);
		return null != target && target.contains(pattern);
	}
	
	
	public static void main(String[] args) throws IOException {
		CommandExecutor executor = CommandExecutor.createCommandExecutor();
		System.out.println(executor.execute("1.6.0_26", "java", "-version"));
	}
}
