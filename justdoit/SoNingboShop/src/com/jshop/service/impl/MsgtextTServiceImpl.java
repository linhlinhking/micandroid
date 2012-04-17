package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.MsgtextTDaoImpl;
import com.jshop.entity.MsgtextT;
import com.jshop.service.MsgtextTService;
@Service("msgtextTServiceImpl")
@Scope("prototype")
public class MsgtextTServiceImpl implements MsgtextTService {
	@Resource(name="msgtextTDaoImpl")
	public MsgtextTDaoImpl msgtextTDaoImpl;
	
	public MsgtextTDaoImpl getMsgtextTDaoImpl() {
		return msgtextTDaoImpl;
	}

	public void setMsgtextTDaoImpl(MsgtextTDaoImpl msgtextTDaoImpl) {
		this.msgtextTDaoImpl = msgtextTDaoImpl;
	}

	public int delMsgtext(String[] list) {
		return this.getMsgtextTDaoImpl().delMsgtext(list);
	}

	public int updateMsgtext(MsgtextT mt) {
		return this.getMsgtextTDaoImpl().updateMsgtext(mt);
	}

	public int addMsgtext(MsgtextT mt) {
		return this.getMsgtextTDaoImpl().addMsgtext(mt);
	}

	public int countfindAllMsgtext() {
		return this.getMsgtextTDaoImpl().countfindAllMsgtext();
	}

	public List<MsgtextT> findAllMsgtext(int currentPage, int lineSize) {
		return this.getMsgtextTDaoImpl().findAllMsgtext(currentPage, lineSize);
	}

	public MsgtextT findMsgtextById(String id) {
		return this.getMsgtextTDaoImpl().findMsgtextById(id);
	}
}
