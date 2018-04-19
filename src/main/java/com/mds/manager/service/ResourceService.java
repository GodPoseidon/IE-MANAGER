package com.mds.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mds.manager.model.SysResource;
import com.mds.manager.model.SysResourceExample;
import com.mds.manager.utils.PageUtils;

public interface ResourceService {

    int countByExample(SysResourceExample example);

    int deleteByExample(SysResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    List<SysResource> selectByExample(SysResourceExample example);

    SysResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByExample(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
    
    /**
     * 获取分页数据
     * @param Page
     * @return
     */
    PageUtils getResourcePage(PageUtils Page);
    
    /**
     * 批量删除资源
     * @param ids
     * @return
     */
    int batchDelResource(String[] ids);
}
