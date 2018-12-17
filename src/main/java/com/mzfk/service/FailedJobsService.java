package com.mzfk.service;

import com.mzfk.entity.FailedJobs;
import com.mzfk.pojo.FailedJobsInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: FailedJobsService
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/3014:13
 */
public interface FailedJobsService {

    /**
     *  获取失败列表
     * @param params
     * @return
     */
    AjaxResult findFailedJobsList(FailedJobsInfoParam params);

    /**
     * 添加失效信息
     * @param failedJobs
     * @return AjaxResult
     */
    AjaxResult addFailedJobsModel(FailedJobs failedJobs);
}
