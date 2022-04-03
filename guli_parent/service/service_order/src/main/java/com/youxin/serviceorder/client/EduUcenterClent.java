package com.youxin.serviceorder.client;

import com.youxin.commonutils.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name="service-ucenter")
public interface EduUcenterClent {
    @GetMapping("/serviceucenter/ucenter-member/auth/getWebInfo/{memberId}")
    public UcenterMember getLoginInfo( @PathVariable(name = "memberId") String memberId);
}
