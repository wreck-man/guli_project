package com.youxin.servicemsm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class MyEmail {
    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${spring.mail.username}")
    private String from;
    
    @Resource
    JavaMailSender javaMailSender;

    /**
     * 发送验证码
     *
     * @param email
     * @param session
     */
    public   void sendMail(String email, HttpSession session) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//            设置发件人
            mimeMessageHelper.setFrom(from);
//            设置收件人
            mimeMessageHelper.setTo(email);
//            设置邮件主题
            mimeMessageHelper.setSubject("笑s验证部門：");
            //生成随机数
            String random = randomInteger();
            //将随机数放置到session中
            redisTemplate.opsForValue().set(email,random,3, TimeUnit.MINUTES);
//            session.setAttribute("email",email);
//            session.setAttribute("code",random);

//            设置验证码的样式
            mimeMessageHelper.setText("尊敬的胖子"+email+"客戶您的驗證碼為："+"<font style='color:green'>"+random+"</font>"+"歡迎您加入笑s家族",true);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

    /**
     * 生成随机验证码
     *
     * @return
     */
    private String randomInteger() {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        //生成6位的随机数
        for (int i = 0;i<6;i++){
            int i1 = random.nextInt(10);
            stringBuffer.append(i1);
        }
        return stringBuffer.toString();
    }

}

