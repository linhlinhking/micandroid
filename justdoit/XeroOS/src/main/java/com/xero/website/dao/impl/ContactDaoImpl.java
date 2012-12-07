package com.xero.website.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xero.admin.bean.type.ContactType;
import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.core.exception.DaoException;
import com.xero.website.bean.Contact;
import com.xero.website.dao.ContactDao;

@Repository("contactDao")
public class ContactDaoImpl extends BaseDaoImpl<Contact, Integer> implements
		ContactDao {

	public ContactDaoImpl() {
		super(Contact.class);
	}

	public List<Contact> queryContactById(Integer id,ContactType type) throws DaoException{
		String hql = "";
		List<Contact>  list = new ArrayList<Contact>() ;
		if(type.equals(ContactType.SUPPLIER) || type.equals(ContactType.CUSTOMER)){
			hql = "from Contact as c where 1=1 and c.groupId = ? order by c.companyName asc ,c.id desc " ;
			list = findByHql(hql, id) ;
		}else{
			hql = "from Contact as c where 1=1 order by c.companyName asc ,c.id desc ";
			list = findByHql(hql) ;
		}
		return list;
	}
}
