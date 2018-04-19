package com.mds.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mds.manager.model.Role;
import com.mds.manager.model.RoleExample;
import com.mds.manager.model.RolePermission;
import com.mds.manager.model.RoleUser;
import com.mds.manager.utils.PageUtils;

public interface RoleService {

    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long id);

    void insert(List<RoleUser> roleUserList, List<RolePermission> rolePermissionList);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    /**
     * 分页查询
     * @param role
     * @return
     */
    PageUtils getRolePage(PageUtils Page);
    
    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    int batchDelRole(String[] ids);
}
