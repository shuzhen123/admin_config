package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Stages;
import com.mzfk.pojo.StagesInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.StagesService;
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
 * @Title: StagesController
 * @ProjectName 8Madmin_config
 * @Description: 阶段
 * @date 2018/10/2516:06
 */
@Controller
@RequestMapping(value = "/config")
@Api(value = "阶段Controller",tags = {"阶段Controller"})
public class StagesController {

    @Autowired
    private StagesService stagesService;

    private Logger log = LoggerFactory.getLogger(StagesController.class);

    /**
     * 获取阶段列表
     * @param Params
     * @return AjaxResult
     */
    @ApiOperation(value = "获取列表", httpMethod = "GET", notes = "获取阶段列表", response = AjaxResult.class)
    @GetMapping("/stages")
    @ResponseBody
    public AjaxResult findStagesList (@ApiParam(name="分页参数",value="Params") StagesInfoParam Params) {
        AjaxResult result = new AjaxResult();
        // 获取列表
        try {
            result = stagesService.findStagesList(Params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取阶段列表 err
            result.setMsg(ResultMsg.C_072);
        }

        return result;
    }


    /**
     *  添加阶段信息
     * @param stages 阶段信息
     * @return AjaxResult
     */
    @ResponseBody
    @PostMapping(value = "/stages")
    @ApiOperation(value = "添加阶段信息", httpMethod = "POST", notes = "添加阶段信息", response = AjaxResult.class)
    public AjaxResult addStages (
            @ApiParam(name="阶段信息",value="stages",required = true)  @RequestBody Stages stages
    ) {
        AjaxResult result = new AjaxResult();
        // 持久化步骤
        try {
            result = stagesService.addStages(stages);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加阶段信息 err
            result.setMsg(ResultMsg.C_073);
        }

        return result;
    }


    /**
     * 更新阶段信息
     * @param stages 阶段信息
     * @return AjaxResult
     */
    @ResponseBody
    @PatchMapping(value = "/stages/{id}")
    @ApiOperation(value = "更新阶段信息", httpMethod = "POST", notes = "添加阶段信息", response = AjaxResult.class)
    public AjaxResult updateStages (
            @ApiParam(name="主键id",value="id",required = true) @PathVariable String id,
            @ApiParam(name="阶段信息",value="stages",required = true) @RequestBody Stages stages
    ) {
        AjaxResult result = new AjaxResult();

        try {
            result = stagesService.updateStages(stages,id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新阶段信息 err
            result.setMsg(ResultMsg.C_074);
        }

        return result;
    }


    /**
     * 删除阶段信息
     * @param id id
     * @return AjaxResult
     */
    @ResponseBody
    @DeleteMapping(value = "/stages/{id}")
    @ApiOperation(value = "删除阶段信息", httpMethod = "DELETE", notes = "删除阶段信息", response = AjaxResult.class)
    public AjaxResult delStages (@ApiParam(name="主键id",value="id",required = true) @PathVariable String id) {
        AjaxResult result = new AjaxResult();

        try {
            stagesService.delStages(id);
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除阶段信息 err
            result.setMsg(ResultMsg.C_075);
        }
        // 成功返回
        return result;
    }

}
