package top.zero3737.service;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm{

	public String getName() {
		
		return "myrealml";
		
	}

	public boolean supports(AuthenticationToken token) {
		
		// ��������Դֻ֧�� UsernamePasswordToken
		return token instanceof UsernamePasswordToken;

	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		// ��ȡ�û���
		String principal = (String)token.getPrincipal();
		// ��ȡ����
		String credentials = new String((char [])token.getCredentials());
		// �ж��û����Ƿ���ȷ������ȷ�׳� UnknownAccountException �쳣
		if(!"test".equals(principal)) {
			
			throw new UnknownAccountException();
			
		}
		// �ж������Ƿ���ȷ������ȡ�׳� IncorrectCredentialsException �쳣
		if(!"123456".equals(credentials)) {
			
			throw new IncorrectCredentialsException();
			
		}
		// �����û��������������Դ����
		return new SimpleAuthenticationInfo(principal, credentials, getName());
		
	}

}
