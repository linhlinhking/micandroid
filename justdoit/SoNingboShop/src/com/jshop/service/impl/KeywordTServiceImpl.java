package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.KeywordTDaoImpl;
import com.jshop.entity.KeywordT;
import com.jshop.service.KeywordTService;
@Service("keywordTServiceImpl")
@Scope("prototype")
public class KeywordTServiceImpl implements KeywordTService {
	@Resource(name="keywordTDaoImpl")
	private KeywordTDaoImpl keywordTDaoImpl;

	public KeywordTDaoImpl getKeywordTDaoImpl() {
		return keywordTDaoImpl;
	}

	public void setKeywordTDaoImpl(KeywordTDaoImpl keywordTDaoImpl) {
		this.keywordTDaoImpl = keywordTDaoImpl;
	}

	public int delKeywordT(String[] list) {
		return this.getKeywordTDaoImpl().delKeywordT(list);
	}

	public int updateKeywordT(KeywordT kt) {
		return this.getKeywordTDaoImpl().updateKeywordT(kt);
	}

	public int updatekeywordsearchcount(String keywordname) {
		return this.getKeywordTDaoImpl().updatekeywordsearchcount(keywordname);
	}

	public int addKeywordT(KeywordT kt) {
		return this.getKeywordTDaoImpl().addKeywordT(kt);
	}

	public int countAllKeywordT() {
		return this.getKeywordTDaoImpl().countAllKeywordT();
	}

	public List<KeywordT> findAllKeywordT(int currentPage, int lineSize) {
		return this.getKeywordTDaoImpl().findAllKeywordT(currentPage, lineSize);
	}

	public KeywordT findKeywordById(String keywordid) {
		return this.getKeywordTDaoImpl().findKeywordById(keywordid);
	}

	public List<KeywordT> findAllKeywordTjson() {
		return this.getKeywordTDaoImpl().findAllKeywordTjson();
	}

	public List<KeywordT> findKeywordLimit(int limit) {
		return this.getKeywordTDaoImpl().findKeywordLimit(limit);
	}
}
