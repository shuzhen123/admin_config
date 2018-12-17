package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Ranks;
import com.mzfk.pojo.RanksInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.RanksService;
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
 * @Title: RanksController
 * @ProjectName 8Madmin_config
 * @Description: 等级
 * @date 2018/11/19:47
 */
@Controller
@RequestMapping("/config")
@Api(value = "计划Controller",tags = {"计划Controller"})
public class RanksController {

    @Autowired
    private RanksService ranksService;

    private Logger log = LoggerFactory.getLogger(RanksController.class);

    /**
     *  获取等级列表
     * @return AjaxResult
     */
    @ApiOperation(value = "获取等级列表", httpMethod = "GET", notes = "获取等级列表", response = AjaxResult.class)
    @GetMapping("/ranks")
    @ResponseBody
    public AjaxResult fildAllRanks (@ApiParam(name="分页参数",value="Params") RanksInfoParam params) {
        AjaxResult result = new AjaxResult();

        try {
            result = ranksService.fildAllRanks(params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取等级列表 err
            result.setMsg(ResultMsg.C_064);
        }

        return result;
    }



    /**
     * 添加等级
     * @param ranks ranks
     * @return AjaxResult
     */
    @ApiOperation(value = "添加等级", httpMethod = "POST", notes = "添加等级", response = AjaxResult.class)
    @PostMapping("/ranks")
    @ResponseBody
    public AjaxResult addRanks (
            @ApiParam(name="等级参数",value="ranks",required = true) @RequestBody Ranks ranks
    ) {
        AjaxResult result = new AjaxResult();

        try {
            result = ranksService.addRanks(ranks);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加等级 err
            result.setMsg(ResultMsg.C_065);
        }

        return result;
    }


    /**
     *  更新等级
     * @param ranks
     * @return
     */
    @ApiOperation(value = "更新等级", httpMethod = "PATCH", notes = "更新等级", response = AjaxResult.class)
    @PatchMapping("/ranks/{code}")
    @ResponseBody
    public AjaxResult uptateRanks (
            @ApiParam(name="等级code",value="code",required = true) @PathVariable String code,
            @ApiParam(name="等级参数",value="ranks",required = true) @RequestBody Ranks ranks
    ) {
        AjaxResult result = new AjaxResult();

        try {
            result = ranksService.uptateRanks(ranks,code);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新等级 err
            result.setMsg(ResultMsg.C_066);
        }

        return result;
    }


    /**
     * 删除等级
     * @param code id
     * @return AjaxResult
     */
    @ApiOperation(value = "删除等级", httpMethod = "DELETE", notes = "删除等级", response = AjaxResult.class)
    @DeleteMapping("/ranks/{code}")
    @ResponseBody
    public AjaxResult delRanks (@ApiParam(name="等级code",value="code",required = true) @PathVariable String code) {
        AjaxResult result = new AjaxResult();
        try {
            result = ranksService.delRanks(code);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除等级 err
            result.setMsg(ResultMsg.C_067);
        }
        return result;
    }









}
