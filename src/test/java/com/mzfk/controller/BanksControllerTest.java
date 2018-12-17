package com.mzfk.controller;

import com.alibaba.fastjson.JSON;
import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Banks;
import com.mzfk.pojo.BanksInfoParam;


import com.mzfk.utils.RandomString;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
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
 * @Title: BanksControllerTest
 * @ProjectName 8Madmin_config
 * @Description: Banks
 * @date 2018/10/2416:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 根据test方法名按照字典顺序升序排序
public class BanksControllerTest {


    @Autowired
    BanksController banksController;

    private static Logger log = LoggerFactory.getLogger(BanksControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private static String code;


    @Before
    public void setup(){
        boolean singleRunner = true;
        if (singleRunner) {
            // 项目拦截器无效
            this.mockMvc = MockMvcBuilders.standaloneSetup(banksController).build();
        } else {
            //MockMvcBuilders使用上下文构建MockMvc对象（项目拦截器有效）
            this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }
    }


    @Test
    public void findBanksList() throws Exception  {
        BanksInfoParam param = new BanksInfoParam();

        MvcResult result = mockMvc.perform(get("/config/banks",param)) //执行请求
                .andExpect(status().isOk())//验证状态码
                .andDo(print())//输出MvcResult到控制台
                .andReturn()
                ;

        String resultString = result.getResponse().getContentAsString();

        System.out.println(resultString);

        Object succesResponse = JSON.parse(resultString);
        Map map = (Map)succesResponse;
        Assert.assertEquals(ResultMsg.C_001,map.get("msg").toString());
        Assert.assertEquals("true",map.get("success").toString());
        Assert.assertNotNull(map.get("data").toString());
    }


    @Test
    public void addBanks() throws Exception {

        Banks info = new Banks();

        // 添加的code
        code = RandomString.getRandomChar(4);

        info.setCode(code);
        info.setName(RandomString.getRandomChar(4));
        info.setCurrencyCode("CNY");
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/config/banks")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
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
        } else if ("当前需要添加的code（编码）已存在".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_012,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_017,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        info = new Banks();
        info.setName(RandomString.getRandomChar(6));
        info.setCode("ssa");
        info.setCurrencyCode("CNY");
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/config/banks")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
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
        } else if ("当前需要添加的code（编码）已存在".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_012,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_017,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        info = new Banks();
        info.setName(RandomString.getRandomChar(6));
        info.setCode("ssa");
        info.setCurrencyCode("CNssY");
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/config/banks")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
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
        } else if ("当前需要添加的code（编码）已存在".equals(msg)) {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_012,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.out.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_017,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


    }



    @Test
    public void updateBanks() throws Exception {
        Banks banks = new Banks();
        banks.setName(RandomString.getRandomChar(8));
        banks.setCode(code);
        banks.setCurrencyCode("CNY");

        String jsonContent = JSON.toJSONString(banks);
        MvcResult result =mockMvc.perform(MockMvcRequestBuilders.patch("/config/banks/"+code)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
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
            Assert.assertEquals(ResultMsg.C_018,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        banks = new Banks();
        banks.setName(RandomString.getRandomChar(8));
        banks.setCode(code);
        banks.setCurrencyCode("CNsssY");

        jsonContent = JSON.toJSONString(banks);
        result =mockMvc.perform(MockMvcRequestBuilders.patch("/config/banks/"+code)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
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
            Assert.assertEquals(ResultMsg.C_018,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }


    @Test
    public void findBankByCode()throws Exception {

        MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/config/banks/"+code))
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
            Assert.assertEquals(ResultMsg.C_020,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

        result =mockMvc.perform(MockMvcRequestBuilders.get("/config/banks/1qa"))
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
            Assert.assertEquals(ResultMsg.C_020,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

    }


    @Test
    public void zdelBankserr()throws Exception {
        MvcResult result =mockMvc.perform(MockMvcRequestBuilders.delete("/config/banks/1qa"))
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
            Assert.assertEquals(ResultMsg.C_019,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

        result =mockMvc.perform(MockMvcRequestBuilders.delete("/config/banks/"+code))
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
            Assert.assertEquals(ResultMsg.C_019,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }

}
















