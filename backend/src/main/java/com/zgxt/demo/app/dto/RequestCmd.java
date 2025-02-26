package com.zgxt.demo.app.dto;

import lombok.Data;

@Data
public class RequestCmd {
    int TraceNumber;
    String Name;
    String TraceName;
    int quality;
    int user;
}
