package com.mzfk.dao;

import com.mzfk.entity.Domains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @author sz
 * @Title: DomainsDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2310:14
 */
@Repository
public interface DomainsDao extends JpaRepository<Domains,String>, JpaSpecificationExecutor<Domains> {


    /**
     * 检查domain 是否重复
     * @param domain 系统域名
     * @return
     */
    @Transactional
    @Query(value = "SELECT COUNT(*) FROM domains WHERE domain = ?1",nativeQuery = true)
    int checkDomainCount(String domain);

    /**
     * 添加系统域名
     * @param domain 系统域名
     * @param userId 用户id
     * @param timestamp 创建时间
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO domains (domain,user_id,created_at) VALUES (?1,?2,?3)" ,nativeQuery = true)
    void addDomains(String domain, String userId, Timestamp timestamp);
}
