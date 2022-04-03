package com.youxin.eduservice.client;

import com.youxin.commonutils.Result;
import com.youxin.commonutils.UcenterMember;
import com.youxin.commonutils.utils.JwtUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Component
@FeignClient(name="service-ucenter")
public interface EduUcenterClent {
    @GetMapping("/serviceucenter/ucenter-member/auth/getWebInfo/{memberId}")
    public UcenterMember getLoginInfo( @PathVariable(name = "memberId") String memberId);
}
