package com.mzfk.dao;

import com.mzfk.entity.Currencies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author sz
 * @Title: CurrenciesDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/210:58
 */
@Repository
public interface CurrenciesDao extends JpaRepository<Currencies,String> , JpaSpecificationExecutor<Currencies> {


    /**
     *  判断code 是否重复
     * @param code code
     * @return Integer
     */
    @Query(value = "SELECT COUNT(*) FROM currencies WHERE `code` = ?1 ",nativeQuery = true)
    Integer checkCode(String code);







}
