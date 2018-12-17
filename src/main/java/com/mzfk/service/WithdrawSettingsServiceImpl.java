package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.WithdrawSettingsDao;
import com.mzfk.entity.PageInfo;
import com.mzfk.entity.WithdrawSettings;
import com.mzfk.pojo.WithdrawSettingsInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.utils.CommonUtils;
import com.mzfk.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;

/**
 * @author sz
 * @Title: WithdrawSettingsServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2316:47
 */
@Service
public class WithdrawSettingsServiceImpl implements WithdrawSettingsService {

    @Autowired
    private WithdrawSettingsDao withdrawSettingsDao;


    /**
     * 获取撤销设置列表
     * @param params 入参容器
     * @return
     */
    @Override
    public AjaxResult findWithdrawSettingsList(WithdrawSettingsInfoParam params) {
        AjaxResult result = new AjaxResult();
        PageInfo pageInfo = new PageInfo();
        // 查询列表
        Page<WithdrawSettings> list = withdrawSettingsDao.findAll(new Specification<WithdrawSettings>() {
            @Override
            public Predicate toPredicate(Root<WithdrawSettings> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        }, new PageRequest(params.getCurPage()-1,params.getPageSize(),new Sort(Sort.Direction.DESC,"createdAt")));
        // 查询总数据
        long totalsize = withdrawSettingsDao.count();
        // 添加到返回容器中
        pageInfo.setList(list.getContent());
        pageInfo.setTotalSize(totalsize);
        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setPageSize(params.getPageSize());
        // 返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(list.getContent());
        // 返回
        return result;
    }


    /**
     * 添加撤销信息
     * @return
     */
    @Override
    @Transactional
    public AjaxResult addWithdrawSettings(WithdrawSettings withdrawSettings) {
        AjaxResult result = new AjaxResult();

        withdrawSettings.setCreatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 数据持久化
        withdrawSettingsDao.save(withdrawSettings);

        result.setMsg(ResultMsg.C_001);
        result.setData(withdrawSettings);
        result.setSuccess(true);

        return result;
    }


    /**
     * 更新撤销信息
     * @param withdrawSettings
     */
    @Override
    @Transactional
    public void updateWithdrawSettings(WithdrawSettings withdrawSettings,String id) {
        WithdrawSettings info = withdrawSettingsDao.findById(id).get();
        // 将前端没有出过来的字段补全
        CopyUtils.copyProperties(withdrawSettings,info);

        // 添加更新时间
        info.setUpdatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 更新操作
        withdrawSettingsDao.save(info);
    }

    /**
     * 删除撤销信息
     * @param id 信息id
     */
    @Override
    @Transactional
    public void delWithdrawSettings(String id) {
        // 删除操作
        withdrawSettingsDao.deleteById(id);
    }



}
