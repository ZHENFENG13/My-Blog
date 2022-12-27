package com.site.blog.my.core.controller.admin;

import com.site.blog.my.core.service.ConfigService;
import com.site.blog.my.core.util.Result;
import com.site.blog.my.core.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link http://13blog.site
 */
@Controller
@RequestMapping("/admin")
public class ConfigurationController {

    @Resource
    private ConfigService configService;

    @GetMapping("/configurations")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "configurations");
        request.setAttribute("configurations", configService.getAllConfigs());
        return "admin/configuration";
    }

    @PostMapping("/configurations/website")
    @ResponseBody
    public Result website(@RequestParam(value = "websiteName", required = false) String websiteName,
                          @RequestParam(value = "websiteDescription", required = false) String websiteDescription,
                          @RequestParam(value = "websiteLogo", required = false) String websiteLogo,
                          @RequestParam(value = "websiteIcon", required = false) String websiteIcon) {
        int updateResult = 0;
        if (StringUtils.hasText(websiteName)) {
            updateResult += configService.updateConfig("websiteName", websiteName);
        }
        if (StringUtils.hasText(websiteDescription)) {
            updateResult += configService.updateConfig("websiteDescription", websiteDescription);
        }
        if (StringUtils.hasText(websiteLogo)) {
            updateResult += configService.updateConfig("websiteLogo", websiteLogo);
        }
        if (StringUtils.hasText(websiteIcon)) {
            updateResult += configService.updateConfig("websiteIcon", websiteIcon);
        }
        return ResultGenerator.genSuccessResult(updateResult > 0);
    }

    @PostMapping("/configurations/userInfo")
    @ResponseBody
    public Result userInfo(@RequestParam(value = "yourAvatar", required = false) String yourAvatar,
                           @RequestParam(value = "yourName", required = false) String yourName,
                           @RequestParam(value = "yourEmail", required = false) String yourEmail) {
        int updateResult = 0;
        if (StringUtils.hasText(yourAvatar)) {
            updateResult += configService.updateConfig("yourAvatar", yourAvatar);
        }
        if (StringUtils.hasText(yourName)) {
            updateResult += configService.updateConfig("yourName", yourName);
        }
        if (StringUtils.hasText(yourEmail)) {
            updateResult += configService.updateConfig("yourEmail", yourEmail);
        }
        return ResultGenerator.genSuccessResult(updateResult > 0);
    }

    @PostMapping("/configurations/footer")
    @ResponseBody
    public Result footer(@RequestParam(value = "footerAbout", required = false) String footerAbout,
                         @RequestParam(value = "footerICP", required = false) String footerICP,
                         @RequestParam(value = "footerCopyRight", required = false) String footerCopyRight,
                         @RequestParam(value = "footerPoweredBy", required = false) String footerPoweredBy,
                         @RequestParam(value = "footerPoweredByURL", required = false) String footerPoweredByURL) {
        int updateResult = 0;
        if (StringUtils.hasText(footerAbout)) {
            updateResult += configService.updateConfig("footerAbout", footerAbout);
        }
        if (StringUtils.hasText(footerICP)) {
            updateResult += configService.updateConfig("footerICP", footerICP);
        }
        if (StringUtils.hasText(footerCopyRight)) {
            updateResult += configService.updateConfig("footerCopyRight", footerCopyRight);
        }
        if (StringUtils.hasText(footerPoweredBy)) {
            updateResult += configService.updateConfig("footerPoweredBy", footerPoweredBy);
        }
        if (StringUtils.hasText(footerPoweredByURL)) {
            updateResult += configService.updateConfig("footerPoweredByURL", footerPoweredByURL);
        }
        return ResultGenerator.genSuccessResult(updateResult > 0);
    }


}
