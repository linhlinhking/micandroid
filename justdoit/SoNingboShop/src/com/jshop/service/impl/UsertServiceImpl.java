package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.UserTDaoImpl;
import com.jshop.entity.UserT;
import com.jshop.service.UsertService;

@Service("usertServiceImpl")
@Scope("prototype")
public class UsertServiceImpl implements UsertService {
	@Resource(name = "userTDaoImpl")
	private UserTDaoImpl userTDaoImpl;

	public UserTDaoImpl getUserTDaoImpl() {
		return userTDaoImpl;
	}

	public void setUserTDaoImpl(UserTDaoImpl userTDaoImpl) {
		this.userTDaoImpl = userTDaoImpl;
	}

	public int delete(UserT transientInstance) {
		return this.getUserTDaoImpl().delete(transientInstance);
	}

	public UserT findById(String id) {
		return this.getUserTDaoImpl().findById(id);
	}

	public UserT login(UserT transientInstance) {
		return this.getUserTDaoImpl().login(transientInstance);
	}

	public int save(UserT transientInstance) {
		return this.getUserTDaoImpl().save(transientInstance);
	}

	public List<UserT> findAllUsert(int currentPage, int lineSize) {
		return this.getUserTDaoImpl().findAllUsert(currentPage, lineSize);
	}

	public int countfindAllUsert() {
		return this.getUserTDaoImpl().countfindAllUsert();
	}

	public UserT checkUserByUsername(UserT transientInstance) {
		return this.getUserTDaoImpl().checkUserByUsername(transientInstance);
	}

	public int updateUserTunpwd(UserT u) {
		return this.getUserTDaoImpl().updateUserTunpwd(u);
	}

	public int delUser(String[] list) {
		return this.getUserTDaoImpl().delUser(list);
	}

	public int updateUserforMyInfo(UserT u) {
		return this.getUserTDaoImpl().updateUserforMyInfo(u);
	}

	public UserT checkUserByEmail(UserT u) {
		return this.getUserTDaoImpl().checkUserByEmail(u);
	}

	public UserT findByUserName(String name) {
		return this.getUserTDaoImpl().findByUserName(name);
	}

	public int updateUserMember(UserT user) {
		return this.getUserTDaoImpl().updateUserMember(user);
	}

	public UserT usert(UserT user) {
		return this.getUserTDaoImpl().usert(user);
	}

	public int updateUserstate(UserT user) {
		return this.getUserTDaoImpl().updateUserstate(user);
	}

	public UserT finduserByuid(String uid) {
		return this.getUserTDaoImpl().finduserByuid(uid);
	}

	public List<UserT> sortAllUsert(int currentPage, int lineSize, String queryString) {

		return this.getUserTDaoImpl().sortAllUsert(currentPage, lineSize, queryString);
	}

		public String[] findEmailByUser() {
			
			return this.getUserTDaoImpl().findEmailByUser();
		}
}
