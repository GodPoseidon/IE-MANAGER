package com.mds.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.manager.dao.SmsMapper;
import com.mds.manager.model.Sms;
import com.mds.manager.model.SmsExample;
import com.mds.manager.service.SmsService;
import com.mds.manager.utils.PageUtils;

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsMapper smsMapper;

    @Override
    public PageUtils getSmsPages(PageUtils Page) {
        SmsExample example = (SmsExample) Page.getQueryParames();
        List<Sms> list = smsMapper.getSmsPages((Page.getPage() - 1) * Page.getLimit(), Page.getLimit(), example);
        int count = smsMapper.countByExample(example);
        Page.setList(list);
        Page.setCountNum(count);
        return Page;
    }

    @Override
    public int insert(Sms sms) {
        return smsMapper.insert(sms);
    }

    @Override
    public int update(Sms sms) {
        return smsMapper.updateByPrimaryKeySelective(sms);
    }

    @Override
    public Boolean getCheckVerifyCode(String phone, String verifyCode, Integer minute) {
        List<Sms> smsList = smsMapper.getCheckVerifyCode(phone, minute);
        if (smsList != null && smsList.size() > 0 && verifyCode.equals(smsList.get(0).getSmsContent())) {
            return true;
        } else {
            return false;
        }
    }

}
