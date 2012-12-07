package com.xero.website.dao;

import java.util.List;

import com.xero.admin.bean.type.ContactType;
import com.xero.core.common.dao.BaseDao;
import com.xero.core.exception.DaoException;
import com.xero.website.bean.Contact;

public interface ContactDao extends BaseDao<Contact, Integer> {

	public List<Contact> queryContactById(Integer id,Integer userId,ContactType type) throws DaoException;
}
