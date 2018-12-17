package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.CountriesDao;
import com.mzfk.entity.Countries;
import com.mzfk.pojo.CountriesInfoParam;
import com.mzfk.entity.PageInfo;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author sz
 * @Title: CountriesServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2911:10
 */
@Service
public class CountriesServiceImpl implements CountriesService {


    @Autowired
    private CountriesDao countriesDao;


    /**
     * 过去国家列表
     * @param params 分页参数
     * @return AjaxResult
     */
    @Override
    public AjaxResult findCountriesList(CountriesInfoParam params) {
        AjaxResult result = new AjaxResult();

        PageInfo pageInfo = new PageInfo();
        // 获取列表
        Page<Countries> list = countriesDao.findAll(new Specification<Countries>() {
            @Override
            public Predicate toPredicate(Root<Countries> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1,params.getPageSize(),new Sort(Sort.Direction.DESC,"iso")));
        // 获取总数量
        long totalsize = countriesDao.count();
        // 整理返回参数
        pageInfo.setList(list.getContent());
        pageInfo.setTotalSize(totalsize);
        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setPageSize(params.getPageSize());
        // 成功返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(pageInfo);
        return result;
    }


    /**
     *  添加国家信息
     * @param countries 国家信息
     * @return AjaxResult
     */
    @Override
    public AjaxResult addCountriesModel(Countries countries) {
        AjaxResult result = new AjaxResult();
        // 添加操作
        countriesDao.save(countries);
        // 返回数据信息
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(countries);
        return result;
    }


    /**
     *  更新家信息
     * @param countries
     * @return AjaxResult
     */
    @Override
    public AjaxResult updateCountriesModel(Countries countries,String id) {
        AjaxResult result = new AjaxResult();

        Countries info = countriesDao.findById(id).get();
        // 将前端没有出过来的字段补全
        CopyUtils.copyProperties(countries,info);

        // 更新操作
        countriesDao.save(info);

        // 返回数据信息
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        return result;
    }


    /**
     *  删除国家信息
     * @param iso id
     */
    @Override
    public void delCountries(String iso) {
        // 删除信息
        countriesDao.deleteById(iso);

    }

}
