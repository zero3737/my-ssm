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
		
		// ��������
		IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-mysql.ini");
		// �ӹ����л�ȡ����ʵ��
		SecurityManager instance = iniSecurityManagerFactory.getInstance();
		// ���� shiro.ini ����
		SecurityUtils.setSecurityManager(instance);
		// ��ȡ��ǰ subject ����
		Subject subject = SecurityUtils.getSubject();
		// �� Realm��Shiro ����Դ�����бȽϵ�����
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("ls", "123456");
		try {
			
			// ��ʼ�Ƚϣ��Ƚϲ�ͨ�����׳��쳣
			subject.login(usernamePasswordToken);
			// isAuthenticated �����ж��Ƿ���֤ͨ����ͨ������ true�����򷵻� false
			if(subject.isAuthenticated()) {	
				
				System.out.println("��¼�ɹ�");
				// �ж��Ƿ�Ϊ admin ��ɫ
				if(subject.hasRole("admin")) {
					
					System.out.println("Ϊ admin ��ɫ");
					
				} else {
					
					System.out.println("������ɫ");
					
				}
				// �ж��Ƿ��� find Ȩ��
				if(subject.isPermitted("find")) {
					
					System.out.println("�� find Ȩ��");
					
				} else {
					
					System.out.println("û�� find Ȩ��");
					
				}
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("��¼ʧ��");
			
		}
		
	}
	
	@Test
	public void run() {
		
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		// ���������֤����
		ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
		modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		// ���������֤����
		defaultSecurityManager.setAuthenticator(modularRealmAuthenticator);
		// ������Ȩ
		ModularRealmAuthorizer modularRealmAuthorizer = new ModularRealmAuthorizer();
		modularRealmAuthorizer.setPermissionResolver(new WildcardPermissionResolver());
		// ������Ȩ��ʽ
		defaultSecurityManager.setAuthorizer(modularRealmAuthorizer);
		// �����Զ�������Դ
		defaultSecurityManager.setRealm(new MyRealm1());
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		// ��ȡ��ǰ subject ����
		Subject subject = SecurityUtils.getSubject();
		// �� Realm��Shiro ����Դ�����бȽϵ�����
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("test", "123456");
		try {
			
			// ��ʼ�Ƚϣ��Ƚϲ�ͨ�����׳��쳣
			subject.login(usernamePasswordToken);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}
