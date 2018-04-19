package com.mds.manager.controller;

import com.mds.manager.model.Notice;
import com.mds.manager.model.NoticeExample;
import com.mds.manager.model.NoticeExample.Criteria;
import com.mds.manager.service.NoticeService;
import com.mds.manager.utils.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NoticeController {

	private Logger Log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/message/goNoticeIndex")
	public String goNoticeIndex(Model model, HttpServletRequest request){
		Log.info(">> 进入公告管理界面");
		return "/message/notice/notice_index";
	}
	
	/**
	 * 获取系统公告列表数据
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/message/getNoticePages")
	public String getNoticePages(Model model,HttpServletRequest request){
		Log.info(">>> 进入系统公告分页列表");
		String page = request.getParameter("pageNumber");
		String query_notice_title = request.getParameter("query_notice_title");
		String query_notice_content = request.getParameter("query_notice_content");
		
		NoticeExample example = new NoticeExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(query_notice_title)) {
			criteria.andNoticeTitleLike("%"+query_notice_title+"%");
		}
		if (!StringUtils.isEmpty(query_notice_content)) {
			criteria.andNoticeContentLike("%"+query_notice_content+"%");
		}
		
		PageUtils Page = new PageUtils(page);
		Page.setQueryParames(example);
		Page = noticeService.getNoticePages(Page);
		return ResponseHelp.responseText(Page);
	}
	
	@RequestMapping("/message/goNoticeAdd")
	public String goNoticeAdd(Model model, HttpServletRequest request){
		Log.info(">> 进入公告管理新增界面");
		return "/message/notice/notice_add";
	}
	
	/**
	 * 系统公告新增方法
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/message/noticeAdd")
	public BackMsg noticeAdd(Model model, HttpServletRequest request){
		Log.info(">> 进入系统公告新增方法");
		
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		String noticeFrom = request.getParameter("noticeFrom");
		String noticeTo = request.getParameter("noticeTo");
		String noiceScope = request.getParameter("noiceScope");
		
		Notice notice = new Notice();
		notice.setNoticeTitle(noticeTitle);
		notice.setNoticeContent(noticeContent);
		notice.setNoticeFrom(noticeFrom);
		notice.setNoticeTo(noticeTo);
		notice.setNoiceScope(Integer.parseInt(noiceScope));
		notice.setStatus(1);
		notice.setCreateTime(DateUtils.getDateTime());
		notice.setCreateUser(ShiroSecurityHelper.getCurrentUsername());
		
		noticeService.insert(notice);
		return ResponseHelp.responseText();
	}
	
	@RequestMapping("/message/goNoticeUpdate")
	public String goNoticeUpdate(Model model, HttpServletRequest request){
		Log.info(">> 进入公告管理修改界面");
		String id = request.getParameter("id");
		Notice notice = noticeService.selectByPrimaryKey(Long.decode(id));
		model.addAttribute("notice", notice);
		return "/message/notice/notice_update";
	}
	
	/**
	 * 系统公告修改方法
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/message/noticeUpdate")
	public BackMsg noticeUpdate(Model model, HttpServletRequest request){
		Log.info(">> 进入公告管理修改方法");
		
		String id = request.getParameter("id");
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		String noticeFrom = request.getParameter("noticeFrom");
		String noticeTo = request.getParameter("noticeTo");
		String noiceScope = request.getParameter("noiceScope");
		
		Notice notice = noticeService.selectByPrimaryKey(Long.decode(id));
		notice.setNoticeTitle(noticeTitle);
		notice.setNoticeContent(noticeContent);
		notice.setNoticeFrom(noticeFrom);
		notice.setNoticeTo(noticeTo);
		notice.setNoiceScope(Integer.parseInt(noiceScope));
		
		noticeService.updateByPrimaryKey(notice);
		return ResponseHelp.responseText();
	}

	/**
	 * 删除系统公告
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/message/noticeDel")
	public BackMsg noticeDel(Model model, HttpServletRequest request){
		Log.info(">> 进入公告管理删除方法");
		String ids = request.getParameter("ids");
		String[] idsArr = ids.split(",");
		noticeService.batchDelNotice(idsArr);
		return ResponseHelp.responseText();
	}
	
	/**
	 * 发布系统公告
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/message/releaseNotice")
	public BackMsg releaseNotice(Model model, HttpServletRequest request){
		Log.info(">> 进入公告管理发布方法");
		String id = request.getParameter("id");
		Notice notice = noticeService.selectByPrimaryKey(Long.decode(id));
		notice.setStatus(2);
		noticeService.updateByPrimaryKey(notice);
		return ResponseHelp.responseText();
	}
}
