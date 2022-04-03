package com.youxin.vods.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VodParamsUtil implements InitializingBean {
    @Value("${com.youxin.key}")
    private String accessKey;
    @Value("${com.youxin.scr}")
    private String accessKeySecret;
    @Value("${com.youxin.enpoint}")
    private String enpoint;
    public static  String ACCESS_KEYS;
    public static  String ACCESS_KEY_SECRET;
    public static  String ENPOINT;

    @Override
    public void afterPropertiesSet() throws Exception {
        VodParamsUtil.ACCESS_KEY_SECRET=this.accessKeySecret;
        VodParamsUtil.ACCESS_KEYS=this.accessKey;
        VodParamsUtil.ENPOINT=this.enpoint;
    }
}
