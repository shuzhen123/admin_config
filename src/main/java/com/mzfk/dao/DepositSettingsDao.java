package com.mzfk.dao;

import com.mzfk.entity.DepositSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sz
 * @Title: DepositSettingsDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2510:27
 */
@Repository
public interface DepositSettingsDao extends JpaRepository<DepositSettings,String>, JpaSpecificationExecutor<DepositSettings> {



}
