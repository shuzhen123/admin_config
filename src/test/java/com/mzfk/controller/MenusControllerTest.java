package com.mzfk.controller;

import com.alibaba.fastjson.JSON;
import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Menus;
import com.mzfk.utils.RandomString;
import org.apache.commons.lang.RandomStringUtils;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author sz
 * @Title: MenusControllerTest
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/1210:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 根据test方法名按照字典顺序升序排序
public class MenusControllerTest {

    @Autowired
    private MenusController menusController;


    private static Logger log = LoggerFactory.getLogger(MenusControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private static String code;

    @Before
    public void setup(){
        boolean singleRunner = true;
        if (singleRunner) {
            // 项目拦截器无效
            this.mockMvc = MockMvcBuilders.standaloneSetup(menusController).build();
        } else {
            //MockMvcBuilders使用上下文构建MockMvc对象（项目拦截器有效）
            this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }
    }



    @Test
    public void fildAllMenus()  throws Exception  {
        MvcResult result = mockMvc.perform(get("/config/menus")) //执行请求
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
        }  else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_052,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }

    @Test
    public void addMenus() throws Exception  {
        Menus info = new Menus();

        code = RandomString.getRandomChar(8);

        info.setCode(code);

        String random = RandomStringUtils.random(2, new char[]{'1', '2', '3', '4', '5', '6'});
        info.setOrdernum(Integer.valueOf(random));

        info.setIsNew(5);
        info.setUri("SSSSSs");
        info.setStatus("publish");
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/config/menus")
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
        } else if ("当前需要添加的order（编码）已存在".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_016,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }else if ("当前需要添加的code（编码）已存在".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_012,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_053,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        info = new Menus();
        info.setCode(RandomString.getRandomChar(8));
        info.setOrdernum(1);
        info.setIsNew(5);
        info.setUri("SSSSSs");
        info.setStatus("publish");
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/config/menus")
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
        } else if ("当前需要添加的order（编码）已存在".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_016,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }else if ("当前需要添加的code（编码）已存在".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_012,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_053,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        info = new Menus();
        info.setCode(RandomString.getRandomChar(32));
        info.setOrdernum(182);
        info.setIsNew(5);
        info.setUri("SSSSSs");
        info.setStatus("publish");
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/config/menus")
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
        } else if ("当前需要添加的order（编码）已存在".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_016,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }else if ("当前需要添加的code（编码）已存在".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_012,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_053,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

    }


    @Test
    public void updateMenus() throws Exception  {
        Menus info = new Menus();
        info.setCode(code);
        String random = RandomStringUtils.random(3, new char[]{'1', '2', '3', '4', '5', '6'});
        info.setOrdernum(Integer.valueOf(random));
        info.setIsNew(7);
        info.setUri("da/s/d/a/d");
        info.setStatus("publish");
        info.setSort(3);
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/menus/"+code)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
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
            Assert.assertEquals(ResultMsg.C_054,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }



        info = new Menus();
        info.setCode(code);
        info.setOrdernum(98);
        info.setIsNew(7);
        info.setUri("da/s/d/a/d");
        info.setStatus("publish");
        info.setSort(3);
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/menus/1qa")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
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
            Assert.assertEquals(ResultMsg.C_054,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }


    @Test
    public void findMenuByCode()  throws Exception  {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/config/menus/"+code))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
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
            Assert.assertEquals(ResultMsg.C_052,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        result = mockMvc.perform(MockMvcRequestBuilders.get("/config/menus/ZZ88"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
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
            Assert.assertEquals(ResultMsg.C_052,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }



    @Test
    public void zdelMenuByCode() throws Exception  {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/menus/1qa"))
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
            Assert.assertEquals(ResultMsg.C_055,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }



        result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/menus/"+code))
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
            Assert.assertEquals(ResultMsg.C_055,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }



}