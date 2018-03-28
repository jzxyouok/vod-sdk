package com.willie.cloud.vod.controller.video;

import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.domain.video.Video;
import com.willie.cloud.vod.exception.ParameterException;
import com.willie.cloud.vod.repository.video.VideoRepository;
import com.willie.cloud.vod.service.video.VideoService;
import com.willie.cloud.vod.service.vod.CloudVodQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/27 15:46</p>
 */
@Controller
@RequestMapping("video")
public class PlayerController {
    private static Logger logger = LoggerFactory.getLogger(PlayerController.class);
    private final VideoService videoService;
    private final CloudVodQueryService cloudVodQueryService;

    @RequestMapping(value = "/play/{id}/videos", method = RequestMethod.GET)
    public String play(@PathVariable(value = "id") Integer id, Model model) {
        if (null == id) {
            throw new ParameterException("文件id能为空！");
        }

        try {
            VideoRepository videoRepository = videoService.getVideoRepository();
            Video video = videoRepository.findOne(id);
            model.addAttribute("appId", video.getAppId());
            model.addAttribute("fileId", video.getVideoId());

            CloudVodConfig enableConfig = cloudVodQueryService.getEnableCloudVodManager();
            List<Video> videos = videoService.getVideoRepository().findVideosByAppIdAndVideoIdIsNotNull(enableConfig.getAppId(),new Sort(Sort.Direction.DESC,"uploadDate"));
            model.addAttribute("videos", videos);
            return "/video/tencent/play/playVideo";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "/error/error";
        }
    }

    @Autowired
    public PlayerController(VideoService videoService, CloudVodQueryService cloudVodQueryService) {
        this.videoService = videoService;
        this.cloudVodQueryService = cloudVodQueryService;
    }
}
