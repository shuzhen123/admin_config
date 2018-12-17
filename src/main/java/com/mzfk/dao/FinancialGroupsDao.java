package com.mzfk.dao;

import com.mzfk.entity.FinancialGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: FinancialGroupsDao
 * @ProjectName 8Madmin_config
 * @Description: 财团dao
 * @date 2018/10/2415:29
 */
@Repository
public interface FinancialGroupsDao extends JpaRepository<FinancialGroups,String>, JpaSpecificationExecutor<FinancialGroups> {


    @Query(value = "SELECT COUNT(*) FROM financial_groups WHERE name = ?1",nativeQuery = true)
    Integer checkName(String name);

    @Query(value = "SELECT COUNT(*) FROM financial_groups WHERE name = ?1 AND id != ?2",nativeQuery = true)
    int checkNameForUpdata(String name, String id);

    /**
     *添加财团信息
     * @param name 名字
     * @param currencyCode 货币代码
     * @param maxTotalBalance 最大余额
     * @param maxDailyDeposit 单日存款最大限额
     * @param maxDailyWithdraw 单日最大提款限额
     * @param minWithdrawAmount 单笔提款最低金额
     * @param maxWithdrawAmount 单笔提款最高金额
     * @param isDefault 默认
     */
    @Modifying
    @Query(value = "INSERT INTO financial_groups (id,name,currency_code,max_total_balance,max_daily_deposit,max_daily_withdraw,min_withdraw_amount,max_withdraw_amount,is_default,created_at) " +
            "VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)",nativeQuery = true)
    void addFinancialGroups(String id, String name, String currencyCode, BigInteger maxTotalBalance, BigInteger maxDailyDeposit, BigInteger maxDailyWithdraw, BigInteger minWithdrawAmount, BigInteger maxWithdrawAmount, Integer isDefault, Timestamp time);



}
