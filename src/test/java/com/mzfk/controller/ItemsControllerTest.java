package com.mzfk.controller;

import com.alibaba.fastjson.JSON;
import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Items;
import com.mzfk.utils.RandomString;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author sz
 * @Title: ItemsControllerTest
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/129:46
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 根据test方法名按照字典顺序升序排序
public class ItemsControllerTest {


    private static Logger log = LoggerFactory.getLogger(ItemsControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ItemsController itemsController;

    private static String id;

    @Before
    public void setup(){
        boolean singleRunner = true;
        if (singleRunner) {
            // 项目拦截器无效
            this.mockMvc = MockMvcBuilders.standaloneSetup(itemsController).build();
        } else {
            //MockMvcBuilders使用上下文构建MockMvc对象（项目拦截器有效）
            this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }
    }



    @Test
    public void findItemsList() throws Exception  {
        MvcResult result = mockMvc.perform(get("/config/items")) //执行请求
                .andExpect(status().isOk())//验证状态码
                .andDo(print())//输出MvcResult到控制台
                .andReturn()
                ;
        String resultString = result.getResponse().getContentAsString();
        System.out.println(resultString);
        Object succesResponse = JSON.parse(resultString);
        Map map = (Map)succesResponse;
        String msg = map.get("msg").toString();
        if ("200.OK".equals(msg)) {
            System.out.print("==正常情况==");
            Assert.assertEquals(ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_048,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }



    @Test
    public void addItemsOk() throws Exception  {
        Items info = new Items();
        info.setTitle(RandomString.getRandomChar(5));
        info.setDescription("uuid测试");
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/config/items")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn()
                ;
        String resultString = result.getResponse().getContentAsString();
        System.out.println(resultString);
        Object succesResponse = JSON.parse(resultString);
        Map map = (Map)succesResponse;

        Map maps = (Map)map.get("data");
        id = maps.get("id").toString();

        String msg = map.get("msg").toString();
        if ("200.OK".equals(msg)) {
            System.out.print("==正常情况==");
            Assert.assertEquals(ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else if ("当前需要添加的项目已存在".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_008,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_049,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        info = new Items();
        info.setTitle("uuid测试");
        info.setDescription("uuid测试");
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/config/items")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn()
                ;
        resultString = result.getResponse().getContentAsString();
        System.out.println(resultString);
        succesResponse = JSON.parse(resultString);
        map = (Map)succesResponse;
        msg = map.get("msg").toString();
        if ("200.OK".equals(msg)) {
            System.out.print("==正常情况==");
            Assert.assertEquals(ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else if ("当前需要添加的项目已存在".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_008,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_049,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }


    @Test
    public void updateItems() throws Exception  {
        Items info = new Items();
        info.setId(id);
        info.setTitle("uuid测试1");
        info.setDescription("测试s111");
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/items/"+id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn()
        ;
        String resultString = result.getResponse().getContentAsString();
        System.out.println(resultString);
        Object succesResponse = JSON.parse(resultString);
        Map map = (Map)succesResponse;
        String msg = map.get("msg").toString();
        if ("200.OK".equals(msg)) {
            System.out.print("==正常情况==");
            Assert.assertEquals(ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_050,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        info = new Items();
        info.setId("1qa");
        info.setTitle("uuid测试11111");
        info.setDescription("测试的juntis111");
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/items/1qa")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn()
                ;
        resultString = result.getResponse().getContentAsString();
        System.out.println(resultString);
        succesResponse = JSON.parse(resultString);
        map = (Map)succesResponse;
        msg = map.get("msg").toString();
        if ("200.OK".equals(msg)) {
            System.out.print("==正常情况==");
            Assert.assertEquals(ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_050,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

    }

    @Test
    public void zdelItems() throws Exception  {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/items/40289f5d673e70e301673e7100480000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn()
        ;
        String resultString = result.getResponse().getContentAsString();
        System.out.println(resultString);
        Object succesResponse = JSON.parse(resultString);
        Map map = (Map)succesResponse;
        String msg = map.get("msg").toString();
        if ("200.OK".equals(msg)) {
            System.out.print("==正常情况==");
            Assert.assertEquals(ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else {
            System.out.print("==异常情况=删除项目失败=");
            Assert.assertEquals(ResultMsg.C_051,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/items/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn()
                ;
        resultString = result.getResponse().getContentAsString();
        System.out.println(resultString);
        succesResponse = JSON.parse(resultString);
        map = (Map)succesResponse;
        msg = map.get("msg").toString();
        if ("200.OK".equals(msg)) {
            System.out.print("==正常情况==");
            Assert.assertEquals(ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else {
            System.out.print("==异常情况=删除项目失败=");
            Assert.assertEquals(ResultMsg.C_051,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }




}