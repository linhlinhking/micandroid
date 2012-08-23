package ningbo.media.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.handler.AbstractHandler;

public class ServerHandler extends AbstractHandler{

	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, int dispatch) throws IOException,
			ServletException {
			response.setContentType("text/html;charset=utf-8") ;
			response.setStatus(HttpServletResponse.SC_OK) ;
	}

}
