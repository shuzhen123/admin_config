package com.mzfk.controller;

import com.alibaba.fastjson.JSON;
import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.FinancialGroups;
import com.mzfk.pojo.FinancialGroupsInfoParam;
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

import java.math.BigInteger;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author sz
 * @Title: FinancialGroupsControllerTest
 * @ProjectName 8Madmin_config
 * @Description:
 * @date 2018/11/916:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 根据test方法名按照字典顺序升序排序
public class FinancialGroupsControllerTest {

    @Autowired
    FinancialGroupsController financialGroupsController;

    private static Logger log = LoggerFactory.getLogger(FinancialGroupsControllerTest.class);

    private MockMvc mockMvc;

    private static String id;

    @Autowired
    private WebApplicationContext context;


    @Before
    public void setup(){
        boolean singleRunner = true;
        if (singleRunner) {
            // 项目拦截器无效
            this.mockMvc = MockMvcBuilders.standaloneSetup(financialGroupsController).build();
        } else {
            //MockMvcBuilders使用上下文构建MockMvc对象（项目拦截器有效）
            this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }
    }


    @Test
    public void findFinancialGroupsList()  throws Exception   {
        FinancialGroupsInfoParam Params = new FinancialGroupsInfoParam();

        MvcResult result = mockMvc.perform(get("/config/financialgroups",Params)) //执行请求
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
            Assert.assertEquals(ResultMsg.C_044,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }

    @Test
    public void addFinancialGroups()  throws Exception  {
        FinancialGroups info = new FinancialGroups();
        info.setName("uuid测试");
        info.setCurrencyCode("CNY");

        BigInteger num=new BigInteger("99");
        info.setMaxTotalBalance(num);
        info.setMaxDailyDeposit(num);
        info.setMaxDailyWithdraw(num);
        info.setMinWithdrawAmount(num);
        info.setMaxWithdrawAmount(num);
        info.setIsDefault(0);
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/config/financialgroups")
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
        } else if ("当前需要添加的财团已存在".equals(msg)) {
            System.err.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_007,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.err.print("==异常情况=添加失败=");
            Assert.assertEquals(ResultMsg.C_045,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }



        info = new FinancialGroups();
        info.setName(RandomString.getRandomChar(8));
        info.setCurrencyCode("CNY");
        num=new BigInteger("88");
        info.setMaxTotalBalance(num);
        info.setMaxDailyDeposit(num);
        info.setMaxDailyWithdraw(num);
        info.setMinWithdrawAmount(num);
        info.setMaxWithdrawAmount(num);
        info.setIsDefault(0);
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/config/financialgroups")
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
        } else if ("当前需要添加的财团已存在".equals(msg)) {
            System.err.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_007,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.err.print("==异常情况=添加失败=");
            Assert.assertEquals(ResultMsg.C_045,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

    }



    @Test
    public void updateFinancialGroups()  throws Exception  {
        FinancialGroups info = new FinancialGroups();
        info.setId(id);
        info.setName(RandomString.getRandomChar(5));
        info.setCurrencyCode("CNY");

        BigInteger num=new BigInteger("10");

        info.setMaxTotalBalance(num);
        info.setMaxDailyDeposit(num);
        info.setMaxDailyWithdraw(num);
        info.setMinWithdrawAmount(num);
        info.setMaxWithdrawAmount(num);
        info.setIsDefault(0);
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/financialgroups/"+id)
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
        } else if ("当前需要更新的财团名已存在".equals(msg)) {
            System.err.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_127,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.err.print("==异常情况=更新失败=");
            Assert.assertEquals(ResultMsg.C_046,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }


        info = new FinancialGroups();
        info.setName(RandomString.getRandomChar(8));
        info.setCurrencyCode("CNY");
        num=new BigInteger("10000");
        info.setMaxTotalBalance(num);
        info.setMaxDailyDeposit(num);
        info.setMaxDailyWithdraw(num);
        info.setMinWithdrawAmount(num);
        info.setMaxWithdrawAmount(num);
        info.setIsDefault(0);
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/financialgroups/ZZZZZZ")
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
        } else if ("当前需要更新的财团名已存在".equals(msg)) {
            System.err.print("==异常情况==");
            Assert.assertEquals(ResultMsg.C_127,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        } else {
            System.err.print("==异常情况=更新失败=");
            Assert.assertEquals(ResultMsg.C_046,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }


    @Test
    public void zdelFinancialGroups()  throws Exception  {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/financialgroups/1qa"))
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
        }  else {
            System.err.print("==异常情况=删除失败=");
            Assert.assertEquals(ResultMsg.C_047,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

        result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/financialgroups/"+id))
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
        }  else {
            System.err.print("==异常情况=删除失败=");
            Assert.assertEquals(ResultMsg.C_047,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }


    @Test
    public void findFinancialById() throws Exception   {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post
                ("/config/financialgroups/"+id))
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
        }  else {
            System.err.print("==异常情况=获取失败=");
            Assert.assertEquals(ResultMsg.C_044,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }

        result = mockMvc.perform(MockMvcRequestBuilders.post
                ("/config/financialgroups/QAQ"))
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
        }  else {
            System.err.print("==异常情况=获取失败=");
            Assert.assertEquals(ResultMsg.C_044,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
    }




}