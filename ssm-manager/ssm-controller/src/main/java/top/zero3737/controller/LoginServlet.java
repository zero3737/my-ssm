package top.zero3737.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginServlet {
	
	@RequestMapping(method=RequestMethod.POST)
	public String returnPageData(String username, String password) {
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		try {
		
			subject.login(usernamePasswordToken);
			if(subject.isAuthenticated()) {
				
				return "redirect:/demo/login/index.html";
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return "redirect:/demo/login/login.html";
			
		}
		
		return "redirect:/demo/login/login.html";
		
	}
	
}
