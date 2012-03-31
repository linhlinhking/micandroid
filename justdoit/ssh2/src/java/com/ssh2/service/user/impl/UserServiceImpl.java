package com.ssh2.service.user.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ssh2.DAOException;
import com.ssh2.ServiceException;
import com.ssh2.dao.user.UserDAO;
import com.ssh2.service.user.UserService;
import com.ssh2.utils.PaginationSupport;
import com.ssh2.utils.StringUtils;
import com.ssh2.vo.user.UserModel;

public class UserServiceImpl implements UserService {

	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
	public UserModel get(String id) throws ServiceException {
		// TODO Auto-generated method stub
		try{
			return userDAO.get(id);
		}catch(DAOException e){
			logger.error(e.getMessage());
			return null;
		}
	}
	

	@Override
	public void remove(String id) throws ServiceException {
		// TODO Auto-generated method stub
			remove(get(id));
	}

	@Override
	public void remove(UserModel model) throws ServiceException {
		// TODO Auto-generated method stub
		try{
			userDAO.remove(model);
		}catch(DAOException e){
			logger.error(e.getMessage());
		}
	}

	@Override
	public void saveOrUpdate(UserModel model) throws ServiceException {
		// TODO Auto-generated method stub
		try{
			userDAO.saveOrUpdate(model);
		}catch(DAOException e){
			logger.error(e.getMessage());
		}
	}

	@Override
	public PaginationSupport<UserModel> getPageByModel(UserModel userModel, int pageSize,
			int startIndex, String order, Boolean isDesc)
			throws ServiceException {
		// TODO Auto-generated method stub
		
		DetachedCriteria criteria = DetachedCriteria.forClass(UserModel.class);
		
		if(StringUtils.isNotEmpty(userModel.getName()))
			criteria.add(Restrictions.eq("name", userModel.getName()));
		
		if (StringUtils.isNotEmpty(userModel.getMail())) {
			criteria.add(Restrictions.eq("mail", userModel.getMail()));
		}
		
		if (StringUtils.isNotEmpty(userModel.getPassword())) {
			criteria.add(Restrictions.eq("password", userModel.getPassword()));
		}
		
		if (StringUtils.isNotEmpty(order)) {
			if (isDesc == null || !isDesc) {
				criteria.addOrder(Order.asc(order));
			} else {
				criteria.addOrder(Order.desc(order));
			}
		} else {
			criteria.addOrder(Order.asc("id"));
		}
		
		try {
			return userDAO.findPageByCriteria(criteria, pageSize, startIndex);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getStackTrace());
			return null;
		}
	}

	@Override
	public int getTotal(UserModel userModel) throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserModel getByNameAndPwd(String name, String pwd)
			throws ServiceException {
		// TODO Auto-generated method stub
		String hql = "from UserModel where name='" + name;
		hql += "' and password='" + pwd;
		hql += "'";
		try {
			List<UserModel> list = userDAO.findListByHSQL(hql);
			if (list !=null && list.size() > 0) {
				return list.get(0);
			}else {
				return null;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
