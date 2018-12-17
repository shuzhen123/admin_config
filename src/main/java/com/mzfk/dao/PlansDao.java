package com.mzfk.dao;

import com.mzfk.entity.Plans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: PlansDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2611:34
 */
@Repository
public interface PlansDao extends JpaRepository<Plans,String> , JpaSpecificationExecutor<Plans> {


    /**
     * 判断code 是否重复
     * @param code code
     * @return
     */
    @Query(value = "SELECT COUNT(*) FROM plans WHERE CODE = ?1",nativeQuery = true)
    Integer checkCountByCode(String code);

    /**
     * 去除自身，检查code是否重复
     * @param code code
     * @return
     */
    @Query(value = "SELECT COUNT(*) FROM plans WHERE CODE != ?1",nativeQuery = true)
    Integer checkCountExcCode(String code);



    @Modifying
    @Query(value = "UPDATE `plans` SET brand_fee_rate= ?2 ,financial_fee_rate= ?3 ,bonus_rate= ?4 ,is_default= ?5 ,updated_at= ?6 WHERE `code` = ?1 ",nativeQuery = true)
      void updatePlans(String code, BigDecimal brandFeeRate, BigDecimal financialFeeRate, BigDecimal bonusRate, Integer isDefault, Timestamp timestamp);

    @Modifying
    @Query(value = "DELETE FROM plans WHERE code = ?1",nativeQuery = true)
    void delPlans(String code);



}
