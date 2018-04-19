package com.mds.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mds.manager.model.Notice;
import com.mds.manager.model.NoticeExample;
import com.mds.manager.utils.PageUtils;

public interface NoticeService {

    int countByExample(NoticeExample example);

    int deleteByExample(NoticeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    int insertSelective(Notice record);

    List<Notice> selectByExample(NoticeExample example);

    Notice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
    
    /**
     * 获取分页数据
     * @param Page
     * @return
     */
    PageUtils getNoticePages(PageUtils Page);
    
    /**
     * 批量删除系统公告
     * @param ids
     * @return
     */
    int batchDelNotice(String[] ids);
}
