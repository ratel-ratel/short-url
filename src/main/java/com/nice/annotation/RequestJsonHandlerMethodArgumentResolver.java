package com.nice.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.alibaba.fastjson.JSONObject;
/**
 *
 * Created by nice on 2018/3/29.
 */
public class RequestJsonHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver /* 自定义参数解析器*/ {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //表示作用于范围,仅作用于添加了 RequestJson 注解的参数
        return parameter.hasParameterAnnotation(RequestJson.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestJson requestJson = parameter.getParameterAnnotation(RequestJson.class);
        String value = requestJson.value();
        Class clazz = parameter.getParameterType();
        String jsonData = webRequest.getParameter(value);
        if (jsonData == null) {
            return clazz.newInstance();
        }
        Object object = JSONObject.parseObject(jsonData, clazz);
        return object;
    }
}
