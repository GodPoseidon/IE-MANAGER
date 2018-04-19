package com.mds.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.manager.dao.RoleUserMapper;
import com.mds.manager.dao.UserMapper;
import com.mds.manager.model.RoleUser;
import com.mds.manager.model.RoleUserExample;
import com.mds.manager.model.User;
import com.mds.manager.service.RoleUserService;

@Service
public class RoleUserServiceImpl implements RoleUserService {
	
	@Autowired
	private RoleUserMapper roleUserMapper;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int countByExample(RoleUserExample example) {
		return roleUserMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(RoleUserExample example) {
		return roleUserMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return roleUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RoleUser record) {
		return roleUserMapper.insert(record);
	}

	@Override
	public int insertSelective(RoleUser record) {
		return roleUserMapper.insertSelective(record);
	}

	@Override
	public List<RoleUser> selectByExample(RoleUserExample example) {
		return roleUserMapper.selectByExample(example);
	}

	@Override
	public RoleUser selectByPrimaryKey(Long id) {
		return roleUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(RoleUser record, RoleUserExample example) {
		return roleUserMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(RoleUser record, RoleUserExample example) {
		return roleUserMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(RoleUser record) {
		return roleUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RoleUser record) {
		return roleUserMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<User> getNoBindingUser(String roleId) {
		return userMapper.getNoBindingUser(roleId);
	}

	@Override
	public int batchInsert(List<RoleUser> list) {
		return roleUserMapper.batchInsert(list);
	}

}
