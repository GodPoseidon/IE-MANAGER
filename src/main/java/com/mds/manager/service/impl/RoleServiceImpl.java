package com.mds.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mds.manager.dao.RoleMapper;
import com.mds.manager.dao.RolePermissionMapper;
import com.mds.manager.model.Role;
import com.mds.manager.model.RoleExample;
import com.mds.manager.model.RolePermission;
import com.mds.manager.model.RoleUser;
import com.mds.manager.service.RoleService;
import com.mds.manager.service.RoleUserService;
import com.mds.manager.utils.PageUtils;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleDao;
	
	@Autowired
	private RoleUserService roleUserService;
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public int countByExample(RoleExample example) {
		return roleDao.countByExample(example);
	}

	@Override
	public int deleteByExample(RoleExample example) {
		return roleDao.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return roleDao.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void insert(List<RoleUser> roleUserList, List<RolePermission> rolePermissionList) {
		roleUserService.batchInsert(roleUserList);
		rolePermissionMapper.batchInsert(rolePermissionList);
	}

	@Override
	public int insertSelective(Role record) {
		return roleDao.insertSelective(record);
	}

	@Override
	public List<Role> selectByExample(RoleExample example) {
		return roleDao.selectByExample(example);
	}

	@Override
	public Role selectByPrimaryKey(Long id) {
		return roleDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Role record, RoleExample example) {
		return roleDao.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Role record, RoleExample example) {
		return roleDao.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		return roleDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		return roleDao.updateByPrimaryKey(record);
	}

	@Override
	public PageUtils getRolePage(PageUtils Page) {
		RoleExample example = (RoleExample) Page.getQueryParames();
		List<Role> list = roleDao.getRolePage((Page.getPage()-1)*Page.getLimit(),Page.getLimit(), example);
		int count = roleDao.countByExample(null);
		Page.setList(list);
		Page.setCountNum(count);
		return Page;
	}

	@Override
	public int batchDelRole(String[] ids) {
		return roleDao.batchDelRole(ids);
	}

}
