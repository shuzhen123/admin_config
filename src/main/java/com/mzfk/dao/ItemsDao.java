package com.mzfk.dao;

import com.mzfk.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author sz
 * @Title: ItemsDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2513:38
 */
@Repository
public interface ItemsDao extends JpaRepository<Items,String>, JpaSpecificationExecutor<Items> {


    /**
     * 检查标题名字是否重复
     * @param title 标题
     * @return Integer
     */
    @Query(value = "SELECT COUNT(*) FROM items WHERE title = ?1",nativeQuery = true)
    Integer checktitle(String title);


    /**
     *  更新操作
     * @param id id
     * @param title 标题
     * @param description 描述
     * @param timestamp 更新时间
     */
    @Modifying
    @Query(value = "UPDATE items SET title = ?2,description = ?3,updated_at = ?4 WHERE id = ?1",nativeQuery = true)
    void updateItems(String id, String title, String description, Timestamp timestamp);


    /**
     * 通过id 删除信息
     * @param id 主键id
     */
    @Modifying
    @Query(value = "DELETE FROM items WHERE id = ?1",nativeQuery = true)
    void delItemsById(String id);
}
