package com.mzfk.controller;

import com.alibaba.fastjson.JSON;
import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Domains;
import com.mzfk.pojo.DomainsInfoParam;
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
 * @Title: DomainsControllerTest
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/915:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 根据test方法名按照字典顺序升序排序
public class DomainsControllerTest {

    @Autowired
    DomainsController domainsController;

    private static Logger log = LoggerFactory.getLogger(DomainsControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private static String domain;


    @Before
    public void setup(){
        boolean singleRunner = true;
        if (singleRunner) {
            // 项目拦截器无效
            this.mockMvc = MockMvcBuilders.standaloneSetup(domainsController).build();
        } else {
            //MockMvcBuilders使用上下文构建MockMvc对象（项目拦截器有效）
            this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }
    }

    @Test
    public void findDomainsList()  throws Exception  {
        DomainsInfoParam Params = new DomainsInfoParam();
        MvcResult result = mockMvc.perform(get("/config/domains",Params)) //执行请求
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
            System.out.print("==异常情况==获取域名列表失败");
            Assert.assertEquals(ResultMsg.C_038,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }

    @Test
    public void addDomains() throws Exception  {
        Domains info = new Domains();

        domain = RandomString.getRandomChar(8);
        info.setDomain(domain);
        info.setUserId("asdasvd");
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/config/domains")
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
        } else if ("系统域名重复 主键id不可重复".equals(msg)) {
            System.out.print("==异常情况==系统域名重复 主键id不可重复");
            Assert.assertEquals(ResultMsg.C_006,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        } else {
            System.out.print("==异常情况==添加域名失败");
            Assert.assertEquals(ResultMsg.C_039,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        }



        info = new Domains();
        info.setDomain("1FPMUON3E0Z5MNGUVPNTC7048SJ3KZE1");
        info.setUserId(RandomString.getRandomChar(32));
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/config/domains")
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
        } else if ("系统域名重复 主键id不可重复".equals(msg)) {
            System.out.print("==异常情况==系统域名重复 主键id不可重复");
            Assert.assertEquals(ResultMsg.C_006,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        } else {
            System.out.print("==异常情况==添加域名失败");
            Assert.assertEquals(ResultMsg.C_039,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        }



        info = new Domains();
        info.setDomain("asd");
        info.setUserId("asd");
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/config/domains")
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
        } else if ("系统域名重复 主键id不可重复".equals(msg)) {
            System.out.print("==异常情况==系统域名重复 主键id不可重复");
            Assert.assertEquals(ResultMsg.C_006,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        } else {
            System.out.print("==异常情况==添加域名失败");
            Assert.assertEquals(ResultMsg.C_039,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        }
    }




    @Test
    public void updateDomains()  throws Exception {
        Domains info = new Domains();
        info.setDomain("asdasdasd1");
        info.setUserId("asdasd");
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/domains/asdasdasd1")
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
            System.out.print("==异常情况==更新域名失败");
            Assert.assertEquals(ResultMsg.C_040,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        info = new Domains();
        info.setDomain(domain);
        info.setUserId(RandomString.getRandomChar(32));
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/domains/"+domain)
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
            System.out.print("==异常情况==更新域名失败");
            Assert.assertEquals(ResultMsg.C_040,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }





    @Test
    public void zdelDomains() throws Exception  {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/domains/1qa1"))
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
            System.err.print("==异常情况==删除域名失败");
            Assert.assertEquals(ResultMsg.C_041,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

        result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/domains/"+domain))
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
            System.err.print("==异常情况==删除域名失败");
            Assert.assertEquals(ResultMsg.C_041,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }



}