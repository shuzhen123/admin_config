package com.mzfk.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: FailedJobs
 * @ProjectName 8Madmin_config
 * @Description: 失败的工作
 * @date 2018/10/3011:27
 */
@Entity
@Data
@Table(name = "failed_jobs")
public class FailedJobs {



    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;//bigint(20) unsigned NOT NULL AUTO_INCREMENT,

    @Column(name = "connection")
    private String connection;//text COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "queue")
    private String queue;//text COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "payload")
    private String payload;//longtext COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "exception")
    private String exception;//longtext COLLATE utf8mb4_unicode_ci NOT NULL,

    @Column(name = "failed_at")
    private Timestamp failedAt;//timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,


}
