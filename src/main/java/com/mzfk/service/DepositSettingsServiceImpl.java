package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.DepositSettingsDao;
import com.mzfk.entity.DepositSettings;
import com.mzfk.pojo.DepositSettingsInfoParam;
import com.mzfk.entity.PageInfo;
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
 * @Title: DepositSettingsServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: 存款设置 service
 * @date 2018/10/2510:33
 */
@Service
public class DepositSettingsServiceImpl implements DepositSettingsService {


    @Autowired
    private DepositSettingsDao depositSettingsDao;


    /**
     * 获取存款相关设置列表
     * @param params 分页参数
     * @return
     */
    @Override
    public AjaxResult findDepositSettingsList(DepositSettingsInfoParam params) {
        AjaxResult result = new AjaxResult();
        PageInfo pageInfo = new PageInfo();
        // 获取列表
        Page<DepositSettings> list = depositSettingsDao.findAll(new Specification<DepositSettings>() {
            @Override
            public Predicate toPredicate(Root<DepositSettings> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1,params.getPageSize(), new Sort(Sort.Direction.DESC,"createdAt")));
        // 获取总数量
        long totalsize = depositSettingsDao.count();

        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setPageSize(params.getPageSize());
        pageInfo.setTotalSize(totalsize);
        pageInfo.setList(list.getContent());

        // 整体返回参数
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        result.setData(pageInfo);
        return result;
    }


    /**
     *  添加存款相关设置
     * @param depositSettings
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult addDepositSettings(DepositSettings depositSettings) {
        AjaxResult result = new AjaxResult();
        //
        depositSettings.setCreatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));

        depositSettingsDao.save(depositSettings);

        // 成功返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(depositSettings);
        return result;
    }


    /**
     *  更新存款相关设置
     * @param depositSettings
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult updateDepositSettings(DepositSettings depositSettings,String id) {
        AjaxResult result = new AjaxResult();
        // 获取数据库中完整的原来的数据
        DepositSettings info = depositSettingsDao.findById(id).get();
        // 将前端没有出过来的字段补全
        CopyUtils.copyProperties(depositSettings,info);
        // 添加更新时间
        info.setUpdatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 更新操作
        depositSettingsDao.save(info);
        // 成功返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        return result;
    }

    /**
     * 通过id 删除记录
     * @param id id
     */
    @Override
    @Transactional
    public void delDepositSettings(String id) {
        depositSettingsDao.deleteById(id);
    }
}



