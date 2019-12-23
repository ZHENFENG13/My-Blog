package com.site.blog.my.core.controller.admin;

import com.site.blog.my.core.entity.BlogConfig;
import com.site.blog.my.core.service.ConfigService;
import com.site.blog.my.core.util.BlogObjectUtils;
import com.site.blog.my.core.util.Result;
import com.site.blog.my.core.util.ResultGenerator;
import com.site.blog.my.core.util.ThemeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 主题
 *
 * @author yuzy
 * @version 1.0
 * @date 2019/12/23 09:49
 */

@Controller
@RequestMapping("/admin")
public class ThemeController {

    private static final Logger log = LoggerFactory.getLogger(ThemeController.class);
    @Resource
    private ConfigService configService;

    @GetMapping("/theme")
    public String list(HttpServletRequest request){
        ThemeUtil.getThemes().forEach(s -> log.info(s.toString()));
        request.setAttribute("themes",ThemeUtil.getThemes());
        request.setAttribute("configurations", configService.getAllConfigs());
        return "admin/theme";
    }

    @PostMapping("/theme/set")
    @ResponseBody
    public Result website(@RequestParam(value = "themeName", required = false) String themeName) {
        int updateResult = 0;
        BlogConfig blogConfig = configService.selectByConfigName("currentTheme");
        if (BlogObjectUtils.isNotEmpty(themeName)) {
            if (blogConfig == null){
                updateResult = configService.insert("currentTheme", themeName);
                return ResultGenerator.genSuccessResult(updateResult > 0);
            }
            updateResult += configService.updateConfig("currentTheme", themeName);
        }
        return ResultGenerator.genSuccessResult(updateResult > 0);
    }
}
