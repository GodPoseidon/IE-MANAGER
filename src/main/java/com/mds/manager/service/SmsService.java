package com.mds.manager.service;

import com.mds.manager.model.Sms;
import com.mds.manager.utils.PageUtils;

public interface SmsService {

    /**
     * 短信分页数据
     *
     * @param Page
     * @return
     */
    PageUtils getSmsPages(PageUtils Page);

    int insert(Sms sms);

    int update(Sms sms);

    Boolean getCheckVerifyCode(String phone, String verifyCode, Integer minute);
}
