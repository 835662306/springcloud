package com.example.cloud;

import com.example.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Spring Boot构建RESTful API与单元测试
 * http://blog.didispace.com/springbootrestfulapi/
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudApplicationTests {

	private MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void testUserController() throws Exception {
		// 测试UserController
		RequestBuilder request = null;

		// 1、get查一下user列表，应该为空
//		request = get("/users/");
//		mvc.perform(request)
//				.andExpect(status().isOk())
//				.andExpect(content().string(equalTo("[]")));
//
//		// 2、post提交一个user
//		request = post("/users/")
//				.param("id", "1")
//				.param("name", "测试大师")
//				.param("age", "20");
//		mvc.perform(request)
//				.andExpect(content().string(equalTo("success")));

	}
}
