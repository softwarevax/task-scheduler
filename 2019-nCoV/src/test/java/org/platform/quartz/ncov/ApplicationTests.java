package org.platform.quartz.ncov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.platform.quartz.ncov.web.dao.HistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
public class ApplicationTests {

	@Autowired
	private HistoryDao historyDao;


	@Before
	public void testBefore(){
		System.out.println("before");
	}

	@After
	public void testAfter(){
		System.out.println("after");
	}

	@Test
	public void contextLoads() {
		List<String> dates = historyDao.getDates();
		System.out.println(dates);
		//System.out.println(HttpUtils.sendGet("https://tianqiapi.com/api?version=epidemic&appid=23035354&appsecret=8YvlPNrz"));
	}

}
