package com.zgxt.demo.domain;

import com.zgxt.demo.common.Result;

public class LinkInfo {

    private boolean code;
    private String info = "";

    public LinkInfo(boolean code, String ...infos) {
        this.code = code;
        for (String str:infos){
            this.info = this.info.concat(str);
        }
    }
    public Result checkLinkInfo( String role) {
        if (!code) return Result.buildTraceSuccessResult();
        if (!role.equals(info)) return Result.permissionError();
        return null;
    }
    public boolean getCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
