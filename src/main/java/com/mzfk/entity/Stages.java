package com.mzfk.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: Stages
 * @ProjectName 8Madmin_config
 * @Description: 阶段模块bean
 * @date 2018/10/2515:30
 */
@Data
@Entity
@Table(name = "stages")
public class Stages {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "plan_code")
    private String planCode; //varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "level")
    private Integer level; //smallint(6) NOT NULL,

    @Column(name = "active_users")
    private Integer activeUsers; //int(10) unsigned NOT NULL,

    @Column(name = "min_profit")
    private Integer minProfit; //int(10) unsigned NOT NULL,

    @Column(name = "max_profit")
    private Integer maxProfit; //int(10) unsigned NOT NULL,

    @Column(name = "rate")
    private BigDecimal rate; //decimal(3,2) NOT NULL,

    @Column(name = "created_at")
    private Timestamp createdAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "updated_at")
    private Timestamp updatedAt; //timestamp NULL DEFAULT NULL,



}
