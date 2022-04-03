package com.youxin.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YouXinException extends RuntimeException{
    private Integer code;
    private String msg;

    @Override
    public String toString() {
        return "YouXinException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
