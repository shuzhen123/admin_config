package com.mzfk.service;


import com.mzfk.entity.WithdrawSettings;
import com.mzfk.pojo.WithdrawSettingsInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: WithdrawSettingsService
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2316:46
 */
public interface WithdrawSettingsService {

    /**
     * 获取撤销列表
     * @param params 入参容器
     * @return AjaxResult
     */
    AjaxResult findWithdrawSettingsList(WithdrawSettingsInfoParam params);

    /**
     * 添加撤销信息
     * @return AjaxResult
     */
    AjaxResult addWithdrawSettings(WithdrawSettings withdrawSettings);

    /**
     * 更新撤销信息
     * @param withdrawSettings
     */
    void updateWithdrawSettings(WithdrawSettings withdrawSettings,String id);

    /**
     * 删除撤销信息
     * @param id 信息id
     */
    void delWithdrawSettings(String id);
}
