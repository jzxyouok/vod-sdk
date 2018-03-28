package com.willie.cloud.vod.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * <p>功能 描述:文件上传工具类</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/28 10:14</p>
 */
public class FileUploadUtil {

    /**
     * 上传文件到服务器
     *
     * @param originFileName 源文件名
     * @param serverPath     服务端路径
     * @param request        请求
     * @param fileEntity     上传文件
     * @return realPath
     * @throws IOException 上传文件异常
     */
    public static String transferFile2Server(String originFileName, String serverPath, HttpServletRequest request, MultipartFile fileEntity) throws IOException {
        String realPath = request.getServletContext().getRealPath(serverPath);
        File filePath = new File(realPath, originFileName);

        //判断路径是否存在，如果不存在就创建一个
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }

        fileEntity.transferTo(new File(realPath + originFileName));//上传文件

        return realPath + File.separator + originFileName;
    }
}
