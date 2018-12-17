package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Plans;
import com.mzfk.pojo.PlansInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.PlansService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author sz
 * @Title: PlansController
 * @ProjectName 8Madmin_config
 * @Description: 计划
 * @date 2018/10/2611:50
 */
@Controller
@RequestMapping("/config")
@Api(value = "计划Controller",tags = {"计划Controller"})
public class PlansController {


    @Autowired
    private PlansService plansService;

    private Logger log = LoggerFactory.getLogger(BanksController.class);


    /**
     * 获取计划列表
     * @param Params
     * @return AjaxResult
     */
    @ApiOperation(value = "获取计划列表", httpMethod = "GET", notes = "获取计划列表", response = AjaxResult.class)
    @GetMapping("/plans")
    @ResponseBody
    public AjaxResult findPlansList (@ApiParam(name="分页参数",value="Params") PlansInfoParam Params) {
        AjaxResult result = new AjaxResult();
        // 获取列表
        try {
            result = plansService.findPlansList(Params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取列表 err
            result.setMsg(ResultMsg.C_060);
        }

        return result;
    }


    /**
     *  添加计划
     * @param plans
     * @return AjaxResult
     */
    @ApiOperation(value = "添加计划", httpMethod = "POST", notes = "添加计划", response = AjaxResult.class)
    @PostMapping("/plans")
    @ResponseBody
    public AjaxResult addPlans (
            @ApiParam(name="计划模型",value="plans",required = true) @RequestBody Plans plans
    ) {
        AjaxResult result = new AjaxResult();
        try {
            result = plansService.addPlans(plans);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加计划 err
            result.setMsg(ResultMsg.C_061);
        }
        return result;
    }




    /**
     *  更新计划
     * @param plans
     * @return
     */
    @ApiOperation(value = "更新计划", httpMethod = "POST", notes = "更新计划", response = AjaxResult.class)
    @PatchMapping("/plans/{code}")
    @ResponseBody
    public AjaxResult updatePlans (
            @ApiParam(name="计划code",value="code",required = true) @PathVariable String code,
            @ApiParam(name="计划模型",value="plans",required = true) @RequestBody Plans plans
    ) {
        AjaxResult result = new AjaxResult();

        if (StringUtils.isEmpty(plans.getCode())) {
            result.setMsg(ResultMsg.C_062);
            return result;
        }
        // 添加操作
        try {
            result = plansService.updatePlans(plans,code);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加操作 err
            result.setMsg(ResultMsg.C_062);
        }

        return result;
    }


    /**
     *通过id 删除计划
     * @return AjaxResult
     */
    @ApiOperation(value = "删除计划", httpMethod = "DELETE", notes = "删除计划", response = AjaxResult.class)
    @DeleteMapping("/plans/{code}")
    @ResponseBody
    public AjaxResult delPlans (@ApiParam(name="计划code",value="code",required = true) @PathVariable String code) {
        AjaxResult result = new AjaxResult();
        try {
            plansService.delPlans(code);
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除计划 err
            result.setMsg(ResultMsg.C_063);
        }

        return result;
    }




}
