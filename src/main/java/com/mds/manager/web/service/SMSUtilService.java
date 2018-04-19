package com.mds.manager.web.service;

import com.mds.manager.model.Sms;
import com.mds.manager.service.SmsService;
import com.mds.manager.tool.cloopen.sdk.CCPRestSDK;
import com.mds.manager.web.common.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class SMSUtilService {

    /**
     * SMS请求根连接
     */
    private static final String SMS_ROOT_URL = "app.cloopen.com";

    /**
     * SMS请求端口
     */
    private static final String SMS_PORT = "8883";

    /**
     * SMS账号ID
     */
    private static final String SMS_ACCOUNT_SID = "aaf98f8954939ed50154bdf5b22c2d5f";

    /**
     * SMS账号token
     */
    private static final String SMS_AUTH_TOKEN = "049cc396b0eb48c5b1003b652e2e154b";

    /**
     * SMS应用ID
     */
    private static final String SMS_APP_ID = "8a216da862a86c900162b2bf90470624";

    /**
     * 注册发送短信模版ID
     */
    private static final String SMS_TEMPLE_REGISTER_ID = "242951";

    /**
     * 注册短信超时时间
     */
    private static final String SMS_TEMPLE_REGISTER_MINUTE = "30";


    @Autowired
    private SmsService smsService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public boolean sendVerifyCode(Sms sms) throws BusinessException {
        String code = new Random().nextInt(9000) + 1000 + "";
        sms.setSmsContent(code);
        int insert = smsService.insert(sms);

        if (insert < 1) {
            return false;
        }

        Boolean aBoolean = sendSMS(sms.getSmsAddress(), SMS_TEMPLE_REGISTER_ID, new String[]{sms.getSmsContent(), SMS_TEMPLE_REGISTER_MINUTE + "分钟"});
        if (aBoolean) {
            sms.setSmsStatus(1);
            int update = smsService.update(sms);
            if (update > 0) {
                return true;
            } else {
                logger.error("修改短信发送状态失败！");
                return false;
            }
        } else {
            logger.error("短信发送失败！");
            throw new BusinessException("短信发送失败");
        }

    }

    public boolean checkVerifyCode(String phone, String verifyCode) {
        Integer minute = Integer.parseInt(SMS_TEMPLE_REGISTER_MINUTE);
        return smsService.getCheckVerifyCode(phone, verifyCode, minute);
    }


    private Boolean sendSMS(String phone, String templateID, String[] params) {
        HashMap<String, Object> result = null;
        CCPRestSDK restAPI = new CCPRestSDK();
        restAPI.init(SMS_ROOT_URL, SMS_PORT);
        restAPI.setAccount(SMS_ACCOUNT_SID, SMS_AUTH_TOKEN);
        restAPI.setAppId(SMS_APP_ID);// 初始化应用ID
        result = restAPI.sendTemplateSMS(phone, templateID, params);
        if ("000000".equals(result.get("statusCode"))) {
            //正常返回输出data包体信息（map）
            //            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            //            Set<String> keySet = data.keySet();
            //            for (String key : keySet) {
            //                Object object = data.get(key);
            //                System.out.println(key + " = " + object);
            //            }
            logger.info("发送成功  " + result.toString());
            return true;
        } else {
            logger.error("发送短信失败，错误码=" + result.get("statusCode") + " 错误信息= "
                    + result.get("statusMsg"));
            return false;
        }

    }

}
