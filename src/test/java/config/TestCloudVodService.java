package config;

import com.willie.cloud.vod.domain.config.CloudVodConfig;
import com.willie.cloud.vod.service.CloudVodService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 11:06</p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class TestCloudVodService {
    @Autowired
    private CloudVodService cloudVodService;

    @Ignore
    @Test
    public void testSaveConfig() {
        CloudVodConfig baofeng = new CloudVodConfig();
        baofeng.setAppName("baofeng");
        baofeng.setAppId("12473494");
        baofeng.setAccessKey("UQ2DhfGq1Dccnb5210=j11nef3OiRmUBxUjD0yc0");
        baofeng.setSecretKey("EV6DhfGq1nE6nbNM606x4rnZlyKpEMNS-RFhG-9f");
        baofeng.setEnable(1L);

        CloudVodConfig tencent = new CloudVodConfig();
        tencent.setAppName("tencent");
        tencent.setAppId("1256242248");
        tencent.setAccessKey("AKIDXxA8uRxqhIfmiRFFQUWP7bt1xf8LD6ey");
        tencent.setSecretKey("IMCn9G8oKdibHiz2rRmsmU1NbxBHJD0g");
        tencent.setEnable(1L);

        List<CloudVodConfig> configs = new ArrayList<>(2);
        configs.add(baofeng);
        configs.add(tencent);

        System.out.println(cloudVodService.getCloudVodConfigRepository().save(configs));
    }

    @Ignore
    @Test
    public void testFindConfigByName() {
        System.out.println(cloudVodService.getCloudVodConfigRepository().findCloudVodConfigByAppName("baofeng"));
    }

    @Ignore
    @Test
    public void testDeleteConfig() {
        CloudVodConfig bean = cloudVodService.getCloudVodConfigRepository().findOne(1);
        cloudVodService.getCloudVodConfigRepository().delete(bean);
    }

    @Ignore
    @Test
    public void testUpdateConfig() {
        CloudVodConfig bean = cloudVodService.getCloudVodConfigRepository().findCloudVodConfigByAppName("tencent");
        bean.setEnable(0L);
        cloudVodService.getCloudVodConfigRepository().save(bean);
    }
}
