package com.mds.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.manager.dao.NoticeMapper;
import com.mds.manager.model.Notice;
import com.mds.manager.model.NoticeExample;
import com.mds.manager.service.NoticeService;
import com.mds.manager.utils.PageUtils;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeMapper NoticeMapper;

	@Override
	public int countByExample(NoticeExample example) {
		return NoticeMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(NoticeExample example) {
		return NoticeMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return NoticeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Notice record) {
		return NoticeMapper.insert(record);
	}

	@Override
	public int insertSelective(Notice record) {
		return NoticeMapper.insertSelective(record);
	}

	@Override
	public List<Notice> selectByExample(NoticeExample example) {
		return NoticeMapper.selectByExample(example);
	}

	@Override
	public Notice selectByPrimaryKey(Long id) {
		return NoticeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Notice record, NoticeExample example) {
		return NoticeMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Notice record, NoticeExample example) {
		return NoticeMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Notice record) {
		return NoticeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Notice record) {
		return NoticeMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageUtils getNoticePages(PageUtils Page) {
		NoticeExample example = (NoticeExample) Page.getQueryParames();
		List<Notice> list = NoticeMapper.getNoticePages((Page.getPage()-1)*Page.getLimit(),Page.getLimit(), example);
		int count = NoticeMapper.countByExample(example);
		Page.setList(list);
		Page.setCountNum(count);
		return Page;
	}

	@Override
	public int batchDelNotice(String[] ids) {
		return NoticeMapper.batchDelNotice(ids);
	}

}
