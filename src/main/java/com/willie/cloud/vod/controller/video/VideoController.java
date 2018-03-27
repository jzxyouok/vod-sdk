package com.willie.cloud.vod.controller.video;

import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.domain.video.Video;
import com.willie.cloud.vod.service.video.VideoService;
import com.willie.cloud.vod.service.vod.CloudVodQueryService;
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
    private final VideoService videoService;
    private final CloudVodQueryService cloudVodQueryService;

    @RequestMapping(value = "/videos",method = RequestMethod.GET)
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

    @RequestMapping(value = "/videos", consumes = "multipart/form-data", method = RequestMethod.POST)
    public String uploadVideo(@RequestParam("videoFile") CommonsMultipartFile videoFile) {
        return null;
    }

    @Autowired
    public VideoController(VideoService videoService, CloudVodQueryService cloudVodQueryService) {
        this.videoService = videoService;
        this.cloudVodQueryService = cloudVodQueryService;
    }
}
