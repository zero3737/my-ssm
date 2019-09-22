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
		
		// 限制数据源只支持 UsernamePasswordToken
		return token instanceof UsernamePasswordToken;

	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		// 获取用户名
		String principal = (String)token.getPrincipal();
		// 获取密码
		String credentials = new String((char [])token.getCredentials());
		// 判断用户名是否正确，不正确抛出 UnknownAccountException 异常
		if(!"test".equals(principal)) {
			
			throw new UnknownAccountException();
			
		}
		// 判断密码是否正确，不争取抛出 IncorrectCredentialsException 异常
		if(!"123456".equals(credentials)) {
			
			throw new IncorrectCredentialsException();
			
		}
		// 返回用户名、密码和数据源名字
		return new SimpleAuthenticationInfo(principal, credentials, getName());
		
	}

}
