package com.mzfk.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: RankConditions
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/110:59
 */
@Entity
@Data
@Table(name = "rank_conditions")
public class RankConditions {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id; //int(10) unsigned NOT NULL AUTO_INCREMENT,


    @Column(name = "currency")
    private String currency; //char(3) COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "designation")
    private String designation; //enum('deposit','bets') COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "comparison")
    private String comparison; //enum('eq','gt','gte','lt','lte') COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "value")
    private Integer value; //int(10) unsigned NOT NULL,

    @Column(name = "type")
    private String type; //enum('grading','upgrading') COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "logicality")
    private String logicality; //enum('AND','OR') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'AND',

    @Column(name = "created_at")
    private Timestamp created_at; //timestamp NULL DEFAULT NULL,

    @Column(name = "updated_at")
    private Timestamp updated_at; //timestamp NULL DEFAULT NULL,

    @Column(name = "rank_code")
    private String rankCode;


}
