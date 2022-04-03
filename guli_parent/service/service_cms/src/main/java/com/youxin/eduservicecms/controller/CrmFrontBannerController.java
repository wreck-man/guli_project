package com.youxin.eduservicecms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youxin.commonutils.Result;
import com.youxin.eduservicecms.entity.CrmBanner;
import com.youxin.eduservicecms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservicecms/crm-front-banner")
public class CrmFrontBannerController {
    @Autowired
    private CrmBannerService crmBannerService;
    @Cacheable(value = "banner", key = "'selectIndexList'")
    @GetMapping("/selectAll")
    public Result selectAllBanner(){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.orderByDesc("sort");
        wrapper.last("limit 4");
        List<CrmBanner> list=crmBannerService.list(wrapper);
        return Result.ok().data("banner",list);
    }
}
