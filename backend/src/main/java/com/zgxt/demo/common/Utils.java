package com.zgxt.demo.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static com.zgxt.demo.config.Config.*;

public class Utils {
    public static JSONArray getParams(Object ...val) {
        return new JSONArray(Arrays.asList(val));
    }
    public  static JSONObject getInputJsonObject(String user, String funcName, JSONArray params) {
        JSONObject _jsonObj = new JSONObject();
        _jsonObj.put("contractName",CONTRACT_NAME);
        _jsonObj.put("contractAddress",CONTRACT_ADDRESS);
        _jsonObj.put("contractAbi",JSONArray.parseArray(CONTRACT_ABI));
        if (user != null) {
            _jsonObj.put("user",user);
        }
        if (params != null) {
            _jsonObj.put("funcParam", params);
        }
        _jsonObj.put("funcName",funcName);
        return _jsonObj;
    }
    /**
     * 发送 post 请求
     * @param url     请求地址
     * @param jsonStr Form表单json字符串
     * @return 请求结果
     */
    public static String httpPost(String url, String jsonStr) {
        // 创建httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建post请求方式实例
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头 发送的是json数据格式
        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        // 设置参数---设置消息实体 也就是携带的数据
        StringEntity entity = new StringEntity(jsonStr, Charset.forName("UTF-8"));
        // 设置编码格式
        entity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        // 把请求消息实体塞进去
        httpPost.setEntity(entity);
        // 执行http的post请求
        CloseableHttpResponse httpResponse;
        String result = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static JSONArray parseJSONArray(String json) {
        return JSONArray.parseArray(json);
    }
}
