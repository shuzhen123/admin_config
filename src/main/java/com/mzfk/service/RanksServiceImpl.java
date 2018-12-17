package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.RanksDao;
import com.mzfk.entity.PageInfo;
import com.mzfk.entity.Ranks;
import com.mzfk.pojo.RanksInfoParam;
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
 * @Title: RanksServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/19:46
 */
@Service
public class RanksServiceImpl implements RanksService {




    @Autowired
    private RanksDao ranksDao;


    /**
     *  获取所有ranks
     * @return AjaxResult
     */
    @Override
    public AjaxResult fildAllRanks(RanksInfoParam params) {
        AjaxResult result = new AjaxResult();

        PageInfo pageInfo = new PageInfo();
        Page<Ranks> list = ranksDao.findAll(new Specification<Ranks>() {
            @Override
            public Predicate toPredicate(Root<Ranks> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1,params.getPageSize(), new Sort(Sort.Direction.DESC,"code")));

        result.setData(list);
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
    }


    /**
     *  addRanks
     * @param ranks ranks
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult addRanks(Ranks ranks) {
        AjaxResult result = new AjaxResult();
        // 判断code 是否重复
        Integer countCode = ranksDao.checkCodeCount(ranks.getCode());
        if (0 != countCode) {
            // 当前code 已经存在
            result.setMsg(ResultMsg.C_012);
            return result;
        }
        // 添加创建时间
        ranks.setCreatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 持久话数据
        ranksDao.save(ranks);
        // 返回
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        result.setData(ranks);
        return result;
    }

    /**
     * 更新等级
     * @param ranks ranks
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult uptateRanks(Ranks ranks,String code) {
        AjaxResult result = new AjaxResult();

        Ranks info = ranksDao.findById(code).get();
        CopyUtils.copyProperties(ranks,info);
        ranksDao.save(info);

        // 返回
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
    }

    /**
     *  删除等级
     * @param code code
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult delRanks(String code) {
        AjaxResult result = new AjaxResult();
        // 删除操作
        ranksDao.deleteById(code);
        // 整理返回参数
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        return result;
    }


}
