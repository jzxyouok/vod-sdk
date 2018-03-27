package config;

import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.domain.video.Video;
import com.willie.cloud.vod.service.video.VideoService;
import com.willie.cloud.vod.service.vod.CloudVodQueryService;
import com.willie.cloud.vod.service.vod.CloudVodUpdateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * <p>功能 描述:腾讯云点播统一接口测试</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 15:06</p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class TestTencentCloudVodService {
    @Autowired
    private CloudVodUpdateService cloudVodUpdateService;

    @Autowired
    private CloudVodQueryService cloudVodQueryService;

    @Autowired
    private VideoService videoService;

    @Test
    public void testFindUploadedVideos() {
        CloudVodConfig enableConfig = cloudVodQueryService.getEnableCloudVodManager();
        List<Video> videos = videoService.getVideoRepository().findVideosByAppIdAndVideoIdIsNotNull(enableConfig.getAppId());
        System.out.println(videos);
    }

    /**
     * 测试简单上传
     */

    @Test
    public void testUploadVideo() {
        try {
            Map<String, Object> info = cloudVodUpdateService.uploadFile2Server("dxyw.mp4", null);
            System.out.println(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
