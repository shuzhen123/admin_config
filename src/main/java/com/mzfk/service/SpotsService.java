package com.mzfk.service;

import com.mzfk.entity.Spots;
import com.mzfk.pojo.SpotsInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: SpotsService
 * @ProjectName 8Madmin_config
 * @Description: 标识service 接口
 * @date 2018/10/1914:37
 */
public interface SpotsService {

    /**
     * 获取标识列表
     * @return
     */
    AjaxResult findSpotsList(SpotsInfoParam Param);

    /**
     * 添加标识
     * @return
     */
    AjaxResult addSpots(Spots spots);

    /**
     * 更新标识
     * @param spots 对象信息
     * @return
     */
    AjaxResult updateSpots(Spots spots,String ids);



    /**
     * 删除
     * @param ids
     * @return
     */
    AjaxResult delSpots(String ids);


}
