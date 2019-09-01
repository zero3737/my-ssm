package top.zero3737.service;

import org.junit.Test;

public class JedisServiceTest {

	@Test
	public void testFindDataWithPool() {
		
		new JedisService().findDataWithPool();
		
	}

}
