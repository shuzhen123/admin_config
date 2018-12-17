package com.mzfk.service;

import com.mzfk.entity.DepositSettings;
import com.mzfk.pojo.DepositSettingsInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: DepositSettingsService
 * @ProjectName 8Madmin_config
 * @Description: 存款设置接口
 * @date 2018/10/2510:32
 */
public interface DepositSettingsService {

    /**
     * 获取存款相关设置列表
     * @param params 分页参数
     * @return AjaxResult
     */
    AjaxResult findDepositSettingsList(DepositSettingsInfoParam params);


    /**
     *  添加存款相关设置
     * @param depositSettings
     * @return AjaxResult
     */
    AjaxResult addDepositSettings(DepositSettings depositSettings);


    /**
     *  更新存款相关设置
     * @param depositSettings
     * @return AjaxResult
     */
    AjaxResult updateDepositSettings(DepositSettings depositSettings,String id);

    /**
     * 通过id 删除记录
     * @param id id
     */
    void delDepositSettings(String id);
}
