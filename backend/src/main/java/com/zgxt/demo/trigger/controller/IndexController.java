package com.zgxt.demo.trigger.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgxt.demo.app.FoodInfoQueryService;
import com.zgxt.demo.app.TraceInfoCommandService;
import com.zgxt.demo.app.dto.RequestCmd;
import com.zgxt.demo.common.Result;
import com.zgxt.demo.trigger.aop.AuthPermisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

import static com.zgxt.demo.common.Constants.*;
import static com.zgxt.demo.config.Config.*;


@Controller
public class IndexController {


    @Resource
    FoodInfoQueryService foodInfoQueryService;
    @Autowired
    TraceInfoCommandService traceInfoCommandService;


    /**
     * 获取用户地址
     * userinfo: 用户角色（producer=食品生产商 distributor=中间商 retailer=超市）
     * @return: 角色对应用户地址
     */
    @ResponseBody
    @GetMapping(path="/userinfo", produces=MediaType.APPLICATION_JSON_VALUE)
    public Object userInfo(String userName){
        //声明返回对象
        JSONObject _outPut = new JSONObject();
        //返回各个用户的地址
        if (userName.equals("producer")){
            _outPut.put("address", PRODUCER_ADDRESS);
        }else if (userName.equals("distributor")){
            _outPut.put("address", DISTRIBUTOR_ADDRESS);
        }else if (userName.equals("retailer")){
            _outPut.put("address", RETAILER_ADDRESS);
        }else {
            _outPut.put("error","user not found");
        }
        return _outPut.toJSONString();
    }

    /**
     * 添加食品生产信息
     * traceNumber: 食品溯源id，食品溯源过程中的标识符
     * foodName: 食物名称
     * traceName: 用户名，食品流转过程各个阶段的用户名
     * quality: 当前食品质量（0=优质 1=合格 2=不合格）
     * @return：添加食品生产信息结果
     */
    @ResponseBody
    @PostMapping(path="/produce", produces=MediaType.APPLICATION_JSON_VALUE)
    @AuthPermisson(PRODUCE_ROLE)
    public Result produce(@RequestBody RequestCmd cmd) throws ParseException {
        return traceInfoCommandService.addTraceInfo(cmd, PRODUCE_ROLE,PRODUCER_ADDRESS,FUNC_NEWFOOD);
    }


    /**
     * 中间商添加食品流转信息
     * traceNumber: 食品溯源id，食品溯源过程中的标识符
     * traceName: 用户名，食品流转过程各个阶段的用户名
     * quality: 当前食品质量（0=优质 1=合格 2=不合格）
     * @return：中间商添加食品流转信息结果
     */
    @ResponseBody
    @PostMapping(path = "/adddistribution", produces = MediaType.APPLICATION_JSON_VALUE)
    @AuthPermisson(DISTRIBUTION_ROLE)
    public Result add_trace_by_distrubutor(@RequestBody RequestCmd cmd) throws ParseException {
        return traceInfoCommandService.addTraceInfo(cmd,DISTRIBUTION_ROLE, DISTRIBUTOR_ADDRESS, FUNC_ADDTRACEINFOBYDISTRIBUTOR);

    }

    /**
     * 超市添加食品流转信息
     * traceNumber: 食品溯源id，食品溯源过程中的标识符
     * traceName: 用户名，食品流转过程各个阶段的用户名
     * quality: 当前食品质量（0=优质 1=合格 2=不合格）
     * @param cmd
     * @return 超市添加食品流转信息结果
     */
    @ResponseBody
    @PostMapping(path="/addretail", produces=MediaType.APPLICATION_JSON_VALUE)
    @AuthPermisson(RETAIL_ROLE)
    public Result add_trace_by_retailer(@RequestBody RequestCmd cmd) throws ParseException {
        return traceInfoCommandService.addTraceInfo(cmd, RETAIL_ROLE, RETAILER_ADDRESS, FUNC_ADDTRACEINFOBYRETAILER);
    }


    /**
     *
     # 获取所有食物信息
     * @return 所有食品信息列表
     */
    @ResponseBody
    @GetMapping(path="/foodlist", produces=MediaType.APPLICATION_JSON_VALUE)
    public String getlist(){
        JSONArray num_list = foodInfoQueryService.get_food_list();
        JSONArray num_list2;
        num_list2 = JSON.parseArray(num_list.getString(0));
        JSONArray resList = new JSONArray();

        for (int i=0;i<num_list2.size();i++){
            String food = foodInfoQueryService.get_food(num_list2.get(i).toString()).toJSONString();
            resList.add(food);
        }
        return resList.toJSONString();
    }


    /**
     * 获取某个食品的溯源信息
     * @param traceNumber 食品溯源id，食品溯源过程中的标识符
     * @return 对应食品的溯源信息
     */
    @ResponseBody
    @GetMapping(path="/trace", produces=MediaType.APPLICATION_JSON_VALUE)
    public String trace(String traceNumber){
        JSONObject _outPut = new JSONObject();
        if (Integer.parseInt(traceNumber) <= 0){
            _outPut.put("error","invalid parameter");
            return _outPut.toJSONString();
        }
        List res = foodInfoQueryService.get_trace(traceNumber);
        JSONArray o = new JSONArray(res);
        return o.toJSONString();
    }

    /**
     * 获取某个食品的当前信息
     * @param traceNumber 食品溯源id，食品溯源过程中的标识符
     * @return 对应食品的当前信息
     */
    @ResponseBody
    @GetMapping(path="/food", produces=MediaType.APPLICATION_JSON_VALUE)
    public String food(String traceNumber){
        JSONObject _outPut = new JSONObject();
        if (Integer.parseInt(traceNumber) <= 0){
            _outPut.put("error","invalid parameter");
            return _outPut.toJSONString();
        }
        String res = foodInfoQueryService.get_food(traceNumber).toJSONString();
        return res;
    }




    /**
     * 获取位于生产商的的食物信息
     * @return 所有位于生产商的食品信息列表
     */
    @ResponseBody
    @GetMapping(path="/producing", produces=MediaType.APPLICATION_JSON_VALUE)
    public String get_producing(){
        return foodInfoQueryService.getFoodInfoList(1);
    }

    /**
     * 获取位于中间商的食物信息
     * @return 所有位于中间商的食品信息列表
     */
    @ResponseBody
    @GetMapping(path="/distributing", produces=MediaType.APPLICATION_JSON_VALUE)
    public String get_distributing(){
        return foodInfoQueryService.getFoodInfoList(2);
    }


    /**
     * 获取位于超市的食物信息
     * @return 所有位于超市的食品信息列表
     */
    @ResponseBody
    @GetMapping(path="/retailing", produces=MediaType.APPLICATION_JSON_VALUE)
    public String get_retailing(){
        return foodInfoQueryService.getFoodInfoList(3);
    }













}
