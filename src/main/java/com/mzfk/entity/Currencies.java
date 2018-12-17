package com.mzfk.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sz
 * @Title: Currencies
 * @ProjectName 8Madmin_config
 * @Description: 货币
 * @date 2018/10/1911:34
 */
@Data
@Entity
@Table(name = "currencies")
public class Currencies {

    @Id
    private String code; //varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,


    @Column(name = "name", nullable = false)
    private String name; //varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,


    @Column(name="symbol")
    private String symbol; //varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标志',

    @Column(name="supported")
    private int supported; //tinyint(1) NOT NULL DEFAULT '0' COMMENT '支持',

    @Column(name="symbol_after")
    private int symbolAfter; //tinyint(1) NOT NULL DEFAULT '0' COMMENT '后标志',



}
