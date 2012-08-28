package ningbo;

import ningbo.media.util.ServerHandler;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class JettyServer {
	
	static Server server;
	static int port = 8000;

	public static void main(String args[]) throws Exception {
		server = new Server(port);
		server.setHandler(new ServerHandler()) ;
		//WebAppContext context = new WebAppContext();
		//context.setWar("src/main/webapp") ;
		//context.setContextPath("/") ;
		
		
		server.addHandler(new WebAppContext("src/main/webapp","/"));
		server.start();
	}

}
