package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.ItemsDao;
import com.mzfk.entity.Items;
import com.mzfk.entity.PageInfo;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.utils.CommonUtils;
import com.mzfk.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author sz
 * @Title: ItemsServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/2513:41
 */
@Service
public class ItemsServiceImpl implements ItemsService {


    @Autowired
    private ItemsDao itemsDao;

    /**
     * 获取项目列表
     * @return AjaxResult
     */
    @Override
    public AjaxResult findItemsList() {
        AjaxResult result = new AjaxResult();
        // 创建返回参数模型
        PageInfo pageInfo = new PageInfo();
        // 数据数据列表
        List<Items> list = itemsDao.findAll(new Sort(Sort.Direction.DESC,"createdAt"));
        //Page<Items> list = (Page<Items>) itemsDao.findAll(new Sort(Sort.Direction.DESC,"createdAt"));
        // 获取总条数
        long totalsize = itemsDao.count();
        pageInfo.setList(list);
        pageInfo.setTotalSize(totalsize);
        // 返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(pageInfo);
        return result;
    }


    /**
     *  添加项目
     * @param items 项目信息
     * @return AjaxResult
     */
    @Transactional
    @Override
    public AjaxResult addItems(Items items) {
        AjaxResult result = new AjaxResult();
        // 判断项目是否重复
        Integer count = itemsDao.checktitle(items.getTitle());
        if (0 != count) {
            result.setSuccess(false);
            // ！当前需要添加的项目已存在
            result.setMsg(ResultMsg.C_008);
            return result;
        }
        // 添加创建时间
        items.setCreatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 持久化步骤
        itemsDao.save(items);
        // 返回
        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(items);
        return result;
    }


    /**
     * 更新项目
     * @param items
     * @return AjaxResult
     */
    @Transactional
    @Override
    public AjaxResult updateItems(Items items,String id) {
        AjaxResult result = new AjaxResult();
        // 持久化操作
        Items info = itemsDao.findById(id).get();

        CopyUtils.copyProperties(items,info);

        itemsDao.save(info);

        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
    }

    /**
     * 删除项目信息
     * @param id 主键id
     * @return AjaxResult
     */
    @Transactional
    @Override
    public AjaxResult delItems(String id) {
        AjaxResult result = new AjaxResult();
        // 删除操作
        itemsDao.deleteById(id);

        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        return result;
    }
}
