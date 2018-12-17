package com.mzfk.dao;

import com.mzfk.entity.Spots;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author sz
 * @Title: SpotsDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/1914:37
 */
@Repository
public interface SpotsDao extends JpaRepository<Spots, String>, JpaSpecificationExecutor<Spots> {

    /**
     * 获取spots列表
     * @param pageRequest
     * @return
     */

    @Query(value = "SELECT s.* FROM spots s ORDER BY s.created_at DESC ",nativeQuery=true)
    List<Spots> findSpotsList(PageRequest pageRequest);

    /**
     * 删除标识id
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM spots WHERE id=?1",nativeQuery=true)
    void delSpots(String ids);




    /**
     * 插入
     * @param
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO spots (`id`,`type`,title,object_type,object_id,image,created_at) " +
            "VALUES (?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    void addSpots(String id,String type, Integer title, String objectType, String objectId, String image, Timestamp createTiem);

    /**
     * 插入
     * @param
     */
    @Modifying
    @Query(value = "UPDATE `spots` SET `type` = ?2,`order` = ?3 ,image = ?4,title=?5,objectType=?6,objectId=?7,updated_at = ?8 WHERE id = ?1",nativeQuery = true)
    void updateSpots(String id, String type, Integer order, String image, Integer title, String objectType, String objectId, Timestamp timestamp);
}
