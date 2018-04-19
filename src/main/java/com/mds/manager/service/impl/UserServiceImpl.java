package com.mds.manager.service.impl;

import java.util.List;

import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.manager.dao.UserMapper;
import com.mds.manager.model.SysResource;
import com.mds.manager.model.User;
import com.mds.manager.model.UserExample;
import com.mds.manager.model.UserExample.Criteria;
import com.mds.manager.service.UserService;
import com.mds.manager.utils.ListUtils;
import com.mds.manager.utils.PageUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int countByExample(UserExample example) {
		return userMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(UserExample example) {
		return userMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		return userMapper.insertSelective(record);
	}

	@Override
	public List<User> selectByExample(UserExample example) {
		return userMapper.selectByExample(example);
	}

	@Override
	public User selectByPrimaryKey(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(User record, UserExample example) {
		return userMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(User record, UserExample example) {
		return userMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return userMapper.updateByPrimaryKey(record);
	}

	public List<SysResource> listAllResources(Long uid) {
		return userMapper.listAllResources(uid);
	}

	public List<String> listRoleSnByUser(Long uid) {
		return userMapper.listRoleSnByUser(uid);
	}

	@Override
	public User login(String username, String password) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		
		//根据用户名查找user
		List<User> list = userMapper.selectByExample(userExample);
		if (!ListUtils.isNotNull(list)) {
			// 因为缓存切面的原因,在这里就抛出用户名不存在的异常
			throw new UnknownAccountException("用户名不存在");
		}else if(list.get(0).getStatus() == 0){
            throw new LockedAccountException("用户已经被禁用，请联系管理员启用该账号");
        }
		return list.get(0);
	}

	@Override
	public PageUtils getUserPages(PageUtils Page) {
		UserExample example = (UserExample) Page.getQueryParames();
		List<User> list = userMapper.getUserPages((Page.getPage()-1)*Page.getLimit(),Page.getLimit(), example);
		int count = userMapper.countByExample(example);
		Page.setList(list);
		Page.setCountNum(count);
		return Page;
	}

	@Override
	public int batchDelUser(String[] ids) {
		return userMapper.batchDelUser(ids);
	}
	
	
}
