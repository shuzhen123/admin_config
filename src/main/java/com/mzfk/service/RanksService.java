package com.mzfk.service;


import com.mzfk.entity.Ranks;
import com.mzfk.pojo.RanksInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: RanksService
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/19:46
 */
public interface RanksService {

    /**
     *  获取所有rank
     * @return AjaxResult
     */
    AjaxResult fildAllRanks(RanksInfoParam params);


    /**
     * 添加等级
     * @param ranks
     * @return AjaxResult
     */
    AjaxResult addRanks(Ranks ranks);

    /**
     * 跟新等级
     * @param ranks ranks
     * @return AjaxResult
     */
    AjaxResult uptateRanks(Ranks ranks,String code);

    /**
     *  删除等级
     * @param code code
     * @return AjaxResult
     */
    AjaxResult delRanks(String code);
}
