package com.mzfk.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: Banks
 * @ProjectName 8Madmin_config
 * @Description: 8Madmin_config
 * @date 2018/10/1911:30
 */
@Data
@Entity
@Table(name = "banks")
public class Banks {

    @Id
    private String code; //varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '代码',

    @Column(name="name")
    private String name; //varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name="currency_code")
    private String currencyCode; //char(3) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '货币代码',

    @Column(name="created_at")
    private Timestamp createdAt; //timestamp NULL DEFAULT NULL COMMENT '创建时间',

    @Column(name="updated_at")
    private Timestamp updatedAt; //timestamp NULL DEFAULT NULL COMMENT '更新时间',

    @Column(name="deleted_at")
    private Timestamp deletedAt; //timestamp NULL DEFAULT NULL COMMENT '删除时间',


}
