package com.mds.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mds.manager.model.SysResource;
import com.mds.manager.model.User;
import com.mds.manager.model.UserExample;
import com.mds.manager.utils.PageUtils;

public interface UserService {
	
	int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
	
	/**
     * 根据用户id查询所有的权限资源
     * @param uid
     * @return
     */
    List<SysResource> listAllResources(Long uid);
    
    /**
     * 根据用户id查询用户所有角色
     * @param uid
     * @return
     */
    List<String> listRoleSnByUser(Long uid);
    
    /**
     * 用户登录
     * 1、先根据用户名查询用户对象
     * 2、如果有用户对象，则继续匹配密码
     * 如果没有用户对象，则抛出异常
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);
    
    /**
     * 获取用户分页数据
     * @param page
     * @return
     */
    PageUtils getUserPages(PageUtils Page);

    /**
     * 根据id批量删除用户
     * @param ids：1,2,3		以逗号间隔的字符串
     * @return
     */
    int batchDelUser(String[] ids);
}
