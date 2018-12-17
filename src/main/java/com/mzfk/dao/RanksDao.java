package com.mzfk.dao;

import com.mzfk.entity.Ranks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author sz
 * @Title: RanksDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/19:45
 */
@Repository
public interface RanksDao extends JpaRepository<Ranks,String>, JpaSpecificationExecutor<Ranks> {


    /**
     *  检测code 是否重复
     * @param code code
     * @return Integer
     */
    @Query(value = "SELECT COUNT(*) FROM ranks WHERE `code` = ?1 ",nativeQuery = true)
    Integer checkCodeCount(String code);


    /**
     *  更新等级
     * @param code code
     * @param level  标准
     * @param defaults 默认
     * @param product 产品
     * @return AjaxResult
     */
    @Modifying
    @Query(value = "UPDATE ranks SET `level` = ?2,defaults = ?3,product= ?4,updated_at = ?5 WHERE `code`= ?1 ",nativeQuery = true)
    void uptateRanks(String code, Integer level, Integer defaults, String product, Timestamp timestamp);
}
