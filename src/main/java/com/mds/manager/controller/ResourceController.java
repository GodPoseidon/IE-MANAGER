package com.mds.manager.controller;

import com.mds.manager.model.SysResource;
import com.mds.manager.model.SysResourceExample;
import com.mds.manager.model.SysResourceExample.Criteria;
import com.mds.manager.service.ResourceService;
import com.mds.manager.utils.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ResourceController {
	
	private Logger Log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/system/goResource")
	public String goResourceIndex(Model model,HttpServletRequest request){
		Log.info(">>进入资源管理界面");
		return "/system/resource/resource_index";
	}
	
	/**
	 * 资源分页
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/getResourcePage")
	public String getResourcePage(Model model,HttpServletRequest request){
		Log.info(">>加载资源分页数据");
		String page = request.getParameter("pageNumber");
		String resource = request.getParameter("query_resource");
		String resourceName = request.getParameter("query_resource_name");
		
		SysResourceExample example = new SysResourceExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(resource)) {
			criteria.andResourceLike("%"+resource+"%");
		}
		if (!StringUtils.isEmpty(resourceName)) {
			criteria.andResourceNameLike("%"+resourceName+"%");
		}
		
		PageUtils Page = new PageUtils(page);
		Page.setQueryParames(example);
		Page = resourceService.getResourcePage(Page);
		return ResponseHelp.responseText(Page);
	}

	@RequestMapping("/system/goResourceAdd")
	public String goResourceAdd(Model model,HttpServletRequest request){
		Log.info(">>进入资源添加界面");
		return "/system/resource/resource_add";
	}
	
	

	/**
	 * 是否存在
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/resourceIsExist")
	public BackMsg resourceIsExist(Model model, HttpServletRequest request) {
		
		String resource = request.getParameter("resource");
		SysResourceExample example = new SysResourceExample();
		Criteria criteria = example.createCriteria();
		criteria.andResourceEqualTo(resource);
		
		List<SysResource> list = resourceService.selectByExample(example);
		if (ListUtils.isNotNull(list)) {
			return ResponseHelp.responseErrorText("资源已存在，不允许重复添加");
		}
		return ResponseHelp.responseText();
	}
	
	
	/**
	 * 修改状态
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/updateRresourceStatus")
	public BackMsg updateRresourceStatus(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		SysResource resource = resourceService.selectByPrimaryKey(Long.decode(id));
		resource.setStatus(Integer.parseInt(status));
		resourceService.updateByPrimaryKey(resource);
		return ResponseHelp.responseText();
	}
	
	/**
	 * 资源添加
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/resourceAdd")
	public BackMsg resourceAdd(Model model,HttpServletRequest request){
		Log.info(">>进入资源添加方法");
		
		String resourceName = request.getParameter("resource_name");
		String resource = request.getParameter("resource");
		String resourceParent = request.getParameter("resource_parent");
		String resourceDesc = request.getParameter("resource_desc");
		String resourceType = request.getParameter("resource_type");
		String resourceScope = request.getParameter("resource_scope");
		String status = request.getParameter("status");
		
		SysResource sysResource = new SysResource();
		sysResource.setResourceName(resourceName);
		sysResource.setResource(resource);
		sysResource.setResourceParent(resourceParent);
		sysResource.setResourceDesc(resourceDesc);
		sysResource.setResourceType(Integer.parseInt(resourceType));
		sysResource.setResourceScope(Integer.parseInt(resourceScope));
		sysResource.setStatus("on".equals(status)?1:2);
		sysResource.setCreateTime(DateUtils.getDateTime());
		
		resourceService.insert(sysResource);
		
		return ResponseHelp.responseText();
	}
	
	
	@RequestMapping("/system/goResourceUpdate")
	public String goResourceUpdate(Model model,HttpServletRequest request){
		Log.info(">>进入资源更新界面");
		String id = request.getParameter("id");
		SysResource sysResource = resourceService.selectByPrimaryKey(Long.decode(id));
		model.addAttribute("resource", sysResource);
		return "/system/resource/resource_update";
	}
	
	
	/**
	 * 资源更新
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/resourceUpdate")
	public BackMsg resourceUpdate(Model model,HttpServletRequest request){
		Log.info(">>进入资源更新方法");
		
		String id = request.getParameter("id");
		String resourceName = request.getParameter("resource_name");
		String resource = request.getParameter("resource");
		String resourceParent = request.getParameter("resource_parent");
		String resourceDesc = request.getParameter("resource_desc");
		String resourceType = request.getParameter("resource_type");
		String resourceScope = request.getParameter("resource_scope");
		String status = request.getParameter("status");
		
		SysResource sysResource = resourceService.selectByPrimaryKey(Long.decode(id));
		sysResource.setResourceName(resourceName);
		sysResource.setResource(resource);
		sysResource.setResourceParent(resourceParent);
		sysResource.setResourceDesc(resourceDesc);
		sysResource.setResourceType(Integer.parseInt(resourceType));
		sysResource.setResourceScope(Integer.parseInt(resourceScope));
		sysResource.setStatus("on".equals(status)?1:2);
		resourceService.updateByPrimaryKey(sysResource);
		
		return ResponseHelp.responseText();
	}
	
	/**
	 * 资源删除
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/resourceDel")
	public BackMsg resourceDel(Model model,HttpServletRequest request){
		Log.info(">>进入资源删除方法");
		String ids = request.getParameter("ids");
		resourceService.batchDelResource(ids.split(","));		
		return ResponseHelp.responseText();
	}
}
