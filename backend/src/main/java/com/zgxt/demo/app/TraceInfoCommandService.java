package com.zgxt.demo.app;

import com.alibaba.fastjson.JSON;
import com.zgxt.demo.app.dto.RequestCmd;
import com.zgxt.demo.common.Result;
import com.zgxt.demo.domain.LinkInfo;
import com.zgxt.demo.domain.entity.StrategyEntity;
import com.zgxt.demo.domain.factory.DefaultChainFactory;
import com.zgxt.demo.domain.service.AddProcedureService;
import com.zgxt.demo.respository.StrategyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.zgxt.demo.common.Constants.*;
import static com.zgxt.demo.common.Utils.*;
import static com.zgxt.demo.config.Config.*;


@Service
public class TraceInfoCommandService {
    @Resource
    DefaultChainFactory defaultChainFactory;
    @Resource
    StrategyServiceImpl strategyService;
    @Autowired
    AddProcedureService addProcedureService;

    public Result addTraceInfo(RequestCmd cmd, String userRole, String userAddress, String funcName) throws ParseException {
        // todo : 判断funcname是否存在
        if (cmd == null) return Result.parameterError();
        String traceNumber = StringUtils.toString(cmd.getTraceNumber());
        StrategyEntity strategyEntity = strategyService.selectOneById(1);
        List<String> strings = strategyEntity.ruleModels();
        LinkInfo linkInfo = defaultChainFactory.openLogicChain(strings).doAuth(traceNumber,new Date());
        Result checkResult = linkInfo.checkLinkInfo(userRole);
        if (checkResult != null) return checkResult;
        String jsonString;
        if (funcName == FUNC_NEWFOOD) {
             jsonString = getInputJsonObject(userAddress,funcName,getParams(cmd.getName(),traceNumber,cmd.getTraceName(),StringUtils.toString(cmd.getQuality())))
                    .toJSONString();
        } else {
             jsonString = getInputJsonObject(userAddress,funcName,getParams(traceNumber,cmd.getTraceName(),StringUtils.toString(cmd.getQuality())))
                    .toJSONString();
        }

        String responseStr = httpPost(URL,jsonString);
        return getResult(JSON.parseObject(responseStr)
                .getString(FISCO_MES_KEY), userRole, traceNumber);
    }

    private Result getResult(String msg, String role,String traceNumber) {
        if (FISCO_RESULT_SUCCESS_MESSAGE.equalsIgnoreCase(msg)){
            addProcedureService.addProcedure(role, traceNumber);
            return Result.buildSuccessResult();
        }
        return Result.buildErrorResult(msg);
    }
}
