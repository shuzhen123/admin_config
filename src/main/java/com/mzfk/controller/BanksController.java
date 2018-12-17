package com.mzfk.controller;

import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Banks;
import com.mzfk.pojo.BanksInfoParam;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.BanksService;
import io.swagger.annotations.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author sz
 * @Title: BanksController
 * @ProjectName 8Madmin_config
 * @Description: 银行相关controller
 * @date 2018/10/1910:44
 */
@RefreshScope
@Controller
@RequestMapping("/config")
@Api(value = "银行Controller",tags = {"银行Controller"})
public class BanksController {


    /**
     * 注入：@BanksService
     */
    @Autowired
    private BanksService banksService;

    private Logger log = LoggerFactory.getLogger(BanksController.class);

//    @Value("${localname}")
//    private String message;

//    @GetMapping("/test")
//    @ResponseBody
//    public String test() {
//        return message;
//    }

    /**
     * 获取银行列表
     * @return AjaxResult
     */
    @ApiOperation(value = "获取银行列表", httpMethod = "GET", notes = "获取银行列表", response = AjaxResult.class)
    @GetMapping(value = "/banks")
    @ResponseBody
        public AjaxResult findBanksList (@ApiParam(name="分页参数",value="param") BanksInfoParam param) {
        AjaxResult result = new AjaxResult();

        try {
            result = banksService.findBanksList(param);
        } catch (Exception e) {
            log.error(e.getMessage());
            result.setMsg(ResultMsg.C_011);
        }

        return result;
    }


    /**
     * 根据code 去查询对应银行的信息
     * @param code 银行code
     * @return
     */
    @ResponseBody
    @GetMapping("/banks/{code}")
    @ApiOperation(value = "根据code 去查询对应银行的信息", httpMethod = "POST", notes = "根据code 去查询对应银行的信息", response = AjaxResult.class)
    public AjaxResult findBankByCode ( @ApiParam(name="主键code",value="code",required = true) @PathVariable String code) {
        AjaxResult result = new AjaxResult();

        try {
            result = banksService.findBankByCode(code);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取银行失败
            result.setMsg(ResultMsg.C_020);
        }
        return result;
    }



    /**
     * 添加银行
     * @return AjaxResult
     */
    @PostMapping("/banks")
    @ResponseBody
    @ApiOperation(value = "添加银行", httpMethod = "POST", notes = "添加银行", response = AjaxResult.class)
    public AjaxResult addBanks (@ApiParam(name="银行实体对象",value="banks",required = true) @RequestBody Banks banks) {
        AjaxResult result = new AjaxResult();

        try {
            result = banksService.addBanks(banks);

        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加银行失败
            result.setMsg(ResultMsg.C_017);
        }

        return result;
    }


    /**
     * 修改银行
     * @param banks
     * @return AjaxResult
     */
    @ResponseBody
    @PatchMapping("/banks/{code}")
    @ApiOperation(value = "修改银行", httpMethod = "PATCH", notes = "修改银行", response = AjaxResult.class)
    public AjaxResult updateBanks (
           @ApiParam(name="主键code",value="code",required = true) @PathVariable  String code,
           @ApiParam(name="银行实体对象",value="banks",required = true)  @RequestBody Banks banks
    ) {
        AjaxResult result = new AjaxResult();

        try {
            result = banksService.updateBanksModel(banks,code);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 修改银行失败
            result.setMsg(ResultMsg.C_018);
        }

        return result;
    }



    /**
     * 删除银行
     * @param code 银行code
     * @return
     */
    @ResponseBody
    @DeleteMapping("/banks/{code}")
    @ApiOperation(value = "删除银行", httpMethod = "DELETE", notes = "删除银行", response = AjaxResult.class)
    public AjaxResult delBanks (@ApiParam(name="主键code",value="code",required = true)  @PathVariable String code) {
        AjaxResult result = new AjaxResult();

        try {
            result = banksService.delBanks(code);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除银行失败
            result.setMsg(ResultMsg.C_019);
        }

        return result;
    }

}










