package com.mzfk.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: Ranks
 * @ProjectName 8Madmin_config
 * @Description: 等级
 * @date 2018/11/19:23
 */
@Entity
@Data
@Table(name = "ranks")
public class Ranks {


    @Id
    private String code; //varchar(15) COLLATE utf8mb4_eyan-003 NOT NULL,

    @Column(name = "level")
    private Integer level; //tinyint(4) NOT NULL DEFAULT '0',

    @Column(name = "defaults")
    private Integer defaults; //   *********  原来数据库中的字段是default ，但default 是java中的关键字，所以这里多家了一个s，改了数据库，上线后记得修改线上库  *********

    @Column(name = "product")
    private String product; //varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "created_at")
    private Timestamp createdAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "updatedAt")
    private Timestamp updatedAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "deleted_at")
    private Timestamp deletedAt; //timestamp NULL DEFAULT NULL,





}
