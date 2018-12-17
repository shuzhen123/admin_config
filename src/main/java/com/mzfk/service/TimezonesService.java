package com.mzfk.service;

import com.mzfk.entity.Timezones;
import com.mzfk.pojo.TimezonesInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: TimezonesService
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2915:47
 */
public interface TimezonesService {
    /**
     *  查找时区列表
     * @param params
     * @return
     */
    AjaxResult findTimezonesList(TimezonesInfoParam params);


    /**
     * 添加时区信息
     * @return AjaxResult
     */
    AjaxResult addTimezones(Timezones timezones);

    /**
     * 更新时区信息
     * @return AjaxResult
     */
    AjaxResult updateTimezones(Timezones timezones,String id);

    /**
     * 删除时区信息
     * @param id id
     * @return AjaxResult
     */
    AjaxResult delTimezones(String id);
}
