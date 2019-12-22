package com.site.blog.my.core.entity;

import java.io.Serializable;

/**
 * 主题实体
 *
 * @author yuzy
 * @version 1.0
 * @date 2019/12/22 20:01
 */

public class Theme implements Serializable {

    /**
     * 主题名称
     */
    private String themeName;
    /**
     * 是否支持设置
     */
    private Boolean hasOptions;

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public Boolean isHasOptions() {
        return hasOptions;
    }

    public void setHasOptions(Boolean hasOptions) {
        this.hasOptions = hasOptions;
    }

    public Theme() {
    }

    public Theme(String themeName, boolean hasOptions) {
        this.themeName = themeName;
        this.hasOptions = hasOptions;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "themeName='" + themeName + '\'' +
                ", hasOptions=" + hasOptions +
                '}';
    }
}
