package com.mds.manager.dao;

import com.mds.manager.model.Account;
import com.mds.manager.model.AccountExample;
import java.util.List;

import com.mds.manager.model.User;
import com.mds.manager.model.UserExample;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    /**
     *
     * @mbggenerated 2018-04-10
     */
    int countByExample(AccountExample example);

    /**
     *
     * @mbggenerated 2018-04-10
     */
    int deleteByExample(AccountExample example);

    /**
     *
     * @mbggenerated 2018-04-10
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-10
     */
    int insert(Account record);

    /**
     *
     * @mbggenerated 2018-04-10
     */
    int insertSelective(Account record);

    /**
     *
     * @mbggenerated 2018-04-10
     */
    List<Account> selectByExample(AccountExample example);

    /**
     *
     * @mbggenerated 2018-04-10
     */
    Account selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-10
     */
    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     *
     * @mbggenerated 2018-04-10
     */
    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     *
     * @mbggenerated 2018-04-10
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     *
     * @mbggenerated 2018-04-10
     */
    int updateByPrimaryKey(Account record);

    /**
     * 获取用户分页数据
     * @param start
     * @param pageNum
     * @param
     * @return
     */
    List<Account> getAccountPages(@Param("start") int start, @Param("pageNum") int pageNum, @Param("example")AccountExample example);

    int batchDel(@Param("ids") String[] ids);

    Account login(@Param("phone") String phone);

    List<Account> getAccountByPhone(String phone);
}