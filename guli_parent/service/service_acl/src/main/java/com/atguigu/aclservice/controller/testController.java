package com.atguigu.aclservice.controller;

import com.atguigu.aclservice.entity.Permission;
import com.atguigu.aclservice.service.PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youxin.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/acl/test")
public class testController {
    @Autowired
    private PermissionService permissionService;
    @GetMapping("/deleteList/{id}")
    public Result deleteList(@PathVariable String id){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("id",id);
        Permission father=permissionService.getById(id);
        List<Permission> list= permissionService.list(null);
        deleteChildren(father,list);
        permissionService.removeById(id);

        return Result.ok().message("刪除成功");
    }
    public void  deleteChildren(Permission cur,List<Permission> list){
        for (Permission c:list){
            if (c.getPid().equals(cur.getId())){
                deleteChildren(c,list);
                permissionService.removeById(c.getId());
            }
        }
    }
    @GetMapping("/selectList")
    public Result selectList(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.orderByAsc("id");
        List<Permission> list=permissionService.list(queryWrapper);
        Permission father=list.get(0);
        System.out.println(father);
        father.setLevel(1);
        father.setChildren(quereChildren(father,list));
        return Result.ok().data("father",father);
    }

    private List<Permission> quereChildren(Permission father, List<Permission> list) {
        List result=new ArrayList();
        for (Permission cur:list){
            if (cur.getPid().equals(father.getId())){
                cur.setLevel(father.getLevel()+1);
                cur.setChildren(quereChildren(cur,list));
                result.add(cur);
            }
        }
        return result;
    }

}
