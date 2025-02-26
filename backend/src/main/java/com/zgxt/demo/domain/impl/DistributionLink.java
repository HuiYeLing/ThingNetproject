package com.zgxt.demo.domain.impl;

import com.zgxt.demo.domain.AbstractLogicChain;
import com.zgxt.demo.domain.ILogicChain;
import com.zgxt.demo.domain.LinkInfo;
import com.zgxt.demo.domain.service.AddProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;

import static com.zgxt.demo.common.Constants.DISTRIBUTION_ROLE;

@Component("DistributionLink")
public class DistributionLink extends AbstractLogicChain {
    @Autowired
    AddProcedureService addProcedureService;
    public LinkInfo doAuth(String traceNumber, Date currentDate) throws ParseException {
        String roleModel = DISTRIBUTION_ROLE;
        Date date = addProcedureService.queryAuthInfo(roleModel, traceNumber);
        if (null == date) return new LinkInfo(true, roleModel);
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
        return "DistributionRole";
    }
}
