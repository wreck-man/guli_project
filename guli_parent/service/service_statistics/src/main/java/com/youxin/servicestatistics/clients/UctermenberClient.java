package com.youxin.servicestatistics.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-ucenter")
public interface UctermenberClient {
    @GetMapping("/serviceucenter/ucenter-member/selectRegister/{data}")
    public String selectRegister(@PathVariable(name = "data") String data);
}
