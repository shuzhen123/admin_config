package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.BanksDao;
import com.mzfk.entity.Banks;
import com.mzfk.pojo.BanksInfoParam;
import com.mzfk.entity.PageInfo;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.utils.CommonUtils;
import com.mzfk.utils.CopyUtils;
import feign.Client;
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
import java.util.Optional;



/**
 * @author sz
 * @Title: BanksServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: 银行相关实现
 * @date 2018/10/1910:57
 */
@Service
public class BanksServiceImpl implements BanksService  {

    @Autowired
    private BanksDao banksDAO;


    /**
     *获取银行列表
     * @param params 分页参数
     * @return AjaxResult
     */
    @Override
    public AjaxResult findBanksList(BanksInfoParam params) {
        AjaxResult result = new AjaxResult();

        PageInfo pageInfo = new PageInfo();
        //获取银行列表
        Page<Banks> list = banksDAO.findAll(new Specification<Banks> () {
            @Override
            public Predicate toPredicate(Root<Banks> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1, params.getPageSize(),new Sort(Sort.Direction.DESC,"code")));
        // 获取数量
        long totalSize = banksDAO.count();
        pageInfo.setList(list.getContent());
        pageInfo.setTotalSize(totalSize);
        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setPageSize(params.getTotalPage());
        result.setData(pageInfo);
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        // 返回
        return result;
    }

    /**
     * 添加银行
     * @param banks 银行实体
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult addBanks(Banks banks) {
        AjaxResult result = new AjaxResult();
        // 通过code 判断对象是否存在
        Optional<Banks> info = banksDAO.findById(banks.getCode());

        //Optional.empty
        if (!"Optional.empty".equals(info.toString())) {
            // 当前code已存在
            result.setMsg(ResultMsg.C_012);
            result.setSuccess(false);
            return result;
        }

        // 获取当前系统的时间
        banks.setCreatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 持久化操作
        banksDAO.save(banks);
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(banks);

        return result;
    }

    /**
     * 通过code 删除银行
     * @param code 银行code
     * @return
     */
    @Override
    @Transactional
    public AjaxResult delBanks(String code) {
        AjaxResult result = new AjaxResult();

        // 删除银行
        banksDAO.deleteById(code);
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        // 返回成功
        return result;
    }


    /**
     *  通过code 获取信息
     * @param code code
     * @return
     */
    @Override
    public AjaxResult findBankByCode(String code) {
        AjaxResult result = new AjaxResult();

        Banks info = banksDAO.findById(code).get();

        result.setData(info);
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        return result;
    }


    /**
     *  更新
     * @param banks 对象数据
     * @param code id
     * @return AjaxResult
     */
    @Override
    public AjaxResult updateBanksModel(Banks banks,String code) {
        // 返回数据模型
        AjaxResult result = new AjaxResult();
        // 获取数据库中完整的原来的数据
        Banks info = banksDAO.findById(code).get();

        // 将前端没有出过来的字段补全
        CopyUtils.copyProperties(banks,info);
        // 添加更新时间
        info.setUpdatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 持久化操作
        banksDAO.save(info);

        // 成功返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        return result;
    }

}
