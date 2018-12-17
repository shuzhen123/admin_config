package com.mzfk.service;

import com.mzfk.entity.Menus;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: MenusService
 * @ProjectName 8Madmin_config
 * @Description: 菜单
 * @date 2018/10/3110:41
 */
public interface MenusService {

    /**
     *  获取所有菜单
     * @return AjaxResult
     */
    AjaxResult fildAllMenus();



    /**
     *  添加菜单
     * @param menus
     * @return AjaxResult
     */
    AjaxResult addMenus(Menus menus);

    /**
     *  通过code 查找菜单
     * @param code code
     * @return AjaxResult
     */
    AjaxResult findMenuByCode(String code);

    /**
     *  通过code 删除菜单
     * @param code code
     * @return AjaxResult
     */
    AjaxResult delMenuByCode(String code);


    /**
     * 更新菜单
     * @param menus
     * @return
     */
    AjaxResult updateMenus(Menus menus,String code);
}
