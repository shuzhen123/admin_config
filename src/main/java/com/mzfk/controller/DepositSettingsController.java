package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.DepositSettings;
import com.mzfk.pojo.DepositSettingsInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.DepositSettingsService;
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
 * @Title: DepositSettingsController
 * @ProjectName 8Madmin_config
 * @Description: 存款相关设置 controller
 * @date 2018/10/2510:34
 */
@Controller
@RequestMapping(value = "/config")
@Api(value = "存款设置Controller",tags = {"存款设置Controller"})
public class DepositSettingsController {



    @Autowired
    private DepositSettingsService depositSettingsService;

    private Logger log = LoggerFactory.getLogger(BanksController.class);



    /**
     * 获取存款相关设置列表
     * @param Params 分页参数
     * @return AjaxResult
     */
    @ApiOperation(value = "获取存款相关设置列表", httpMethod = "GET", notes = "获取财团列表", response = AjaxResult.class)
    @GetMapping("/depositSettings")
    @ResponseBody
    public AjaxResult findDepositSettingsList (@ApiParam(name="分页参数",value="Params") DepositSettingsInfoParam Params) {
        AjaxResult result = new AjaxResult();
        // 获取列表
        try {
            result = depositSettingsService.findDepositSettingsList(Params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取存款相关设置列表失败
            result.setMsg(ResultMsg.C_034);
        }

        return result;
    }



    /**
     * 添加存款相关设置
     * @param depositSettings
     * @return AjaxResult
     */
    @ResponseBody
    @PostMapping(value = "/depositSettings")
    @ApiOperation(value = "添加存款相关设置", httpMethod = "POST", notes = "添加存款相关设置", response = AjaxResult.class)
    public AjaxResult addDepositSettings (
            @ApiParam(name="存款相关设置参数",value="depositSettings",required = true) @RequestBody DepositSettings depositSettings
    ) {
            AjaxResult result = new AjaxResult();
        try {
            // 添加操作
            result = depositSettingsService.addDepositSettings(depositSettings);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加存款相关设置失败
            result.setMsg(ResultMsg.C_035);
        }
        return result;
    }



    /**
     * 更新存款相关设置
     * @param depositSettings
     * @return AjaxResult
     */
    @ResponseBody
    @PatchMapping(value = "/depositSettings/{id}")
    @ApiOperation(value = "更新存款相关设置", httpMethod = "POST", notes = "添加存款相关设置", response = AjaxResult.class)
    public AjaxResult updateDepositSettings (
            @ApiParam(name="主键id",value="id",required = true) @PathVariable String id,
            @ApiParam(name="存款相关设置参数",value="depositSettings",required = true) @RequestBody  DepositSettings depositSettings
    ) {
        AjaxResult result = new AjaxResult();
        try {
            // 添加操作
            result = depositSettingsService.updateDepositSettings(depositSettings,id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新存款相关设置失败
            result.setMsg(ResultMsg.C_036);
        }
        return result;
    }


    /**
     * 通过id 删除记录
     * @param id id
     * @return AjaxResult
     */
    @ResponseBody
    @DeleteMapping(value = "/depositSettings/{id}")
    @ApiOperation(value = "删除存款相关记录", httpMethod = "DELETE", notes = "删除存款相关记录", response = AjaxResult.class)
    public AjaxResult delDepositSettings (@ApiParam(name="主键id",value="id",required = true) @PathVariable String id) {
        AjaxResult result = new AjaxResult();
        // 删除操作
        try {
            depositSettingsService.delDepositSettings(id);
            result.setSuccess(true);
            result.setMsg(ResultMsg.C_001);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除记录失败
            result.setMsg(ResultMsg.C_037);
        }
        return result;
    }





}
