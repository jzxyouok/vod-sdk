package com.willie.cloud.vod.controller.video.bf;

import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.domain.video.Video;
import com.willie.cloud.vod.service.video.VideoService;
import com.willie.cloud.vod.service.vod.CloudVodQueryService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/28 13:49</p>
 */
@Controller
@RequestMapping("video")
public class BfCloudVideoController {
    private final VideoService videoService;
    private final CloudVodQueryService cloudVodQueryService;

    @RequestMapping(value = "/bf/videos", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Model model) {
        CloudVodConfig enableConfig = cloudVodQueryService.getEnableCloudVodManager();
        List<Video> videos = videoService.getVideoRepository().findVideosByAppIdAndVideoIdIsNotNull(enableConfig.getAppId(), new Sort(Sort.Direction.DESC, "uploadDate"));
        model.addAttribute("videos", videos);
        return "/video/bf/bfVideos";
    }

    public BfCloudVideoController(VideoService videoService, CloudVodQueryService cloudVodQueryService) {
        this.videoService = videoService;
        this.cloudVodQueryService = cloudVodQueryService;
    }
}
