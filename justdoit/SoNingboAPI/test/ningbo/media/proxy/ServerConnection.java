package ningbo.media.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerConnection {
	
	//private static String remoteURL = "https://api.searchningbo.com/resource/user/show/27" ;
	private static String localURL = "http://192.168.0.104:8000/resource/user/show/27" ;

	public ServerConnection() {

	}
	
	public static void main(String args[]){
		ServerConnection s = new ServerConnection() ;
		long startTime = System.currentTimeMillis() ;
		String temp = s.doGet(localURL) ;
		long endTime = System.currentTimeMillis() ;
		long used = endTime - startTime ;
		System.out.println(temp) ;
		System.out.println("used:" + used + " ms" ) ;
	}

	public String doGet(String path) {
		URL url;
		HttpURLConnection uRLConnection;
		try {
			url = new URL(path);
			uRLConnection = (HttpURLConnection) url.openConnection();
			InputStream is = uRLConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String response = "";
			String readLine = null;
			while ((readLine = br.readLine()) != null) {
				response = response + readLine;
			}
			is.close();
			br.close();
			uRLConnection.disconnect();
			return response;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * public String doPost(String username,String password){ try { url = new
	 * URL(urlAddress); uRLConnection = (HttpURLConnection)url.openConnection();
	 * uRLConnection.setDoInput(true); uRLConnection.setDoOutput(true);
	 * uRLConnection.setRequestMethod("POST");
	 * uRLConnection.setUseCaches(false);
	 * uRLConnection.setInstanceFollowRedirects(false);
	 * uRLConnection.setRequestProperty("Content-Type",
	 * "application/x-www-form-urlencoded"); uRLConnection.connect();
	 * 
	 * DataOutputStream out = new
	 * DataOutputStream(uRLConnection.getOutputStream()); String content =
	 * "username="+username+"&password="+password; out.writeBytes(content);
	 * out.flush(); out.close();
	 * 
	 * InputStream is = uRLConnection.getInputStream(); BufferedReader br = new
	 * BufferedReader(new InputStreamReader(is)); String response = ""; String
	 * readLine = null; while((readLine =br.readLine()) != null){ response =
	 * response + readLine; } is.close(); br.close();
	 * uRLConnection.disconnect(); return response; } catch
	 * (MalformedURLException e) { e.printStackTrace(); return null; } catch
	 * (IOException e) { e.printStackTrace(); return null; }
	 *  }
	 */
}
