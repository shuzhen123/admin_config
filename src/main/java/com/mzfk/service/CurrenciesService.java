package com.mzfk.service;

import com.mzfk.dao.CurrenciesInfoParam;
import com.mzfk.entity.Currencies;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: CurrenciesService
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/210:58
 */
public interface CurrenciesService {


    /**
     *  获取所有货币信息
     * @param params 分页信息
     * @return
     */
    AjaxResult fildCurrencies(CurrenciesInfoParam params);



    /**
     *  删除货币信息
     * @param code code
     */
    void delCurrencies(String code);

    /**
     *  查询一个货币信息
     * @param code code
     * @return AjaxResult
     */
    AjaxResult getCurrencies(String code);

    /**
     *  添加货币信息
     * @param currencies 货币信息
     * @return
     */
    AjaxResult addCurrenciesModel(Currencies currencies);

    /**
     *  更新货币信息
     * @param currencies
     * @return AjaxResult
     */
    AjaxResult updateCurrenciesModel(Currencies currencies,String code);
}
