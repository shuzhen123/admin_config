package com.mzfk.service;

import com.mzfk.entity.Banks;
import com.mzfk.pojo.BanksInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: BanksService
 * @ProjectName 8Madmin_config
 * @Description: 银行相关 service 接口
 * @date 2018/10/1910:56
 */
public interface BanksService {

    /**
     * 获取或有银行列表
     * @return
     */
    AjaxResult findBanksList(BanksInfoParam param);

    /**
     * 添加银行
     * @return
     */
    AjaxResult addBanks(Banks banks);

    /**
     * 通过code 删除银行
     * @param code 银行code
     * @return
     */
    AjaxResult delBanks(String code);

    /**
     * 通过code 获取
     * @param code code
     * @return
     */
    AjaxResult findBankByCode(String code);


    /**
     *
     * @param banks
     * @return
     */
    AjaxResult updateBanksModel(Banks banks,String code);
}
