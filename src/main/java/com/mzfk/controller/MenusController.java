package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Menus;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.MenusService;
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
 * @Title: MenusController
 * @ProjectName 8Madmin_config
 * @Description: 菜单
 * @date 2018/10/3110:44
 */
@Controller
@RequestMapping("/config")
@Api(value = "菜单Controller",tags = {"菜单Controller"})
public class MenusController {

    @Autowired
    private MenusService menusService;

    private Logger log = LoggerFactory.getLogger(MenusController.class);

    /**
     *  获取菜单列表
     * @return AjaxResult
     */
    @ApiOperation(value = "获取菜单列表", httpMethod = "GET", notes = "获取菜单列表", response = AjaxResult.class)
    @GetMapping("/menus")
    @ResponseBody
    public AjaxResult fildAllMenus () {
        AjaxResult result = new AjaxResult();
        try {
            result = menusService.fildAllMenus();
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取菜单列表 err
            result.setMsg(ResultMsg.C_052);
        }

        return result;
    }



    /**
     *  添加菜单
     * @param menus 菜单
     * @return AjaxResult
     */
    @ApiOperation(value = "添加菜单", httpMethod = "POST", notes = "添加菜单", response = AjaxResult.class)
    @PostMapping("/menus")
    @ResponseBody
    public AjaxResult addMenus (
            @ApiParam(name="菜单",value="menus",required = true) @RequestBody Menus menus
    ) {
        AjaxResult result = new AjaxResult();

        try {
            result = menusService.addMenus(menus);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加菜单 err
            result.setMsg(ResultMsg.C_053);
        }

        return result;
    }


    /**
     *  更新菜单
     * @param menus 菜单
     * @return
     */
    @ApiOperation(value = "更新菜单", httpMethod = "PATCH", notes = "添加菜单", response = AjaxResult.class)
    @PatchMapping("/menus/{code}")
    @ResponseBody
    public AjaxResult updateMenus (
            @ApiParam(name="菜单主键",value="code",required = true)  @PathVariable String code,
            @ApiParam(name="菜单",value="menus",required = true)  @RequestBody Menus menus
    ) {
        AjaxResult result = new AjaxResult();

        try {
            result = menusService.updateMenus(menus,code);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新菜单 err
            result.setMsg(ResultMsg.C_054);
        }

        return result;
    }


    /**
     *  通过code 查找菜单
     * @param code code
     * @return AjaxResult
     */
    @ApiOperation(value = "通过code查找菜单", httpMethod = "GET", notes = "通过code查找菜单", response = AjaxResult.class)
    @GetMapping("/menus/{code}")
    @ResponseBody
    public AjaxResult findMenuByCode (@ApiParam(name="菜单主键",value="code",required = true) @PathVariable String code ) {
        AjaxResult result = new AjaxResult();
        try {
            result = menusService.findMenuByCode(code);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 查找菜单 err
            result.setMsg(ResultMsg.C_052);
        }
        return result;
    }


    /**
     *  通过code 删除菜单
     * @param code code
     * @return AjaxResult
     */
    @ApiOperation(value = "通过code删除菜单", httpMethod = "DELETE", notes = "通过code删除菜单", response = AjaxResult.class)
    @DeleteMapping("/menus/{code}")
    @ResponseBody
    public AjaxResult delMenuByCode (@ApiParam(name="菜单主键",value="code",required = true) @PathVariable String code) {
        AjaxResult result = new AjaxResult();
        try {
            result = menusService.delMenuByCode(code);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除菜单 err
            result.setMsg(ResultMsg.C_055);
        }
        return result;
    }











}
