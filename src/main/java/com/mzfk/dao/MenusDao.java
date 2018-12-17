package com.mzfk.dao;

import com.mzfk.entity.Menus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author sz
 * @Title: MenusDao
 * @ProjectName 8Madmin_config
 * @Description: 菜单
 * @date 2018/10/3110:40
 */
@Repository
public interface MenusDao extends JpaRepository<Menus,String>, JpaSpecificationExecutor<Menus> {


    /**
     *  检测需要添加的code 是否重复
     * @param code code
     * @return Integer
     */
    @Query(value = "SELECT COUNT(*) FROM menus WHERE `code` = ?1",nativeQuery = true)
    Integer checkCode(String code);

    /**
     *  更新菜单
     * @param code code
     * @param order 顺序
     * @param isNew 是否是新的
     * @param uri 链接
     * @param status 状态
     * @return AjaxResult
     */
//    @Modifying
//    @Query(value = "UPDATE menus SET `order_num` = ?2, isnew = ?3,uri =?4,`status` = ?5,sort = ?6,updated_at = ?7 WHERE `code` = ?1",nativeQuery = true)
//    void updateMenus(String code, Integer order, Integer isNew, String uri, String status, Integer sort, Timestamp timestamp);

    /**
     *  检测需要更新的order 是否重复
     * @param code 主键
     * @param ordernum order
     * @return Integer
     */
    @Query(value = "SELECT COUNT(*) FROM menus WHERE order_num = ?2 AND `code` != ?1",nativeQuery = true)
    Integer checkOrder(String code, Integer ordernum);

    /**
     *  检测需要添加的order 是否重复
     * @param ordernum order
     * @return Integer
     */
    @Query(value = "SELECT COUNT(*) FROM menus WHERE order_num = ?1",nativeQuery = true)
    Integer addcheckOrder(Integer ordernum);


}
