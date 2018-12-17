package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.WithdrawSettings;
import com.mzfk.pojo.WithdrawSettingsInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.WithdrawSettingsService;
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
 * @Title: WithdrawSettingsController
 * @ProjectName 8Madmin_config
 * @Description: 撤销设置 controller
 * @date 2018/10/2316:47
 */
@Controller
@RequestMapping("/config")
@Api(value = "撤回设置Controller",tags = {"撤回设置Controller"})
public class WithdrawSettingsController {


    @Autowired
    private WithdrawSettingsService withdrawSettingsService;

    private Logger log = LoggerFactory.getLogger(WithdrawSettingsController.class);

    /**
     * 获取设置列表
     * @param Params 参数模型
     * @return AjaxResult
     */
    @ApiOperation(value = "获取撤销设置列表", httpMethod = "GET", notes = "获取撤销设置列表", response = AjaxResult.class)
    @GetMapping("/withdrawsettings")
    @ResponseBody
    public AjaxResult findWithdrawSettingsList (@ApiParam(name="分页参数",value="Params") WithdrawSettingsInfoParam Params) {
        AjaxResult result = new AjaxResult();
        // 获取列表
        try {
            result = withdrawSettingsService.findWithdrawSettingsList(Params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取设置列表 err
            result.setMsg(ResultMsg.C_084);
        }

        return result;
    }


    /**
     * 添加撤销设置信息
     * @return AjaxResult
     */
    @ApiOperation(value = "添加撤销设置信息", httpMethod = "POST", notes = "添加撤销设置信息", response = AjaxResult.class)
    @PostMapping("/withdrawsettings")
    @ResponseBody
    public AjaxResult addWithdrawSettings (@ApiParam(name="撤销设置信息",value="withdrawSettings") @RequestBody WithdrawSettings withdrawSettings
    ) {
        AjaxResult result = new AjaxResult();
        // 持久化步骤
        try {
            result = withdrawSettingsService.addWithdrawSettings(withdrawSettings);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加撤销设置信息 err
            result.setMsg(ResultMsg.C_085);
        }
        // 返回
        return result;
    }


    /**
     * 更新撤销记录
     * @param withdrawSettings
     * @return
     */
    @ApiOperation(value = "更新撤销设置信息", httpMethod = "POST", notes = "更新撤销设置信息", response = AjaxResult.class)
    @PatchMapping("/withdrawsettings/{id}")
    @ResponseBody
    public AjaxResult updateWithdrawSettings (
            @ApiParam(name="主键id",value="id",required = true) @PathVariable String id,
            @ApiParam(name="撤销设置信息",value="withdrawSettings",required = true) @RequestBody WithdrawSettings withdrawSettings
    ) {
        AjaxResult result = new AjaxResult();

        // 持久化步骤
        try {
            withdrawSettingsService.updateWithdrawSettings(withdrawSettings,id);
            // 返回
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新撤销记录 err
            result.setMsg(ResultMsg.C_086);
        }

        return result;
    }


    /**
     * 删除撤销记录
     * @param id 记录id
     * @return AjaxResult
     */
    @ResponseBody
    @DeleteMapping("/withdrawsettings/{id}")
    @ApiOperation(value = "删除撤销设置信息", httpMethod = "DELETE", notes = "删除撤销设置信息", response = AjaxResult.class)
    public AjaxResult delWithdrawSettings (
            @ApiParam(name="主键id",value="id",required = true) @PathVariable String id
    ) {
        AjaxResult result = new AjaxResult();

        try {
            withdrawSettingsService.delWithdrawSettings(id);
            // 返回
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除撤销记录 err
            result.setMsg(ResultMsg.C_087);
        }
        return result;
    }




}
