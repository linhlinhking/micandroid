package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.ArticleTDaoImpl;
import com.jshop.entity.ArticleT;
import com.jshop.service.ArticleTService;
@Service("articleTServiceImpl")
@Scope("prototype")
public class ArticleTServiceImpl implements ArticleTService {
	@Resource(name="articleTDaoImpl")
	private ArticleTDaoImpl articleTDaoImpl;

	public ArticleTDaoImpl getArticleTDaoImpl() {
		return articleTDaoImpl;
	}

	public void setArticleTDaoImpl(ArticleTDaoImpl articleTDaoImpl) {
		this.articleTDaoImpl = articleTDaoImpl;
	}

	public void addArticleT(ArticleT at) {
		this.getArticleTDaoImpl().addArticleT(at);
	}

	public int countfindAllArticle(String creatorid) {
		return this.getArticleTDaoImpl().countfindAllArticle(creatorid);
	}

	public List<ArticleT> findAllArticleT(int currentPage, int lineSize, String creatorid) {
		return this.getArticleTDaoImpl().findAllArticleT(currentPage, lineSize, creatorid);
	}

	public ArticleT findArticleByarticleid(String articleid, String creatorid) {
		return this.getArticleTDaoImpl().findArticleByarticleid(articleid, creatorid);
	}

	public int updateArticleT(ArticleT at) {
		return this.getArticleTDaoImpl().updateArticleT(at);
	}

	public int updateHtmlPath(String articleid, String htmlPath) {
		return this.getArticleTDaoImpl().updateHtmlPath(articleid, htmlPath);
	}

	public List<ArticleT> findAllArticleBycreatorid(String creatorid) {
		return this.getArticleTDaoImpl().findAllArticleBycreatorid(creatorid);
	}

	public List<ArticleT> findAllArticleT(String status) {
		return this.getArticleTDaoImpl().findAllArticleT(status);
	}

	public List<ArticleT> sortAllArticleT(int currentPage, int lineSize, String creatorid, String queryString) {

		return this.getArticleTDaoImpl().sortAllArticleT(currentPage, lineSize, creatorid, queryString);
	}

	public int delArticleT(String[] list) {
		return this.getArticleTDaoImpl().delArticleT(list);
	}
	
	
}
