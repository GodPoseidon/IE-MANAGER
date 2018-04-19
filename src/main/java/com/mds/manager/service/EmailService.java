package com.mds.manager.service;

import com.mds.manager.utils.PageUtils;

public interface EmailService {

	/**
	 * 分页
	 * @param Page
	 * @return
	 */
	PageUtils getEmailPages(PageUtils Page);
}
