package com.mzfk.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: ColumnItems
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/2614:31
 */
@Data
@Entity
@Table(name = "column_items")
public class ColumnItems {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id; //varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "column_id",columnDefinition = "varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String columnId;

    @Column(name = "objectId",columnDefinition = "char(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String objectId;

    @Column(name = "isTall",columnDefinition = "tinyint(1) NOT NULL DEFAULT '0'")
    private Integer isTall;

    @Column(name = "isSuperTall",columnDefinition = "tinyint(1) NOT NULL DEFAULT '0'")
    private Integer isSuperTall;

    @Column(name = "isCollection",columnDefinition = "tinyint(1) NOT NULL DEFAULT '0'")
    private Integer isCollection;

    @Column(name = "coverImage",columnDefinition = "varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String coverImage;

    @Column(name = "collectionName",columnDefinition = "int(11) DEFAULT NULL")
    private Integer collectionName;

    @Column(name = "url",columnDefinition = "varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String url;

    @Column(name = "order",columnDefinition = "enum('0','1') COLLATE utf8mb4_unicode_ci NOT NULL")
    private String order;

    @Column(name = "created_at",columnDefinition = "timestamp NULL DEFAULT NULL")
    private Timestamp createdAt;

    @Column(name = "updated_at",columnDefinition = "timestamp NULL DEFAULT NULL")
    private Timestamp updatedAt;


}
