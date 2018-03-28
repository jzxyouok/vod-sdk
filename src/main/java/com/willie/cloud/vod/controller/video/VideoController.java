package com.willie.cloud.vod.controller.video;

import com.alibaba.fastjson.JSONObject;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.domain.video.Video;
import com.willie.cloud.vod.service.video.VideoService;
import com.willie.cloud.vod.service.vod.CloudVodQueryService;
import com.willie.cloud.vod.service.vod.CloudVodUpdateService;
import com.willie.cloud.vod.util.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
        List<Video> videos = videoService.getVideoRepository().findVideosByAppIdAndVideoIdIsNotNull(enableConfig.getAppId(), new Sort(Sort.Direction.DESC, "uploadDate"));
        model.addAttribute("videos", videos);
        return "/video/tencent/videos";
    }

    @RequestMapping(value = "/upload/videos", method = RequestMethod.GET)
    public String toUpload() {
        return "/video/tencent/upload/uploadVideo";
    }

    @RequestMapping(value = "/videos", method = RequestMethod.POST)
    public String uploadVideo(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String name = file.getOriginalFilename();
                logger.info("文件名：name{}", name);
                String serverPath = File.separator + "static" + File.separator + "video" + File.separator;//文件在服务端路径
                String realPath = FileUploadUtil.transferFile2Server(name, serverPath, request, file);//上传文件
                JSONObject info = (JSONObject) cloudVodUpdateService.uploadFile2Server(realPath, null);
                if (0 != info.getIntValue("code")) {
                    return "/error/error";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
