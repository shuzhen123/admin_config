package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.FailedJobsDao;
import com.mzfk.entity.FailedJobs;
import com.mzfk.pojo.FailedJobsInfoParam;
import com.mzfk.entity.PageInfo;
import com.mzfk.pojo.AjaxResult;
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

/**
 * @author sz
 * @Title: FailedJobsServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: 失败的工作
 * @date 2018/10/3014:13
 */
@Service
public class FailedJobsServiceImpl implements FailedJobsService {

    @Autowired
    FailedJobsDao failedJobsDao;


    /**
     * 获取失败工作列表
     * @param params 分页参数
     * @return AjaxResult
     */
    @Override
    public AjaxResult findFailedJobsList(FailedJobsInfoParam params) {
        AjaxResult result = new AjaxResult();
        // 获取失败工作列表信息
        PageInfo pageInfo = new PageInfo();
        Page<FailedJobs> list = failedJobsDao.findAll(new Specification<FailedJobs>() {
            @Override
            public Predicate toPredicate(Root<FailedJobs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1,params.getPageSize(),new Sort(Sort.Direction.DESC,"failedAt")));
        // 获取总数量
        long totalsize = failedJobsDao.count();
        pageInfo.setList(list.getContent());
        pageInfo.setTotalSize(totalsize);
        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setPageSize(params.getPageSize());
        // 数据返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(pageInfo);
        return result;
    }

    /**
     * 添加失效信息
     * @param failedJobs
     * @return AjaxResult ipz522
     */
    @Override
    @Transactional
    public AjaxResult addFailedJobsModel(FailedJobs failedJobs) {
        AjaxResult result = new AjaxResult();

        // 持久化步骤
        failedJobsDao.save(failedJobs);
        // 数据返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(failedJobs);
        return result;
    }


}
