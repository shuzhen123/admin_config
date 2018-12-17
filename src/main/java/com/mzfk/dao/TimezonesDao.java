package com.mzfk.dao;

import com.mzfk.entity.Timezones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author sz
 * @Title: TimezonesDao
 * @ProjectName 8Madmin_config
 * @Description: 时区 dao
 * @date 2018/10/2915:45
 */
@Repository
public interface TimezonesDao extends JpaRepository<Timezones,String> , JpaSpecificationExecutor<Timezones> {


    /**
     * 验证名字是否重复
     * @param name 名字
     * @return Integer
     */
    @Query(value = "SELECT COUNT(*) FROM timezones WHERE name = ?1 ",nativeQuery = true)
    Integer checkName(Integer name);

    /**
     * 验证名字是否重复
     * @param id id
     * @param name name
     * @return
     */
    @Query(value = "SELECT COUNT(*) FROM timezones WHERE id != ?1 AND name = ?2 ",nativeQuery = true)
    Integer checkNameTaUpdate(String id, Integer name);

    /**
     *  更新时区信息
     * @param id
     * @param name
     * @param gmtOffset
     * @param countryCode
     * @param timestamp
     */
    @Modifying
    @Query(value = "UPDATE timezones SET country_code = ?4, name = ?2, gmt_offset = ?3, updated_at= ?5 WHERE id = ?1 ",nativeQuery = true)
    void updateTimezones(String id, Integer name, String gmtOffset, String countryCode, Timestamp timestamp);

    /**
     * 删除时区信息
     * @param id 主键id
     */
    @Modifying
    @Query(value = "DELETE FROM timezones WHERE id = ?1",nativeQuery = true)
    void delTimezones(String id);
}
