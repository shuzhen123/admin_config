package com.mzfk.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: Menus
 * @ProjectName 8Madmin_config
 * @Description: 菜单
 * @date 2018/10/3110:35
 */
@Entity
@Data
@Table(name = "menus")
public class Menus {

    @Id
    private String code; //char(10) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "order_num")
    private Integer ordernum; //tinyint(4) NOT NULL,

    @Column(name = "isnew")
    private Integer isNew; //tinyint(1) NOT NULL,

    @Column(name = "uri")
    private String uri; //varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,

    @Column(name = "status")
    private String status; //enum('draft','publish') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'draft',

    @Column(name = "created_at")
    private Timestamp createdAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "updated_at")
    private Timestamp updatedAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "deleted_at")
    private Timestamp deletedAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "sort")
    private Integer sort;




}
