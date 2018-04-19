package com.mds.manager.controller.api;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.script.ScriptUtil;
import com.mds.manager.model.Account;
import com.mds.manager.model.Sms;
import com.mds.manager.service.AccountService;
import com.mds.manager.service.SmsService;
import com.mds.manager.utils.AccountValidatorUtil;
import com.mds.manager.utils.BackMsg;
import com.mds.manager.utils.ResponseHelp;
import com.mds.manager.web.common.BusinessException;
import com.mds.manager.web.service.SMSUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * api 会员账户
 */
@RequestMapping("/api/account")
@RestController
public class ApiAccountController {

    @Autowired
    private AccountService accountService;

    @Resource
    private SMSUtilService smsUtilService;

    /**
     * 注册
     *
     * @param request
     * @return
     */
    @RequestMapping("/register")
    public BackMsg register(HttpServletRequest request) {
        BackMsg msg =new BackMsg();
        String phone = request.getParameter("phone");
        String verifyCode = request.getParameter("verifyCode");
        String password = request.getParameter("password");

        if (StrUtil.isBlank(phone) || StrUtil.isBlank(verifyCode) || StrUtil.isBlank(password)) {
            return ResponseHelp.responseErrorText("参数不完整！");
        }

        List<Account> accountList = accountService.getAccountByPhone(phone);
        if (accountList.size() > 0) {
            if (accountList.get(0).getStatus() == 0) {
                return ResponseHelp.responseErrorText("该账号已被禁用，请联系客服！");
            } else {
                return ResponseHelp.responseErrorText("该账号已注册！");
            }
        }

        boolean b = smsUtilService.checkVerifyCode(phone, verifyCode);
        if (!b) {
            return ResponseHelp.responseErrorText("验证码不正确！");
        }

        Account account = new Account();
        account.setPassword(SecureUtil.md5("akx" + password));
        account.setPhone(phone);
        account.setCreateTime(new Date());
        account.setDel(0);
        account.setStatus(1);
        int insert = accountService.insert(account);
        if (insert > 0) {
            msg.setStatus(true);
            msg.setContent(phone);
            return msg;
        } else {
            return ResponseHelp.responseErrorText("注册失败！");
        }
    }

    /**
     * 登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public BackMsg login(HttpServletRequest request) {
        BackMsg msg =new BackMsg();
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        boolean isMobile = AccountValidatorUtil.isMobile(phone);
        if (!isMobile) {
            return ResponseHelp.responseErrorText("手机号码有误！");
        }
        boolean isPassword = AccountValidatorUtil.isPassword(password);
        if (!isPassword) {
            return ResponseHelp.responseErrorText("密码格式有误！");
        }
        Account login = accountService.login(phone);
        if (login == null) {
            return ResponseHelp.responseErrorText("该账户不存在！");
        } else if(!login.getPassword().equals(SecureUtil.md5("akx" + password))){
            return ResponseHelp.responseErrorText("密码错误！");
        }else if (login.getStatus() == 0) {
            return ResponseHelp.responseErrorText("该账户已禁用，请联系客服！");
        }
        msg.setStatus(true);
        msg.setContent(phone);
        return msg;
    }

    @RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
    public BackMsg getVerifyCode(HttpServletRequest request) throws BusinessException {
        String phone = request.getParameter("phone");
        boolean isMobile = AccountValidatorUtil.isMobile(phone);
        if (!isMobile) {
            return ResponseHelp.responseErrorText("手机号码有误！");
        }
        Sms sms = new Sms();
        sms.setSystemNo(1);
        sms.setSmsId(RandomUtil.randomUUID());
        sms.setCreateTime(new Date());
        sms.setSendTime(new Date());
        sms.setSmsAddress(phone);
        sms.setSmsAddressuser(phone);
        sms.setSmsFrom("system");
        sms.setSmsFromuser("system");
        sms.setSmsStatus(2);
        sms.setSmsType(1);
        boolean b = smsUtilService.sendVerifyCode(sms);
        if (b) {
            return ResponseHelp.responseText();
        } else {
            return ResponseHelp.responseErrorText("短信发送失败！");
        }
    }

}
