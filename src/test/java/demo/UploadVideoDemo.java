package demo;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadURLStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadURLStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/26 18:38</p>
 * <p>
 * 以下Java示例代码演示了如何在服务端上传文件至视频点播。
 * 目前支持两种方式上传：
 * 1.上传本地文件，使用分片上传，并支持断点续传，最大支持48.8TB的单个文件。参见testUploadVideo函数。
 * 2.上传网络流，可指定文件URL进行上传，不支持断点续传，最大支持5GB的单个文件。参见testUploadURLStream函数。
 * 请替换示例中的必选参数，示例中的可选参数如果您不需要设置，请将其删除，以免设置无效参数值与您的预期不符。
 */

public class UploadVideoDemo {
    //账号AK信息请填写(必选)
    private static final String accessKeyId = "";
    //账号AK信息请填写(必选)
    private static final String accessKeySecret = "";

    public static void main(String[] args) {
        //视频标题(必选)
        String title = "测试标题";
        //1.本地文件上传时，文件名称为上传文件绝对路径，如:/User/sample/文件名称.mp4 (必选) 2.网络流上传时，文件名称为源文件名，如文件名称.mp4(必选)。任何上传方式文件名必须包含扩展名
        String fileName = "文件名称.mp4";
        //本地文件上传
        testUploadVideo(accessKeyId, accessKeySecret, title, fileName);
        //待上传视频的网络流地址
        String url = "http://video.sample.com/sample.mp4";
        //网络流上传
        testUploadURLStream(accessKeyId, accessKeySecret, title, fileName, url);
    }

    /**
     * 本地文件上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     */
    private static void testUploadVideo(String accessKeyId, String accessKeySecret, String title, String fileName) {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        request.setPartSize(10 * 1024 * 1024L);     //可指定分片上传时每个分片的大小，默认为10M字节
        request.setTaskNum(1);                      //可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）
        request.setIsShowWaterMark(true);           //是否使用默认水印
        request.setCallback("http://callback.sample.com");  //设置上传完成后的回调URL(可选)
        request.setCateId(0);                       //视频分类ID(可选)
        request.setTags("标签1,标签2");              //视频标签,多个用逗号分隔(可选)
        request.setDescription("视频描述");          //视频描述(可选)
        request.setCoverURL("http://cover.sample.com/sample.jpg"); //封面图片(可选)
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 网络流上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     * @param url
     */
    private static void testUploadURLStream(String accessKeyId, String accessKeySecret, String title, String fileName, String url) {
        UploadURLStreamRequest request = new UploadURLStreamRequest(accessKeyId, accessKeySecret, title, fileName, url);
        request.setShowWaterMark(true);             //是否使用默认水印
        request.setCallback("http://callback.sample.com");//设置上传完成后的回调URL(可选)
        request.setCateId(0);                       //视频分类ID(可选)
        request.setTags("标签1,标签2");              //视频标签,多个用逗号分隔(可选)
        request.setDescription("视频描述");          //视频描述(可选)
        request.setCoverURL("http://cover.sample.com/sample.jpg");  //封面图片(可选)
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadURLStreamResponse response = uploader.uploadURLStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n"); //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
}
