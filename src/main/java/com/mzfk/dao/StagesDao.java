package com.mzfk.dao;

import com.mzfk.entity.Stages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: StagesDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2515:54
 */
@Repository
public interface StagesDao extends JpaRepository<Stages,String>, JpaSpecificationExecutor<Stages> {


    /**
     * 检查planCode 是否重复
     * @param planCode planCode
     * @return Integer
     */
    @Query(value = "SELECT COUNT(*) FROM stages WHERE plan_code = ?1 ",nativeQuery = true)
    Integer checkPlanCode(String planCode);

    @Query(value = "SELECT COUNT(*) FROM stages WHERE plan_code = ?2  AND id != ?1",nativeQuery = true)
    Integer checkPlanCodeExcOneself(String id, String planCode);


    /**
     * 通过id 删除
     * @param id
     */
    @Modifying
    @Query(value = "DELETE FROM stages WHERE id = ?1",nativeQuery = true)
    void delStages(String id);

    /**
     * 检测是否有重复存在 （同时满足 planCode，level） 添加时检测
     * @param planCode planCode
     * @param level level
     * @return Integer
     */
    @Query(value = "SELECT COUNT(*) FROM `stages` WHERE plan_code = ?1 AND `level` = ?2 ",nativeQuery = true)
    Integer checkCount(String planCode, Integer level);

    /**
     * 更新时检测 检测是否有重复存在 （同时满足 planCode，level）
     * @param planCode planCode
     * @param level level
     * @param id id
     * @return Integer
     */
    @Query(value = "SELECT COUNT(*) FROM `stages` WHERE plan_code = ?1 AND `level` = ?2  AND id != ?3",nativeQuery = true)
    Integer checkCountAndId(String planCode, Integer level, String id);
}
