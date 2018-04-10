package com.nice.controller;

import com.alibaba.fastjson.JSONObject;
import com.nice.annotation.RequestJson;
import com.nice.domain.Person;
import org.springframework.web.bind.annotation.*;

/**
 * 自定义注解测试
 * Created by nice on 2018/3/30.
 */
@RestController
public class MyController {
    @RequestMapping(value = "/new/test")
    public Object newTest(@RequestJson("a") Person person, @RequestParam("b") Integer b) {
        System.out.println("a : " + JSONObject.toJSONString(person));
        System.out.println("b : " + b);
        return "ok";
    }


}
