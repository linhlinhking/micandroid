package com.jeecms.cms.action.front;

import static com.jeecms.cms.Constants.TPLDIR_SPECIAL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.entity.assist.CmsVoteTopic;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.manager.assist.CmsVoteTopicMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;

@Controller
public class VoteAct {
	private static final Logger log = LoggerFactory.getLogger(VoteAct.class);

	public static final String VOTE_INPUT = "tpl.voteInput";
	public static final String VOTE_SUBMIT = "tpl.voteSubmit";
	public static final String VOTE_RESULT = "tpl.voteResult";

	@RequestMapping(value = "/vote_result.jspx", method = RequestMethod.GET)
	public String result(Integer voteId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsVoteTopic vote = cmsVoteTopicMng.findById(voteId);
		model.addAttribute("vote", vote);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_SPECIAL, VOTE_RESULT);
	}

	@RequestMapping(value = "/vote.jspx", method = RequestMethod.GET)
	public String input(Integer voteId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsVoteTopic vote = cmsVoteTopicMng.findById(voteId);
		model.addAttribute("vote", vote);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_SPECIAL, VOTE_INPUT);
	}

	@RequestMapping(value = "/vote.jspx", method = RequestMethod.POST)
	public String submit(Integer voteId, Integer[] itemIds,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);

		CmsVoteTopic vote = cmsVoteTopicMng.vote(voteId, itemIds);
		model.addAttribute("vote", vote);

		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_SPECIAL, VOTE_SUBMIT);
	}

	@Autowired
	private CmsVoteTopicMng cmsVoteTopicMng;
}
