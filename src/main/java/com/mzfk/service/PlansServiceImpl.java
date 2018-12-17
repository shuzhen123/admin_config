package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.PlansDao;
import com.mzfk.entity.PageInfo;
import com.mzfk.entity.Plans;
import com.mzfk.pojo.PlansInfoParam;
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
 * @Title: PlansServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2611:38
 */
@Service
public class PlansServiceImpl implements PlansService {

    @Autowired
    private PlansDao plansDao;


    /**
     * 获取计划列表
     * @param params 分页参数
     * @return
     */
    @Override
    public AjaxResult findPlansList(PlansInfoParam params) {
        AjaxResult result = new AjaxResult();
        // 返回参数容器
        PageInfo pageInfo = new PageInfo();
        // 获取列表
        Page<Plans> list = plansDao.findAll(new Specification<Plans>() {
            @Override
            public Predicate toPredicate(Root<Plans> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1 , params.getPageSize(),new Sort(Sort.Direction.DESC,"createdAt")));
        // 获取数量
        long totalsize = plansDao.count();
        pageInfo.setTotalSize(totalsize);
        pageInfo.setList(list.getContent());
        pageInfo.setPageSize(params.getPageSize());
        pageInfo.setCurPage(params.getCurPage());

        // 构建返回
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        result.setData(pageInfo);
        return result;
    }



    /**
     * 添加计划
     * @param plans
     * @return
     */
    @Transactional
    @Override
    public AjaxResult addPlans(Plans plans) {
        AjaxResult result = new AjaxResult();
        // 判断code 是否重复
        Integer count = plansDao.checkCountByCode(plans.getCode());
        if (0 != count) {
            // 当前需要添加的code（编码）已存在
            result.setMsg(ResultMsg.C_012);
            return result;
        }
        plans.setCreatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        plansDao.save(plans);
        // 返回数据
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(plans);
        return result;
    }

    /**
     *  updatePlans
     * @param plans
     * @return
     */
    @Transactional
    @Override
    public AjaxResult updatePlans(Plans plans,String code) {
        AjaxResult result = new AjaxResult();
        // 获取系统当前时间作为更新时间
        Timestamp timestamp = Timestamp.valueOf(CommonUtils.getChinaTime());
        // 更新操作
        plansDao.updatePlans(plans.getCode(),plans.getBrandFeeRate(),plans.getFinancialFeeRate(),
                plans.getBonusRate(),plans.getIsDefault(),timestamp);

        // 成功返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        return result;
    }

    /**
     * 删除记录
     * @param code code
     */
    @Override
    @Transactional
    public void delPlans(String code) {
        // 删除操作
        plansDao.delPlans(code);
    }
}
