package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Countries;
import com.mzfk.pojo.CountriesInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.CountriesService;
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
 * @Title: CountriesController
 * @ProjectName 8Madmin_config
 * @Description: 国家
 * @date 2018/10/2911:11
 */
@Controller
@RequestMapping(value = "/config")
@Api(value = "国家Controller",tags = {"国家Controller"})
public class CountriesController {



    @Autowired
    private CountriesService countriesService;

    private Logger log = LoggerFactory.getLogger(CountriesController.class);

    /**
     * 获取国家列表
     * @param Params 分页参数
     * @return AjaxResult
     */
    @ApiOperation(value = "获取计划列表", httpMethod = "GET", notes = "获取计划列表", response = AjaxResult.class)
    @GetMapping("/countries")
    @ResponseBody
    public AjaxResult findCountriesList (
            @ApiParam(name="分页参数",value="Params")
            CountriesInfoParam Params) {
        AjaxResult result = new AjaxResult();

        try {
            // 获取列表
            result = countriesService.findCountriesList(Params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取国家列表失败
            result.setMsg(ResultMsg.C_025);
        }

        return result;
    }


    /**
     * 添加国家信息
     * @param countries 国家信息
     * @return
     */
    @ApiOperation(value = "添加国家信息", httpMethod = "POST", notes = "添加国家信息", response = AjaxResult.class)
    @PostMapping("/countries")
    @ResponseBody
    public AjaxResult addCountries (
            @ApiParam(name="国家信息",value="countries",required = true)
            @RequestBody Countries countries
    ) {
        AjaxResult result = new AjaxResult();
        // 持久化步骤
        try {
            result = countriesService.addCountriesModel(countries);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加国家失败
            result.setMsg(ResultMsg.C_026);
        }
        // 成功返回
        return result;
    }


    /**
     *  更新国家信息
     * @param countries 国家信息
     * @return AjaxResult
     */
    @ApiOperation(value = "更新国家信息", httpMethod = "POST", notes = "更新国家信息", response = AjaxResult.class)
    @PatchMapping("/countries/{id}")
    @ResponseBody
    public AjaxResult updateCountries (
            @ApiParam(name="主键id",value="主键id",required = true)  @PathVariable String id,
            @ApiParam(name="国家信息",value="countries",required = true)  @RequestBody Countries countries
    ) {
        AjaxResult result = new AjaxResult();
        // 调库更新步骤
        try {
            result = countriesService.updateCountriesModel(countries,id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新国家失败
            result.setMsg(ResultMsg.C_027);
        }
        // 成功返回
        return result;
    }


    /**
     * 删除国家信息
     * @param iso id
     * @return AjaxResult
     */
    @ApiOperation(value = "删除国家信息", httpMethod = "DELETE", notes = "更新国家信息", response = AjaxResult.class)
    @DeleteMapping("/countries/{iso}")
    @ResponseBody
    public AjaxResult delCountries ( @ApiParam(name="主键id",value="iso",required = true) @PathVariable String iso) {
        AjaxResult result = new AjaxResult();
        try {
            countriesService.delCountries(iso);
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除国家失败
            result.setMsg(ResultMsg.C_028);
        }
        // 返回
        return result;
    }




}



