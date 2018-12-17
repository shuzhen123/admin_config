package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.StagesDao;
import com.mzfk.entity.PageInfo;
import com.mzfk.entity.Stages;
import com.mzfk.pojo.StagesInfoParam;
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
 * @Title: StagesServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2516:02
 */
@Service
public class StagesServiceImpl implements StagesService {

    @Autowired
    private StagesDao stagesDao;


    /**
     * 获取阶段列表
     * @param params
     * @return
     */
    @Override
    public AjaxResult findStagesList(StagesInfoParam params) {
        AjaxResult result = new  AjaxResult();

        PageInfo pageInfo = new PageInfo();
        // 获取列表
        Page<Stages> list = stagesDao.findAll(new Specification<Stages>() {
            @Override
            public Predicate toPredicate(Root<Stages> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1,params.getPageSize(),new Sort(Sort.Direction.DESC,"createdAt")));
        // 计算总条数
        long totalsize = stagesDao.count();
        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setPageSize(params.getPageSize());
        pageInfo.setList(list.getContent());
        pageInfo.setTotalSize(totalsize);

        result.setData(pageInfo);
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
    }


    /**
     * 添加阶段信息
     * @return
     */
    @Transactional
    @Override
    public AjaxResult addStages(Stages stages) {
        AjaxResult result = new AjaxResult();

        // 不能同时存在 plan_code 和 level 相同的，此处做验证
        Integer checkCount = stagesDao.checkCount(stages.getPlanCode(),stages.getLevel());
        if (0 != checkCount) {
            // 存在同时满足planCode,level的数据，请修改
            result.setMsg(ResultMsg.C_089);
            return result;
        }

        // 添加创建时间
        stages.setCreatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        stagesDao.save(stages);
        // 成功返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(stages);
        return result;
    }


    /**
     * 更新阶段信息
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult updateStages(Stages stages,String id) {
        AjaxResult result = new AjaxResult();

        // 不能同时存在 plan_code 和 level 相同的，此处做验证
        Integer checkCount = stagesDao.checkCountAndId(stages.getPlanCode(),stages.getLevel(),stages.getId());
        if (0 != checkCount) {
            // 存在同时满足planCode,level的数据，请修改
            result.setMsg(ResultMsg.C_089);
            return result;
        }


        Stages info = stagesDao.findById(id).get();
        CopyUtils.copyProperties(stages,info);
        // 获取系统当前时间
        Timestamp timestamp = Timestamp.valueOf(CommonUtils.getChinaTime());
        info.setUpdatedAt(timestamp);
        stagesDao.save(info);

        // 成功返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        return result;
    }

    /**
     * 通过id 删除记录
     * @param id id
     */
    @Override
    @Transactional
    public void delStages(String id) {
        // 删除操作
        stagesDao.deleteById(id);

    }


}
