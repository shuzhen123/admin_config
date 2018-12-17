package com.mzfk.controller;

import com.alibaba.fastjson.JSON;
import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Clients;
import com.mzfk.pojo.ClientsIInfoParam;
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
 * @Title: ClientsControllerTest
 * @ProjectName 8Madmin_config
 * @Description: Clients
 * @date 2018/11/814:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 根据test方法名按照字典顺序升序排序
public class ClientsControllerTest {

    @Autowired
    ClientsController clientsController;

    private static String id;

    private static Logger log = LoggerFactory.getLogger(ClientsControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup(){
        boolean singleRunner = true;
        if (singleRunner) {
            // 项目拦截器无效
            this.mockMvc = MockMvcBuilders.standaloneSetup(clientsController).build();
        } else {
            //MockMvcBuilders使用上下文构建MockMvc对象（项目拦截器有效）
            this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        }
    }


    @Test
    public void findClientsList()  throws Exception  {
        ClientsIInfoParam Param = new ClientsIInfoParam();

        MvcResult result = mockMvc.perform(get("/config/clients",Param)) //执行请求
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
            System.out.print("==异常情况==获取客户列表失败");
            Assert.assertEquals(ResultMsg.C_021,map.get("msg").toString());//获取客户列表失败
            Assert.assertEquals("false",map.get("success").toString());
        }
    }


    @Test
    public void addClients() throws Exception  {
        Clients info = new Clients();
        info.setAppName(RandomString.getRandomChar(8));
        info.setAppToken("a00f6b4a72b4aea47ffe389d05ebc8da");
        info.setDisabled(0);
        String jsonContent = JSON.toJSONString(info);
        MvcResult result  = mockMvc.perform(MockMvcRequestBuilders.post("/config/clients")
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

        System.err.println(id);
        String msg = map.get("msg").toString();

        if ("200.OK".equals(msg)) {
            System.out.print("==正常情况==");
            Assert.assertEquals(ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"true");
        } else if ("app名字重复".equals(msg)) {
            System.out.print("==异常情况==app名字重复");
            Assert.assertEquals(ResultMsg.C_004,map.get("msg").toString());//app名字重复
            Assert.assertEquals(map.get("success").toString(),"false");
        } else if ("apptoken重复".equals(msg)) {
            System.out.print("==异常情况==apptoken重复");
            Assert.assertEquals(ResultMsg.C_005,map.get("msg").toString());//apptoken重复
            Assert.assertEquals(map.get("success").toString(),"false");
        } else {
            System.out.print("==异常情况==添加客户信息失败");
            Assert.assertEquals(ResultMsg.C_022,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        }



        info = new Clients();
        info.setAppName(RandomString.getRandomChar(8));
        info.setAppToken("a00f6b4a72b4aea47ffe389d05ebc8da");
        info.setDisabled(0);
        jsonContent = JSON.toJSONString(info);
        result  = mockMvc.perform(MockMvcRequestBuilders.post("/config/clients")
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
        } else if ("app名字重复".equals(msg)) {
            System.out.print("==异常情况==app名字重复");
            Assert.assertEquals(ResultMsg.C_004,map.get("msg").toString());//app名字重复
            Assert.assertEquals(map.get("success").toString(),"false");
        } else if ("apptoken重复".equals(msg)) {
            System.out.print("==异常情况==apptoken重复");
            Assert.assertEquals(ResultMsg.C_005,map.get("msg").toString());//apptoken重复
            Assert.assertEquals(map.get("success").toString(),"false");
        } else {
            System.out.print("==异常情况==添加客户信息失败");
            Assert.assertEquals(ResultMsg.C_022,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        }



        info = new Clients();
        info.setAppName(RandomString.getRandomChar(8));
        info.setAppToken("a00f6b4a72b-4aea47ffe3ss89d05ebc8da");
        info.setDisabled(0);
        jsonContent = JSON.toJSONString(info);
        result  = mockMvc.perform(MockMvcRequestBuilders.post("/config/clients")
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
        } else if ("app名字重复".equals(msg)) {
            System.out.print("==异常情况==app名字重复");
            Assert.assertEquals(ResultMsg.C_004,map.get("msg").toString());//app名字重复
            Assert.assertEquals(map.get("success").toString(),"false");
        } else if ("apptoken重复".equals(msg)) {
            System.out.print("==异常情况==apptoken重复");
            Assert.assertEquals(ResultMsg.C_005,map.get("msg").toString());//apptoken重复
            Assert.assertEquals(map.get("success").toString(),"false");
        } else {
            System.out.print("==异常情况==添加客户信息失败");
            Assert.assertEquals(ResultMsg.C_022,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        }


    }



    @Test
    public void updateClients()  throws Exception {
        Clients info = new Clients();
        info.setId(id);
        info.setAppName("madmin");
        info.setAppToken("a00f6b4a72b4aea46ffe389d05ebc8ss");
        info.setDisabled(0);
        String jsonContent = JSON.toJSONString(info);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/clients/"+id)
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
        } else if ("app名字重复".equals(msg)) {
            System.out.print("==异常情况==app名字重复");
            Assert.assertEquals(ResultMsg.C_004,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        } else if ("apptoken重复".equals(msg)) {
            System.out.print("==异常情况==apptoken重复");
            Assert.assertEquals(ResultMsg.C_005,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        } else {
            System.out.print("==异常情况==更新客户信息失败");
            Assert.assertEquals(ResultMsg.C_023,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        }

        info = new Clients();
        info.setId("18");
        info.setAppName(RandomString.getRandomChar(8));
        info.setAppToken("a00f6b4a72b4aea47ffe389d05ebc8da");
        info.setDisabled(0);
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/clients/18")
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
        } else if ("app名字重复".equals(msg)) {
            System.out.print("==异常情况==app名字重复");
            Assert.assertEquals(ResultMsg.C_004,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        } else if ("appToken重复".equals(msg)) {
            System.out.print("==异常情况==apptoken重复");
            Assert.assertEquals(ResultMsg.C_005,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        } else {
            System.out.print("==异常情况==更新客户信息失败");
            Assert.assertEquals(ResultMsg.C_023,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        }


        info = new Clients();
        info.setId("18");
        info.setAppName(RandomString.getRandomChar(8));
        info.setAppToken(RandomString.getRandomChar(20));
        info.setDisabled(0);
        jsonContent = JSON.toJSONString(info);
        result = mockMvc.perform(MockMvcRequestBuilders.patch("/config/clients/"+id)
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
        } else if ("app名字重复".equals(msg)) {
            System.out.print("==异常情况==app名字重复");
            Assert.assertEquals(ResultMsg.C_004,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        } else if ("appToken重复".equals(msg)) {
            System.out.print("==异常情况==apptoken重复");
            Assert.assertEquals(ResultMsg.C_005,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        } else {
            System.out.print("==异常情况==更新客户信息失败");
            Assert.assertEquals(ResultMsg.C_023,map.get("msg").toString());
            Assert.assertEquals(map.get("success").toString(),"false");
        }

    }






    @Test
    public void zdelClixentserr() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/clients/1qa"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().string("Server"))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                ;
        String resultString = result.getResponse().getContentAsString();
        Object succesResponse = JSON.parse(resultString);
        Map map = (Map)succesResponse;
        String msg = map.get("msg").toString();
        if ("200.OK".equals(msg)) {
            System.out.print("==正常情况==");
            Assert.assertEquals(ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else {
            System.out.print("==异常情况==删除客户信息失败");
            Assert.assertEquals(ResultMsg.C_024,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }



        result = mockMvc.perform(MockMvcRequestBuilders.delete("/config/clients/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().string("Server"))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                ;
        resultString = result.getResponse().getContentAsString();
        succesResponse = JSON.parse(resultString);
        map = (Map)succesResponse;
        msg = map.get("msg").toString();
        if ("200.OK".equals(msg)) {
            System.out.print("==正常情况==");
            Assert.assertEquals(ResultMsg.C_001,map.get("msg").toString());
            Assert.assertEquals("true",map.get("success").toString());
        } else {
            System.out.print("==异常情况==删除客户信息失败");
            Assert.assertEquals(ResultMsg.C_024,map.get("msg").toString());
            Assert.assertEquals("false",map.get("success").toString());
        }
        
    }





}