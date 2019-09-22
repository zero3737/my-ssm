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
	
	// Ȩ����֤���õķ�������֤���û��Ƿ�ӵ�ж�Ӧ��ɫ��
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		String sql = "select role from shiro_user_role where username = ?";
		// ��ȡ�û�
		String primaryPrincipal = (String)principals.getPrimaryPrincipal();
		// �����û���ѯ��ɫ
		List<String> queryForList = JdbcTemplate.queryForList(sql, String.class, primaryPrincipal);
		// ��װ����
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRoles(queryForList);
		// �ѷ�װ���󷵻ظ� Shiro �Զ���֤
		return simpleAuthorizationInfo;
	}

	// ��¼��ʱ����ã��Ƚ��û���������
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		new UsernamePasswordToken
		String sql = "select password from user where username = ?";
		String principal = (String)token.getPrincipal();
		String queryForObject = JdbcTemplate.queryForObject(sql, String.class, principal);
		// ��װ���󣬲����ֱ�Ϊ�û��������롢���ܵ���ֵ��Realm������
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, queryForObject, null, getName());
		
		// ���ظ� Shiro ���Զ��������֤
		return simpleAuthenticationInfo;
		
	}

}
