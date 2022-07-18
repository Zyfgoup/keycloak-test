package com.zyfgoup.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

@Slf4j
public class HttpClientUtil {

    /**
     * 通过post方式调用http接口
     *
     * @param url       url路径
     * @param
     * @return
     * @throws Exception
     */
    public static String sendPostUrlEncoded(String url, Map<String,Object> map) {
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime = System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime = 0L;
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            // 创建连接
            httpClient = HttpClientBuilder.create().build();
            // 设置请求头和报文
            HttpPost httpPost = new HttpPost(url);
            // 设置提交方式
            httpPost.setHeader("Content-type", HttpConstant.APPLICATION_FORM_URLENCODED_VALUE);
            // 设置报文和通讯格式
            // 添加参数
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            if (map.size() != 0) {
                // 将mapdata中的key存在set集合中，通过迭代器取出所有的key，再获取每一个键对应的值
                Set keySet = map.keySet();
                Iterator it = keySet.iterator();
                while (it.hasNext()) {
                    String k =  it.next().toString();// key
                    String v = (String)map.get(k);// value
                    nameValuePairs.add(new BasicNameValuePair(k, v));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            log.info("请求{}接口的参数为{}", url, nameValuePairs);
            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            log.error("请求{}接口出现异常", url, e);
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                log.error("http请求释放资源异常", e);
            }
        }
        log.info("请求{}接口的响应报文内容为{},本次请求API接口的响应时间为:{}毫秒", url, result, (endTime - startTime));
        return result;
    }
}