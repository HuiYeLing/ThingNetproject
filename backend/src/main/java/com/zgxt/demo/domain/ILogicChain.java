package com.zgxt.demo.domain;


import java.text.ParseException;
import java.util.Date;

public interface ILogicChain extends ILogicChainArmory{


    LinkInfo doAuth(String tranceNumber, Date authDate) throws ParseException;

}
