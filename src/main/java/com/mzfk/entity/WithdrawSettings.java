package com.mzfk.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: WithdrawSettings
 * @ProjectName 8Madmin_config
 * @Description: 撤销设置
 * @date 2018/10/2316:11
 */
@Entity
@Data
@Table(name = "withdraw_settings")
public class WithdrawSettings {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "financial_group_id")
    private String financialGroupId; //char(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '财务组id',

    @Column(name = "bank_code")
    private String bankCode; //varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '银行代码',

    @Column(name = "created_at")
    private Timestamp createdAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "updated_at")
    private Timestamp updatedAt; //timestamp NULL DEFAULT NULL,

}
