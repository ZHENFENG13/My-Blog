package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.dao.BlogConfigMapper;
import com.site.blog.my.core.entity.BlogConfig;
import com.site.blog.my.core.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private BlogConfigMapper configMapper;

    public static final String websiteName = "personal blog";
    public static final String websiteDescription = "personal blog是SpringBoot2+Thymeleaf+Mybatis建造的个人博客网站.SpringBoot实战博客源码.个人博客搭建";
    public static final String websiteLogo = "/admin/dist/img/logo2.png";
    public static final String websiteIcon = "/admin/dist/img/favicon.png";

    public static final String yourAvatar = "/admin/dist/img/13.png";
    public static final String yourEmail = "2449207463@qq.com";
    public static final String yourName = "十三";

    public static final String footerAbout = "your personal blog. have fun.";
    public static final String footerICP = "浙ICP备 xxxxxx-x号";
    public static final String footerCopyRight = "@2018 十三";
    public static final String footerPoweredBy = "personal blog";
    public static final String footerPoweredByURL = "##";

    @Override
    public int updateConfig(String configName, String configValue) {
        BlogConfig blogConfig = configMapper.selectByPrimaryKey(configName);
        if (blogConfig != null) {
            blogConfig.setConfigValue(configValue);
            blogConfig.setUpdateTime(new Date());
            return configMapper.updateByPrimaryKeySelective(blogConfig);
        }
        return 0;
    }

    @Override
    public Map<String, String> getAllConfigs() {
        //获取所有的map并封装为map
        List<BlogConfig> blogConfigs = configMapper.selectAll();
        Map<String, String> configMap = blogConfigs.stream().collect(Collectors.toMap(BlogConfig::getConfigName, BlogConfig::getConfigValue));
        for (Map.Entry<String, String> config : configMap.entrySet()) {
            if ("websiteName".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(websiteName);
            }
            if ("websiteDescription".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(websiteDescription);
            }
            if ("websiteLogo".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(websiteLogo);
            }
            if ("websiteIcon".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(websiteIcon);
            }
            if ("yourAvatar".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(yourAvatar);
            }
            if ("yourEmail".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(yourEmail);
            }
            if ("yourName".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(yourName);
            }
            if ("footerAbout".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerAbout);
            }
            if ("footerICP".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerICP);
            }
            if ("footerCopyRight".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerCopyRight);
            }
            if ("footerPoweredBy".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerPoweredBy);
            }
            if ("footerPoweredByURL".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerPoweredByURL);
            }
        }
        return configMap;
    }
}
