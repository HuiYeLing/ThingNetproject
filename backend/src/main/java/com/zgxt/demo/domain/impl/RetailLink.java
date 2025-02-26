package com.zgxt.demo.domain.impl;

import com.zgxt.demo.domain.AbstractLogicChain;
import com.zgxt.demo.domain.ILogicChain;
import com.zgxt.demo.domain.LinkInfo;
import com.zgxt.demo.domain.service.AddProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

import static com.zgxt.demo.common.Constants.RETAIL_ROLE;

@Component("RetailLink")
public class RetailLink extends AbstractLogicChain {
    @Autowired
    AddProcedureService addProcedureService;
    public LinkInfo doAuth( String traceNumber, Date authDate) throws ParseException {
        String roleModel = RETAIL_ROLE;
        Date date = addProcedureService.queryAuthInfo(roleModel, traceNumber);
        if (null == date) return new LinkInfo(true, roleModel());
        ILogicChain next = super.getNext();
        if (null == next) return new LinkInfo(false, roleModel());
        return next.doAuth(traceNumber, authDate);
    }

    @Override
    protected String roleModel() {
        return "RetailRole";
    }
}
