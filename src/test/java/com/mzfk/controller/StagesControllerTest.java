package com.mzfk.controller;

import com.alibaba.fastjson.JSON;
import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Stages;
import com.mzfk.pojo.StagesInfoParam;
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

import java.math.BigDecimal;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author sz
 * @Title: StagesControllerTest
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/1217:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 根据test方法名按照字典顺序升序排序
public class StagesControllerTest {

    @Autowired
    private StagesController stagesController;

    private static Logger log = LoggerFactory.getLogger(StagesControllerTest.class);

    private MockMvc mockMvc;

    private static String id;

    @Autowired
    private WebApplicationContext context;


    @Before
    public void setup(){
        boolean singleRunner = true;
        if (singleRunner) {
            // 项目拦截器无效
            this.mockMvc = MockMvcBuilders.standaloneSetup(stagesController).build();
        } else {
            //MockMvcBuilders使用上下文构建MockMvc对象（项目拦截器有效）
            this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }
    }


    @Test
    public void findStagesList() throws Exception  {
        StagesInfoParam Params = new StagesInfoParam();
        MvcResult result = mockMvc.perform(get("/config/stages",Params)) //执行请求
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
            Assert.assertEquals(ResultMsg.C_072,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

    }

    @Test
    public void addStages() throws Exception  {
        Stages info = new Stages();
        info.setPlanCode("aaaa");

        String random = RandomStringUtils.random(3, new char[]{'1', '2', '3', '4', '5', '6'});

        info.setLevel(Integer.valueOf(random));
        info.setActiveUsers(12);
        info.setMinProfit(121);
        info.setMaxProfit(2555);

        BigDecimal mm = new BigDecimal("1");
        info.setRate(mm);
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/config/stages")
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
        } else if ("存在同时满足planCode,level的数据，请修改".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_089,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_073,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        info = new Stages();
        info.setPlanCode("DEFAULT");
        info.setLevel(88);
        info.setActiveUsers(12);
        info.setMinProfit(121);
        info.setMaxProfit(2555);
        mm = new BigDecimal("1");
        info.setRate(mm);
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/config/stages")
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
        } else if ("存在同时满足planCode,level的数据，请修改".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_089,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_073,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

    }



    @Test
    public void updateStages() throws Exception  {
        Stages info = new Stages();
        info.setId(id);
        info.setPlanCode("ASASCCZ");

        String random = RandomStringUtils.random(3, new char[]{'1', '2', '3', '4', '5',});
        info.setLevel(Integer.valueOf(random));

        info.setActiveUsers(12);
        info.setMinProfit(888);
        info.setMaxProfit(2555);
        BigDecimal mm = new BigDecimal("1");
        info.setRate(mm);
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/stages/"+id)
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
        } else if ("存在同时满足planCode,level的数据，请修改".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_089,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_074,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }



        info = new Stages();
        info.setId("1qa");
        info.setPlanCode("AAAAAA");
        info.setLevel(8);
        info.setActiveUsers(12);
        info.setMinProfit(888);
        info.setMaxProfit(2555);
        mm = new BigDecimal("1");
        info.setRate(mm);
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/stages/1qa")
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
        } else if ("存在同时满足planCode,level的数据，请修改".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_089,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_074,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

    }

    @Test
    public void zdelStages() throws Exception  {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/stages/1qa"))
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
            Assert.assertEquals(ResultMsg.C_075,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/stages/"+id))
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
            Assert.assertEquals(ResultMsg.C_075,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }



}