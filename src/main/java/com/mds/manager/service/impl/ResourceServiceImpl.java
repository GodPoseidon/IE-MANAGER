package com.mds.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.manager.dao.SysResourceMapper;
import com.mds.manager.model.SysResource;
import com.mds.manager.model.SysResourceExample;
import com.mds.manager.service.ResourceService;
import com.mds.manager.utils.PageUtils;

@Service
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private SysResourceMapper sysResourceMapper;

	@Override
	public int countByExample(SysResourceExample example) {
		return sysResourceMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(SysResourceExample example) {
		return sysResourceMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sysResourceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysResource record) {
		return sysResourceMapper.insert(record);
	}

	@Override
	public int insertSelective(SysResource record) {
		return sysResourceMapper.insertSelective(record);
	}

	@Override
	public List<SysResource> selectByExample(SysResourceExample example) {
		return sysResourceMapper.selectByExample(example);
	}

	@Override
	public SysResource selectByPrimaryKey(Long id) {
		return sysResourceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(SysResource record,
			SysResourceExample example) {
		return sysResourceMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(SysResource record, SysResourceExample example) {
		return sysResourceMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(SysResource record) {
		return sysResourceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysResource record) {
		return sysResourceMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageUtils getResourcePage(PageUtils Page) {
		SysResourceExample example = (SysResourceExample) Page.getQueryParames();
		List<SysResource> list = sysResourceMapper.getResourcePage((Page.getPage()-1)*Page.getLimit(),Page.getLimit(), example);
		int count = sysResourceMapper.countByExample(example);
		Page.setList(list);
		Page.setCountNum(count);
		return Page;
	}

	@Override
	public int batchDelResource(String[] ids) {
		return sysResourceMapper.batchDelResource(ids);
	}

}
