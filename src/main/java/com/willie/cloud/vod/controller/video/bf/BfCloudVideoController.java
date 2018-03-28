package com.willie.cloud.vod.controller.video.bf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/28 13:49</p>
 */
@Controller
@RequestMapping("video")
public class BfCloudVideoController {
    @RequestMapping(value = "/bf/videos", method = RequestMethod.GET)
    public String list() {
        return "/video/bf/baofeng";
    }
}
