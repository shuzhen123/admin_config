package com.mzfk.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: Timezones
 * @ProjectName 8Madmin_config
 * @Description: Timezones
 * @date 2018/10/2910:08
 */
@Entity
@Data
@Table(name = "timezones")
public class Timezones {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "name")
    private Integer name; //int(10) unsigned NOT NULL,

    @Column(name = "gmt_offset")
    private String gmtOffset; //char(6) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "created_at")
    private Timestamp createdAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "updated_at")
    private Timestamp updatedAt; //timestamp NULL DEFAULT NULL,


    @Column(name = "country_code")
    private String countryCode;


}
