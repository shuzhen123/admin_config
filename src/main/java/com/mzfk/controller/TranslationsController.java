package com.mzfk.controller;


import com.mzfk.constant.ResultMsg;
import com.mzfk.entity.Translations;
import com.mzfk.pojo.TranslationsInfo;
import com.mzfk.pojo.AjaxResult;
import com.mzfk.service.TranslationsService;
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
 * @Title: TranslationsController
 * @ProjectName 8Madmin_config
 * @Description: 译文相关controller
 * @date 2018/10/2215:01
 */
@Controller
@RequestMapping("/config")
@Api(value = "翻译Controller",tags = {"翻译Controller"})
public class TranslationsController{

    @Autowired
    TranslationsService tService;

    private Logger log = LoggerFactory.getLogger(TranslationsController.class);

    /**
     *获取译文相关列表
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/translations")
    @ApiOperation(value = "获取译文相关列表",httpMethod = "GET",notes = "获取译文相关列表",response = AjaxResult.class)
    public AjaxResult findTranslationsList(@ApiParam(name="分页参数",value="params") TranslationsInfo params) {
        AjaxResult result = new AjaxResult();

        try {
            result = tService.findTranslationsList(params);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 获取译文相关列表 err
            result.setMsg(ResultMsg.C_080);
        }

        return result;
    }


    /**
     * 添加译文
     * @return AjaxResult
     */
    @ResponseBody
    @PostMapping("/translations")
    @ApiOperation(value = "添加译文",httpMethod = "POST",notes = "添加译文",response = AjaxResult.class)
    public AjaxResult addTranslations(@ApiParam(name="译文参数",value="translations",required = true) @RequestBody Translations translations
    ) {
        AjaxResult result = new AjaxResult();

        try {
            result = tService.addTranslations(translations);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 添加译文 err
            result.setMsg(ResultMsg.C_081);
        }

        return result;
    }



    /**
     * 更新译文
     * @param translations 标识对象
     * @return AjaxResult
     */
    @ApiOperation(value = "更新译文", httpMethod = "PATCH", notes = "更新译文", response = AjaxResult.class)
    @PatchMapping("/translations/{id}")
    @ResponseBody
    public AjaxResult updateTranslations (
            @ApiParam(name="译文id",value="id",required = true) @PathVariable String id,
            @ApiParam(name="译文参数",value="translations",required = true) @RequestBody Translations translations
    ) {
        AjaxResult result = new AjaxResult();

        try {
            result = tService.updateTranslations(translations,id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 更新译文 err
            result.setMsg(ResultMsg.C_082);
        }

        return result;

    }


    /**
     * 删除译文
     * @param id id
     * @return AjaxResult
     */
    @ApiOperation(value = "删除译文", httpMethod = "DELETE", notes = "删除译文", response = AjaxResult.class)
    @DeleteMapping("/translations/{id}")
    @ResponseBody
    public AjaxResult delTranslations (@ApiParam(name="译文id",value="id",required = true) @PathVariable String id) {
        AjaxResult result = new AjaxResult();
        // 删除操作
        try {
            result = tService.delTranslations(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 删除译文 err
            result.setMsg(ResultMsg.C_083);
        }

        return result;

    }


}
