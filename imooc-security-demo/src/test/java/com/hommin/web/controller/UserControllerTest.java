package com.hommin.web.controller;/**
 * Created by Hommin on 2018/3/1.
 */

import com.hommin.web.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author Hommin
 * @ClassName: UserControllerTest
 * @Description: 用户ctrl测试
 * @data 2018年03月01日 下午2:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUpt() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user").contentType(MediaType.APPLICATION_JSON_UTF8).param("username", "jojo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }


    @Test
    public void whenCreatSuccess() throws Exception {
        String content = "{\"name\":\"Hommin\",\"pass\":\"123\",\"brithday\":"+new Date().getTime()+"}";
//        content = "{\"id\":\"1\", \"name\":\"tom\",\"pass\":\"123\",\"birthday\":"+new Date().getTime()+"}";
        String result = mvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andReturn().getResponse().getContentAsString();
//        System.out.println(result);
    }

    @Test
    public void whenUploadSuccess() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.fileUpload("/file")
                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "heloo".getBytes("utf-8"))))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenTestSuccess() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test").contentType(MediaType.APPLICATION_JSON).param("name", "hommin"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
