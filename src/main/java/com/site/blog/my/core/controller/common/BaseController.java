package com.site.blog.my.core.controller.common;

import com.site.blog.my.core.util.BlogObjectUtils;

import java.util.Map;

/**
 * Controller基类,目前只实现了主题自动切换功能
 *
 * @author yuzy
 * @version 1.0
 * @date 2019/12/22 20:16
 */

public abstract class BaseController {
    /**
     * 定义默认主题
     */
    public static String THEME = "amaze";
    /**
     * 根据主题名称渲染页面
     *
     * @param pageName pageName
     * @param configMap 配置内容
     * @return 返回拼接好的模板路径
     */
    public String render(String pageName, Map<String, String> configMap){
        // 若有配置过主题,则使用配置的主题,否则使用默认
        if (configMap.containsKey("currentTheme") &&
                BlogObjectUtils.isNotEmpty(configMap.get("currentTheme")) &&
                !THEME.equals(configMap.get("currentTheme"))){
            THEME = configMap.get("currentTheme");
        }
        StringBuffer themeStr = new StringBuffer("blog/");
        themeStr.append(THEME);
        themeStr.append("/");
        return themeStr.append(pageName).toString();
    }
}
