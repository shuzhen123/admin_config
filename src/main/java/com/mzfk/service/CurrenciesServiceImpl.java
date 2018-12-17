package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.CurrenciesDao;
import com.mzfk.dao.CurrenciesInfoParam;
import com.mzfk.entity.Currencies;
import com.mzfk.entity.PageInfo;
import com.mzfk.pojo.AjaxResult;
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
import java.util.Optional;

/**
 * @author sz
 * @Title: CurrenciesServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: 货币
 * @date 2018/11/210:58
 */
@Service
public class CurrenciesServiceImpl implements CurrenciesService {

    @Autowired
    private CurrenciesDao currenciesDao;


    /**
     *  获取所有货币信息
     * @param params 分页信息
     * @return
     */
    @Override
    public AjaxResult fildCurrencies(CurrenciesInfoParam params) {
        AjaxResult result = new AjaxResult();
        PageInfo pageInfo = new PageInfo();
        Page<Currencies> list = currenciesDao.findAll(new Specification<Currencies>() {
            @Override
            public Predicate toPredicate(Root<Currencies> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1,params.getPageSize(),new Sort(Sort.Direction.DESC,"code")));
        // 获取数量
        long tatolsize = currenciesDao.count();
        // 整理返回数据
        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setPageSize(params.getPageSize());
        pageInfo.setTotalSize(tatolsize);
        pageInfo.setList(list.getContent());

        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        result.setData(pageInfo);
        return result;
    }



    /**
     *  添加信息
     * @param currencies 货币信息
     * @return
     */
    @Override
    @Transactional
    public AjaxResult addCurrenciesModel(Currencies currencies) {
        AjaxResult result = new AjaxResult();
        // 判断code 是否重复
        Integer countCode = currenciesDao.checkCode(currencies.getCode());
        if (0 != countCode) {
            result.setMsg(ResultMsg.C_012);
            return result;
        }
        currenciesDao.save(currencies);

        // 创建返回数据
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(currencies);
        return result;
    }

    /**
     *  更新货币信息
     * @param currencies
     * @return AjaxResult
     */
    @Transactional
    @Override
    public AjaxResult updateCurrenciesModel(Currencies currencies,String code) {
        AjaxResult result = new AjaxResult();
        Currencies info = currenciesDao.findById(code).get();
        // 将前端没有出过来的字段补全
        CopyUtils.copyProperties(currencies,info);
        // 持久化操作
        currenciesDao.save(info);
        // 创建返回数据
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        return result;
    }


    /**
     *  删除信息
     * @param code code
     */
    @Override
    @Transactional
    public void delCurrencies(String code) {
        // 删除操作
        currenciesDao.deleteById(code);
    }


    /**
     *  获取一个货币信息
     * @param code code
     * @return AjaxResult
     */
    @Override
    public AjaxResult getCurrencies(String code) {
        AjaxResult result = new AjaxResult();
        // 查询操作
        Currencies info = currenciesDao.findById(code).get();

        // 返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(info);
        return result;
    }



}
