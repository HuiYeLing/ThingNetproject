package com.zgxt.demo.domain.factory;


import com.zgxt.demo.domain.ILogicChain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DefaultChainFactory {

    private final Map<String, ILogicChain> logicChainGroup;

    public ILogicChain openLogicChain(List<String> model) {

        if (null == model || 0 == model.size()) return logicChainGroup.get("default");

        List<ILogicChain> logicChains = model.stream()
                .map(logicChainGroup::get)
                .collect(ArrayList::new, (list, nextChain) ->
                                list.add(list.isEmpty() ? nextChain : list.get(list.size() - 1).appendNext(nextChain)),
                        ArrayList::addAll);
        ILogicChain logicChain = logicChains.isEmpty() ? null : logicChains.get(0);

        return logicChain;
    }

}
