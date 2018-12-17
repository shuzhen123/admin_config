package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.DomainsDao;
import com.mzfk.entity.Domains;
import com.mzfk.pojo.DomainsInfoParam;
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
 * @Title: DomainsServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: 域名
 * @date 2018/10/2310:17
 */
@Service
public class DomainsServiceImpl implements DomainsService {


    @Autowired
    private DomainsDao domainsDao;


    /**
     * 获取域名列表
     * @param params 参数模型
     * @return
     */
    @Override
    public AjaxResult findDomainsList(DomainsInfoParam params) {
        AjaxResult result = new AjaxResult();

        PageInfo pageInfo = new PageInfo();
        // 获取列表数据
        Page<Domains> list = domainsDao.findAll(new Specification<Domains>() {
            @Override
            public Predicate toPredicate(Root<Domains> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1,params.getPageSize(),new Sort(Sort.Direction.DESC,"createdAt")));
        // 获取总数量
        long totalsize = domainsDao.count();

        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setPageSize(params.getPageSize());
        pageInfo.setTotalSize(totalsize);
        pageInfo.setList(list.getContent());

        // 整理返回数据
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        result.setData(pageInfo);
        // 返回
        return result;
    }



    /**
     * 添加系统域名 model
     * @param domains 系统域名
     * @return AjaxResult
     */
    @Transactional
    @Override
    public AjaxResult addDomainsModel(Domains domains) {
        AjaxResult result = new AjaxResult();

        // 判断domain 是否重复

        int domainCount = domainsDao.checkDomainCount(domains.getDomain());

        if (0 != domainCount) {
            // 系统域名重复 主键id 不可以重复
            result.setMsg(ResultMsg.C_006);
            return result;
        }
        // 添加创建时间
        domains.setCreatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        domainsDao.save(domains);
        // 成功返回
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        result.setData(domains);
        return result;
    }



    /**
     * 更新系统域名操作
     * @param domains 数据模型
     */
    @Override
    @Transactional
    public void updateDomains(Domains domains,String domain) {
        // 获取数据库中完整的原来的数据
        Domains info = domainsDao.findById(domain).get();
        // 将前端没有出过来的字段补全
        CopyUtils.copyProperties(domains,info);
        // 添加更新时间
        info.setUpdatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 持久化操作
        domainsDao.save(info);
    }

    /**
     * 删除系统域名下得用户
     * @param domain
     */
    @Override
    @Transactional
    public void delDomains(String domain) {
        domainsDao.deleteById(domain);
    }



}
