package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.FinancialGroups;
import com.mzfk.pojo.FinancialGroupsInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.FinancialGroupsService;
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
 * @Title: FinancialGroupsController
 * @ProjectName 8Madmin_config
 * @Description: 财团controller
 * @date 2018/10/2415:33
 */
@Controller
@RequestMapping("/config")
@Api(value = "金融分组Controller",tags = {"金融分组Controller"})
public class FinancialGroupsController {

    @Autowired
    private FinancialGroupsService financialGroupsService;

    private Logger log = LoggerFactory.getLogger(FinancialGroupsController.class);

    /**
     * 获取财团列表
     * @param Params
     * @return AjaxResult
     */
    @ApiOperation(value = "获取财团列表", httpMethod = "GET", notes = "获取财团列表", response = AjaxResult.class)
    @GetMapping("/financialgroups")
    @ResponseBody
    public AjaxResult findFinancialGroupsList (@ApiParam(name="分页参数",value="Params") FinancialGroupsInfoParam Params) {
        AjaxResult result = new AjaxResult();
        // 获取列表
        try {
            result = financialGroupsService.findFinancialGroupsList(Params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取财团列表失败
            result.setMsg(ResultMsg.C_044);
        }

        return result;
    }


    /**
     * 添加财团信息
     * @param financialGroups
     * @return AjaxResult
     */
    @ApiOperation(value = "添加财团信息", httpMethod ="POST", notes = "添加财团信息", response = AjaxResult.class)
    @PostMapping("/financialgroups")
    @ResponseBody
    public AjaxResult addFinancialGroups (
            @ApiParam(name="财团信息",value="financialGroups",required = true) @RequestBody FinancialGroups financialGroups
    ) {
        AjaxResult result = new AjaxResult();

        try {
            result = financialGroupsService.addFinancialGroupsModel(financialGroups);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加财团信息失败
            result.setMsg(ResultMsg.C_045);
        }

        return result;
    }


    /**
     *  更新财团信息
     * @param financialGroups
     * @return
     */
    @ApiOperation(value = "更新财团信息", httpMethod ="POST", notes = "更新财团信息", response = AjaxResult.class)
    @PatchMapping("/financialgroups/{id}")
    @ResponseBody
    public AjaxResult updateFinancialGroups (
            @ApiParam(name="财团id",value="id",required = true) @PathVariable String id,
            @ApiParam(name="财团信息",value="financialGroups",required = true) @RequestBody FinancialGroups financialGroups
    ) {
        AjaxResult result = new AjaxResult();

        try {
            result = financialGroupsService.updateFinancialGroups(financialGroups,id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新财团信息失败
            result.setMsg(ResultMsg.C_046);
        }
        return result;
    }



    /**
     * 删除财团信息
     * @param id 财团id
     * @return AjaxResult
     */
    @ApiOperation(value = "删除财团信息", httpMethod ="DELETE", notes = "删除财团信息", response = AjaxResult.class)
    @DeleteMapping("/financialgroups/{id}")
    @ResponseBody
    public AjaxResult delFinancialGroups (@ApiParam(name="财团id",value="id",required = true) @PathVariable String id
    ) {
        // 返回数据模型
        AjaxResult result = new AjaxResult();

        try {
            financialGroupsService.delFinancialGroups(id);
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除财团信息失败
            result.setMsg(ResultMsg.C_047);
        }
        return result;
    }


    /**
     * 根据id获取财团信息
     * @param id 主键id
     * @return AjaxResult
     */
    @ApiOperation(value = "根据id获取财团信息", httpMethod ="GET", notes = "根据id获取财团信息", response = AjaxResult.class)
    @PostMapping("/financialgroups/{id}")
    @ResponseBody
    public AjaxResult findFinancialById (
            @ApiParam(name="财团id",value="id",required = true) @PathVariable String id
    ) {
        // 返回数据模型
        AjaxResult result = new AjaxResult();

        try {
            result = financialGroupsService.findFinancialById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取财团信息失败
            result.setMsg(ResultMsg.C_044);
        }
        return result;

    }






}
