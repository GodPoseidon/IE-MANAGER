package com.mds.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mds.manager.model.EmailExample;
import com.mds.manager.model.EmailExample.Criteria;
import com.mds.manager.service.EmailService;
import com.mds.manager.utils.PageUtils;
import com.mds.manager.utils.ResponseHelp;

@Controller
public class EmailController {

	private Logger Log = Logger.getLogger(this.getClass());
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/message/goEmailIndex")
	public String goEmailIndex(Model model, HttpServletRequest request){
		Log.info(">> 进入邮件管理首页");
		return "/message/email/email_index";
	}
	
	@ResponseBody
	@RequestMapping("/message/getEmailPages")
	public String getEmailPages(Model model, HttpServletRequest request){
		Log.info(">> 进入短信分页列表界面");
		
		String page = request.getParameter("pageNumber");
		String query_emailTouser = request.getParameter("query_emailTouser");
		String query_emailFromuser = request.getParameter("query_emailFromuser");
		EmailExample example = new EmailExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(query_emailTouser)) {
			criteria.andEmailTouserLike("%"+query_emailTouser+"%");
		}
		if (!StringUtils.isEmpty(query_emailFromuser)) {
			criteria.andEmailFromuserLike("%"+query_emailFromuser+"%");
		}
		
		PageUtils Page = new PageUtils(page);
		Page.setQueryParames(example);
		Page = emailService.getEmailPages(Page);
		return ResponseHelp.responseText(Page);
	}
}
