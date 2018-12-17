package com.mzfk.service;

import com.mzfk.entity.FinancialGroups;
import com.mzfk.pojo.FinancialGroupsInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: FinancialGroupsService
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2415:31
 */
public interface FinancialGroupsService {

    /**
     * 获取财团列表
     * @param params
     * @return AjaxResult
     */
    AjaxResult findFinancialGroupsList(FinancialGroupsInfoParam params);

    /**
     *  添加财团信息
     * @param financialGroups
     * @return AjaxResult
     */
    AjaxResult addFinancialGroupsModel(FinancialGroups financialGroups);

    /**
     * 通过id 删除财团信息
     * @param id 主键id
     */
    void delFinancialGroups(String id);


    /**
     *  通过id 获取财团的信息
     * @param id id
     * @return AjaxResult
     */
    AjaxResult findFinancialById(String id);


    /**
     *  更新财团信息
     * @param financialGroups
     * @return AjaxResult
     */
    AjaxResult updateFinancialGroups(FinancialGroups financialGroups,String id );
}
