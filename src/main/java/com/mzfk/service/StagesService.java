package com.mzfk.service;

import com.mzfk.entity.Stages;
import com.mzfk.pojo.StagesInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: StagesService
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2516:02
 */
public interface StagesService {

    /**
     * 查找阶段列表
     * @param params
     * @return
     */
    AjaxResult findStagesList(StagesInfoParam params);

    /**
     * 添加阶段信息
     * @param stages
     * @return AjaxResult
     */
    AjaxResult addStages(Stages stages);

    /**
     * 更新阶段信息
     * @return AjaxResult
     */
    AjaxResult updateStages(Stages stages,String id);

    /**
     * 删除 通过id
     * @param id id
     */
    void delStages(String id);
}
