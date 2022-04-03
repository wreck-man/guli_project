package com.youxin.serviceucenter.controller;


import com.youxin.commonutils.Result;
import com.youxin.commonutils.utils.JwtUtils;
import com.youxin.servicebase.exception.YouXinException;
import com.youxin.serviceucenter.entity.UcenterMember;
import com.youxin.serviceucenter.entity.vo.LoginVo;
import com.youxin.serviceucenter.entity.vo.RegisterVo;
import com.youxin.serviceucenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */

@RestController
@RequestMapping("/serviceucenter/ucenter-member")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){

        String token=ucenterMemberService.login(loginVo);
        if (StringUtils.isEmpty(token)){
            throw new YouXinException(20001,"登錄失敗");
        }
        return Result.ok().message("登錄成功").data("token",token);
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo){
        boolean flag=ucenterMemberService.register(registerVo);
        if (!flag){
            throw new YouXinException(20001,"登錄失敗");
        }
        return Result.ok().message("注冊成功");

    }
    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("/auth/getLoginInfo")
    public Result getLoginInfo(HttpServletRequest request) {
        String ucenterId= JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember ucenterMember=ucenterMemberService.getLoginInfo(ucenterId);
        return Result.ok().data("item",ucenterMember);
    }
    @GetMapping("/auth/getWebInfo/{memberId}")
    public UcenterMember getWebInfo(@PathVariable String memberId) {
        UcenterMember ucenterMember=ucenterMemberService.getById(memberId);
        System.out.println(ucenterMember);
        return ucenterMember;
    }
    @GetMapping("/selectRegister/{data}")
    public String selectRegister(@PathVariable String data){
        Integer num=ucenterMemberService.selectRegiter(data);
        return String.valueOf(num);
    }
}

