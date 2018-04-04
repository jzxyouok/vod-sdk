package config;

import com.aliyuncs.exceptions.ClientException;
import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.domain.video.Video;
import com.willie.cloud.vod.service.video.VideoService;
import com.willie.cloud.vod.service.vod.CloudVodQueryService;
import com.willie.cloud.vod.service.vod.CloudVodUpdateService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
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

    /**
     * 测试上传成功的文件查询
     */
    @Ignore
    @Test
    public void testFindUploadedVideos() {
        Sort sort = new Sort(Sort.Direction.DESC, "uploadDate");
        CloudVodConfig enableConfig = cloudVodQueryService.getEnableCloudVodManager();
        List<Video> videos = videoService.getVideoRepository().findVideosByAppIdAndVideoIdIsNotNull(enableConfig.getAppId(), sort);
        System.out.println(videos);
    }

    /**
     * 测试增加分类
     */
    @Ignore
    @Test
    public void testAddCategory() {
        try {
            Map<String, Object> info = cloudVodUpdateService.addCategory("测试腾讯分类", "", null);
            System.out.println(info);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试在分类中添加文件
     */
    @Ignore
    @Test
    public void testModifyVideoInfo() {
        Map<String, Object> info = null;
        try {
            info = cloudVodUpdateService.addFile2Category("395555", "7447398155216264774", null);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.println(info);
    }

    /**
     * 测试简单上传
     */
    @Ignore
    public void testUploadVideo() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
