package com.mzfk.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: Items
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2513:33
 */
@Entity
@Data
@Table(name = "items")
public class Items {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id; //int(10) unsigned NOT NULL AUTO_INCREMENT,

    @Column(name = "title")
    private String title; //varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "description")
    private String description; //varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "created_at")
    private Timestamp createdAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "updated_at")
    private Timestamp updatedAt; //timestamp NULL DEFAULT NULL,



}
