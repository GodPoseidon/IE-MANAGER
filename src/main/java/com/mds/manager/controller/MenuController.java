package com.mds.manager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mds.manager.model.Menu;
import com.mds.manager.model.MenuExample;
import com.mds.manager.model.MenuExample.Criteria;
import com.mds.manager.model.Tree;
import com.mds.manager.service.MenuService;
import com.mds.manager.utils.BackMsg;
import com.mds.manager.utils.ListUtils;
import com.mds.manager.utils.ResponseHelp;

import net.sf.json.JSONArray;

@Controller
public class MenuController {
	
	private Logger Log = Logger.getLogger(this.getClass());
	
	@Autowired
	private MenuService menuService;

	@RequestMapping("/system/goMenuIndex")
	public String goMenuIndex(Model model){
		Log.info(">> 进入菜单管理界面");
		List<Tree> list = menuService.selectTreeNodes(0L);
		JSONArray json = JSONArray.fromObject(list);
		String rs = json.toString();
		rs = rs.replace("\"", "'");
		Log.info(rs);
		model.addAttribute("options", rs);
		return "/system/menu/menu_index";
	}
	
	/**
	 * 菜单添加
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/menuAdd")
	public BackMsg menuAdd(Model model, HttpServletRequest request){
		Log.info(">> 进入菜单添加方法");
		
		String menuName = request.getParameter("menu_name");
		String menu = request.getParameter("menu");
		String parentMenu = request.getParameter("parent_menu");
		String menuDesc = request.getParameter("menu_desc");
		String menuIcon = request.getParameter("menu_icon");
		String menuType = request.getParameter("menu_type");
		String menuScope = request.getParameter("menu_scope");
		String status = request.getParameter("status");
		
		Menu menuObj = new Menu();
		menuObj.setMenu(menu);
		menuObj.setMenuDesc(menuDesc);
		menuObj.setMenuIcon(menuIcon);
		menuObj.setMenuName(menuName);
		menuObj.setMenuScope(Integer.parseInt(menuScope));
		menuObj.setMenuType(Integer.parseInt(menuType));
		menuObj.setParentMenu(parentMenu);
		menuObj.setStatus("on".equals(status)?1:2);
		
		menuService.insert(menuObj);
		
		return ResponseHelp.responseText();
	}
	
	
	/**
	 * 菜单修改
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/updateMenu")
	public BackMsg updateMenu(Model model, HttpServletRequest request){
		Log.info(">> 进入菜单修改方法");
		
		String id = request.getParameter("id");
		String menu = request.getParameter("menu");
		String menuName = request.getParameter("menu_name");
		String parentMenu = request.getParameter("parent_menu");
		String menuDesc = request.getParameter("menu_desc");
		String menuIcon = request.getParameter("menu_icon");
		String menuType = request.getParameter("menu_type");
		String menuScope = request.getParameter("menu_scope");
		String status = request.getParameter("status");
		
		
		Menu menuObj = menuService.selectByPrimaryKey(Long.decode(id));
		menuObj.setMenu(menu);
		menuObj.setMenuDesc(menuDesc);
		menuObj.setMenuIcon(menuIcon);
		menuObj.setMenuName(menuName);
		menuObj.setMenuScope(Integer.parseInt(menuScope));
		menuObj.setMenuType(Integer.parseInt(menuType));
		menuObj.setParentMenu(parentMenu);
		menuObj.setStatus("on".equals(status)?1:2);
		
		menuService.updateByPrimaryKey(menuObj);
		
		return ResponseHelp.responseText();
	}
	
	
	/**
	 * 获取节点信息
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/getNodeInfo")
	public String getNodeInfo(Model model, HttpServletRequest request){
		String id = request.getParameter("id");
		Map<String, Object> map = menuService.selectParentMenuInfoById(Long.decode(id));
		return ResponseHelp.responseArrayToText(map);
	}
	
	/**
	 * 删除节点
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/delNodeInfo")
	public BackMsg delNodeInfo(Model model, HttpServletRequest request){
		String id = request.getParameter("id");
		BackMsg msg = new BackMsg();
		
		MenuExample example = new MenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentMenuEqualTo(id);
		List<Menu> list = menuService.selectByExample(example);
		if (ListUtils.isNotNull(list)) {
			msg.setStatus(false);
			msg.setMessage("当前节点下面不为空，不允许删除");
			return msg;
		}
		
		menuService.deleteByPrimaryKey(Long.decode(id));
		return ResponseHelp.responseText();
	}
	
	/**
	 * 加载树形结构数据
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/loadTreeInfo")
	public String loadTreeInfo(Model model, HttpServletRequest request){
		List<Tree> list = menuService.selectTreeNodes(0L);
		JSONArray json = JSONArray.fromObject(list);
		String rs = json.toString();
		rs = rs.replace("\"", "'");
		Log.info(rs);
		return ResponseHelp.responseText(rs);
	}
}
