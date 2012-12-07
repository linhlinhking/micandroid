package com.xero.website.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xero.admin.bean.type.ContactType;
import com.xero.core.Response.ResponseCollection;
import com.xero.core.common.service.impl.BaseServiceImpl;
import com.xero.core.exception.ServiceException;
import com.xero.website.bean.Contact;
import com.xero.website.dao.ContactDao;
import com.xero.website.service.ContactService;


@Service("contactService")
public class ContactServiceImpl extends BaseServiceImpl<Contact, Integer>
		implements ContactService {
	
	@Resource
	private ContactDao contactDao ;

	@Autowired
	public ContactServiceImpl(@Qualifier("contactDao") ContactDao contactDao) {
		super(contactDao);
	}

	public ResponseCollection<Contact> queryContactById(Integer id,Integer userId,
			ContactType type) throws ServiceException {
		ResponseCollection<Contact> res = new ResponseCollection<Contact>(false) ;
		List<Contact> list = contactDao.queryContactById(id,userId, type) ;
		if(null == list){
			res.setData(null) ;
		}else{
			res.setData(list) ;
			res.setResult(true) ;
		}
		return res;
	}

	
}
