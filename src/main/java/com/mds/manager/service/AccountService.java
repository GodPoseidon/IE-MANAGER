package com.mds.manager.service;

import com.mds.manager.model.Account;
import com.mds.manager.model.AccountExample;
import com.mds.manager.model.User;
import com.mds.manager.utils.PageUtils;

import java.util.List;

public interface AccountService {

    int insert(Account record);

    /**
     * 获取用户分页数据
     * @param Page
     * @return
     */
    PageUtils getAccountPages(PageUtils Page);

    List<Account> selectByExample(AccountExample example);

    int updateByPrimaryKeySelective(Account account);

    int batchDel(String[] ids);

    Account login(String phone);

    List<Account> getAccountByPhone(String phone);
}
