package com.mds.manager.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.mds.manager.model.SysResource;
import com.mds.manager.model.User;
import com.mds.manager.service.UserService;

/**
 * 自定义shiro认证
 * 
 * @author huahao
 *
 */
public class MyShiroRealm extends AuthorizingRealm {

	private Logger Log = Logger.getLogger(MyShiroRealm.class);

	@Autowired
	private UserService userService;

	/**
	 * 赋权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Log.debug("===>正在赋权");
		User user = (User) principals.getPrimaryPrincipal();
		Long userId = user.getId();

		// 根据用户id获取权限和角色，并返回
		List<SysResource> resourceList = userService.listAllResources(userId);
		List<String> roleSnList = userService.listRoleSnByUser(userId);

		List<String> resStrList = new ArrayList<String>();
		for (SysResource resource : resourceList) {
			resStrList.add(resource.getResource());
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(new HashSet<String>(roleSnList));
		info.setStringPermissions(new HashSet<String>(resStrList));

		// 以上完成了动态地对用户授权
		Log.debug("role ===> " + roleSnList);
		Log.debug("permission ===> " + resStrList);
		return info;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		Log.debug("===> 开始校验用户名和密码");
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		if (StringUtils.isEmpty(userToken.getUsername())) {
			return null;
		}
		User user = userService.login(userToken.getUsername(), new String(
				userToken.getPassword()));
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,
				user.getPassword(), getName());
		info.setCredentialsSalt(ByteSource.Util.bytes(user.getUsername().getBytes()));
		return info;
	}

}
