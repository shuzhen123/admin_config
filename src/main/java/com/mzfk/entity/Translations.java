package com.mzfk.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author sz
 * @Title: Translations
 * @ProjectName 8Madmin_config
 * @Description: 译文
 * @date 2018/10/2214:59
 */
@Entity
@Data
@Table(name = "translations")
public class Translations {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id; //int(10) unsigned NOT NULL AUTO_INCREMENT,

    @Column(name = "group_id")
    private Integer groupId; //int(10) unsigned NOT NULL,

    @Column(name = "value")
    private String value; //text COLLATE utf8mb4_unicode_ci,

    @Column(name = "locale")
    private String locale; //varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,

}
