package com.nice.controller;


import com.nice.utils.BackResponseUtil;
import com.nice.utils.BaseResponse;
import com.nice.utils.DateUtil;
import com.nice.utils.ReturnCodeEnum;
import io.undertow.util.DateUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 服务基础服务Controller抽象类
 * <p>
 * Created by 自定义异常拦截器 on 2017/5/12.
 */
@ControllerAdvice
@Slf4j
public abstract class BaseController {

    /**
     * 异常拦截处理
     *
     * @param exp
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleException(Exception exp, HttpServletRequest request, HttpServletResponse response) {
        BaseResponse respInfo = new BaseResponse();
        Long timeStamp = System.currentTimeMillis();
        if (exp instanceof Exception) {
            respInfo = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1004.getCode());
            respInfo.setMessage(exp.getMessage()+ "  exception time  " + DateUtil.formatDate(new Date(),DateUtil.YEAR_MONTH_DAY_HH_MM_SS));
            log.error("timeStamp:" + timeStamp, exp.getMessage(), exp);
        } else {
            respInfo.setReturnCode(ReturnCodeEnum.CODE_1004.getCode());
            respInfo.setMessage("exception, timStamp:" + timeStamp);
            log.error("timeStamp:" + timeStamp + exp.getMessage(), exp);
        }
        return respInfo;
    }
}
