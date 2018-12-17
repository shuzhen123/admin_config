package com.mzfk.service;

import com.mzfk.entity.Domains;
import com.mzfk.pojo.DomainsInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: DomainsService
 * @ProjectName 8Madmin_config
 * @Description: 域名接口
 * @date 2018/10/2310:16
 */
public interface DomainsService {


    /**
     * 获取域名列表
     * @param params 参数模型
     * @return AjaxResult
     */
    AjaxResult findDomainsList(DomainsInfoParam params);



    /**
     * 更新操作
     * @param domains
     */
    void updateDomains(Domains domains,String domain);

    /**
     * 删除系统域名下得用户
     * @param domain
     */
    void delDomains(String domain);

    /**
     *  添加域名
     * @param domains
     * @return AjaxResult
     */
    AjaxResult addDomainsModel(Domains domains);
}
