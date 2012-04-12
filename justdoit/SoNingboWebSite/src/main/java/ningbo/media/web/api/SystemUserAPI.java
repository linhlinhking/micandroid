package ningbo.media.web.api;

import java.util.ArrayList;
import java.util.List;

import ningbo.media.web.bean.QParameter;


public class SystemUserAPI extends RequestAPI{

	
	public String login(String username,String password,String key,String device_id) throws Exception{
		List<QParameter> parameters = new ArrayList<QParameter>();
		parameters.add(new QParameter("username", username)) ;
		parameters.add(new QParameter("password", password)) ;
		parameters.add(new QParameter("key", key)) ;
		parameters.add(new QParameter("device_id", device_id)) ;
		return postContent("https://api.searchningbo.com/resource/user/login", parameters) ;
	}
	
	public String show(String userid) throws Exception{
		return getResource("https://api.searchningbo.com/resource/user/show/" + userid, null);
	}
	

	public String register(String username,String name_cn,String email,String password, String gender, String birthday,String key) throws Exception{
		List<QParameter> parameters = new ArrayList<QParameter>();
		parameters.add(new QParameter("username", username)) ;
		parameters.add(new QParameter("name_cn", name_cn)) ;
		parameters.add(new QParameter("email", email)) ;
		parameters.add(new QParameter("password", password)) ;
		parameters.add(new QParameter("gender", gender)) ;
		parameters.add(new QParameter("birthday", birthday)) ;
		parameters.add(new QParameter("key", key)) ;
		return postContent("https://api.searchningbo.com/user/register", parameters) ;
	}
	
	
	
}
