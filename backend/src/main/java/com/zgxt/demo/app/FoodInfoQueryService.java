package com.zgxt.demo.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zgxt.demo.respository.FoodInfoRespository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FoodInfoQueryService {


    @Resource
    FoodInfoRespository foodInfoRespository;
    public JSONArray get_food_list() {
        return foodInfoRespository.get_food_list();
    }

    public JSON get_food(String traceNumber) {
        return foodInfoRespository.get_food(traceNumber);
    }

    public List get_trace(String traceNumber) {
        return foodInfoRespository.get_trace(traceNumber);
    }
    public String getFoodInfoList(int filterSize) {
        return foodInfoRespository.getFoodInfoList(filterSize);
    }
}
