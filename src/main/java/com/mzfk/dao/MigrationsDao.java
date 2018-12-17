package com.mzfk.dao;

import com.mzfk.entity.Migrations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author sz
 * @Title: MigrationsDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2314:20
 */
@Repository
public interface MigrationsDao extends JpaRepository<Migrations,String>, JpaSpecificationExecutor<Migrations> {

    /**
     *添加数据迁移记录
     * @param migration
     * @param batch
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO migrations (migration,batch) VALUES (?1,?2)",nativeQuery = true)
    void addMigrations(String migration, String batch);
}
