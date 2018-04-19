package com.mds.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.manager.dao.RolePermissionMapper;
import com.mds.manager.model.RolePermission;
import com.mds.manager.model.RolePermissionExample;
import com.mds.manager.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public int countByExample(RolePermissionExample example) {
		return rolePermissionMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(RolePermissionExample example) {
		return rolePermissionMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return rolePermissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RolePermission record) {
		return rolePermissionMapper.insert(record);
	}

	@Override
	public int insertSelective(RolePermission record) {
		return rolePermissionMapper.insertSelective(record);
	}

	@Override
	public List<RolePermission> selectByExample(RolePermissionExample example) {
		return rolePermissionMapper.selectByExample(example);
	}

	@Override
	public RolePermission selectByPrimaryKey(Long id) {
		return rolePermissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(RolePermission record, RolePermissionExample example) {
		return rolePermissionMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(RolePermission record, RolePermissionExample example) {
		return rolePermissionMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(RolePermission record) {
		return rolePermissionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RolePermission record) {
		return rolePermissionMapper.updateByPrimaryKey(record);
	}

}
