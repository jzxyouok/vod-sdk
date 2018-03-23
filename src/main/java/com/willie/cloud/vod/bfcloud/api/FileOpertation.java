package com.willie.cloud.vod.bfcloud.api;

import java.util.Map;

/**
 * <p>功能 描述:暴风云直接文件操作接口</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/16 15:49</p>
 */
public interface FileOpertation {
    String DELETE_FILE = "/api/deletefile";//删除文件接口地址

    /**
     * <li>接口名：/api/deletefile</li>
     * <li>请求方法：GET</li>
     * 删除一个文件，如果文件不存在则会返回成功
     *
     * @param fileId  32字节文件id，唯一标识符
     * @param expires token超时时间, Unix时间，从1970年01月01日起至今的秒数
     * @return status 0表示成功，非0表示失败
     */
    Map<String, Object> deleteFile(String fileId, long expires);
}
