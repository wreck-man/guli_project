package com.youxin.eduoss.utils;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtil implements InitializingBean {
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    @Value("${aliyun.oss.file.keyid}")
    private String accessKeyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String accessKeySecret;
    // 填写Bucket名称，例如examplebucket。
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;
    @Value("${aliyun.oss.file.filehost}")
    private String fileHost;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static String FILE_HOST;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = this.endpoint;
        ACCESS_KEY_ID = this.accessKeyId;
        ACCESS_KEY_SECRET = this.accessKeySecret;
        BUCKET_NAME = this.bucketName;
        FILE_HOST = this.fileHost;
    }
}
