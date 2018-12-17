package com.mzfk.dao;

import com.mzfk.entity.Banks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author sz
 * @Title: BanksDAO
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/1912:45
 */
@Repository
public interface BanksDao extends JpaRepository<Banks, String>, JpaSpecificationExecutor<Banks> {


    /**
     * 查询银行列表
     * @return
     */
    @Query(value = "SELECT b.NAME, b.CODE, b.currency_code currencyCode, b.created_at createdAt, b.updated_at updatedAt, b.deleted_at deletedAt FROM banks b",nativeQuery=true)
    List<Banks> findBanksList(Pageable pageable);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM banks WHERE code in (1?)",nativeQuery=true)
    void delBanks(List<String> list);

    /**
     * 更新操作
     * @param name 名字
     * @param currencycode 国家code
     * @param code code
     */
    @Modifying
    @Query(value = "UPDATE banks SET name = ?1, code = ?3, currency_code = ?2, updated_at = ?4 WHERE code = ?3 ",nativeQuery = true)
    void banupdateks(String name, String currencycode, String code, Timestamp time);
}
