package com.mds.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.manager.dao.OrganizationMapper;
import com.mds.manager.model.Organization;
import com.mds.manager.model.OrganizationExample;
import com.mds.manager.model.Tree;
import com.mds.manager.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private OrganizationMapper organizationMapper;

	@Override
	public int countByExample(OrganizationExample example) {
		return organizationMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(OrganizationExample example) {
		return organizationMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return organizationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Organization record) {
		return organizationMapper.insert(record);
	}

	@Override
	public int insertSelective(Organization record) {
		return organizationMapper.insertSelective(record);
	}

	@Override
	public List<Organization> selectByExample(OrganizationExample example) {
		return organizationMapper.selectByExample(example);
	}

	@Override
	public Organization selectByPrimaryKey(Long id) {
		return organizationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Organization record,
			OrganizationExample example) {
		return organizationMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Organization record, OrganizationExample example) {
		return organizationMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Organization record) {
		return organizationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Organization record) {
		return organizationMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Tree> selectTreeNodes(String organization) {
		return organizationMapper.selectTreeNodes(organization);
	}

	@Override
	public Map<String, Object> selectParentNodeInfoById(Long id) {
		return organizationMapper.selectParentNodeInfoById(id);
	}

}
