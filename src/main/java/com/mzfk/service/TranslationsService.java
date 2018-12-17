package com.mzfk.service;


import com.mzfk.entity.Translations;
import com.mzfk.pojo.TranslationsInfo;
import com.mzfk.pojo.AjaxResult;

/**
 * @author sz
 * @Title: TranslationsService
 * @ProjectName 8Madmin_config
 * @Description: 译文相关
 * @date 2018/10/2215:08
 */
public interface TranslationsService {


    /**
     * 获取译文相关列表
     * @param params
     * @return
     */
    AjaxResult findTranslationsList(TranslationsInfo params);

    /**
     * 添加译文
     * @return
     */
    AjaxResult addTranslations(Translations translations);

    /**
     * 更新译文
     * @param translations
     * @return
     */
    AjaxResult updateTranslations(Translations translations,String id);

    /**
     * 删除译文
     * @param id
     * @return
     */
    AjaxResult delTranslations(String id);
}
