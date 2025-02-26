package com.zgxt.demo.config;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
@Repository

public class Config {
    //填写WeBASE-Front地址，用于后续交互
    public static String URL;

    public static String CONTRACT_NAME = "FoodInfo";
    public static String CONTRACT_ADDRESS;

    public static final String CONTRACT_ABI ="[{\"constant\":true,\"inputs\":[{\"name\":\"_traceNumber\",\"type\":\"string\"}],\"name\":\"getTraceDetailByTraceNumber\",\"outputs\":[{\"name\":\"\",\"type\":\"string[]\"},{\"name\":\"\",\"type\":\"string[]\"},{\"name\":\"\",\"type\":\"string[]\"},{\"name\":\"\",\"type\":\"string[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_traceNumber\",\"type\":\"uint256\"},{\"name\":\"_traceName\",\"type\":\"string\"},{\"name\":\"_quality\",\"type\":\"uint256\"}],\"name\":\"addTraceInfoByDistributor\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_user_addr\",\"type\":\"address\"}],\"name\":\"onlyDRRole\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_traceNumber\",\"type\":\"uint256\"},{\"name\":\"_traceName\",\"type\":\"string\"},{\"name\":\"_quality\",\"type\":\"uint256\"}],\"name\":\"addTraceInfoByRetailer\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_user_addr\",\"type\":\"address\"}],\"name\":\"onlyPRRole\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getAllFood\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_user_addr\",\"type\":\"address\"}],\"name\":\"onlyRRRole\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_traceNumber\",\"type\":\"string\"}],\"name\":\"getTraceinfoByTraceNumber\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_name\",\"type\":\"string\"},{\"name\":\"_traceNumber\",\"type\":\"uint256\"},{\"name\":\"_traceName\",\"type\":\"string\"},{\"name\":\"_quality\",\"type\":\"string\"}],\"name\":\"newFood\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_origin\",\"type\":\"uint256\"}],\"name\":\"uint256ToString\",\"outputs\":[{\"name\":\"result\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"PutResult\",\"type\":\"event\"}]";
                //填写用户地址信息
    public static String PRODUCER_ADDRESS;
    public static String DISTRIBUTOR_ADDRESS;
    public static String RETAILER_ADDRESS;

    @PostConstruct
    public void loadConfig() {
        try {
            //读取配置文件
            Properties properties = new Properties();
            File file = new File("conf.properties");
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
            fis.close();
            //获取配置文件数据
            URL = properties.getProperty("URL");
            System.out.println(URL);
            CONTRACT_ADDRESS = properties.getProperty("CONTRACT_ADDRESS");
            System.out.println(CONTRACT_ADDRESS);
            PRODUCER_ADDRESS = properties.getProperty("PRODUCER_ADDRESS");
            System.out.println(PRODUCER_ADDRESS);
            DISTRIBUTOR_ADDRESS = properties.getProperty("DISTRIBUTOR_ADDRESS");
            System.out.println(DISTRIBUTOR_ADDRESS);
            RETAILER_ADDRESS = properties.getProperty("RETAILER_ADDRESS");
            System.out.println(RETAILER_ADDRESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
