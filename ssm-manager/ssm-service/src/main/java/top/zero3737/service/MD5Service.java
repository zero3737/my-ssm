package top.zero3737.service;

import org.springframework.util.DigestUtils;

public class MD5Service {
	
	public void md5() {
		
		String md5DigestAsHex = DigestUtils.md5DigestAsHex("123".getBytes());
		
		System.out.println(md5DigestAsHex);
		
	}
	
}
