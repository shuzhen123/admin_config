package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Migrations;
import com.mzfk.pojo.MigrationsInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.MigrationsService;
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
 * @Title: MigrationsCountroller
 * @ProjectName 8Madmin_config
 * @Description: 数据迁移
 * @date 2018/10/23 14:23
 */
@Controller
@RequestMapping(value = "/config")
@Api(value = "数据库迁移Controller",tags = {"数据库迁移Controller"})
public class MigrationsCountroller {

    @Autowired
    private MigrationsService migrationsService;

    private Logger log = LoggerFactory.getLogger(MigrationsCountroller.class);


    /**
     * 获取数据库迁移列表
     * @param Params 参数模型
     * @return AjaxResult
     */
    @ApiOperation(value = "获取数据库迁移列表", httpMethod = "GET", notes = "获取数据库迁移列表", response = AjaxResult.class)
    @GetMapping("/migrations")
    @ResponseBody
    public AjaxResult findMigrationsList (@ApiParam(name="分页参数",value="Params") MigrationsInfoParam Params) {
        AjaxResult result = new AjaxResult();
        // 获取列表
        try {
            result = migrationsService.findMigrationsList(Params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取数据库迁移列表 err
            result.setMsg(ResultMsg.C_056);
        }
        return result;
    }


    /**
     * 添加域名
     * @return AjaxResult
     */
    @ResponseBody
    @PostMapping("/migrations")
    @ApiOperation(value = "添加迁移数据", httpMethod = "POST", notes = "添加迁移数据", response = AjaxResult.class)
    public AjaxResult addMigrations (@ApiParam(name="数据模型",value="migrations",required = true) @RequestBody Migrations migrations ) {
        AjaxResult result = new AjaxResult();
        //
        try {
            result = migrationsService.addMigrations(migrations);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加域名 err
            result.setMsg(ResultMsg.C_057);
        }
        return result;
    }


    /**
     * 更新数据迁移记录
     * @param migrations 入参模型
     * @return AjaxResult
     */
    @ResponseBody
    @PatchMapping("/migrations/{id}")
    @ApiOperation(value = "更新数据迁移记录", httpMethod = "POST", notes = "更新数据迁移记录", response = AjaxResult.class)
    public AjaxResult updateMigrations (
            @ApiParam(name="id",value="主键id",required = true) @PathVariable String id,
            @ApiParam(name="数据模型",value="migrations",required = true) @RequestBody Migrations migrations) {
        AjaxResult result = new AjaxResult();
        // 更新数据迁移记录
        try {
            result = migrationsService.updateMigrations(migrations,id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新数据迁移记录 err
            result.setMsg(ResultMsg.C_058);
        }
        return result;
    }


    /**
     * 删除数据迁移记录
     * @return
     */
    @ResponseBody
    @DeleteMapping("/migrations/{id}")
    @ApiOperation(value = "删除数据迁移记录", httpMethod = "DELETE", notes = "删除数据迁移记录", response = AjaxResult.class)
    public AjaxResult delMigrations (
            @ApiParam(name="主键id",value="id",required = true) @PathVariable String id
    ) {
        AjaxResult result = new AjaxResult();

        try {
            result = migrationsService.delMigrations(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除数据迁移记录 err
            result.setMsg(ResultMsg.C_059);
        }

        return result;
    }






}
