package com.willie.cloud.vod.controller.video;

import com.willie.cloud.vod.constent.vod.Vod;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.service.vod.CloudVodQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/28 14:37</p>
 */
@Controller
public class VodPortalController {
    private final CloudVodQueryService cloudVodQueryService;

    @RequestMapping("/")
    public String index() {
        CloudVodConfig enableConfig = cloudVodQueryService.getEnableCloudVodManager();
        String appName = enableConfig.getAppName();
        if (Vod.AliyunConstent.APP_NAME.equalsIgnoreCase(appName)) {
            return "";//到阿里首页
        } else if (Vod.BfCloudConstent.APP_NAME.equalsIgnoreCase(appName)) {
            return "redirect:/video/bf/videos";//暴风首页
        } else {
            return "redirect:/video/videos";//腾讯首页
        }
    }

    @Autowired
    public VodPortalController(CloudVodQueryService cloudVodQueryService) {
        this.cloudVodQueryService = cloudVodQueryService;
    }
}
