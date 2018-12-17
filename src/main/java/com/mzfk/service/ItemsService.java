package com.mzfk.service;

import com.mzfk.entity.Items;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: ItemsService
 * @ProjectName 8Madmin_config
 * @Description: 项目 相关接口
 * @date 2018/10/2513:40
 */
public interface ItemsService {

    /**
     * 获取项目列表
     * @return AjaxResult
     */
    AjaxResult findItemsList();


    /**
     *  添加项目
     * @param items
     * @return AjaxResult
     */
    AjaxResult addItems(Items items);


    /**
     * 更新项目
     * @param items
     * @return
     */
    AjaxResult updateItems(Items items,String id);

    /**
     * 删除项目
     * @param id 主键id
     * @return AjaxResult
     */
    AjaxResult delItems(String id);
}
