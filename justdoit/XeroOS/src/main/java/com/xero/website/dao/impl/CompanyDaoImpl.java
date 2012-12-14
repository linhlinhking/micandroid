package com.xero.website.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.core.exception.DaoException;
import com.xero.website.bean.Company;
import com.xero.website.dao.CompanyDao;

@Repository("companyDao")
public class CompanyDaoImpl extends BaseDaoImpl<Company, Integer> implements
		CompanyDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass()) ;

	public CompanyDaoImpl() {
		super(Company.class);
	}

	public Company getCompanyByUserId(Integer userId) {
		Company company = null ;
		try {
			String hql = "select c.* from tb_company as c inner join tb_company_user as u where 1=1 and c.id = u.companyId and u.userId = ? order by c.id asc ";
			List<Company> lists = findByNativeSql(hql, userId) ;
			if(null != lists && lists.size() > 0){
				company = lists.get(0); 
			}else{
				company = new Company();
			}
			
		} catch (DaoException ex) {
			logger.error("Error Query Company By UserId" + userId, ex) ;
		}
		return company;
	}
}
