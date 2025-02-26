package com.zgxt.demo.domain;


import com.zgxt.demo.domain.service.AddProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
@Service
public abstract class AbstractLogicChain implements ILogicChain {
    @Autowired
    AddProcedureService addProcedureService;

    protected ILogicChain next;
    protected Date queryAuthDate(String tranceNumber) throws ParseException {
        Date authDate = addProcedureService.QueryPreviousStepDate(tranceNumber);
        return authDate;
    }

    @Override
    public ILogicChain getNext() {
        return next;
    }

    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }
    protected abstract String roleModel();


}
