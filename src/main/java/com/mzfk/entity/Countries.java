package com.mzfk.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author sz
 * @Title: Countries
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2910:26
 */
@Data
@Entity
@Table(name = "countries")
public class Countries {


    @Id
    private String iso; //char(2) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "name")
    private String name; //varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "nice_name")
    private String niceName; //varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "iso3")
    private String iso3; //char(3) COLLATE utf8mb4_unicode_ci DEFAULT NULL,

    @Column(name = "numcode")
    private Integer numcode; //smallint(6) DEFAULT NULL,

    @Column(name = "phone_code")
    private Integer phoneCode; //smallint(6) NOT NULL,

    @Column(name = "currency_code")
    private String currencyCode; //char(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL,



}
