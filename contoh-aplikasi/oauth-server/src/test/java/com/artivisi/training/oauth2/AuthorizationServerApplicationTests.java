package com.artivisi.training.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthorizationServerApplication.class)
@WebAppConfiguration
public class AuthorizationServerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
