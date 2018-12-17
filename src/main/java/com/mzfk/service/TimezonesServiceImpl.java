package com.mzfk.service;


import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.TimezonesDao;
import com.mzfk.entity.PageInfo;
import com.mzfk.entity.Timezones;
import com.mzfk.pojo.TimezonesInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.utils.CommonUtils;
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
 * @Title: TimezonesServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: 时区
 * @date 2018/10/2915:47
 */
@Service
public class TimezonesServiceImpl implements TimezonesService {


    @Autowired
    private TimezonesDao timezonesDao;


    /**
     * 查找时区列表
     * @param params
     * @return AjaxResult
     */
    @Override
    public AjaxResult findTimezonesList(TimezonesInfoParam params) {
        AjaxResult result = new AjaxResult();
        PageInfo pageInfo = new PageInfo();
        // 获取列表信息
        Page<Timezones> list = timezonesDao.findAll(new Specification<Timezones>() {
            @Override
            public Predicate toPredicate(Root<Timezones> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1,params.getPageSize(),new Sort(Sort.Direction.DESC,"createdAt")));
        // 获取总数量
        long totalsize = timezonesDao.count();
        // 整理返回数据
        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setPageSize(params.getPageSize());
        pageInfo.setList(list.getContent());
        pageInfo.setTotalSize(totalsize);

        // 成功返回
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        result.setData(pageInfo);
        return result;


    }


    /**
     * 添加时区名
     * @return
     */
    @Transactional
    @Override
    public AjaxResult addTimezones(Timezones timezones) {
        AjaxResult result = new AjaxResult();

        // 验证名字是否重复
        Integer count = timezonesDao.checkName(timezones.getName());
        if(0 != count) {
            result.setMsg(ResultMsg.C_015);
            result.setSuccess(false);
            return result;
        }
        timezones.setCreatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 持久化步骤
        timezonesDao.save(timezones);
        // 成功返回
        result.setData(timezones);
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);

        return result;
    }


    /**
     * 更新时区信息
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult updateTimezones(Timezones timezones,String id) {
        AjaxResult result = new AjaxResult();

        // 验证名字是否重复
        Integer count = timezonesDao.checkNameTaUpdate(timezones.getId(),timezones.getName());
        if(0 != count) {
            result.setMsg(ResultMsg.C_015);
            result.setSuccess(false);
            return result;
        }

        // 更新操作
        timezonesDao.updateTimezones(timezones.getId(),timezones.getName(),timezones.getGmtOffset(),
                timezones.getCountryCode(),Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 成功返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        return result;
    }

    /**
     * 删除时区信息
     * @param id id
     * @return AjaxResult
     */
    @Override
    public AjaxResult delTimezones(String id) {
        AjaxResult result = new AjaxResult();

        timezonesDao.deleteById(id);
        // 成功返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        return result;
    }

}
