package com.mzfk.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author sz
 * @Title: Migrations
 * @ProjectName 8Madmin_config
 * @Description: 数据库迁移
 * @date 2018/10/2314:12
 */
@Entity
@Data
@Table(name = "migrations")
public class Migrations {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id ;

    @Column(name = "migration")
    private String migration;   // 迁移

    @Column(name = "batch")
    private Integer batch;      // 批次


}
