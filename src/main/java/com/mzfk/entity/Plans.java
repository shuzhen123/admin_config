package com.mzfk.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: Plans
 * @ProjectName 8Madmin_config
 * @Description: 计划
 * @date 2018/10/2610:33
 */
@Entity
@Data
@Table(name = "plans")
public class Plans {


    @Id
    private String code; //varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "brandFeeRate")
    private BigDecimal brandFeeRate; //decimal(5,3) NOT NULL,

    @Column(name = "financial_fee_rate")
    private BigDecimal financialFeeRate; //decimal(5,3) NOT NULL,

    @Column(name = "bonus_rate")
    private BigDecimal bonusRate; //decimal(5,3) NOT NULL DEFAULT '1.000',

    @Column(name = "is_default")
    private Integer isDefault; //tinyint(1) NOT NULL DEFAULT '0',

    @Column(name = "created_at")
    private Timestamp createdAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "updated_at")
    private Timestamp updatedAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "deleted_at")
    private Timestamp deletedAt; //timestamp NULL DEFAULT NULL,



}
