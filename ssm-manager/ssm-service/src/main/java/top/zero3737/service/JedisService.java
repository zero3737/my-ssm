package top.zero3737.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisService {

	JedisPool jedisPool;
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
	
	public void findData() {
		
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		jedis.set("key1", "haha");
		String string = jedis.get("key1");
		jedis.close();
		System.out.println(string);
		
	}
	
	public void findDataWithPool() {
		
		JedisService bean = (JedisService)new ClassPathXmlApplicationContext("applicationContext.xml").getBean("jedis");
		JedisPool jedisPool2 = bean.getJedisPool();
		Jedis resource = jedisPool2.getResource();
		String string = resource.get("key1");
		System.out.println(string);
		
	}
	
}
