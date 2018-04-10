package com.nice;

import com.nice.annotation.RequestJsonHandlerMethodArgumentResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
@MapperScan("com.nice.mapper*")
public class Application extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //参数解析器
        argumentResolvers.add(new RequestJsonHandlerMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(
        Application.class);
        application.run(args);
    }

}
