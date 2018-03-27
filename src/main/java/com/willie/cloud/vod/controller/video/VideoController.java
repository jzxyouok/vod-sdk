package com.willie.cloud.vod.controller.video;

import com.alibaba.fastjson.JSONObject;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.domain.video.Video;
import com.willie.cloud.vod.service.video.VideoService;
import com.willie.cloud.vod.service.vod.CloudVodQueryService;
import com.willie.cloud.vod.service.vod.CloudVodUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/27 11:36</p>
 */
@Controller
@RequestMapping("video")
public class VideoController {
    private static Logger logger = LoggerFactory.getLogger(VideoController
            .class);
    private final VideoService videoService;
    private final CloudVodQueryService cloudVodQueryService;
    private final CloudVodUpdateService cloudVodUpdateService;

    @RequestMapping(value = "/videos", method = RequestMethod.GET)
    public String index(Model model) {
        CloudVodConfig enableConfig = cloudVodQueryService.getEnableCloudVodManager();
        List<Video> videos = videoService.getVideoRepository().findVideosByAppIdAndVideoIdIsNotNull(enableConfig.getAppId());
        model.addAttribute("videos", videos);
        return "/video/tencent/videos";
    }

    @RequestMapping(value = "/upload/videos", method = RequestMethod.GET)
    public String toUpload() {
        return "/video/tencent/upload/uploadVideo";
    }

    @RequestMapping(value = "/videos", method = RequestMethod.POST)
    public String uploadVideo(@RequestParam("file") CommonsMultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String name = file.getOriginalFilename();
                logger.info("文件名：name{}", name);

                JSONObject info = (JSONObject) cloudVodUpdateService.uploadFile2Server(name, null);
                if (0 != info.getIntValue("code")) {
                    return "/error/error";
                }
            }
        } catch (Exception e) {
            logger.error("", e.getMessage(), e);
            return "/error/error";
        }
        return "redirect:/video/videos";
    }

    @Autowired
    public VideoController(VideoService videoService, CloudVodQueryService cloudVodQueryService, CloudVodUpdateService cloudVodUpdateService) {
        this.videoService = videoService;
        this.cloudVodQueryService = cloudVodQueryService;
        this.cloudVodUpdateService = cloudVodUpdateService;
    }
}
