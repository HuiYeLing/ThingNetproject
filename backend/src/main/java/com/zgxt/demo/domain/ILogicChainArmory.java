package com.zgxt.demo.domain;


public interface ILogicChainArmory {

    ILogicChain getNext();

    ILogicChain appendNext(ILogicChain next);

}
