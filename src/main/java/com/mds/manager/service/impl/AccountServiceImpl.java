package com.mds.manager.service.impl;

import com.mds.manager.dao.AccountMapper;
import com.mds.manager.model.Account;
import com.mds.manager.model.AccountExample;
import com.mds.manager.model.User;
import com.mds.manager.model.UserExample;
import com.mds.manager.service.AccountService;
import com.mds.manager.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public int insert(Account record) {
        return accountMapper.insert(record);
    }

    @Override
    public PageUtils getAccountPages(PageUtils Page) {
        AccountExample example = (AccountExample) Page.getQueryParames();
        List<Account> list = accountMapper.getAccountPages((Page.getPage()-1)*Page.getLimit(),Page.getLimit(), example);
        int count = accountMapper.countByExample(example);
        Page.setList(list);
        Page.setCountNum(count);
        return Page;
    }

    @Override
    public List<Account> selectByExample(AccountExample example) {
        return accountMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKeySelective(Account account) {
        return accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public int batchDel(String[] ids) {
        return accountMapper.batchDel(ids);
    }

    @Override
    public Account login(String phone) {
        return accountMapper.login(phone);
    }

    @Override
    public List<Account> getAccountByPhone(String phone) {
        return accountMapper.getAccountByPhone(phone);
    }
}
