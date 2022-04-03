package com.youxin.servicemsm.controller;

import com.youxin.commonutils.Result;
import com.youxin.servicemsm.entity.ToEmail;
import com.youxin.servicemsm.utils.MyEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/registerservie/codmsm")
public class MsmController {
    @Autowired
    private MyEmail myEmail;
    @GetMapping("/getCodeMsm/{email}")
    public Result CodeMsm(@PathVariable String email, HttpSession session){
        myEmail.sendMail(email,session);
        return Result.ok().message("信息發送成功").data("email",email);

    }
}
