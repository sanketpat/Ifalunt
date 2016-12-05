package com.iflaunt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.iflaunt.backend.IflauntApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { IflauntApplication.class, WebApplicationContext.class })
@ActiveProfiles("test")
@WebAppConfiguration
public class TestControllerService {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testUserControllerService() throws Exception {

		mockMvc.perform(get("/user/")).andExpect(status().isOk())
				.andExpect(content().string("User Service is Running."));

	}

	@Test
	public void testRelationshipControllerService() throws Exception {
		mockMvc.perform(get("/relationship/test/")).andExpect(status().isOk())
				.andExpect(content().string("Relationship Service is Running."));
	}

	@Test
	public void testPhotoController() throws Exception {
		mockMvc.perform(get("/photo/")).andExpect(status().isOk())
		.andExpect(content().string("Photo Service is Running."));

	}

}