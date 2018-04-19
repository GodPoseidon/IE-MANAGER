package com.mds.manager.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mds.manager.model.Organization;
import com.mds.manager.model.OrganizationExample;
import com.mds.manager.model.Tree;

public interface OrganizationService {

    int countByExample(OrganizationExample example);

    int deleteByExample(OrganizationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Organization record);

    int insertSelective(Organization record);

    List<Organization> selectByExample(OrganizationExample example);

    Organization selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Organization record, @Param("example") OrganizationExample example);

    int updateByExample(@Param("record") Organization record, @Param("example") OrganizationExample example);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
    
    /**
     * 获取树形结构
     * @param organization
     * @return
     */
    List<Tree> selectTreeNodes(String organization);
    
    /**
     * 根据树节点ID获取当前节点和父节点信息
     * @param organization
     * @return
     */
    Map<String, Object> selectParentNodeInfoById(Long id);
}
