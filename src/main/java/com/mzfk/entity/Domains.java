package com.mzfk.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: Domains
 * @ProjectName 8Madmin_config
 * @Description: 域名
 * @date 2018/10/2310:10
 */
@Data
@Entity
@Table(name = "domains")
public class Domains {

    @Id
    @Column(name = "domain")
    private String domain; //varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "user_id")
    private String userId; //char(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,

    @Column(name = "created_at")
    private Timestamp createdAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "updated_at")
    private Timestamp updatedAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "deleted_at")
    private Timestamp deletedAt; //timestamp NULL DEFAULT NULL,




}
