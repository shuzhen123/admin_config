package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.ClientsDao;
import com.mzfk.entity.Clients;
import com.mzfk.pojo.ClientsIInfoParam;
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
 * @Title: ClientsServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2216:57
 */
@Service
public class ClientsServiceImpl implements ClientsService {

    @Autowired
    private ClientsDao cientsDao;


    /**
     * 获取客户列表
     * @param params 分页参数
     * @return
     */
    @Override
    public AjaxResult findSpotsList(ClientsIInfoParam params) {
        AjaxResult result = new AjaxResult();

        PageInfo pageInfo = new PageInfo();
        // 获取列表
        Page<Clients> list = cientsDao.findAll(new Specification<Clients>() {
            @Override
            public Predicate toPredicate(Root<Clients> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },new PageRequest(params.getCurPage()-1, params.getPageSize(), new Sort(Sort.Direction.DESC,"createdAt")));
        // 获取数量
        long totalSiez = cientsDao.count();
        // 整理返回参数
        pageInfo.setList(list.getContent());
        pageInfo.setTotalSize(totalSiez);
        pageInfo.setCurPage(params.getCurPage());
        pageInfo.setPageSize(params.getPageSize());
        // 整理返回数据
        result.setData(pageInfo);
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        return result;
    }


    /**
     * 添加客户信息
     * @param clients 客户数据
     * @return AjaxResult
     */
    @Transactional
    @Override
    public AjaxResult addClientsModel(Clients clients) {
        AjaxResult result = new AjaxResult();

        // 判断app名字是否重复
        Integer nameCount = cientsDao.getNameCountByappName(clients.getAppName());
        if (0 !=  nameCount) {
            // app名字重复！
            result.setMsg(ResultMsg.C_004);
            result.setSuccess(false);
            return result;
        }

        // 判断token是否重复
        Integer appTokenCount = cientsDao.getTokenCountByAppToken(clients.getAppToken());
        if (0 !=  nameCount) {
            // apptoken重复！
            result.setMsg(ResultMsg.C_005);
            result.setSuccess(false);
            return result;
        }

        clients.setCreatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        cientsDao.save(clients);
        // 成功返回
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        result.setData(clients);
        return result;
    }



    /**
     * 更新客户信息
     * @param clients 客户数据
     */
    @Transactional
    @Override
    public AjaxResult updateClients(Clients clients,String id) {
        AjaxResult result = new AjaxResult();
        // 更新时检查需要更新的名字在数据库中是否存在
        Integer nameCount = cientsDao.getUpdataCheckName(clients.getAppName(),clients.getId());
        if (0 !=  nameCount) {
            // app名字重复！
            result.setMsg(ResultMsg.C_004);
            result.setSuccess(false);
            return result;
        }

        // 更新时检查更新的token是否已经存在
        Integer tokenCount = cientsDao.getUpdataCheckToken(clients.getAppToken(),clients.getId());
        if (0 !=  tokenCount) {
            // apptoken重复！
            result.setMsg(ResultMsg.C_005);
            result.setSuccess(false);
            return result;
        }
        // 获取原来得数据
        Clients info = cientsDao.findById(id).get();

        // 将前端没有出过来的字段补全
        CopyUtils.copyProperties(clients,info);

        // 添加更新时间
        info.setUpdatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 更新操作
        cientsDao.save(info);
        // 成功返回
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;

    }



    /**
     * 通过id 删除客户
     * @param id 用户id
     */
    @Override
    @Transactional
    public void delClients(String id) {
        cientsDao.deleteById(id);
    }



}
