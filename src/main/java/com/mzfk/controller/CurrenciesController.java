package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.dao.CurrenciesInfoParam;
import com.mzfk.entity.Currencies;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.CurrenciesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author sz
 * @Title: CurrenciesController
 * @ProjectName 8Madmin_config
 * @Description: 货币 相关
 * @date 2018/11/210:51
 */
@Controller
@RequestMapping("/config")
@Api(value = "货币Controller",tags = {"货币Controller"})
public class CurrenciesController {

    @Autowired
    private CurrenciesService currenciesService;

    private Logger log = LoggerFactory.getLogger(CurrenciesController.class);

    /**
     *  获取货币列表
     * @return AjaxResult
     */
    @ApiOperation(value = "获取货币列表", httpMethod = "GET", notes = "获取货币列表", response = AjaxResult.class)
    @GetMapping("/currencies")
    @ResponseBody
    public AjaxResult fildCurrencies (@ApiParam(name="分页参数",value="params") CurrenciesInfoParam params) {
        AjaxResult result = new AjaxResult();

        try {
            // 获取货币列表
            result = currenciesService.fildCurrencies(params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取货币列表失败
            result.setMsg(ResultMsg.C_029);
        }

        return result;
    }



    /**
     *  添加货币信息
     * @param currencies
     * @return AjaxResult
     */
    @ApiOperation(value = "添加货币信息", httpMethod = "POST", notes = "添加货币信息", response = AjaxResult.class)
    @PostMapping("/currencies")
    @ResponseBody
    public AjaxResult addCurrencies (
            @ApiParam(name="货币信息",value="currencies",required = true) @RequestBody Currencies currencies
    ) {
        AjaxResult result = new AjaxResult();
        // 持久化
        try {
            result = currenciesService.addCurrenciesModel(currencies);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加货币信息失败
            result.setMsg(ResultMsg.C_030);
        }

        return result;
    }


    /**
     *  更新货币信息
     * @param currencies
     * @return AjaxResult
     */
    @ApiOperation(value = "更新货币信息", httpMethod = "PATCH", notes = "更新货币信息", response = AjaxResult.class)
    @PatchMapping("/currencies/{code}")
    @ResponseBody
    public AjaxResult updateCurrencies (
            @ApiParam(name="主键code",value="code",required = true) @PathVariable String code,
            @ApiParam(name="货币信息",value="currencies",required = true) @RequestBody Currencies currencies
    ) {
        AjaxResult result = new AjaxResult();
        // 持久化
        try {
            result = currenciesService.updateCurrenciesModel(currencies,code);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新货币信息失败
            result.setMsg(ResultMsg.C_031);
        }

        return result;
    }


    /**
     *  删除操作
     * @param code code
     * @return  AjaxResult
     */
    @ApiOperation(value = "删除货币信息", httpMethod = "DELETE", notes = "删除货币信息", response = AjaxResult.class)
    @DeleteMapping("/currencies/{code}")
    @ResponseBody
    public AjaxResult delCurrencies (@ApiParam(name="主键code",value="code",required = true) @PathVariable String code ) {
        AjaxResult result = new AjaxResult();
        // 删除操作
        try {
            currenciesService.delCurrencies(code);
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除操作
            result.setMsg(ResultMsg.C_032);
        }

        return result;
    }



    /**
     *  获取一个货币信息 通过code
     * @param code code
     * @return  AjaxResult
     */
    @ApiOperation(value = "删除货币信息", httpMethod = "GET", notes = "删除货币信息", response = AjaxResult.class)
    @GetMapping("/currencies/{code}")
    @ResponseBody
    public AjaxResult getCurrencies (
            @ApiParam(name="主键code",value="code",required = true) @PathVariable String code
    ) {
        AjaxResult result = new AjaxResult();
        // 查询操作
        try {
            result = currenciesService.getCurrencies(code);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取货币信息失败
            result.setMsg(ResultMsg.C_033);
        }
        return result;
    }




}
