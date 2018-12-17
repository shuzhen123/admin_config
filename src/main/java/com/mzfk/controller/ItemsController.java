package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Items;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.ItemsService;
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
 * @Title: ItemsController
 * @ProjectName 8Madmin_config
 * @Description: 项目列表相关
 * @date 2018/10/2513:43
 */
@Controller
@RequestMapping(value = "/config")
@Api(value = "项目Controller",tags = {"项目Controller"})
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    private Logger log = LoggerFactory.getLogger(ItemsController.class);


    /**
     * 获取项目列表
     * @return AjaxResult
     */
    @ApiOperation(value = "获取项目列表", httpMethod = "GET", notes = "获取项目列表", response = AjaxResult.class)
    @GetMapping("/items")
    @ResponseBody
    public AjaxResult findItemsList () {
        AjaxResult result = new AjaxResult();
        // 获取列表
        try {
            result = itemsService.findItemsList();
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取项目列表 err
            result.setMsg(ResultMsg.C_048);
        }

        return result;
    }



    /**
     *  添加信息
     * @param items
     * @return AjaxResult
     */
    @ApiOperation(value = "添加项目信息", httpMethod = "POST", notes = "添加项目信息", response = AjaxResult.class)
    @PostMapping("/items")
    @ResponseBody
    public AjaxResult addItems (
            @ApiParam(name="Items参数",value="items",required = true)  @RequestBody Items items
    ) {
        AjaxResult result = new AjaxResult();
        try {
            result = itemsService.addItems(items);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加信息 err
            result.setMsg(ResultMsg.C_049);
        }
        return result;
    }



    /**
     * 更新信息
     * @param items
     * @return
     */
    @ApiOperation(value = "更新信息", httpMethod = "PATCH", notes = "更新信息", response = AjaxResult.class)
    @PatchMapping("/items/{id}")
    @ResponseBody
    public AjaxResult updateItems (
            @ApiParam(name="主键id",value="id",required = true)   @PathVariable String id,
            @ApiParam(name="Items参数",value="items",required = true)  @RequestBody Items items
    ) {
        AjaxResult result = new AjaxResult();
        try {
            result = itemsService.updateItems(items,id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新信息 err
            result.setMsg(ResultMsg.C_050);
        }
        return result;
    }


    /**
     * 通过id 删除项目信息
     * @return
     */
    @ApiOperation(value = "删除信息", httpMethod = "DELETE", notes = "删除信息", response = AjaxResult.class)
    @DeleteMapping("/items/{id}")
    @ResponseBody
    public AjaxResult delItems (
            @ApiParam(name="主键id",value="id",required = true) @PathVariable String id
    ) {
        AjaxResult result = new AjaxResult();
        try {
            result = itemsService.delItems(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除项目信息 err
            result.setMsg(ResultMsg.C_051);
        }
        return result;
    }



}
