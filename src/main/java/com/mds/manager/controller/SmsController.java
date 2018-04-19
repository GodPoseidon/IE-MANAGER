package com.mds.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mds.manager.model.SmsExample;
import com.mds.manager.model.SmsExample.Criteria;
import com.mds.manager.service.SmsService;
import com.mds.manager.utils.PageUtils;
import com.mds.manager.utils.ResponseHelp;

@Controller
public class SmsController {

	private Logger Log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private SmsService smsService;
	
	@RequestMapping("/message/goSmsIndex")
	public String goSmsIndex(Model model, HttpServletRequest request){
		Log.info(">> 进入短信首页");
		return "/message/sms/sms_index";
	}
	
	@ResponseBody
	@RequestMapping("/message/getSmsPages")
	public String getSmsPages(Model model, HttpServletRequest request){
		Log.info(">> 进入短信分页列表界面");
		
		String page = request.getParameter("pageNumber");
		String query_smsFromuser = request.getParameter("query_smsFromuser");
		String query_smsAddressuser = request.getParameter("query_smsAddressuser");
		
		SmsExample example = new SmsExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(query_smsFromuser)) {
			criteria.andSmsFromuserLike("%"+query_smsFromuser+"%");
		}
		if (!StringUtils.isEmpty(query_smsAddressuser)) {
			criteria.andSmsAddressuserLike("%"+query_smsAddressuser+"%");
		}
		
		PageUtils Page = new PageUtils(page);
		Page.setQueryParames(example);
		Page = smsService.getSmsPages(Page);
		return ResponseHelp.responseText(Page);
	}
}
