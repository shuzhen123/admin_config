package com.mzfk.controller;

import com.alibaba.fastjson.JSON;
import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Countries;
import com.mzfk.pojo.CountriesInfoParam;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author sz
 * @Title: CountriesControllerTest
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/911:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 根据test方法名按照字典顺序升序排序
public class CountriesControllerTest {

    @Autowired
    CountriesController countriesController;


    private static Logger log = LoggerFactory.getLogger(CountriesControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


    private static String ios;

    @Before
    public void setup(){
        boolean singleRunner = true;
        if (singleRunner) {
            // 项目拦截器无效
            this.mockMvc = MockMvcBuilders.standaloneSetup(countriesController).build();
        } else {
            //MockMvcBuilders使用上下文构建MockMvc对象（项目拦截器有效）
            this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }
    }


    @Test
    public void findCountriesList() throws Exception {
        CountriesInfoParam Params = new CountriesInfoParam();
        MvcResult result = mockMvc.perform(get("/config/countries",Params)) //执行请求
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
            System.out.print("==异常情况==获取国家列表失败");
            Assert.assertEquals(ResultMsg.C_025,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }


    @Test
    public void addCountries() throws Exception {
        Countries info = new Countries();

        ios = RandomString.getRandomChar(2);

        info.setIso(ios);
        info.setPhoneCode(8);
        info.setNumcode(888);
        info.setNiceName("junitTest");
        info.setName("test01");
        info.setIso3("ASD");
        info.setCurrencyCode("CNY");
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/config/countries")
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
            System.err.print("==正常情况==");
            Assert.assertEquals(map.get("msg").toString(),"200.OK");
            Assert.assertEquals(map.get("success").toString(),"true");
        } else {
            System.out.print("==异常情况==添加国家信息失败");
            Assert.assertEquals(ResultMsg.C_026,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        }


        info = new Countries();
        info.setPhoneCode(8);
        info.setNumcode(888);
        info.setNiceName("junitTest");
        info.setName("test01");
        info.setIso3("ASD");
        info.setIso("ZR");
        info.setCurrencyCode("ALL");
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/config/countries")
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
            Assert.assertEquals(map.get("msg").toString(),"200.OK");
            Assert.assertEquals(map.get("success").toString(),"true");
        } else {
            System.out.print("==异常情况==添加国家信息失败");
            Assert.assertEquals(ResultMsg.C_026,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        }
    }


    @Test
    public void updateCountries() throws Exception {
        Countries info = new Countries();
        info.setPhoneCode(8);
        info.setNumcode(888);
        info.setNiceName("junitTest");
        info.setName("test01");
        info.setIso3("ASD");
        info.setIso("ZR");
        info.setCurrencyCode("CNY");
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/countries/7")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
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
            Assert.assertEquals( ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else {
            System.err.print("==异常情况==更新国家信息失败");
            Assert.assertEquals(ResultMsg.C_027,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }



        info = new Countries();
        info.setIso(ios);
        info.setPhoneCode(8);
        info.setNumcode(888);
        info.setNiceName("ios测试");
        info.setName("tios测试2");
        info.setIso3("ASD");
        info.setCurrencyCode("CNY");
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/countries/"+ios)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
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
            Assert.assertEquals( ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else {
            System.err.print("==异常情况==更新国家信息失败");
            Assert.assertEquals(ResultMsg.C_027,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }


    @Test
    public void zdelCountries() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/countries/1qa")) ///countries/delCountries/{id}
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
            Assert.assertEquals( ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else {
            System.err.print("==异常情况==删除国家信息失败");
            Assert.assertEquals(ResultMsg.C_028,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/countries/"+ios)) ///countries/delCountries/{id}
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
            Assert.assertEquals( ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else {
            System.err.print("==异常情况==删除国家信息失败");
            Assert.assertEquals(ResultMsg.C_028,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

    }




}