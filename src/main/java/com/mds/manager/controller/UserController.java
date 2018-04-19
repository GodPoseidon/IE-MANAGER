package com.mds.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mds.manager.model.Organization;
import com.mds.manager.model.User;
import com.mds.manager.model.UserExample;
import com.mds.manager.model.UserExample.Criteria;
import com.mds.manager.service.OrganizationService;
import com.mds.manager.service.UserService;
import com.mds.manager.utils.BackMsg;
import com.mds.manager.utils.DateUtils;
import com.mds.manager.utils.ListUtils;
import com.mds.manager.utils.PageUtils;
import com.mds.manager.utils.ResponseHelp;
import com.mds.manager.utils.ShiroSecurityHelper;

@Controller
public class UserController {

	private Logger Log = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@RequestMapping("/system/userIndex")
	public String goUserIndex(Model model){
		Log.info("进入用户管理界面");
		return "/system/user/user_index";
	}
	
	/**
	 * 用户列表分页数据
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/getUserPage")
	public String getPageData(Model model,HttpServletRequest request){
		Log.info(">>> 进入用户分页列表");
		String page = request.getParameter("pageNumber");
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(username)) {
			criteria.andUsernameLike("%"+username+"%");
		}
		if (!StringUtils.isEmpty(nickname)) {
			criteria.andNicknameLike("%"+nickname+"%");
		}
		
		PageUtils Page = new PageUtils(page);
		Page.setQueryParames(example);
		Page = userService.getUserPages(Page);
		return ResponseHelp.responseText(Page);
	}
	
	@RequestMapping("/system/goAddUserPage")
	public String goAddUserPage(Model model){
		Log.info("进入用户添加界面");
		List<Organization> list = organizationService.selectByExample(null);
		model.addAttribute("organizationList", list);
		return "/system/user/user_add";
	}
	
	/**
	 * 用户名是否存在
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/userNameIsExist")
	public BackMsg userNameIsExist(Model model,HttpServletRequest request) {
		String username = request.getParameter("username");
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userService.selectByExample(example);
		if (ListUtils.isNotNull(list)) {
			return ResponseHelp.responseErrorText("用户名已存在！");
		}
		return ResponseHelp.responseText();
	}
	
	/**
	 * 添加用户
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/userAdd")
	public BackMsg userAdd(Model model,HttpServletRequest request){
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String userdesc = request.getParameter("userdesc");
		String birthDate = request.getParameter("birth_date");
		String userType = request.getParameter("user_type");
		String organization = request.getParameter("organization");
		String phoneNumber = request.getParameter("phone_number");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String status = request.getParameter("status");
		
		User user = new User();
		user.setUsername(username);
		user.setNickname(nickname);
		user.setUserdesc(userdesc);
		user.setPassword(ShiroSecurityHelper.getInitPass());
		user.setBirthDate(birthDate);
		user.setUserType(Integer.parseInt(userType));
		user.setOrganization(organization);
		user.setPhoneNumber(phoneNumber);
		user.setEmail(email);
		user.setAddress(address);
		user.setCreateTime(DateUtils.getDateTime());
		user.setStatus("on".equals(status)?1:2);
		userService.insert(user);
		
		return ResponseHelp.responseText();
	}
	
	@RequestMapping("/system/goUpdateUserPage")
	public String goUpdateUserPage(Model model,HttpServletRequest request){
		Log.info("进入用户修改界面");
		
		String id = request.getParameter("id");
		User user = userService.selectByPrimaryKey(Long.decode(id));
		List<Organization> list = organizationService.selectByExample(null);
		
		model.addAttribute("organizationList", list);
		model.addAttribute("user", user);
		return "/system/user/user_update";
	}
	
	/**
	 * 修改状态
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/updateStatus")
	public BackMsg updateStatus(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		
		if (id.equals("1")) {
			return ResponseHelp.responseErrorText("admin用户不能被禁用");
		}
		User user = userService.selectByPrimaryKey(Long.decode(id));
		user.setStatus(Integer.parseInt(status));
		userService.updateByPrimaryKey(user);
		
		return ResponseHelp.responseText();
	}
	
	
	/**
	 * 更新用户
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/userUpdate")
	public String userUpdate(Model model,HttpServletRequest request){
		BackMsg msg = new BackMsg();
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String userdesc = request.getParameter("userdesc");
		String birthDate = request.getParameter("birth_date");
		String userType = request.getParameter("user_type");
		String organization = request.getParameter("organization");
		String phoneNumber = request.getParameter("phone_number");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String status = request.getParameter("status");
		
		User user = userService.selectByPrimaryKey(Long.decode(id));
		user.setNickname(nickname);
		user.setUserdesc(userdesc);
		user.setPassword(ShiroSecurityHelper.getInitPass());
		user.setBirthDate(birthDate);
		user.setUserType(Integer.parseInt(userType));
		user.setOrganization(organization);
		user.setPhoneNumber(phoneNumber);
		user.setEmail(email);
		user.setAddress(address);
		user.setUsername(username);
		user.setModifyTime(DateUtils.getDateTime());
		user.setStatus("on".equals(status)?1:2);
		userService.updateByPrimaryKey(user);
		
		return ResponseHelp.responseText(msg);
	}
	
	/**
	 * 删除用户
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/userDel")
	public BackMsg userDel(Model model,HttpServletRequest request){
		String ids = request.getParameter("ids");
		String[] idsArr = ids.split(",");
		for (String id : idsArr) {
			if ("1".equals(id)) {
				return ResponseHelp.responseErrorText("admin用户不允许被删除");
			}
		}
		userService.batchDelUser(idsArr);
		return ResponseHelp.responseText();
	}
	
	/**
	 * 密码修改
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/system/updatePass")
	public BackMsg updatePass(Model model,HttpServletRequest request){
		BackMsg msg = new BackMsg();
		String password = request.getParameter("password");
		String regPassword = request.getParameter("regPassword");
		String newPassword = request.getParameter("newPassword");
		String username = ShiroSecurityHelper.getCurrentUsername();
		
		if (!password.equals(regPassword)) {
			msg.setStatus(false);
			msg.setMessage("两次输入的密码不一致，请确认后再试");
			return msg;
		}
		
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> userList = userService.selectByExample(userExample);
		if (ListUtils.isNotNull(userList)) {
			User user = userList.get(0);
			Log.info(ShiroSecurityHelper.encryptedPass(password));
			if (!user.getPassword().equals(ShiroSecurityHelper.encryptedPass(password))) {
				msg.setStatus(false);
				msg.setMessage("旧密码不匹配，请重新确认");
				return msg;
			}else{
				user.setPassword(ShiroSecurityHelper.encryptedPass(newPassword));
				userService.updateByPrimaryKey(user);
			}
		}
		return msg;
	}
}
