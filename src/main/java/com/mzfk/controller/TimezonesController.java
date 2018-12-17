package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Timezones;
import com.mzfk.pojo.TimezonesInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.TimezonesService;
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
 * @Title: TimezonesController
 * @ProjectName 8Madmin_config
 * @Description: 时区
 * @date 2018/10/2915:48
 */
@Controller
@RequestMapping("/config")
@Api(value = "时区Controller",tags = {"时区Controller"})
public class TimezonesController {


    @Autowired
    private TimezonesService timezonesService;

    private Logger log = LoggerFactory.getLogger(TimezonesController.class);

    /**
     * 获取时区列表
     * @param Params
     * @return AjaxResult
     */
    @ApiOperation(value = "获取时区列表", httpMethod = "GET", notes = "获取时区列表", response = AjaxResult.class)
    @GetMapping("/timezones")
    @ResponseBody
    public AjaxResult findTimezonesList (@ApiParam(name="分页参数",value="Params") TimezonesInfoParam Params) {
        AjaxResult result = new AjaxResult();
        // 获取列表
        try {
            result = timezonesService.findTimezonesList(Params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取时区列表 err
            result.setMsg(ResultMsg.C_076);
        }

        return result;
    }


    /**
     * 添加时区信息
     * @return AjaxResult
     */
    @ApiOperation(value = "添加时区信息", httpMethod = "POST", notes = "添加时区信息", response = AjaxResult.class)
    @PostMapping("/timezones")
    @ResponseBody
    public AjaxResult addTimezones (
            @ApiParam(name="时区信息",value="timezones",required = true) @RequestBody Timezones timezones
    ) {
        AjaxResult result = new AjaxResult();
        // 调库
        try {
            result = timezonesService.addTimezones(timezones);
            // 返回
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加时区信息 err
            result.setMsg(ResultMsg.C_077);
            return result;
        }

    }


    /**
     * 更新时区信息
     * @return AjaxResult
     */
    @ApiOperation(value = "更新时区信息", httpMethod = "PATCH", notes = "更新时区信息", response = AjaxResult.class)
    @PatchMapping("/timezones/{id}")
    @ResponseBody
    public AjaxResult updateTimezones (
            @ApiParam(name="主键id",value="id",required = true) @PathVariable String id,
            @ApiParam(name="时区信息",value="timezones",required = true) @RequestBody Timezones timezones
    ) {
        AjaxResult result = new AjaxResult();
        // 调库
        try {
            result = timezonesService.updateTimezones(timezones,id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新时区信息 err
            result.setMsg(ResultMsg.C_078);
        }
        // 返回
        return result;
    }


    /**
     * 删除时区信息
     * @param id  id
     * @return AjaxResult
     */
    @ApiOperation(value = "删除时区信息", httpMethod = "DELETE", notes = "删除时区信息", response = AjaxResult.class)
    @DeleteMapping("/timezones/{id}")
    @ResponseBody
    public AjaxResult delTimezones (
            @ApiParam(name="主键id",value="id",required = true) @PathVariable String id) {
        AjaxResult result = new AjaxResult();
        try {
            result = timezonesService.delTimezones(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除时区信息 err
            result.setMsg(ResultMsg.C_079);
        }
        // 返回
        return result;
    }

}
