package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Spots;
import com.mzfk.pojo.SpotsInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.SpotsService;
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
 * @Title: SpotsController
 * @ProjectName 8Madmin_config
 * @Description: 识别controller
 * @date 2018/10/1914:36
 */
@Controller
@RequestMapping("/config")
@Api(value = "地点Controller",tags = {"地点Controller"})
public class SpotsController {

    @Autowired
    private SpotsService spotsService;

    private Logger log = LoggerFactory.getLogger(SpotsController.class);

    /**
     * 获取识别列表
     * @return AjaxResult
     */
    @ApiOperation(value = "获取识别列表", httpMethod = "GET", notes = "获取识别列表", response = AjaxResult.class)
    @GetMapping("/spots")
    @ResponseBody
    public AjaxResult findSpotsList (@ApiParam(name="分页参数",value="Params") SpotsInfoParam Param) {
        AjaxResult result = new AjaxResult();

        try {
            result = spotsService.findSpotsList(Param);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取识别列表 err
            result.setMsg(ResultMsg.C_068);
        }
        return result;
    }


    /**
     * 添加识别
     * @return AjaxResult
     */
    @ApiOperation(value = "添加识别", httpMethod = "POST", notes = "获取识别列表", response = AjaxResult.class)
    @PostMapping("/spots")
    @ResponseBody
    public AjaxResult addSpots (
            @ApiParam(name="识别数据",value="spots",required = true) @RequestBody Spots spots
    ) {
        AjaxResult result = new AjaxResult();

        // 添加操作
        try {
            result = spotsService.addSpots(spots);
            // 成功返回
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加识别 err
            result.setMsg(ResultMsg.C_069);
        }
        return result;
    }


    /**
     * 更新识别
     * @param spots 标识对象
     * @return AjaxResult
     */
    @ApiOperation(value = "更新识别", httpMethod = "PATCH", notes = "更新识别", response = AjaxResult.class)
    @PatchMapping("/spots/{ids}")
    @ResponseBody
    public AjaxResult updateSpots (
            @ApiParam(name="主键id",value="ids",required = true) @PathVariable String ids,
            @ApiParam(name="标识对象",value="spots",required = true) @RequestBody Spots spots) {
        AjaxResult result = new AjaxResult();

        try {
            result = spotsService.updateSpots(spots,ids);
            // 整理返回参数
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
            return result;

        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新识别 err
            result.setMsg(ResultMsg.C_070);
            return result;
        }
    }


    /**
     * 删除识别
     * @return AjaxResult
     */
    @ApiOperation(value = "删除识别", httpMethod = "DELETE", notes = "删除识别", response = AjaxResult.class)
    @DeleteMapping("/spots/{id}")
    @ResponseBody
    public AjaxResult delSpots ( @ApiParam(name="主键id",value="id",required = true) @PathVariable String id) {
        AjaxResult result = new AjaxResult();

        try {
            result = spotsService.delSpots(id);
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除识别 err
            result.setMsg(ResultMsg.C_071);
        }

        return result;
    }

}
