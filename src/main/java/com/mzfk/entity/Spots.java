package com.mzfk.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: Spots
 * @ProjectName 8Madmin_config
 * @Description: spots 实体类
 * @date 2018/10/1914:23
 */
@Entity
@Data
@Table(name = "spots")
public class Spots {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "resptype",columnDefinition = "enum('large','promo') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'large' COMMENT '类型'")
    private String resptype;

    @Column(name = "ordernum",columnDefinition = "smallint(6) NOT NULL DEFAULT '0' COMMENT '顺序'")
    private Integer order;

    @Column(name = "image",columnDefinition = "char(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图'")
    private String image;

    @Column(name = "title",columnDefinition = "int(11) NOT NULL COMMENT '标题'")
    private Integer title;

    @Column(name = "object_type",columnDefinition = "enum('game','promotion') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'game' COMMENT '对象类型'")
    private String objectType;

    @Column(name = "object_id",columnDefinition = "char(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '对象id'")
    private String objectId;

    @Column(name = "created_at",columnDefinition = "timestamp NULL DEFAULT NULL COMMENT '创建时间'")
    private Timestamp createdAt;

    @Column(name = "updated_at",columnDefinition = "timestamp NULL DEFAULT NULL COMMENT '更新时间'")
    private Timestamp updatedAt;




}
