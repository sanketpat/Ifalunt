package com.iflaunt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.iflaunt.backend.IflauntApplication;
import com.iflaunt.backend.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {IflauntApplication.class, WebApplicationContext.class})
@WebAppConfiguration
public class TestUserController {
	@Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    
    private User user;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        user= new User();
    	user.setUserName("spati56l265@uncc.edu");
    	user.setPassword("125634");
    	Gson gson = new Gson();
    	String json=gson.toJson(user);
    	
    	mockMvc.perform(post("/user/register").header("Content-Type", "application/json").
    			 content(json)).andExpect(status().isOk());   
    }
    @Test
	public void testLoginUserController() throws Exception {
    	
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
       		File f = new File("./src/test/java/com/iflaunt/controller/test.jpg");
            System.out.println(f.isFile()+"  "+f.getName()+f.exists());
            FileInputStream fi1 = new FileInputStream(f);
            MockMultipartFile fstmp = new MockMultipartFile("upload", f.getName(), "multipart/form-data",fi1);
            MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            mockMvc.perform(MockMvcRequestBuilders.fileUpload("/user/upload")                
                    .file(fstmp)
                    .param("username",user.getUserName()))               
                    .andExpect(status().isOk());
       }
}