package com.youxin.serviceorder.client;

import com.youxin.commonutils.CourseWebVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-edu")
public interface CourseClient {
    @GetMapping("/eduservice/frontCourse/webCourseInfo/{id}")
    public CourseWebVo webCourseVo(@PathVariable(name = "id") String id);
}
