package com.jshop.action.front;


import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Controller;

import com.jshop.action.tools.BaseTools;
import com.jshop.entity.UserT;
import com.jshop.service.impl.UsertServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("jshop")
@Namespace("")
@InterceptorRefs({  
    @InterceptorRef("defaultStack")  
})
@Controller("myInfoAction")
public class MyInfoAction extends ActionSupport {
	@Resource(name="usertServiceImpl")
	private UsertServiceImpl usertServiceImpl;
	private String userid;
	private String username;
	private String realname;
	private String email;
	private String telno;
	private String mobile;
	private String question;
	private String answer;
	private String password;
	private String userstate;
	private Double points;
	private Integer postingcount;
	private String sex;
	private Date registtime;
	private Date disablebegin;
	private Date disableend;
	private String section;
	private String position;
	private String groupid;
	private String parttime1;
	private String parttime2;
	private String parttime3;
	private String parttime4;
	private String parttime5;
	private String parttime6;
	private String hobby;
	private String qq;
	private String msn;
	private String othercontract;
	private String address;
	private String postcode;
	private String birthday;
	private String grade;
	private Date gradetime;
	private String state;
	@JSON(serialize = false)
	public UsertServiceImpl getUsertServiceImpl() {
		return usertServiceImpl;
	}

	public void setUsertServiceImpl(UsertServiceImpl usertServiceImpl) {
		this.usertServiceImpl = usertServiceImpl;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserstate() {
		return userstate;
	}

	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public Integer getPostingcount() {
		return postingcount;
	}

	public void setPostingcount(Integer postingcount) {
		this.postingcount = postingcount;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getRegisttime() {
		return registtime;
	}

	public void setRegisttime(Date registtime) {
		this.registtime = registtime;
	}

	public Date getDisablebegin() {
		return disablebegin;
	}

	public void setDisablebegin(Date disablebegin) {
		this.disablebegin = disablebegin;
	}

	public Date getDisableend() {
		return disableend;
	}

	public void setDisableend(Date disableend) {
		this.disableend = disableend;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getParttime1() {
		return parttime1;
	}

	public void setParttime1(String parttime1) {
		this.parttime1 = parttime1;
	}

	public String getParttime2() {
		return parttime2;
	}

	public void setParttime2(String parttime2) {
		this.parttime2 = parttime2;
	}

	public String getParttime3() {
		return parttime3;
	}

	public void setParttime3(String parttime3) {
		this.parttime3 = parttime3;
	}

	public String getParttime4() {
		return parttime4;
	}

	public void setParttime4(String parttime4) {
		this.parttime4 = parttime4;
	}

	public String getParttime5() {
		return parttime5;
	}

	public void setParttime5(String parttime5) {
		this.parttime5 = parttime5;
	}

	public String getParttime6() {
		return parttime6;
	}

	public void setParttime6(String parttime6) {
		this.parttime6 = parttime6;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getOthercontract() {
		return othercontract;
	}

	public void setOthercontract(String othercontract) {
		this.othercontract = othercontract;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getGradetime() {
		return gradetime;
	}

	public void setGradetime(Date gradetime) {
		this.gradetime = gradetime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 清理错误
	 */
	@Override
	public void validate() {
		this.clearErrorsAndMessages(); 
	
	}
	
	/**
	 * 根据用户id获取用户信息
	 * @return
	 */
	@Action(value = "findUserByuserId", results = { 
			@Result(name = "success",location = "/usercenter/basicinfo/myinfo.jsp"),
			@Result(name = "input",type="redirect",location = "/html/default/shop/login.html")
	})
	public String findUserByuserId(){
		UserT user=(UserT)ActionContext.getContext().getSession().get(BaseTools.USER_SESSION_KEY);
		if(user!=null){
			ActionContext.getContext().put("u", user);
			return SUCCESS;
		}
		return INPUT;
	}
	
	
	/**
	 * 更新用户更新自己的资料
	 * @return
	 */
	
	@Action(value = "UpdateUserforMyInfo", results = { 
			@Result(name = "success",type="chain",location = "findUserByuserId"),
			@Result(name = "input",location = "/usercenter/login.jsp")
	})
	public String UpdateUserforMyInfo(){
		UserT user=(UserT)ActionContext.getContext().getSession().get(BaseTools.USER_SESSION_KEY);
		if(user!=null){
			UserT u1=new UserT();
			u1.setUserid(user.getUserid());
			u1.setUsername(user.getUsername());
			u1.setRealname(this.getRealname().trim());
			u1.setEmail(user.getEmail());
			u1.setTelno(this.getTelno().trim());
			u1.setMobile(this.getMobile().trim());
			u1.setQuestion(user.getQuestion());
			u1.setAnswer(user.getAnswer());
			u1.setPassword(user.getPassword());
			u1.setUserstate(user.getUserstate());
			u1.setPoints(user.getPoints());
			u1.setPostingcount(user.getPostingcount());
			u1.setSex(this.getSex());
			u1.setRegisttime(user.getRegisttime());
			u1.setDisablebegin(user.getDisablebegin());
			u1.setDisableend(user.getDisableend());
			u1.setSection(user.getSection());
			u1.setPosition(user.getPosition());
			u1.setGroupid(user.getGroupid());
			u1.setParttime1(user.getParttime1());
			u1.setParttime2(user.getParttime2());
			u1.setParttime3(user.getParttime3());
			u1.setParttime4(user.getParttime4());
			u1.setParttime5(user.getParttime5());
			u1.setParttime6(user.getParttime6());
			u1.setHobby(user.getHobby());
			u1.setQq(this.getQq().trim());
			u1.setMsn(this.getMsn().trim());
			u1.setOthercontract(this.getOthercontract().trim());
			u1.setAddress(user.getAddress());
			u1.setPostcode(user.getPostcode());
			u1.setBirthday(user.getBirthday());
			u1.setGrade(user.getGrade());
			u1.setGradetime(user.getGradetime());
			u1.setState(user.getState());
			if(this.getUsertServiceImpl().updateUserforMyInfo(u1)>0){
				return SUCCESS;
			}else{
				return INPUT;
			}
		}else{
			return INPUT;
		}
	}
	
	
	
	
	
}
