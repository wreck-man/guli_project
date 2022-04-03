package com.youxin.eduservicecms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youxin.commonutils.Result;
import com.youxin.eduservicecms.entity.CrmBanner;
import com.youxin.eduservicecms.entity.CrmBannerVo;
import com.youxin.eduservicecms.service.CrmBannerService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-21
 */

@RestController
@RequestMapping("/eduservicecms/crm-banner")
public class CrmBannerController {
    @Autowired
    private CrmBannerService crmBannerService;
    @PostMapping("/pageList/{page}/{limit}")
    public Result pageList(@PathVariable Long page, @PathVariable Long limit, @RequestBody CrmBannerVo searchObj){
        CrmBanner crmBanner=new CrmBanner();
        System.out.println(searchObj.getGmtCreate());
        Date begin= searchObj.getGmtCreate();
        Date end=searchObj.getGmtModified();
        Page page1=new Page(page,limit);
        QueryWrapper wrapper=new QueryWrapper();
        System.out.println(searchObj);
        if (crmBanner.getTitle()!=""&&crmBanner.getTitle()!=null){
         wrapper.eq("title",searchObj.getTitle());
        }
        if (begin!=null){
            wrapper.ge("gmt_create",begin);
        }
        if (end!=null){
            wrapper.le("gmt_modified",end);
        }
        if (crmBanner.getLinkUrl()!=""&&crmBanner.getTitle()!=null){
            wrapper.eq("link_url",crmBanner.getLinkUrl());
        }
        crmBannerService.page(page1,wrapper);
        List<CrmBanner> list=page1.getRecords();
        Long total=page1.getTotal();
        return Result.ok().data("list",list).data("total",total);
    }
    @PostMapping("/add")
    public Result addCourse(@RequestBody CrmBanner crmBanner) {
        CrmBanner crmBanner1=new CrmBanner();
        BeanUtils.copyProperties(crmBanner,crmBanner1);
        System.out.println(crmBanner1);
        crmBannerService.save(crmBanner1);
        return Result.ok();
    }
    @GetMapping("/selectOne/{id}")
    public Result selectOne(@PathVariable String id) {
        CrmBanner  flag = crmBannerService.getById(id);
        return Result.ok().data("item", flag);
    }

    @PostMapping("/update")
    public Result updateCourse(@RequestBody CrmBanner crmBanner) {
        System.out.println(crmBanner);
        boolean flag = crmBannerService.update(crmBanner,null);
        return Result.ok().data("flag", flag);
    }
    @PostMapping("/deleteCourse/{id}")
    public Result deleteCourse(@PathVariable String id){
        boolean n=crmBannerService.removeById(id);
        if (!n){
            return Result.error().message("刪除失敗");
        }
        return Result.ok().message("刪除成功");
    }
}

