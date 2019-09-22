package top.zero3737.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import top.zero3737.entity.SsmUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringJDBCService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void UpdataData() {
		
		int update = jdbcTemplate.update("insert ssm_user ( username, password ) VALUES (?, ?);", "zs", 123456);
		System.out.println(update);
		
	}
	
	@Test
	public void FindDataWithBean() {
		
		String sql = "select * from ssm_user where id = ?;";
		Object[] objects = {1};
		SsmUser queryForObject = jdbcTemplate.queryForObject(sql, objects, new BeanPropertyRowMapper<SsmUser>(SsmUser.class));
		System.out.println(queryForObject);
		
	}
	
	@Test
	public void FindDataWithMap() {
		
		String sql = "select * from ssm_user where id = ?;";
		Map<String, Object> queryForMap = jdbcTemplate.queryForMap(sql, 1);
		System.out.println(queryForMap);
		
	}

	@Test
	public void FindDataWithBeanForList() {
		
		String sql = "select * from ssm_user where id > ?;";
		Object[] object = {10};
		List<SsmUser> query = jdbcTemplate.query(sql, object, new BeanPropertyRowMapper<SsmUser>(SsmUser.class));
		for (SsmUser ssmUser : query) {
			
			System.out.println(ssmUser);
			
		}
		
	}
	
	@Test
	public void FindDataWithMapForList() {
		
		String sql = "select * from ssm_user where id > ?;";
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql, 10);
		for (Map<String, Object> User : queryForList) {
			
			System.out.println(User);
			
		}
		
	}
	
}
