package com.nice.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nice on 2017/10/27.
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/report")
public class ReportController {


    /**
     * adpter
     */
    @RequestMapping(value = "/post")
    @ResponseBody
    public String post(String request) {
        return "report  : "+request;
    }

    /**
     * aop 测试
     */
    @RequestMapping(value = "/aop")
    @ResponseBody
    public String aop(String request) {
        return "report  : "+request;
    }
}
