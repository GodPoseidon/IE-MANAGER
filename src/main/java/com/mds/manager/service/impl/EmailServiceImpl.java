package com.mds.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.manager.dao.EmailMapper;
import com.mds.manager.model.Email;
import com.mds.manager.model.EmailExample;
import com.mds.manager.service.EmailService;
import com.mds.manager.utils.PageUtils;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailMapper emailMapper;
	
	@Override
	public PageUtils getEmailPages(PageUtils Page) {
		EmailExample example = (EmailExample) Page.getQueryParames();
		List<Email> list = emailMapper.getEmailPages((Page.getPage()-1)*Page.getLimit(),Page.getLimit(), example);
		int count = emailMapper.countByExample(example);
		Page.setList(list);
		Page.setCountNum(count);
		return Page;
	}

}
