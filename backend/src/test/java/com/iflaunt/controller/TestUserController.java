package com.iflaunt.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.iflaunt.backend.IflauntApplication;
import com.iflaunt.backend.dao.UserDao;
import com.iflaunt.backend.model.User;
import com.iflaunt.backend.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {IflauntApplication.class, WebApplicationContext.class})
@WebAppConfiguration
public class TestUserController {
	@Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    
    private User user;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }
    @Test
	public void testLoginUserController() throws Exception {
    	
    	user= new User();
    	user.setUserName("spati56l265@uncc.edu");
    	user.setPassword("125634");
    	
    	Gson gson = new Gson();
    	String json=gson.toJson(user);
    	
    	mockMvc.perform(post("/user/register").header("Content-Type", "application/json").
    			 content(json)).andExpect(status().isOk());   
    
    	
    	mockMvc.perform(post("/user/login").header("Content-Type", "application/json").
   			 content("{\"username\":\"spati56l265@uncc.edu\",\"password\":\"125634\"}")).andExpect(status().isOk());   

    }
    
    @Test
   	public void testRegistrationUserController() throws Exception {
       	
       	user= new User();
       	user.setUserName("spati56l265@uncc.edu");
       	user.setPassword("125634");
       	
       	Gson gson = new Gson();
       	String json=gson.toJson(user);
       	
       	mockMvc.perform(post("/user/register").header("Content-Type", "application/json").
       			 content(json)).andExpect(status().isOk());
       }
    
    
    @Test
   	public void testUpdateUserController() throws Exception {
       	
       	user= new User();
       	user.setUserName("spati56l265@uncc.edu");
       	user.setPassword("125634");
       	
       	Gson gson = new Gson();
       	String json=gson.toJson(user);
       	
       	mockMvc.perform(post("/user/update").header("Content-Type", "application/json").
       			 content(json)).andExpect(status().isOk());
       }
    
    @Test
   	public void testImageUploadUserController() throws Exception {
       	
       	mockMvc.perform(post("/user/upload").header("", "").
       			 content("\"name\":\"abc")).andExpect(status().isOk());
       }
}