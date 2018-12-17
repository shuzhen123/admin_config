package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.TranslationsDao;
import com.mzfk.entity.PageInfo;
import com.mzfk.entity.Translations;
import com.mzfk.pojo.TranslationsInfo;
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

/**
 * @author sz
 * @Title: TranslationsServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: 译文相关
 * @date 2018/10/2215:08
 */
@Service
public class TranslationsServiceImpl implements TranslationsService {


    @Autowired
    private TranslationsDao translationsDao;


    /**
     * 获取译文列表
     * @param params
     * @return AjaxResult
     */
    @Override
    public AjaxResult findTranslationsList(TranslationsInfo params) {
        AjaxResult result = new AjaxResult();

        PageInfo pageInfo = new PageInfo();

        // 获取列表
        Page<Translations> list = translationsDao.findAll(new Specification<Translations>() {
            @Override
            public Predicate toPredicate(Root<Translations> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1, params.getPageSize(), new Sort(Sort.Direction.DESC,"id")));
        // 获取数量
        long pagesize = translationsDao.count();

        pageInfo.setList(list.getContent());
        pageInfo.setTotalSize(pagesize);
        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setTotalPage(params.getTotalPage());

        // 整理返回数据模型
        result.setData(pageInfo);
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);

        return result;
    }

    /**
     * 添加译文
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult addTranslations(Translations translations) {
        AjaxResult result = new AjaxResult();

        // 持久化操作
        translationsDao.save(translations);
        // 数据返回
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        result.setData(translations);
        return result;
    }


    /**
     * 更新译文
     * @param translations
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult updateTranslations(Translations translations,String id) {
        AjaxResult result = new AjaxResult();
        // 获取数据库中完整的原来的数据
        Translations info = translationsDao.findById(id).get();
        // 将前端没有出过来的字段补全
        CopyUtils.copyProperties(translations,info);

        // 持久化操作
        translationsDao.save(info);
        // 整理返回数据
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
    }


    /**
     * 删除译文
     * @param id 主键
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult delTranslations(String id) {
        AjaxResult result = new AjaxResult();
        // 删除译文 通过id
        translationsDao.delTranslations(id);
        // 整理返回数据
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
    }


}
