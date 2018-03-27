package config;

import com.willie.cloud.vod.service.vod.CloudVodUpdateService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * <p>功能 描述:暴风云点播统一接口测试</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 15:04</p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class TestBaoFengCloudVodService {
    @Autowired
    private CloudVodUpdateService cloudVodUpdateService;

    /**
     * 测试删除分类接口
     */
    @Ignore
    @Test
    public void testDeleteCategory() {
        Map<String, Object> info = cloudVodUpdateService.deleteCategory("6365528", null);
        System.out.println(info);
    }

    /**
     * 测试在分类中添加文件
     */
    @Ignore
    @Test
    public void testUpdateFile2Category() {

    }

    public void testDeleleFile() {
        String fileId = "5E6E6AA54DD84FAE80B8CD167029EF2D";
    }
}
