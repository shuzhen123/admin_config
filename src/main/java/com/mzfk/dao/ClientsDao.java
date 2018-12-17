package com.mzfk.dao;

import com.mzfk.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author sz
 * @Title: ClientsDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2216:55
 */
@Repository
public interface ClientsDao extends JpaRepository<Clients, String>, JpaSpecificationExecutor<Clients> {


    /**
     * 检查app名字是否重复
     * @param appName app名字
     * @return int @Param("appName"
     */
    @Query(value ="SELECT COUNT(*) FROM  clients WHERE app_name = ?1",nativeQuery = true)
    Integer getNameCountByappName(String appName);

    /**
     * 更新时检查名字
     * @param appName
     * @param id
     * @return
     */
    @Query(value ="SELECT COUNT(*) FROM  clients WHERE app_name = ?1 AND id != ?2",nativeQuery = true)
    Integer getUpdataCheckName(String appName, String id);


    /**
     * 检查apptoken是否重复
     * @param appToken apptoken
     * @return int
     */
    @Query(value ="SELECT COUNT(*) FROM  clients WHERE app_token = ?1",nativeQuery = true)
    Integer getTokenCountByAppToken( String appToken);

    @Query(value ="SELECT COUNT(*) FROM  clients WHERE app_token = ?1 AND id != ?2",nativeQuery = true)
    Integer getUpdataCheckToken(String appToken, String id);


    /**
     * 添加客户信息
     * @param appName app名字
     * @param appToken apptoken
     * @param disabled 是否禁用
     */
    @Modifying
    @Query(value ="INSERT INTO clients (app_name,app_token,disabled,created_at) VALUES (?1,?2,?3,?4)",nativeQuery = true)
    void addClients(String appName, String appToken, Integer disabled, Timestamp createTime);


    /**
     * 通过id 删除客户信息
     * @param id 主键id
     */
    @Modifying
    @Query(value = "DELETE FROM clients WHERE id = ?1",nativeQuery = true)
    void delClients(String id);

    /**
     * 通过id获取数据
     * @param id 主键id
     * @return Clients
     */
    @Query(value = "SELECT new com.mzfk.entity.Clients( id, app_name, app_token, created_at, disabled, updated_at) FROM `clients` WHERE id = ?1",nativeQuery = true)
    Clients findInfo(String id);



}