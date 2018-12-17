package com.mzfk.controller;

import com.alibaba.fastjson.JSON;
import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.CurrenciesInfoParam;
import com.mzfk.entity.Currencies;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author sz
 * @Title: CurrenciesControllerTest
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/914:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 根据test方法名按照字典顺序升序排序
public class CurrenciesControllerTest {

    @Autowired
    CurrenciesController urrenciesControllerc;

    private static Logger log = LoggerFactory.getLogger(CurrenciesControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private static String code;

    @Before
    public void setup(){
        boolean singleRunner = true;
        if (singleRunner) {
            // 项目拦截器无效
            this.mockMvc = MockMvcBuilders.standaloneSetup(urrenciesControllerc).build();
        } else {
            //MockMvcBuilders使用上下文构建MockMvc对象（项目拦截器有效）
            this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }
    }


    @Test
    public void fildCurrencies() throws Exception  {
        CurrenciesInfoParam params = new CurrenciesInfoParam();
        MvcResult result = mockMvc.perform(get("/config/currencies",params)) //执行请求
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
            System.out.print("==异常情况==获取货币列表失败");
            Assert.assertEquals(ResultMsg.C_029,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

    }




    @Test
    public void addCurrencies() throws Exception  {
        Currencies info = new Currencies();

        code = RandomString.getRandomChar(4);

        info.setCode(code);
        info.setName("Afghani");
        info.setSupported(6);
        info.setSymbol("DT ");
        info.setSymbolAfter(8);
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/config/currencies")
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
            Assert.assertEquals(map.get("success").toString(),"true");
        } else if ("当前需要添加的code（编码）已存在".equals(msg)) {
            System.err.print("==当前需要添加的code（编码）已存在==");
            Assert.assertEquals(ResultMsg.C_012,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else  {
            System.err.print("==异常情况==添加货币信息失败");
            Assert.assertEquals(ResultMsg.C_030,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        info = new Currencies();
        info.setCode(RandomString.getRandomChar(4));
        info.setName("测试id");
        info.setSupported(6);
        info.setSymbol("DT ");
        info.setSymbolAfter(8);
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/config/currencies")
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
            Assert.assertEquals(map.get("success").toString(),"true");
        } else if ("当前需要添加的code（编码）已存在".equals(msg)) {
            System.err.print("==当前需要添加的code（编码）已存在==");
            Assert.assertEquals(ResultMsg.C_012,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else  {
            System.err.print("==异常情况==添加货币信息失败");
            Assert.assertEquals(ResultMsg.C_030,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        info = new Currencies();
        info.setCode("sda");
        info.setName("测试id");
        info.setSupported(6);
        info.setSymbol("DT");
        info.setSymbolAfter(8);
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/config/currencies")
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
            Assert.assertEquals(map.get("success").toString(),"true");
        } else if ("当前需要添加的code（编码）已存在".equals(msg)) {
            System.err.print("==当前需要添加的code（编码）已存在==");
            Assert.assertEquals(ResultMsg.C_012,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else  {
            System.err.print("==异常情况==添加货币信息失败");
            Assert.assertEquals(ResultMsg.C_030,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


    }


    @Test
    public void getCurrencies() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/config/currencies/"+code)  // {id} 主键id
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //.content("STD")  // {id} 主键id
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().string("Server"))
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
            System.err.print("==异常情况==获取货币信息失败");
            Assert.assertEquals(ResultMsg.C_033,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        result = mockMvc.perform(MockMvcRequestBuilders.get("/config/currencies/1qa")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //.content("STD")  // {id} 主键id
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().string("Server"))
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
            System.err.print("==异常情况==获取货币信息失败");
            Assert.assertEquals(ResultMsg.C_033,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }


    @Test
    public void updateCurrencies()throws Exception  {
        Currencies info = new Currencies();
        info.setCode(code);
        info.setName("测试uuid");
        info.setSupported(6);
        info.setSymbol("DT");
        info.setSymbolAfter(8);
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/currencies/"+code)
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
            Assert.assertEquals(map.get("success").toString(),"true");
        } else {
            System.err.print("==异常情况==更新货币信息失败");
            Assert.assertEquals(ResultMsg.C_031,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        info = new Currencies();
        info.setCode("ALL");
        info.setName("Afghsani");
        info.setSupported(6);
        info.setSymbol("DT");
        info.setSymbolAfter(8);
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/currencies/ALL")
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
            Assert.assertEquals(map.get("success").toString(),"true");
        } else {
            System.err.print("==异常情况==更新货币信息失败");
            Assert.assertEquals(ResultMsg.C_031,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }


    @Test
    public void zdelCurrencies() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/currencies/1qa"))    // {id} 主键id
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().string("Server"))
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
            Assert.assertEquals(map.get("success").toString(),"true");
        } else {
            System.err.print("==异常情况==删除货币信息失败");
            Assert.assertEquals(ResultMsg.C_032,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

        result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/currencies/"+code))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().string("Server"))
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
            Assert.assertEquals(map.get("success").toString(),"true");
        } else {
            System.err.print("==异常情况==删除货币信息失败");
            Assert.assertEquals(ResultMsg.C_032,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }




}