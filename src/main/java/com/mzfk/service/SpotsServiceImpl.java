package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.SpotsDao;
import com.mzfk.entity.PageInfo;
import com.mzfk.entity.Spots;
import com.mzfk.pojo.SpotsInfoParam;
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
 * @Title: SpotsServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: 标识service
 * @date 2018/10/1914:37
 */
@Service
public class SpotsServiceImpl implements SpotsService {

    @Autowired
    private SpotsDao spotsDao;


    /**
     * 获取识别列表
     * @returnaddSpots
     */
    @Override
    public AjaxResult findSpotsList(SpotsInfoParam params) {
        // 构建返回模型
        AjaxResult result = new AjaxResult();
        // 查询列表
        PageInfo pageInfo = new PageInfo();

        Page<Spots> list = spotsDao.findAll(new Specification<Spots>() {

            @Override
            public Predicate toPredicate(Root<Spots> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1, params.getPageSize(), new Sort(Sort.Direction.DESC,"order")));

        result.setData(list);
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        // 返回
        return result;
    }

    /**
     * 添加标识
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult addSpots(Spots spots) {
        // 构建返回模型
        AjaxResult result = new AjaxResult();

        // 添加操作
        spotsDao.save(spots);
        result.setData(spots);
        return result;
    }


    /**
     * 更新标识操作
     * @param spots 对象信息
     * @return
     */
    @Override
    @Transactional
    public AjaxResult updateSpots(Spots spots,String ids) {
        // 构建返回模型
        AjaxResult result = new AjaxResult();

        Spots info = spotsDao.findById(ids).get();
        CopyUtils.copyProperties(spots,info);

        spotsDao.save(info);

        return result;
    }


    /**
     * 删除标识
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public AjaxResult delSpots(String ids) {
        // 构建返回模型
        AjaxResult result = new AjaxResult();
        // 删除操作
        spotsDao.deleteById(ids);
        // 返回
        return result;
    }


}
