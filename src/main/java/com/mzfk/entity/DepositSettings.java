package com.mzfk.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: DepositSettings
 * @ProjectName 8Madmin_config
 * @Description: 存款设置
 * @date 2018/10/2510:19
 */
@Data
@Entity
@Table(name = "deposit_settings")
public class DepositSettings {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id; //int(10) unsigned NOT NULL AUTO_INCREMENT,

    @Column(name = "financial_group_id")
    private String financialGroupId; //char(36) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "method")
    private String method; //char(10) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "psp")
    private String psp; //char(36) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "min_amount")
    private BigInteger minAmount; //bigint(20) unsigned NOT NULL,

    @Column(name = "max_amount")
    private BigInteger maxAmount; //bigint(20) unsigned NOT NULL,

    @Column(name = "created_at")
    private Timestamp createdAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "updated_at")
    private Timestamp updatedAt; //timestamp NULL DEFAULT NULL,


}







