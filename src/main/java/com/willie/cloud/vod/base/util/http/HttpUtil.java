package com.willie.cloud.vod.base.util.http;

import com.alibaba.fastjson.JSONObject;
import com.willie.cloud.vod.constent.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>功能 描述:http工具类</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/3/16 16:12</p>
 */
public abstract class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 发送GET请求
     *
     * @param url 接口地址
     * @return status 0 代表成功，非0代表失败
     */
    public static JSONObject transferGetResult(String url) {
        logger.info("GET请求URL:{}", url);
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        JSONObject resultMap = null;
        try {
            httpGet.setHeader("Content-Type", "application/json");
            ResponseHandler<String> responseHandler = getResponseHandler();
            logger.info("绑定求处理:{}", responseHandler);
            String responseBody = client.execute(httpGet, responseHandler);
            logger.info("GET请求响应体:{}", responseBody);
            resultMap = JSONObject.parseObject(responseBody);
        } catch (IOException e) {
            logger.error("客户端请求失败errInfo:{}",e);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 发送POST请求
     *
     * @param url  接口地址
     * @param data 请求参数
     * @return status 0 代表成功，非0代表失败
     */
    public static JSONObject transferPostResult(String url, Map<String, String> data) {
        logger.info("POST请求URL:{},请求参数:{}", url, data);
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> nameValuePairArrayList;
        UrlEncodedFormEntity entity = null;
        JSONObject resultMap = null;
        try {
            //将传过来的参数添加到List<NameValuePair>中
            if (null != data && !data.isEmpty()) {
                nameValuePairArrayList = new ArrayList<>(data.size());
                data.forEach((key, value) -> nameValuePairArrayList.add(new BasicNameValuePair(key, value)));
               /* for (Map.Entry<String, String> entry : data.entrySet()) {
                    nameValuePairArrayList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }*/
                entity = new UrlEncodedFormEntity(nameValuePairArrayList, Charset.CHARSET);
                // 为HttpPost设置实体数据
                httpPost.setEntity(entity);
            }

            ResponseHandler<String> responseHandler = getResponseHandler();
            logger.info("绑定求处理:{}", responseHandler);
            String responseBody = client.execute(httpPost, responseHandler);
            logger.info("POST请求响应体:{}", responseBody);
            resultMap = JSONObject.parseObject(responseBody);
        } catch (UnsupportedEncodingException | ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 取得响应处理
     *
     * @return 响应体
     */
    private static ResponseHandler<String> getResponseHandler() {
        return httpResponse -> {
            int status = httpResponse.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = httpResponse.getEntity();
                return Objects.isNull(entity) ? null : EntityUtils.toString(entity);
            } else {
                throw new ClientProtocolException(
                        "Unexpected response status: " + status);
            }
        };
    }
}
