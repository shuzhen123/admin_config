package com.mzfk.service;

import com.mzfk.entity.Plans;
import com.mzfk.pojo.PlansInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: PlansService
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2611:38
 */
public interface PlansService {

    /**
     * 获取计划列表
     * @param params
     * @return
     */
    AjaxResult findPlansList(PlansInfoParam params);


    /**
     * 添加计划
     * @param plans
     * @return
     */
    AjaxResult addPlans(Plans plans);

    /**
     *
     * @param plans
     * @return
     */
    AjaxResult updatePlans(Plans plans,String code);

    /**
     * 删除计划
     * @param code code
     */
    void delPlans(String code);
}
