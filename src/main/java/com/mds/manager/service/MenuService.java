package com.mds.manager.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mds.manager.model.Menu;
import com.mds.manager.model.MenuExample;
import com.mds.manager.model.Tree;

public interface MenuService {

	int countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    /**
     * 获取树形结构
     * @param id
     * @return
     */
    List<Tree> selectTreeNodes(Long id);
    
    /**
     * 根据树节点ID获取当前节点和父节点信息
     * @param id
     * @return
     */
    Map<String, Object> selectParentMenuInfoById(Long id);
}
