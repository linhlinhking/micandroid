package com.cisco.pmonitor.service.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.telnet.EchoOptionHandler;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;
import org.apache.commons.net.telnet.SuppressGAOptionHandler;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TerminalTypeOptionHandler;

/**
 * provide telnet protocol connection.
 * @author shuaizha
 *
 */
public class Telnet{

	private String hostAddress;
	private int remotePort;
	private TelnetClient tc;
	private OutputStream sender;
	private InputStream receive;
	private volatile StringBuffer sb = new StringBuffer();
	
	private long timeout;
	
	public Telnet(String hostAddress) {
		this(hostAddress, 23);
	}
	
	public Telnet(String hostAddress, int remotePort) {
		this.hostAddress = hostAddress;
		this.remotePort = remotePort;
		this.timeout = 0x927C0; // default the timeout is one minute
	}
	
	public void init() {
		tc = new TelnetClient();
		TerminalTypeOptionHandler ttopt = new TerminalTypeOptionHandler("", false, false, true, false);
        EchoOptionHandler echoopt = new EchoOptionHandler(true, false, true, false);
        SuppressGAOptionHandler gaopt = new SuppressGAOptionHandler(true, true, true, true);
        try {
			tc.addOptionHandler(ttopt);
			tc.addOptionHandler(echoopt);
		    tc.addOptionHandler(gaopt);
		    
		    tc.connect(hostAddress, remotePort);
		    sender = tc.getOutputStream();
		} catch (InvalidTelnetOptionException e) {
		} catch (IOException e) {
		}
	}
	
	public boolean send(String command) {
		boolean success = false;
		if(null == sender || null == command) {
			return success;
		}
		command = command + "\r\n";
		byte[] buffer = command.getBytes();
		try {
			sender.write(buffer, 0, buffer.length);
			sender.flush();
			success = true;
		} catch (IOException e) {
			success = true;
		}
		return success;
	}
	
	public void destory() {
		try {
			tc.disconnect();
		} catch (IOException e) {
			if(null != tc) {
				tc = null;
			}
		}
	}
	
	/**
	 * receive the message from console with the special command 
	 * @return
	 */
	public String receive() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				receive = tc.getInputStream();
				try{
		            byte[] buff = new byte[1024];
		            int ret_read = 0;

		            do{
		                ret_read = receive.read(buff);
		                if(ret_read > 0){
		                	sb.append(new String(buff, 0, ret_read));
		                	System.out.println(new String(buff, 0, ret_read));
		                }
		            } while (ret_read >= 0);
		        }catch (IOException e){
		        }
			}}).start();
		return sb.toString();
	}
	
	/**
	 * verify the command is running over or not
	 * @param pattern
	 * @return
	 */
	public boolean finishCommand(String pattern) {
		return finishCommand(pattern, timeout);
	}
	
	public boolean finishCommand(String pattern, long timeout) {
		long timer = 0;
		boolean success = false;
		do {
			timer += 100;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			success = sb.toString().contains(pattern);
			if(success) {
				sb.delete(0, sb.length());
				break;
			}
		}while(timer < timeout);
		return success;
	}
	
	public static void main(String[] args) throws InterruptedException {
//		Telnet telnet = new Telnet("192.168.78.129");
		Telnet telnet = new Telnet("sh-cable-con-40.cisco.com");
		telnet.init();
//		new Thread(telnet).start();
		telnet.receive();
		if(telnet.finishCommand("Password")) {
			telnet.send("lab");
		}
		if(telnet.finishCommand(">")) {
			telnet.send("enable");
		}
		if(telnet.finishCommand("Password")) {
			telnet.send("poPPee");
		}
		telnet.send("");
		if(telnet.finishCommand("#")){
			telnet.send("conf t");
		}
		telnet.send("line " + 50);
		Thread.sleep(2000);
		telnet.destory();
//		telnet.send("ls -l");
		
	}
	
}
