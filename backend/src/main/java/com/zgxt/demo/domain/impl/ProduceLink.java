package com.zgxt.demo.domain.impl;


import com.zgxt.demo.domain.AbstractLogicChain;
import com.zgxt.demo.domain.ILogicChain;
import com.zgxt.demo.domain.LinkInfo;
import com.zgxt.demo.domain.service.AddProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

import static com.zgxt.demo.common.Constants.PRODUCE_ROLE;

@Component("ProduceLink")
public class ProduceLink extends AbstractLogicChain {
    @Autowired
    AddProcedureService addProcedureService;
    @Override
    public LinkInfo doAuth(String traceNumber, Date currentDate) throws ParseException {
        String roleModel = PRODUCE_ROLE;
        Date date = addProcedureService.queryAuthInfo(roleModel, traceNumber);
        if (null == date) return new LinkInfo(true, roleModel());
        ILogicChain next = super.getNext();
        Date date1 = queryAuthDate(traceNumber);
        if (date1 == null) {
            return next.doAuth(traceNumber, date1);
        }
        if (null == next || date1.compareTo(currentDate) > 0) return new LinkInfo(false, roleModel());
        return next.doAuth(traceNumber, date1);
    }

    @Override
    protected String roleModel() {
        return "ProduceRole";
    }
}
