package team1.videoplay.video.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;
import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class VideoDetailServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int videoID = Integer.parseInt(request.getParameter("videoID"));
		Video video = VideoServiceImpl.getInstance().findVideoID(videoID);
		if(request.getSession().getAttribute("userInfo")!=null){
			User user1 = (User)request.getSession().getAttribute("userInfo");
			User user = UserServiceImpl.getInstance().getUser(user1.getUser_id());
			request.setAttribute("user", user);
		}
		request.setAttribute("videoInfo", video);
		request.getRequestDispatcher("videoDetail.jsp").forward(request, response);
	}
}
