package com.mds.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mds.manager.model.RoleUser;
import com.mds.manager.model.RoleUserExample;
import com.mds.manager.model.User;

public interface RoleUserService {

	int countByExample(RoleUserExample example);

    int deleteByExample(RoleUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoleUser record);

    int insertSelective(RoleUser record);

    List<RoleUser> selectByExample(RoleUserExample example);

    RoleUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoleUser record, @Param("example") RoleUserExample example);

    int updateByExample(@Param("record") RoleUser record, @Param("example") RoleUserExample example);

    int updateByPrimaryKeySelective(RoleUser record);

    int updateByPrimaryKey(RoleUser record);
    
    /**
     * 获取未绑定用户
     * @return
     */
    List<User> getNoBindingUser(String roleId);
    
    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(List<RoleUser> list);
}
