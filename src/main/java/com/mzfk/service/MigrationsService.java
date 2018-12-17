package com.mzfk.service;

import com.mzfk.entity.Migrations;
import com.mzfk.pojo.MigrationsInfoParam;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: MigrationsService
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2314:22
 */
public interface MigrationsService {

    /**
     * 获取数据迁移列表
     * @param params
     * @return
     */
    AjaxResult findMigrationsList(MigrationsInfoParam params);



    /**
     *  添加迁移数据
     * @param migrations
     * @return AjaxResult
     */
    AjaxResult addMigrations(Migrations migrations);


    /**
     * 更新迁移数据
     * @return
     */
    AjaxResult updateMigrations(Migrations migrations,String id );

    /**
     * 删除记录
     * @param id 记录id
     * @return
     */
    AjaxResult delMigrations(String id);
}
