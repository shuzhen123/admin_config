package com.mzfk.service;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.MenusDao;
import com.mzfk.entity.Menus;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author sz
 * @Title: MenusServiceImpl
 * @ProjectName 8Madmin_config
 * @Description: 菜单
 * @date 2018/10/3110:41
 */
@Service
public class MenusServiceImpl implements MenusService {

    @Autowired
    private MenusDao menusDao;

    /**
     *  获取所有菜单
     * @return
     */
    @Override
    public AjaxResult fildAllMenus() {
        AjaxResult result = new AjaxResult();
        // 获取列表
        List<Menus> list = menusDao.findAll();
        // 菜单数量
        long totalsize = menusDao.count();
        // 返回参数整理
        Map<String,Object> data = new HashMap<>();
        data.put("list",list);
        data.put("totalsize",totalsize);
        // 返回信息整理
        result.setData(data);
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
    }



    /**
     * 添加菜单
     * @param menus
     * @return
     */
    @Transactional
    @Override
    public AjaxResult addMenus(Menus menus) {
        AjaxResult result = new AjaxResult();

        Integer codeCount = menusDao.checkCode(menus.getCode());
        if (0 != codeCount) {
            // Order 重复
            result.setMsg(ResultMsg.C_012);
            result.setSuccess(false);
            return result;
        }

        // 判断order是否重复
        Integer orderCount = menusDao.addcheckOrder(menus.getOrdernum());
        if (0 != orderCount) {
            // Order 重复
            result.setMsg(ResultMsg.C_016);
            result.setSuccess(false);
            return result;
        }

        // 添加 创建时间
        menus.setCreatedAt(Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 持久化步骤
        menusDao.save(menus);

        result.setMsg(ResultMsg.C_001);
        result.setSuccess(true);
        result.setData(menus);
        return result;
    }

    /**
     *  通过code 查找菜单
     * @param code code
     * @return AjaxResult
     */
    @Override
    public AjaxResult findMenuByCode(String code) {
        AjaxResult result = new AjaxResult();
        //  通过id 查找
        Menus info = menusDao.findById(code).get();
        // 返回
        result.setData(info);
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
    }

    /**
     *  通过code 删除菜单
     * @param code code
     * @return AjaxResult
     */
    @Override
    @Transactional
    public AjaxResult delMenuByCode(String code) {
        AjaxResult result = new AjaxResult();
        try {
            menusDao.deleteById(code);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(ResultMsg.C_011);
            return result;
        }
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
    }


    /**
     * 更新菜单
     * @param menus menus
     * @return AjaxResult
     */
    @Transactional
    @Override
    public AjaxResult updateMenus(Menus menus,String code) {
        AjaxResult result = new AjaxResult();
        // 判断order是否重复
        Integer orderCount = menusDao.checkOrder(menus.getCode(),menus.getOrdernum());
        if (0 != orderCount) {
            // Order 重复
            result.setMsg(ResultMsg.C_016);
            result.setSuccess(false);
            return result;
        }
        // 更新操作
//        menusDao.updateMenus(menus.getCode(),menus.getOrdernum(),menus.getIsNew(),menus.getUri()
//                ,menus.getStatus(),menus.getSort(),Timestamp.valueOf(CommonUtils.getChinaTime()));
        // 返回数据
        result.setSuccess(true);
        result.setMsg(ResultMsg.C_001);
        return result;
    }




}
