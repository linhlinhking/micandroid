package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.ArticleCategoryTDaoImpl;
import com.jshop.entity.ArticleCategoryT;
import com.jshop.service.ArticleCategoryTService;
@Service("articleCategoryTServiceImpl")
@Scope("prototype")
public class ArticleCategoryTServiceImpl implements ArticleCategoryTService {
	@Resource(name="articleCategoryTDaoImpl")
	private ArticleCategoryTDaoImpl articleCategoryTDaoImpl;

	
	public ArticleCategoryTDaoImpl getArticleCategoryTDaoImpl() {
		return articleCategoryTDaoImpl;
	}

	public void setArticleCategoryTDaoImpl(ArticleCategoryTDaoImpl articleCategoryTDaoImpl) {
		this.articleCategoryTDaoImpl = articleCategoryTDaoImpl;
	}

	public void addArticleCategoryT(ArticleCategoryT act) {
		getArticleCategoryTDaoImpl().addArticleCategoryT(act);
	}

	public int checkArticleCategoryName(String name, String creatorid) {
		return getArticleCategoryTDaoImpl().checkArticleCategoryName(name, creatorid);
	}

	public int checkArticleCategoryNamewithoutMe(String articleCategoryTid, String name, String creatorid) {
		return getArticleCategoryTDaoImpl().checkArticleCategoryNamewithoutMe(articleCategoryTid, name, creatorid);
	}

	public int checkArticleCategorySign(String sign, String creatorid) {
		return getArticleCategoryTDaoImpl().checkArticleCategorySign(sign, creatorid);
	}

	public int checkArticleCategorySignwithoutMe(String articleCategoryTid, String sign, String creatorid) {
		return getArticleCategoryTDaoImpl().checkArticleCategorySignwithoutMe(articleCategoryTid, sign, creatorid);
	}

	public int countfindAllArticleCategoryT(String status, String creatorid) {
		return getArticleCategoryTDaoImpl().countfindAllArticleCategoryT(status, creatorid);
	}

	public int countfindAllArticleCategoryTByGrade(String grade, String status, String creatorid) {
		return getArticleCategoryTDaoImpl().countfindAllArticleCategoryTByGrade(grade, status, creatorid);
	}

//	public int delArticleCategoryT(String articlecategoryTid, String status, String creatorid) {
//		return getArticleCategoryTDaoImpl().delArticleCategoryT(articlecategoryTid, status, creatorid);
//	}
	
	public void delArticleCategoryT(ArticleCategoryT act) {
		this.getArticleCategoryTDaoImpl().delArticleCategoryT(act);
		
	}
	
	public List<ArticleCategoryT> findAllArticleCategoryT(int currentPage, int lineSize, String status, String creatorid) {
		return getArticleCategoryTDaoImpl().findAllArticleCategoryT(currentPage, lineSize, status, creatorid);
	}

	

	public List<ArticleCategoryT> findAllArticleCategoryTByGrade(int currentPage, int lineSize, String status, String grade, String creatorid) {
		return getArticleCategoryTDaoImpl().findAllArticleCategoryTByGrade(currentPage, lineSize, status, grade, creatorid);
	}

	public List<ArticleCategoryT> findArticleCategoryByGrade(String grade, String status, String creatorid) {
		return getArticleCategoryTDaoImpl().findArticleCategoryByGrade(grade, status, creatorid);
	}

	public ArticleCategoryT findArticleCategoryByarticleCategoryTid(String articleCategoryTid) {
		return getArticleCategoryTDaoImpl().findArticleCategoryByarticleCategoryTid(articleCategoryTid);
	}

	public List<ArticleCategoryT> findArticleCategoryByparentId(String status, String parentId, String creatorid) {
		return getArticleCategoryTDaoImpl().findArticleCategoryByparentId(status, parentId, creatorid);
	}

	public List<ArticleCategoryT> findArticleCategoryByparentIdnull(String status, String creatorid) {
		return getArticleCategoryTDaoImpl().findArticleCategoryByparentIdnull(status, creatorid);
	}

	public ArticleCategoryT findPathParentIdByParentId(String parentId, String creatorid) {
		return getArticleCategoryTDaoImpl().findPathParentIdByParentId(parentId, creatorid);
	}

	public void updateArticleCategoryT(ArticleCategoryT act) {
		this.getArticleCategoryTDaoImpl().updateArticleCategoryT(act);
	}

	public List<ArticleCategoryT> findAllArticleCategoryBycreatorid(String creatorid) {
		return getArticleCategoryTDaoImpl().findAllArticleCategoryBycreatorid(creatorid);
	}

	public List<ArticleCategoryT> findArticleCategoryByposition(int lineSize, String status, String position, String creatorid) {
		return getArticleCategoryTDaoImpl().findArticleCategoryByposition(lineSize, status, position, creatorid);
	}

	public List<ArticleCategoryT> findArticleCategoryByposition(int lineSize, String status, String position) {
		return getArticleCategoryTDaoImpl().findArticleCategoryByposition(lineSize, status, position);
	}

	public List<ArticleCategoryT> sortAllArticleCategoryT(int currentPage, int lineSize, String status, String creatorid, String queryString) {

		return this.getArticleCategoryTDaoImpl().sortAllArticleCategoryT(currentPage, lineSize, status, creatorid, queryString);
	}

	public List<ArticleCategoryT> findArticleCategoryByGrade(String grade, String status) {
		return this.getArticleCategoryTDaoImpl().findArticleCategoryByGrade(grade,status);
	}
	
	
	
}
