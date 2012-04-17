package com.jshop.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.jshop.dao.DeliverAddressTDao;

import com.jshop.entity.DeliverAddressT;

/**
 * A data access object (DAO) providing persistence and search support for
 * DeliverAddressT entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.jshop.entity.DeliverAddressT
 * @author MyEclipse Persistence Tools
 */
@Repository("deliverAddressTDaoImpl")
public class DeliverAddressTDaoImpl extends HibernateDaoSupport implements DeliverAddressTDao {
	

	private static final Log log = LogFactory.getLog(DeliverAddressTDaoImpl.class);
	
	public int addDeliverAddress(DeliverAddressT d) {
		log.debug("save DeliverAddressT");
		try {
			this.getHibernateTemplate().save(d);
			log.debug("save successful");
			return 1;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public int delDeliverAddress(final String[] list) {
		log.debug("del DeliverAddressT");
		try {

			final String queryString = "delete from DeliverAddressT as d where d.addressid=:addressid";
			this.getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery(queryString);
					int i = 0;
					for (String s : list) {
						query.setParameter("addressid", s);
						i = query.executeUpdate();
						i++;
					}
					if (list.length == i) {
						return i;
					} else {
						return 0;
					}
				}
			});
		} catch (RuntimeException re) {
			log.error("del DeliverAddressT failed", re);
			throw re;
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<DeliverAddressT> findAllDeliverAddress() {
		log.debug("find all DeliverAddressT");
		try {
			String queryString = "from DeliverAddressT order by createtime desc";
			List<DeliverAddressT> list = this.getHibernateTemplate().find(queryString);
			if (list != null && list.size() > 0) {
				return list;
			}
			return null;
		} catch (RuntimeException re) {
			log.error("find all DeliverAddressT  error", re);
			throw re;
		}
	}

	public int updateDeliverAddress(final DeliverAddressT d) {
		log.debug("update DeliverAddressT");
		try {
			final String queryString = "update DeliverAddressT as d set d.username=:username,d.country=:country,d.province=:province,d.city=:city,d.district=:district,d.street=:street,d.postcode=:postcode,d.telno=:telno,d.mobile=:mobile,d.email=:email,d.createtime=:createtime,d.state=:state where d.addressid=:addressid and d.userid=:userid ";
			this.getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					int i = 0;
					Query query = session.createQuery(queryString);
					query.setParameter("addressid", d.getAddressid());
					query.setParameter("userid", d.getUserid());
					query.setParameter("username", d.getUsername());
					query.setParameter("province", d.getProvince());
					query.setParameter("city", d.getCity());
					query.setParameter("district", d.getDistrict());
					query.setParameter("street", d.getStreet());
					query.setParameter("postcode", d.getPostcode());
					query.setParameter("telno", d.getTelno());
					query.setParameter("mobile", d.getMobile());
					query.setParameter("createtime", d.getCreatetime());
					query.setParameter("state", d.getState());
					query.setParameter("country", d.getCountry());
					query.setParameter("email", d.getEmail());
					i = query.executeUpdate();
					
					return i;
				}
			});
		} catch (RuntimeException re) {
			log.error("update  DeliverAddressT error", re);
			throw re;
		}
		return 0;
	}

	public List<DeliverAddressT> findDeliverAddressByuserid(String userid) {
		log.debug("find all DeliverAddressT by userid");
		try {
			String queryString = "from DeliverAddressT as d where d.userid=:userid order by createtime desc";
			List<DeliverAddressT> list = this.getHibernateTemplate().findByNamedParam(queryString, "userid", userid);
			return list;
		} catch (RuntimeException re) {
			log.error("find all DeliverAddressT  error", re);
			throw re;

		}
	}

	public DeliverAddressT findDeliverAddressById(String addressid) {
		log.debug("find all DeliverAddressT by addressid");
		try {
			String queryString = "from DeliverAddressT as d where d.addressid=:addressid order by createtime desc";
			List<DeliverAddressT> list = this.getHibernateTemplate().findByNamedParam(queryString, "addressid", addressid);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("find all DeliverAddressT  error", re);
			throw re;
		}
	}
}