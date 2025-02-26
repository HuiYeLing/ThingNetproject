package com.zgxt.demo.common;



public class Constants {

    public final static String PRODUCE_ROLE = "ProduceRole";
    public final static String DISTRIBUTION_ROLE = "DistributionRole";
    public final static String RETAIL_ROLE = "RetailRole";

    public static final String FUNC_NEWFOOD = "newFood";
    public static final String FUNC_ADDTRACEINFOBYDISTRIBUTOR = "addTraceInfoByDistributor";
    public static final String FUNC_ADDTRACEINFOBYRETAILER = "addTraceInfoByRetailer";
    public static final String FUNC_GETALLFOOD = "getAllFood";
    public static final String FUNC_GETFOOD = "getFood";
    public static final String FUNC_GETTRACEINFO = "getTraceInfo";

    public static final String FISCO_RESULT_SUCCESS_MESSAGE = "Success";
    public static final String FISCO_MES_KEY = "message";

    public enum ResponseCode {
        SUCCESS(1, "成功"),
        TRACE_SUCCESS(1, "信息添加已完成，无须修改信息"),
        ERROR(0, "失败"),
        PERMISSION_ERROR(0, "权限不正确，或需要上一阶段添加后才可以添加此阶段信息"),
        UN_ERROR(0, "未知失败"),
        ;


        private int code;
        private String info;

        ResponseCode(int code, String info) {
            this.code = code;
            this.info = info;
        }

        public int getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

    }
}
