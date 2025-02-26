package com.zgxt.demo.respository;


import com.zgxt.demo.domain.entity.StrategyEntity;
import com.zgxt.demo.respository.dao.StrategyMapper;
import com.zgxt.demo.respository.po.StrategyPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StrategyServiceImpl {
    @Autowired
    StrategyMapper strategyMapper;
    public StrategyEntity selectOneById(int id) {
        StrategyPo strategyPo = strategyMapper.selectById(id);
        StrategyEntity strategyEntity = StrategyEntity.builder()
                .id(strategyPo.getId())
                .ruleModels(strategyPo.getRuleModels())
                .build();
        return strategyEntity;
    }
}





