package com.mds.manager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mds.manager.model.*;
import com.mds.manager.utils.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mds.manager.model.RoleExample.Criteria;
import com.mds.manager.service.MenuService;
import com.mds.manager.service.RoleService;
import com.mds.manager.service.RoleUserService;
import com.mds.manager.service.UserService;

@Controller
public class RoleController {

    private Logger Log = Logger.getLogger(this.getClass());

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/system/roleIndex")
    public String goRoleIndex(Model model) {
        Log.debug(">>>进入角色管理界面");
        return "/system/role/role_index";
    }

    /**
     * 角色列表分页数据
     *
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/getRolePage")
    public String getPageData(Model model, HttpServletRequest request, HttpServletResponse res) {
        String page = request.getParameter("pageNumber");
        String query_role = request.getParameter("query_role");

        RoleExample example = new RoleExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(query_role)) {
            criteria.andRoleLike("%" + query_role + "%");
        }

        PageUtils Page = new PageUtils(page);
        Page.setQueryParames(example);
        Page = roleService.getRolePage(Page);
        return ResponseHelp.responseText(Page);
    }

    @RequestMapping("/system/goRoleAdd")
    public String goRoleAdd(Model model) {
        Log.debug(">>>进入新增角色界面");
        return "/system/role/role_add";
    }

    /**
     * 是否存在
     *
     * @param model
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/roleIsExist")
    public BackMsg roleIsExist(Model model, HttpServletRequest request) {

        String role = request.getParameter("role");
        RoleExample example = new RoleExample();
        Criteria criteria = example.createCriteria();
        criteria.andRoleEqualTo(role);

        List<Role> list = roleService.selectByExample(example);
        if (ListUtils.isNotNull(list)) {
            return ResponseHelp.responseErrorText("角色已存在，不允许重复添加");
        }
        return ResponseHelp.responseText();
    }


    /**
     * 修改角色状态
     *
     * @param model
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/updateRoleStatus")
    public BackMsg updateRoleStatus(Model model, HttpServletRequest request) {
        Log.debug(">>>进入修改角色状态方法");
        String id = request.getParameter("id");
        String status = request.getParameter("status");

        Role role = roleService.selectByPrimaryKey(Long.decode(id));
        role.setStatus(Integer.parseInt(status));
        roleService.updateByPrimaryKey(role);
        return ResponseHelp.responseText();
    }

    /**
     * 新增角色
     *
     * @param model
     * @param request
     * @param request
     * @return
     */
    @ResponseBody

    @RequestMapping("/system/roleAdd")
    public BackMsg roleAdd(Model model, HttpServletRequest request) {
        String roleNo = request.getParameter("role");
        String roleName = request.getParameter("role_name");
        String roleDesc = request.getParameter("role_desc");
        String roleType = request.getParameter("role_type");
        String status = request.getParameter("status");

        String permission = request.getParameter("permission");
        String roleUserStr = request.getParameter("roleUser");

        String[] rolePermission = permission.split(",");
        String[] roleUser = roleUserStr.split(",");

        Role role = new Role();
        role.setRole(roleNo);
        role.setRoleName(roleName);
        role.setRoleDesc(roleDesc);
        role.setRoleType(Integer.parseInt(roleType));
        role.setStatus("on".equals(status) ? 1 : 2);
        role.setCreateTime(DateUtils.getDateTime());

        roleService.insertSelective(role);

        //绑定的用户
        List<RoleUser> roleUserList = new ArrayList<RoleUser>();
        for (String user : roleUser) {
            RoleUser ru = new RoleUser();
            ru.setRoleId(role.getId());
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUsernameEqualTo(user);
            List<User> users = userService.selectByExample(userExample);
            ru.setUserId(users.get(0).getId());
            roleUserList.add(ru);
        }

        //绑定的权限
        List<RolePermission> rolePermissionList = new ArrayList<RolePermission>();
        for (String rr : rolePermission) {
            RolePermission rp = new RolePermission();
            rp.setPermissionId(Long.decode(rr));
            rp.setRoleId(role.getId());
            rolePermissionList.add(rp);
        }

        roleService.insert(roleUserList, rolePermissionList);
        return ResponseHelp.responseText();
    }

    @RequestMapping("/system/goRoleUpdate")
    public String goRoleUpdate(Model model, HttpServletRequest request) {
        Log.debug(">>>进入角色修改界面");
        String id = request.getParameter("id");
        Role role = roleService.selectByPrimaryKey(Long.decode(id));
        model.addAttribute("role", role);
        return "/system/role/role_update";
    }


    /**
     * 角色修改
     *
     * @param model
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/roleUpdate")
    public BackMsg roleUpdate(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        String roleNo = request.getParameter("role");
        String roleName = request.getParameter("role_name");
        String roleDesc = request.getParameter("role_desc");
        String roleType = request.getParameter("role_type");
        String status = request.getParameter("status");

        Role role = new Role();
        role.setId(Long.decode(id));
        role.setRole(roleNo);
        role.setRoleName(roleName);
        role.setRoleDesc(roleDesc);
        role.setRoleType(Integer.parseInt(roleType));
        role.setStatus("on".equals(status) ? 1 : 2);
        role.setCreateTime(DateUtils.getDateTime());

        roleService.updateByPrimaryKey(role);
        return ResponseHelp.responseText();
    }


    /**
     * 删除角色
     *
     * @param model
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/roleDel")
    public BackMsg roleDel(Model model, HttpServletRequest request) {
        String ids = request.getParameter("ids");
        String[] idsArr = ids.split(",");
        for (String id : idsArr) {
            if ("1".equals(id)) {
                return ResponseHelp.responseErrorText("admin角色不允许被删除");
            }
        }
        roleService.batchDelRole(ids.split(","));
        return ResponseHelp.responseText();
    }

    @RequestMapping("/system/goRoleBindUser")
    public String goRoleBindUser(Model model, HttpServletRequest request) {
        Log.debug(">>>进入角色用户授权添加界面");

        List<User> list = userService.selectByExample(null);
        model.addAttribute("userList", list);
        return "/system/role/role_bindUser";
    }

    @RequestMapping("/system/goRoleBindPermission")
    public String goRoleBindPermission(Model model, HttpServletRequest request) {
        Log.debug(">>>进入角色权限绑定界面");

        List<Menu> list = menuService.selectByExample(null);
        model.addAttribute("menuList", list);
        return "/system/role/role_bindPermission";
    }

}
