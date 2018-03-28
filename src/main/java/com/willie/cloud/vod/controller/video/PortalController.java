package com.willie.cloud.vod.controller.video;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/28 14:37</p>
 */
@Controller
public class PortalController {
    @RequestMapping("/")
    public String index() {
        return "/index";
    }
}
