package com.site.blog.my.core.util;

import com.site.blog.my.core.entity.Theme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 博客主题工具类
 *
 * @author yuzy
 * @version 1.0
 * @date 2019/12/22 20:28
 */

public class ThemeUtil {
    private static final Logger log = LoggerFactory.getLogger(ThemeUtil.class);
    /**
     * 获取所有主题
     * @return list
     */
    public static List<Theme> getThemes(){
        List<Theme> themes = new ArrayList<>();
        try {
            // 获取项目根路径
            File basePath = new File(ResourceUtils.getURL("classpath:").getPath());
            // 获取主题路径
            File themesPath = new File(basePath.getAbsolutePath(),"templates/blog");
            File[] files = themesPath.listFiles();
            if(null!=files) {
                Theme theme = null;
                for (File file : files) {
                    if (file.isDirectory()) {
                        theme = new Theme();
                        theme.setThemeName(file.getName());
                        File optionsPath = new File(themesPath.getAbsolutePath(), file.getName() + "/index.html");
                        // 判断是否存在index页面模板
                        if (optionsPath.exists()) {
                            theme.setHasOptions(true);
                        } else {
                            theme.setHasOptions(false);
                        }
                        themes.add(theme);
                    }
                }
            }
        }catch (Exception e){
            log.error("主题获取失败："+e.getMessage());
        }
        return themes;
    }
}
