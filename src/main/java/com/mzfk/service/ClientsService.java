package com.mzfk.service;

import com.mzfk.entity.Clients;
import com.mzfk.pojo.ClientsIInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: ClientsService
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2216:57
 */
public interface ClientsService {


    /**
     * 获取客户的列表
     * @param param 分页参数
     * @return
     */
    AjaxResult findSpotsList(ClientsIInfoParam param);


    /**
     * 更新客户信息
     * @param clients
     */
    AjaxResult updateClients(Clients clients,String id);

    /**
     *通过id 删除客户
     * @param id 用户id
     */
    void delClients(String id);

    /**
     *  添加客户
     * @param clients
     * @return
     */
    AjaxResult addClientsModel(Clients clients);
}
