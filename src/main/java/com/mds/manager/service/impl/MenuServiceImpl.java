package com.mds.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.manager.dao.MenuMapper;
import com.mds.manager.model.Menu;
import com.mds.manager.model.MenuExample;
import com.mds.manager.model.Tree;
import com.mds.manager.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public int countByExample(MenuExample example) {
		return menuMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(MenuExample example) {
		return menuMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return menuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Menu record) {
		return menuMapper.insert(record);
	}

	@Override
	public int insertSelective(Menu record) {
		return menuMapper.insertSelective(record);
	}

	@Override
	public List<Menu> selectByExample(MenuExample example) {
		return menuMapper.selectByExample(example);
	}

	@Override
	public Menu selectByPrimaryKey(Long id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Menu record, MenuExample example) {
		return menuMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Menu record, MenuExample example) {
		return menuMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Menu record) {
		return menuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Menu record) {
		return menuMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Tree> selectTreeNodes(Long id) {
		return menuMapper.selectTreeNodes(id);
	}

	@Override
	public Map<String, Object> selectParentMenuInfoById(Long id) {
		return menuMapper.selectParentMenuInfoById(id);
	}
}
