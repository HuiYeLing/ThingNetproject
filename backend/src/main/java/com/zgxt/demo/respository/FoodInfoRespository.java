package com.zgxt.demo.respository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;
import java.util.Arrays;
import java.util.List;

import static com.zgxt.demo.common.Constants.*;
import static com.zgxt.demo.common.Utils.*;
import static com.zgxt.demo.config.Config.URL;

@Repository
public class FoodInfoRespository {
    /**
     * # 从链上获取所有食品信息
     * @return 所有食品信息列表
     */
    public JSONArray get_food_list(){
        JSONObject _jsonObj = getInputJsonObject("",FUNC_GETALLFOOD,null);
        String responseStr = httpPost(URL,_jsonObj.toJSONString());

        JSONArray responseJsonObj = JSON.parseArray(responseStr);
        return responseJsonObj;
    }
    /**
     * 从链上获取某个食品的基本信息
     * @param traceNumber: 食品溯源id，食品溯源过程中的标识符
     * @return 对应食品的信息
     */
    public JSONObject get_food(String traceNumber){
        JSONObject _jsonObj = getInputJsonObject("","getTraceinfoByTraceNumber",getParams(traceNumber));
        String responseStr = httpPost(URL,_jsonObj.toJSONString());
        JSONArray food  = JSON.parseArray(responseStr);

        JSONObject _jsonObj2 = getInputJsonObject("","getTraceDetailByTraceNumber",getParams(traceNumber));
        String responseStr2 = httpPost(URL,_jsonObj2.toJSONString());
        JSONArray food2  = JSON.parseArray(responseStr2);
        JSONArray timestamp  = JSON.parseArray((String) food2.get(0));
        JSONArray address  = JSON.parseArray((String) food2.get(2));
        JSONArray produce  = JSON.parseArray((String) food2.get(1));

        JSONObject _outPut = new JSONObject();
        _outPut.put("timestamp", timestamp.get(0));
        _outPut.put("produce",produce.get(0));
        _outPut.put("name",food.get(0));
        _outPut.put("current",food.get(1));
        _outPut.put("address",address.get(0));
        _outPut.put("quality",food.get(3));

        return _outPut;
    }
    /**
     * 获取食品信息列表
     * @param filterSize 过滤的 trace 大小
     * @return 符合条件的食品信息列表
     */
    public String getFoodInfoList(int filterSize) {
        List<Integer> numList2 = JSON.parseArray(StringUtils.toString(get_food_list().get(0)), Integer.class);
        JSONArray resList = new JSONArray();
        numList2.stream()
                .map(integer -> get_trace(integer.toString()))
                .filter(trace -> trace.size() == filterSize)
                .map(trace -> trace.get(filterSize - 1))
                .forEachOrdered(resList::add);

        return resList.toJSONString();
    }
    /**
     * 从链上获取某个食品的溯源信息
     * @param traceNumber 食品溯源id，食品溯源过程中的标识符
     * @return 对应食品的溯源信息
     */
    public JSONArray get_trace(String traceNumber){
        List<Object> food = Arrays.asList(get_food(traceNumber).values().toArray());

        JSONObject _jsonObj2 = getInputJsonObject("","getTraceinfoByTraceNumber",getParams(traceNumber));
        String responseStr2 = httpPost(URL,_jsonObj2.toJSONString());
        JSONArray traceDetailList = JSON.parseArray(responseStr2);

        JSONArray time_list = parseJSONArray(traceDetailList.get(0).toString());//完善代码缺失内容
        JSONArray name_list = parseJSONArray(traceDetailList.get(1).toString());//完善代码缺失内容
        JSONArray address_list = parseJSONArray(traceDetailList.get(2).toString()); //完善代码缺失内容
        JSONArray quality_list = parseJSONArray(traceDetailList.get(3).toString());//完善代码缺失内容

        JSONArray _outPut = new JSONArray();
        for (int i = 0; i < time_list.size(); i++) {
            JSONObject _outPutObj = new JSONObject();
            _outPutObj.put("traceNumber", traceNumber);
            _outPutObj.put("name", traceNumber);
            _outPutObj.put("produce_time", food.get(2));
            _outPutObj.put("timestamp", time_list.get(0));
            _outPutObj.put("quality", time_list.get(i));
            if (i == 0) {
                _outPutObj.put("from", name_list.get(i));
                _outPutObj.put("from_address", address_list.get(i));
                _outPut.add(_outPutObj);
            } else {
                _outPutObj.put("from",name_list.get(i - 1));
                _outPutObj.put("to", name_list.get(i));
                _outPutObj.put("from_address", address_list.get(i - 1));
                _outPutObj.put("to_address", address_list.get(i));
                _outPut.add(_outPutObj);
            }
        }
        return _outPut;
    }

}
