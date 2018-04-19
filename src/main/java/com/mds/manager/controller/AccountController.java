package com.mds.manager.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSONArray;
import com.mds.manager.model.Account;
import com.mds.manager.model.AccountExample;
import com.mds.manager.model.User;
import com.mds.manager.service.AccountService;
import com.mds.manager.utils.*;
import org.apache.log4j.Logger;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {

    private Logger Log = Logger.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @RequestMapping("/accountIndex")
    public String goUserIndex(Model model) {
        Log.info("进入用户管理界面");
        return "/account/account_index";
    }

    /**
     * 用户列表分页数据
     *
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAccountPage")
    public Object getPageData(Model model, HttpServletRequest request) {
        Log.info(">>> 进入用户分页列表");
        String page = request.getParameter("pageNumber");
        String id = request.getParameter("id");
        String phone = request.getParameter("phone");

        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andDelEqualTo(0);
        if (!StringUtils.isEmpty(id)) {
            criteria.andIdEqualTo(Long.valueOf(id));
        }
        if (!StringUtils.isEmpty(phone)) {
            criteria.andPhoneLike("%" + phone + "%");
        }

        PageUtils Page = new PageUtils(page);
        Page.setQueryParames(example);
        Page = accountService.getAccountPages(Page);
        return Page;
    }

    @RequestMapping("/goAddAccountPage")
    public String goAddUserPage(Model model) {
        Log.info("进入用户添加界面");

        return "/account/account_add";
    }

    /**
     * 添加用户
     *
     * @param model
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/accountAdd")
    public BackMsg accountAdd(Model model, HttpServletRequest request) {
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String status = request.getParameter("status");

        Account account = new Account();
        account.setDel(0);
        account.setCreateTime(new Date());
        account.setPhone(phone);
        account.setPassword(SecureUtil.md5("akx" + password));
        account.setStatus("on".equals(status) ? 1 : 0);
        accountService.insert(account);

        return ResponseHelp.responseText();
    }


    /**
     * 用户名是否存在
     *
     * @param model
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/accountIsExist")
    public BackMsg accountIsExist(Model model, HttpServletRequest request) {
        String phone = request.getParameter("phone");

        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        criteria.andDelEqualTo(0);

        List<Account> list = accountService.selectByExample(example);
        if (ListUtils.isNotNull(list)) {
            return ResponseHelp.responseErrorText("手机号已存在！");
        }
        return ResponseHelp.responseText();
    }


    @ResponseBody
    @RequestMapping("/accountDel")
    public BackMsg accountDel(HttpServletRequest request) {
        BackMsg msg = new BackMsg();
        String ids = request.getParameter("ids");
        String[] idsArr = ids.split(",");

        int i = accountService.batchDel(idsArr);
        if (i > 0) {
            msg.setStatus(true);
            msg.setMessage("删除成功！");
        } else {
            msg.setMessage("删除失败！");
            msg.setStatus(false);
        }
        return msg;
    }


    @ResponseBody
    @RequestMapping("/updateStatus")
    public BackMsg updateStatus(HttpServletRequest request) {
        BackMsg msg = new BackMsg();
        String id = request.getParameter("id");
        String status = request.getParameter("status");

        Account account = new Account();
        account.setId(Long.decode(id));
        account.setStatus(Integer.decode(status));

        int i = accountService.updateByPrimaryKeySelective(account);
        if (i > 0) {
            msg.setMessage("修改成功！");
            msg.setStatus(true);
        } else {
            msg.setMessage("修改失败！");
            msg.setStatus(false);
        }
        return msg;
    }

    @RequestMapping("/exportExcel")
    public void export(HttpServletResponse response) {
        List<Account> accounts = accountService.selectByExample(null);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < accounts.size(); i++) {
            jsonArray.add(accounts.get(i));
        }

        Map<String, String> headMap = new HashMap<String, String>();
        headMap.put("phone", "手机号");
        headMap.put("id", "用户ID");
        headMap.put("createTime", "注册时间");
        headMap.put("status", "账户状态");

        ExcelUtils.downloadExcelFile("会员表", headMap, jsonArray, response);
    }

}
