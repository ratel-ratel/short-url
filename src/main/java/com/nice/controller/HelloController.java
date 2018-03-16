package com.nice.controller;


import com.nice.service.impl.ShortUrlService;
import com.nice.utils.BackResponseUtil;
import com.nice.utils.BaseResponse;
import com.nice.utils.ReturnCodeEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 短链生成控制器
 * </p>
 *
 * @author wangliang
 * @since 2017/8/31
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/sendRedirect")
public class HelloController extends BaseController {


    @RequestMapping(value = "/hello")
    @ResponseBody
    public BaseResponse hello() {
        BaseResponse baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
        baseResponse.setDataInfo("hello");
        return baseResponse;
    }
}
