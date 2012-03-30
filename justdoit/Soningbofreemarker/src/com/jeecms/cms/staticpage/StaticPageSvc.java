package com.jeecms.cms.staticpage;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.Content;

import freemarker.template.TemplateException;

public interface StaticPageSvc {

	public void index() throws IOException, TemplateException;

	public void index(HttpServletRequest request) throws IOException,
			TemplateException;

	public void index(CmsSite site, String tpl, Map<String, Object> data)
			throws IOException, TemplateException;

	public boolean deleteIndex(CmsSite site);

	public int channel(Integer siteId, Integer channelId, boolean containChild,
			HttpServletRequest request) throws IOException, TemplateException;

	public int channel(Integer siteId, Integer channelId, boolean containChild,
			Map<String, Object> data) throws IOException, TemplateException;

	public void channel(Channel channel, boolean firstOnly) throws IOException,
			TemplateException;

	public void channel(Channel channel, boolean firstOnly,
			HttpServletRequest request) throws IOException, TemplateException;

	public void deleteChannel(Channel channel);

	public int content(Integer siteId, Integer channelId, Date start,
			HttpServletRequest request) throws IOException, TemplateException;

	public int content(Integer siteId, Integer channelId, Date start,
			Map<String, Object> data) throws IOException, TemplateException;

	public void contentRelated(Content content) throws IOException,
			TemplateException;

	public void content(Content content) throws IOException, TemplateException;

	public void content(Content content, HttpServletRequest request)
			throws IOException, TemplateException;

	public void deleteContent(Content content);

}
