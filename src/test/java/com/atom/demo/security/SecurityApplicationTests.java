
package com.atom.demo.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//spring security用户
@WithMockUser(username = "java", password = "123")
class SecurityApplicationTests {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;

	//@Before junit4
	@BeforeEach  //junit5
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		System.out.println("===>"+this.mockMvc);
	}

	@Test
	void testHello() throws Exception {
		System.out.println(this.mockMvc);
		this.mockMvc.perform(request(HttpMethod.GET, "/hello").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

}
