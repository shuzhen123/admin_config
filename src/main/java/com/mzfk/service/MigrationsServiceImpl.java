package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.MigrationsDao;
import com.mzfk.entity.Migrations;
import com.mzfk.pojo.MigrationsInfoParam;
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

/**
 * @author sz
 * @Title: MigrationsServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2314:22
 */
@Service
public class MigrationsServiceImpl implements MigrationsService {

    @Autowired
    private MigrationsDao migrationsDao;

    /**
     * 获取数据迁移列表
     * @param params 分页参数
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult findMigrationsList(MigrationsInfoParam params) {
        AjaxResult result = new AjaxResult();
        // 返回参数容器
        PageInfo pageInfo = new PageInfo();
        // 获取列表
        Page<Migrations> list = migrationsDao.findAll(new Specification<Migrations>() {
            @Override
            public Predicate toPredicate(Root<Migrations> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1,params.getPageSize(),new Sort(Sort.Direction.DESC,"id")));
        // 获取总数量
        long totalsize = migrationsDao.count();

        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setPageSize(params.getPageSize());
        pageInfo.setList(list.getContent());
        pageInfo.setTotalSize(totalsize);
        // 整理返回参数
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        result.setData(list.getContent());
        // 返回
        return result;
    }



    /**
     * 添加迁得批次
     * @param migrations 迁得批次
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult addMigrations(Migrations migrations) {
        AjaxResult result = new AjaxResult();

        // 数据持久化
        migrationsDao.save(migrations);
        // 返回数据类型
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(migrations);
        return result;
    }


    /**
     *更新操作
     * @param migrations 数据模型
     * @return AjaxResult
     */
    @Transactional
    @Override
    public AjaxResult updateMigrations(Migrations migrations,String id) {
        AjaxResult result = new AjaxResult();
        //
        Migrations info = migrationsDao.findById(id).get();
        // 将前端没有出过来的字段补全
        CopyUtils.copyProperties(migrations,info);

        // 持久化操作
        migrationsDao.save(info);
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
        }


    /**
     *删除记录
     * @param id 记录id
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult delMigrations(String id) {
        AjaxResult result = new AjaxResult();

        migrationsDao.deleteById(id);
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);

        return result;
    }
}
