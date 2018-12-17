package com.mzfk.service;

import com.mzfk.entity.Countries;
import com.mzfk.pojo.CountriesInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: CountriesService
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2911:08
 */
public interface CountriesService {


    /**
     *  获取国家列表
     * @param params 分页参数
     * @return AjaxResult
     */
    AjaxResult findCountriesList(CountriesInfoParam params);


    /**
     * 删除国家信息
     * @param iso id
     */
    void delCountries(String iso);

    /**
     *  添加国家信息
     * @param countries
     * @return AjaxResult
     */
    AjaxResult addCountriesModel(Countries countries);

    /**
     *  更新国家信息
     * @param countries
     * @return AjaxResult
     */
    AjaxResult updateCountriesModel(Countries countries , String id);
}
