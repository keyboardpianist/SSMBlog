package com.deltaplus.utils;

import org.springframework.stereotype.Component;

@Component("Code")
public class Code {
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
