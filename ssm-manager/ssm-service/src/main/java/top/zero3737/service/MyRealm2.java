package top.zero3737.service;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MyRealm2 extends AuthorizingRealm{

	@Autowired
	JdbcTemplate JdbcTemplate;
	
	// 权限验证调用的方法，验证该用户是否拥有对应角色。
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		String sql = "select role from shiro_user_role where username = ?";
		// 获取用户
		String primaryPrincipal = (String)principals.getPrimaryPrincipal();
		// 根据用户查询角色
		List<String> queryForList = JdbcTemplate.queryForList(sql, String.class, primaryPrincipal);
		// 封装对象
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRoles(queryForList);
		// 把封装对象返回给 Shiro 自动验证
		return simpleAuthorizationInfo;
	}

	// 登录的时候调用，比较用户名和密码
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		new UsernamePasswordToken
		String sql = "select password from user where username = ?";
		String principal = (String)token.getPrincipal();
		String queryForObject = JdbcTemplate.queryForObject(sql, String.class, principal);
		// 封装对象，参数分别为用户名、密码、加密的盐值、Realm的名字
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, queryForObject, null, getName());
		
		// 返回给 Shiro 会自动缓存和验证
		return simpleAuthenticationInfo;
		
	}

}
