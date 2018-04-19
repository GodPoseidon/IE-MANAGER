package com.mds.manager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mds.manager.utils.*;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mds.manager.model.Organization;
import com.mds.manager.model.OrganizationExample;
import com.mds.manager.model.OrganizationExample.Criteria;
import com.mds.manager.model.Tree;
import com.mds.manager.service.OrganizationService;

@Controller
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	private Logger Log = Logger.getLogger(this.getClass());

	@RequestMapping("/system/goOrganizationIndex")
	public String goOrganizationIndex(Model model, HttpServletRequest request){
		Log.info(">> 入进组织管理界面");
		
		List<Tree> list = organizationService.selectTreeNodes("0");
		JSONArray json = JSONArray.fromObject(list);
		String rs = json.toString();
		rs = rs.replace("\"", "'");
		Log.info(rs);
		model.addAttribute("options", rs);
		
		return "/system/organization/organization_index";
	}
	
	/**
	 * 添加组织信息
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/addOrganization")
	public BackMsg addOrganization(Model model, HttpServletRequest request){
		
		String organizationNo = request.getParameter("organization");
		String organizationName = request.getParameter("organization_name");
		String organizationParent = request.getParameter("organization_parent");
		String organizationDesc = request.getParameter("organization_desc");
		String organizationScope = request.getParameter("organization_scope");
		String organizationInnerPhone = request.getParameter("organization_innerPhone");
		String organizationOuterPhone = request.getParameter("organization_outerPhone");
		String organizationAddress = request.getParameter("organization_address");
		
		Organization organization = new Organization();
		organization.setOrganization(organizationNo);
		organization.setOrganizationName(organizationName);
		organization.setOrganizationParent(organizationParent);
		organization.setOrganizationDesc(organizationDesc);
		organization.setOrganizationScope(Integer.parseInt(organizationScope));
		if (!StringUtils.isEmpty(organizationInnerPhone)) {
			organization.setOrganizationInnerphone(Long.decode(organizationInnerPhone));
		}
		if (!StringUtils.isEmpty(organizationOuterPhone)) {
			organization.setOrganizationOuterphone(Long.decode(organizationOuterPhone));
		}
		organization.setOrganizationAddress(organizationAddress);
		
		organization.setCreateTime(DateUtils.getDateTime());
		organization.setCreateUser(ShiroSecurityHelper.getCurrentUsername());
		
		organizationService.insert(organization);
		return ResponseHelp.responseText();
	}
	
	/**
	 * 获取节点信息
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/getOrganizationNodeInfo")
	public String getNodeInfo(Model model, HttpServletRequest request){
		String id = request.getParameter("id");
		Map<String, Object> map = organizationService.selectParentNodeInfoById(Long.decode(id));
		return ResponseHelp.responseArrayToText(map);
	}
	
	/**
	 * 修改组织信息
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/updateOrganization")
	public BackMsg updateOrganization(Model model, HttpServletRequest request){
		
		String id = request.getParameter("id");
		String organizationNo = request.getParameter("organization");
		String organizationName = request.getParameter("organization_name");
		String organizationParent = request.getParameter("organization_parent");
		String organizationDesc = request.getParameter("organization_desc");
		String organizationScope = request.getParameter("organization_scope");
		String organizationInnerPhone = request.getParameter("organization_innerPhone");
		String organizationOuterPhone = request.getParameter("organization_outerPhone");
		String organizationAddress = request.getParameter("organization_address");
		
		Organization organization = organizationService.selectByPrimaryKey(Long.decode(id));
		organization.setOrganization(organizationNo);
		organization.setOrganizationName(organizationName);
		organization.setOrganizationParent(organizationParent);
		organization.setOrganizationDesc(organizationDesc);
		organization.setOrganizationScope(Integer.parseInt(organizationScope));
		if (!StringUtils.isEmpty(organizationInnerPhone)) {
			organization.setOrganizationInnerphone(Long.decode(organizationInnerPhone));
		}
		if (!StringUtils.isEmpty(organizationOuterPhone)) {
			organization.setOrganizationOuterphone(Long.decode(organizationOuterPhone));
		}
		organization.setOrganizationAddress(organizationAddress);
		
		organizationService.updateByPrimaryKey(organization);
		return ResponseHelp.responseText();
	}
	
	
	/**
	 * 获取节点信息
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/delOrganization")
	public BackMsg delOrganization(Model model, HttpServletRequest request){
		String organization_no = request.getParameter("organization");
		
		OrganizationExample example = new OrganizationExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrganizationParentEqualTo(organization_no);
		List<Organization> list = organizationService.selectByExample(example);
		if (ListUtils.isNotNull(list)) {
			return ResponseHelp.responseErrorText("当前节点下面不为空，不允许删除");
		}
		
		example = new OrganizationExample();
		criteria = example.createCriteria();
		criteria.andOrganizationEqualTo(organization_no);
		organizationService.deleteByExample(example);
		return ResponseHelp.responseText();
	}
}
