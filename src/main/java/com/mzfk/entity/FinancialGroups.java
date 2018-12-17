package com.mzfk.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: FinancialGroups
 * @ProjectName 8Madmin_config
 * @Description: 财团model
 * @date 2018/10/2415:13
 */
@Entity
@Data
@Table(name = "financial_groups")
public class FinancialGroups {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id; //char(36) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "name")
    private String name; //varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "currency_code")
    private String currencyCode; //char(3) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "max_total_balance")
    private BigInteger maxTotalBalance; //bigint(20) unsigned NOT NULL COMMENT '最大余额',

    @Column(name = "max_daily_deposit")
    private BigInteger maxDailyDeposit; //bigint(20) unsigned NOT NULL COMMENT '单日存款最大限额',

    @Column(name = "max_daily_withdraw")
    private BigInteger maxDailyWithdraw; //bigint(20) unsigned NOT NULL COMMENT '单日最大提款限额',

    @Column(name = "min_withdraw_amount")
    private BigInteger minWithdrawAmount; //bigint(20) unsigned NOT NULL COMMENT '单笔提款最低金额',

    @Column(name = "max_withdraw_amount")
    private BigInteger maxWithdrawAmount; //bigint(20) unsigned NOT NULL COMMENT '单笔提款最高金额',


    @Column(name = "is_default")
    private Integer isDefault; //tinyint(1) NOT NULL DEFAULT '0',

    @Column(name = "created_at")
    private Timestamp createdAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "updated_at")
    private Timestamp updatedAt; //timestamp NULL DEFAULT NULL,


}
