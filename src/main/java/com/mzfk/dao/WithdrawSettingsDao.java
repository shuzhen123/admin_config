package com.mzfk.dao;

import com.mzfk.entity.WithdrawSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: WithdrawSettingsDao
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2316:41
 */
@Repository
public interface WithdrawSettingsDao extends JpaRepository<WithdrawSettings,String>, JpaSpecificationExecutor<WithdrawSettings> {



    /**
     * 添加撤销信息记录
     * @param financialGroupId 财务组的id
     * @param bankCode 银行的code
     */
    @Modifying
    @Query(value = "INSERT INTO withdraw_settings (financial_group_id,bank_code,created_at) VALUES (?1,?2,?3)",nativeQuery = true)
    void addWithdrawSettings(String financialGroupId, String bankCode, Timestamp timestamp);



}
