package com.mzfk.dao;

import com.mzfk.entity.FailedJobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * @author sz
 * @Title: FailedJobsDao
 * @ProjectName 8Madmin_config
 * @Description: 失败的工作 dao
 * @date 2018/10/3014:11
 */
@Repository
public interface FailedJobsDao extends JpaRepository<FailedJobs, String>, JpaSpecificationExecutor<FailedJobs> {


}
