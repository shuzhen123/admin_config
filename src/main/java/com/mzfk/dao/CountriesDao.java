package com.mzfk.dao;

import com.mzfk.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author sz
 * @Title: CountriesDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2911:07
 */
@Repository
public interface CountriesDao extends JpaRepository<Countries,String>, JpaSpecificationExecutor<Countries> {

    /**
     *  判断iOS 是否重复
     * @param iso 主键
     * @return int
     */
    @Query(value = "SELECT COUNT(*) FROM countries WHERE iso = ?1 ",nativeQuery = true)
    Integer checkIsoName(String iso);


}
