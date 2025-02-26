package com.zgxt.demo.domain.service;


import com.zgxt.demo.respository.TraceFoodServiceImpl;
import com.zgxt.demo.respository.po.TraceFoodPo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class AddProcedureService {
    @Resource
    TraceFoodServiceImpl traceFoodService;


    public Date queryAuthInfo(String roleModel, String traceNumber) {
        TraceFoodPo select = traceFoodService.select(roleModel, traceNumber);
        if (select == null) return null;
        return select.getDate();
    }

    public  void addProcedure(String roleModel, String traceNumber) {
        TraceFoodPo build = TraceFoodPo.builder().rolemodel(roleModel).tracenumber(traceNumber).date(new Date()).build();
        traceFoodService.insert(build);
    }

    public Date QueryPreviousStepDate(String traceNumber)  {
        TraceFoodPo select = traceFoodService.selectLast(traceNumber);
        if (select == null) return null;
        return select.getDate();
    }

}
