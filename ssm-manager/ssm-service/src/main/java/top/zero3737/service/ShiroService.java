package top.zero3737.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroService {

	@Test
	public void main() {
		
		// 创建工程
		IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-mysql.ini");
		// 从工厂中获取配置实例
		SecurityManager instance = iniSecurityManagerFactory.getInstance();
		// 加载 shiro.ini 配置
		SecurityUtils.setSecurityManager(instance);
		// 获取当前 subject 对象
		Subject subject = SecurityUtils.getSubject();
		// 和 Realm（Shiro 数据源）进行比较的数据
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("ls", "123456");
		try {
			
			// 开始比较，比较不通过会抛出异常
			subject.login(usernamePasswordToken);
			// isAuthenticated 方法判断是否验证通过，通过返回 true，否则返回 false
			if(subject.isAuthenticated()) {	
				
				System.out.println("登录成功");
				// 判断是否为 admin 角色
				if(subject.hasRole("admin")) {
					
					System.out.println("为 admin 角色");
					
				} else {
					
					System.out.println("其他角色");
					
				}
				// 判断是否有 find 权限
				if(subject.isPermitted("find")) {
					
					System.out.println("有 find 权限");
					
				} else {
					
					System.out.println("没有 find 权限");
					
				}
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("登录失败");
			
		}
		
	}
	
	@Test
	public void run() {
		
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		// 设置身份验证策略
		ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
		modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		// 加载身份验证策略
		defaultSecurityManager.setAuthenticator(modularRealmAuthenticator);
		// 设置授权
		ModularRealmAuthorizer modularRealmAuthorizer = new ModularRealmAuthorizer();
		modularRealmAuthorizer.setPermissionResolver(new WildcardPermissionResolver());
		// 加载授权方式
		defaultSecurityManager.setAuthorizer(modularRealmAuthorizer);
		// 加载自定义数据源
		defaultSecurityManager.setRealm(new MyRealm1());
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		// 获取当前 subject 对象
		Subject subject = SecurityUtils.getSubject();
		// 和 Realm（Shiro 数据源）进行比较的数据
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("test", "123456");
		try {
			
			// 开始比较，比较不通过会抛出异常
			subject.login(usernamePasswordToken);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}
