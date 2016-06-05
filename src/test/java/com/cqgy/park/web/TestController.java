/**
 * @作者 admin
 * @时间 2016年5月12日 下午3:48:50
 * @类名 TestController.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月12日 下午3:48:50
 *   修改描述
 */
package com.cqgy.park.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersonController.class)
@WebAppConfiguration
public class TestController {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before(value = "testSort")
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testSort() throws Exception{
		String uri = "/sort";
		RequestBuilder rb = MockMvcRequestBuilders.get(uri);
		MvcResult result = mockMvc.perform(rb).andReturn();
		
		String returnContent = result.getResponse().getContentAsString();
		System.out.println(returnContent);
		
	}

}
