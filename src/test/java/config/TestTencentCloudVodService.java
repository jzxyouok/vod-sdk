package config;

import com.willie.cloud.vod.service.CloudVodService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>功能 描述:腾讯云点播统一接口测试</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 15:06</p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class TestTencentCloudVodService {
    @Autowired
    private CloudVodService cloudVodService;
}
