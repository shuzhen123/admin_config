package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Domains;
import com.mzfk.pojo.DomainsInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.DomainsService;
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
 * @Title: DomainsController
 * @ProjectName 8Madmin_config
 * @Description: 域名相关
 * @date 2018/10/2310:24
 */
@Controller
@RequestMapping("/config")
@Api(value = "域名Controller",tags = {"域名Controller"})
public class DomainsController {

    @Autowired
    private DomainsService domainsService;

    private Logger log = LoggerFactory.getLogger(DomainsController.class);


    /**
     * 获取域名列表
     * @param Params 参数模型
     * @return AjaxResult
     */
    @ApiOperation(value = "获取域名列表", httpMethod = "GET", notes = "获取域名列表", response = AjaxResult.class)
    @GetMapping("/domains")
    @ResponseBody
    public AjaxResult findDomainsList (@ApiParam(name="分页参数",value="Params") DomainsInfoParam Params) {
        AjaxResult result = new AjaxResult();
        // 获取列表
        try {
            result = domainsService.findDomainsList(Params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取域名列表失败
            result.setMsg(ResultMsg.C_038);
        }

        return result;
    }


    /**
     *  添加域名
     * @param domains
     * @return AjaxResult
     */
    @ResponseBody
    @PostMapping("/domains")
    @ApiOperation(value = "添加域名", httpMethod = "POST", notes = "添加域名", response = AjaxResult.class)
    public AjaxResult addDomains (
            @ApiParam(name="域名",value="domains",required = true) @RequestBody Domains domains
    ) {
        AjaxResult result = new AjaxResult();

        try {
            result = domainsService.addDomainsModel(domains);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加域名失败
            result.setMsg(ResultMsg.C_039);
        }

        return result;
    }


    /**
     * 更新用户系统域名
     * @param domains 数据模型
     * @return AjaxResult
     */
    @ResponseBody
    @PatchMapping("/domains/{domain}")
    @ApiOperation(value = "更新用户系统域名",httpMethod = "POST", notes = "更新用户系统域名", response = AjaxResult.class)
    public AjaxResult updateDomains (
            @ApiParam(name="域名主键",value="domain",required = true)  @PathVariable String domain,
            @ApiParam(name="域名数据",value="domains",required = true) @RequestBody Domains domains
    ) {
        AjaxResult result = new AjaxResult();
        // 更新操作
        try {
            domainsService.updateDomains(domains,domain);
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新域名失败
            result.setMsg(ResultMsg.C_040);
        }

        return result;
    }


    /**
     * 删除用户得系统域名
     * @return
     */
    @ResponseBody
    @DeleteMapping("/domains/{domain}")
    @ApiOperation(value = "删除用户系统域名",httpMethod = "DELETE", notes = "删除用户系统域名", response = AjaxResult.class)
    public AjaxResult delDomains (
            @ApiParam(name="域名主键",value="domain",required = true) @PathVariable String domain
    ) {
        AjaxResult result = new AjaxResult();
        // 更新操作
        try {
            domainsService.delDomains(domain);
            result.setMsg(ResultMsg.C_001);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除域名失败
            result.setMsg(ResultMsg.C_041);
        }

        return result;
    }





}
