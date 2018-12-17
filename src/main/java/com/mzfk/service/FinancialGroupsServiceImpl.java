package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.FinancialGroupsDao;
import com.mzfk.entity.FinancialGroups;
import com.mzfk.pojo.FinancialGroupsInfoParam;
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
 * @Title: FinancialGroupsServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: 财团service
 * @date 2018/10/2415:32
 */
@Service
public class FinancialGroupsServiceImpl implements FinancialGroupsService {

    @Autowired
    private FinancialGroupsDao financialGroupsDao;


    /**
     *获取财团列表
     * @param params
     * @return
     */
    @Override
    public AjaxResult findFinancialGroupsList(FinancialGroupsInfoParam params) {
        AjaxResult result = new AjaxResult();
        PageInfo pageInfo = new PageInfo();
        // 获取列表
        Page<FinancialGroups> list = financialGroupsDao.findAll(new Specification<FinancialGroups>() {
            @Override
            public Predicate toPredicate(Root<FinancialGroups> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1,params.getPageSize(),new Sort(Sort.Direction.DESC,"id")));
        // 获取总数量
        long totalsize = financialGroupsDao.count();
        // 整体返沪数据模型
        pageInfo.setList(list.getContent());
        pageInfo.setTotalSize(totalsize);
        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setTotalPage(params.getTotalPage());

        //数据返回
        result.setData(pageInfo);
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;

    }


    /**
     * 添加财团信息
     * @param financialGroups  财团信息
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult addFinancialGroupsModel(FinancialGroups financialGroups) {
        AjaxResult result = new AjaxResult();
        // 判断当前需要添加的财团的名字是否已存在
        Integer nameCount = financialGroupsDao.checkName(financialGroups.getName());
        if (0 != nameCount) {
            result.setMsg(ResultMsg.C_007);
            result.setSuccess(false);
            return result;
        }

        // 添加创建时间
        financialGroups.setCreatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 持久化步骤
        financialGroupsDao.save(financialGroups);
        // 成功 返回
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        result.setData(financialGroups);
        return result;
    }



    /**
     *  更新财团信息
     * @param financialGroups 财团信息
     * @return AjaxResult
     */
    @Override
    public AjaxResult updateFinancialGroups(FinancialGroups financialGroups,String id ) {
        AjaxResult result = new AjaxResult();
        // 判断当前需要添加的财团的名字是否已存在
        int nameCount = financialGroupsDao.checkNameForUpdata(financialGroups.getName(),financialGroups.getId());
        if (0 != nameCount) {
            result.setMsg(ResultMsg.C_127);
            result.setSuccess(false);
            return result;
        }
        // 查找原来得数据
        FinancialGroups info = financialGroupsDao.findById(id).get();
        // 将前端没有出过来的字段补全
        CopyUtils.copyProperties(financialGroups,info);
        // 添加更新时间
        info.setUpdatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        //更新操作
        financialGroupsDao.save(info);
        // 成功返回
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
    }






    /**
     * 通过id  删除主键id
     * @param id 主键id
     */
    @Transactional
    @Override
    public void delFinancialGroups(String id) {
        // 删除操作
        financialGroupsDao.deleteById(id);

    }




    /**
     *  通过id 获取财团信息
     * @param id id
     * @return AjaxResult
     */
    @Override
    public AjaxResult findFinancialById(String id) {
        AjaxResult result = new AjaxResult();
        // 获取财团的信息
        FinancialGroups info = financialGroupsDao.findById(id).get();

        result.setData(info);
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
    }



}
