package com.mds.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mds.manager.model.Menu;
import com.mds.manager.model.MenuExample;
import com.mds.manager.model.Tree;

public interface MenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated Fri Aug 18 23:26:18 CST 2017
     */
    int countByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated Fri Aug 18 23:26:18 CST 2017
     */
    int deleteByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated Fri Aug 18 23:26:18 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated Fri Aug 18 23:26:18 CST 2017
     */
    int insert(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated Fri Aug 18 23:26:18 CST 2017
     */
    int insertSelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated Fri Aug 18 23:26:18 CST 2017
     */
    List<Menu> selectByExample(MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated Fri Aug 18 23:26:18 CST 2017
     */
    Menu selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated Fri Aug 18 23:26:18 CST 2017
     */
    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated Fri Aug 18 23:26:18 CST 2017
     */
    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated Fri Aug 18 23:26:18 CST 2017
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated Fri Aug 18 23:26:18 CST 2017
     */
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