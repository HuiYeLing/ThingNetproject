package com.zgxt.demo.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private static final long serialVersionUID = -3826891916021780628L;
    private int ret;
    private String info;


    public static Result buildSuccessResult() {
        return new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }
    public static Result buildTraceSuccessResult() {
        return new Result(Constants.ResponseCode.TRACE_SUCCESS.getCode(), Constants.ResponseCode.TRACE_SUCCESS.getInfo());
    }

    public static Result buildErrorResult() {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
    }

    public static Result buildErrorResult(String info) {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), info);
    }

    public Result(int code, String info) {
        this.ret = code;
        this.info = info;
    }
    public static Result parameterError() {
        return new Result(Constants.ResponseCode.ERROR.getCode(), "invalid parameter");
    }
    public static Result permissionError() {
        return new Result(Constants.ResponseCode.PERMISSION_ERROR.getCode(), Constants.ResponseCode.PERMISSION_ERROR.getInfo());
    }

}
