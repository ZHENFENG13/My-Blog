package com.site.blog.my.core.service;

import com.site.blog.my.core.entity.BlogConfig;

import javax.validation.constraints.NotNull;
import java.util.Map;

public interface ConfigService {
    /**
     * 修改配置项
     *
     * @param configName
     * @param configValue
     * @return
     */
    int updateConfig(String configName, String configValue);

    /**
     * 获取所有的配置项
     *
     * @return
     */
    Map<String,String> getAllConfigs();

    /**
     * 插入配置下
     * @param configName 配置名
     * @param configValue 配置值
     * @return 影响几行
     */
    int insert (@NotNull String configName, String configValue);

    /**
     * 查询配置是否存在
     * @param configName 配置名
     * @return 配置
     */
    BlogConfig selectByConfigName(String configName);
}
