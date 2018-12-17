package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Clients;
import com.mzfk.pojo.ClientsIInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.ClientsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author sz
 * @Title: ClientsController
 * @ProjectName 8Madmin_config
 * @Description: 客户相关controller
 * @date 2018/10/2216:59
 */
@Controller
@RequestMapping("/config")
@Api(value = "顾客Controller",tags = {"顾客Controller"})
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    private Logger log = LoggerFactory.getLogger(ClientsController.class);

    /**
     * 获取客户列表
     * @return AjaxResult
     */
    @ApiOperation(value = "获取客户列表", httpMethod = "GET", notes = "获取客户列表", response = AjaxResult.class)
    @GetMapping("/clients")
    @ResponseBody
    public AjaxResult findClientsList (@ApiParam(name="分页参数",value="Param") ClientsIInfoParam Param) {
        AjaxResult result = new AjaxResult();

        try {
            result = clientsService.findSpotsList(Param);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取客户列表失败
            result.setMsg(ResultMsg.C_021);
        }

        return result;
    }



    /**
     *  添加客户
     * @param clients
     * @return
     */
    @ApiOperation(value = "添加客户", httpMethod = "POST", notes = "添加客户", response = AjaxResult.class)
    @PostMapping("/clients")
    @ResponseBody
    public AjaxResult addClients (
            @ApiParam(name="客户实体",value="clients",required = true) @RequestBody Clients clients
    ) {
        AjaxResult result = new AjaxResult();
        // 添加操作
        try {
            result = clientsService.addClientsModel(clients);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加客户失败
            result.setMsg(ResultMsg.C_022);
        }

        return result;
    }



    /**
     * 更新客户信息
     * @param id 主键id
     * @param clients 客户数据模型
     * @return AjaxResult
     */
    @ApiOperation(value = "更新客户信息", httpMethod = "PATCH", notes = "更新客户信息", response = AjaxResult.class)
    @PatchMapping("/clients/{id}")
    @ResponseBody
    public AjaxResult updateClients (
            @ApiParam(name="主键id",value="id",required = true) @PathVariable String id,
            @ApiParam(name="客户信息",value="clients",required = true)  @RequestBody Clients clients
    ) {
        AjaxResult result = new AjaxResult();
        try {
            // 更新客户数据操作
            result = clientsService.updateClients(clients,id);
        } catch (Exception e) {
            log.error(e.getMessage());
            //更新客户失败
            result.setMsg(ResultMsg.C_023);
        }
        return result;
    }


    /**
     * 删除客户信息
     * @param id 客户id
     * @return AjaxResult
     */
    @ApiOperation(value = "删除客户信息", httpMethod = "DELETE", notes = "删除客户信息", response = AjaxResult.class)
    @DeleteMapping("/clients/{id}")
    @ResponseBody
    public AjaxResult delClients (
            @ApiParam(name="主键id",value="id",required = true) @PathVariable String id
    ) {
        AjaxResult result = new AjaxResult();
        // 更新客户数据操作
        try {
            clientsService.delClients(id);
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            //删除客户失败
            result.setMsg(ResultMsg.C_024);
        }
        return result;
    }


}
