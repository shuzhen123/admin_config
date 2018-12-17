package com.mzfk.dao;

import com.mzfk.entity.Translations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;

/**
 * @author sz
 * @Title: TranslationsDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2215:16
 */
@Repository
public interface TranslationsDao extends JpaRepository<Translations, String>, JpaSpecificationExecutor<Translations> {


    /**
     * 添加译文
     * @param groupId
     * @param value
     * @param locale
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO translations (group_id,value,locale) VALUES (?1,?2,?3)",nativeQuery = true)
    void addTranslations(String groupId, Integer value, String locale);

    /**
     * 通过id 删除译文
     * @param id id
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM translations WHERE id = ?1",nativeQuery = true)
    void delTranslations(String id);
}
