package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.FailedJobs;
import com.mzfk.pojo.FailedJobsInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.FailedJobsService;
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
 * @Title: FailedJobsController
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/3014:16
 */
@Controller
@RequestMapping("/config")
@Api(value = "失败的工作Controller",tags = {"失败的工作Controller"})
public class FailedJobsController {

    @Autowired
    FailedJobsService failedJobsService;

    private Logger log = LoggerFactory.getLogger(FailedJobsController.class);


    /**
     * 获取失败工作列表
     * @param Params
     * @return AjaxResult
     */
    @ApiOperation(value = "获取失败工作列表", httpMethod = "GET", notes = "获取失败工作列表", response = AjaxResult.class)
    @GetMapping("/failedJobs")
    @ResponseBody
    public AjaxResult findFailedJobsList ( @ApiParam(name="分页参数",value="Params") FailedJobsInfoParam Params) {
        AjaxResult result = new AjaxResult();
        // 获取列表
        try {
            result = failedJobsService.findFailedJobsList(Params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 收取失效列表失败
            result.setMsg(ResultMsg.C_042);
        }
        return result;
    }


    /**
     *  添加失效信息
     * @param failedJobs
     * @return AjaxResult
     */
    @ApiOperation(value = "添加失效信息", httpMethod = "POST", notes = "添加失效信息", response = AjaxResult.class)
    @PostMapping("/failedJobs")
    @ResponseBody
    public AjaxResult addFailedJobs (
            @ApiParam(name="失效信息",value="failedJobs",required = true)  @RequestBody FailedJobs failedJobs
    ) {
        AjaxResult result = new AjaxResult();
        // 调库
        try {
            result = failedJobsService.addFailedJobsModel(failedJobs);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加失效信息失败
            result.setMsg(ResultMsg.C_043);
        }
        // 返回
        return result;
    }





}
