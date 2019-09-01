package top.zero3737.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringJDBCService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void springJDBCFindData() {
		
		jdbcTemplate.update("insert ssm_user SET username = 'Ò¦Ã÷', password = '25', id=233;");
		
	}
	
}
