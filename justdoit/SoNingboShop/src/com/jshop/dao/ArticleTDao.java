package com.jshop.dao;

import java.util.List;

import com.jshop.entity.ArticleT;

public interface ArticleTDao {

	/**
	 * 增加文章
	 * @param at
	 */
	public void addArticleT(ArticleT at);
	/**
	 * 批量删除文章
	 * @param list
	 * @return
	 */
	public abstract int delArticleT(String[]list);
	
	/**
	 * 根据articleid获取文章
	 * @param articleid
	 * @param creatorid
	 * @return
	 */
	public abstract ArticleT findArticleByarticleid(String articleid,String creatorid);
	/**
	 * 获取所有文章
	 * @param currentPage
	 * @param lineSize
	 * @param status
	 * @param creatorid
	 * @return
	 */
	public abstract List<ArticleT>findAllArticleT(final int currentPage,final int lineSize,final String creatorid);
	/**
	 * 统计获取所有文章
	 * @param status
	 * @param creatorid
	 * @return
	 */
	public abstract int countfindAllArticle(String creatorid);
	/**
	 * 更新文章
	 * @param at
	 * @return
	 */
	public abstract int updateArticleT(ArticleT at);
	/**
	 * 更新文章静态页
	 * @param articleid
	 * @param htmlPath
	 */
	public abstract int updateHtmlPath(String articleid,String htmlPath);
	/**
	 * 根据用户id获取文章列表
	 * @param creatorid
	 * @return
	 */
	public abstract List<ArticleT>findAllArticleBycreatorid(String creatorid);
	/**
	 * 根据status读取所有文章数据
	 * @param status
	 * @return
	 */
	public abstract List<ArticleT>findAllArticleT(String status);
	
	
	
}
