package com.mzfk.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author sz
 * @Title: Clients
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2216:51
 */
@Data
@Entity
@Table(name = "clients")
public class Clients {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "app_name")
    private String appName; //varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "app_token")
    private String appToken; //varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "disabled")
    private Integer disabled; //tinyint(1) NOT NULL DEFAULT '0',

    @Column(name = "created_at")
    private Timestamp createdAt; //timestamp NULL DEFAULT NULL,

    @Column(name = "updated_at")
    private Timestamp updatedAt; //timestamp NULL DEFAULT NULL,

}
