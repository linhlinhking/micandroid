package com.xero.website.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xero.admin.util.DateUtil;
import com.xero.core.Response.ResponseCollection;
import com.xero.core.Response.ResponseEntity;
import com.xero.website.bean.ProjectNote;
import com.xero.website.service.ProjectNoteService;

@Controller
public class ProjectNoteController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ProjectNoteService projectNoteService;
	
	
	@RequestMapping(value = "/note-add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ProjectNote> doAdd(HttpServletRequest request,
			@RequestParam("projectId") Integer projectId,
			@RequestParam("userId") Integer userId,
			@RequestParam("content") String noteContent,
			@RequestParam("landmarkDate") String landmarkDate,
			@RequestParam("showCustomer") Integer showCustomer) {
		ResponseEntity<ProjectNote> res = new ResponseEntity<ProjectNote>(false);
		try {
			ProjectNote pNote = new ProjectNote();

			Date tempDate = (null == landmarkDate) ? new Date() : DateUtil
					.strToEnDate(landmarkDate);
			boolean isShowCustomer = false;
			if (null != showCustomer && showCustomer == 1) {
				isShowCustomer = true;
			}
			pNote.setContent(noteContent);
			pNote.setProjectId(projectId);
			pNote.setUserId(userId);
			pNote.setLandmarkDate(tempDate);
			pNote.setShowCustomer(isShowCustomer);
			pNote.setCreateDateTime(new Date());

			pNote = projectNoteService.saveOrUpdate(pNote);

			res.setData(pNote);
			res.setResult(true);

		} catch (Exception ex) {
			logger.error("Save Project Note Error On Controller", ex);
			res.setResult(false);
			res.setData(null);
		}
		return res;
	}
	
	

	@RequestMapping(value = "/note-list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseCollection<ProjectNote> toList(HttpServletRequest request,
			@RequestParam("projectId") Integer projectId) {
		ResponseCollection<ProjectNote> res = new ResponseCollection<ProjectNote>(); 
		try {
			res = projectNoteService.getNotesByProjectId(projectId) ;
		} catch (Exception ex) {
			logger.error("Save Project Note Error On Controller", ex);
			res.setResult(false);
			res.setData(null);
		}
		return res;
	}

}
