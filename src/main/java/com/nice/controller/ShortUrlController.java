package com.nice.controller;


import com.nice.domain.ShortLink;
import com.nice.domain.ShortUrl;
import com.nice.service.IShortUrlService;
import com.nice.utils.BackResponseUtil;
import com.nice.utils.BaseResponse;
import com.nice.utils.ReturnCodeEnum;
import com.nice.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
@RequestMapping(value = "/shortUrl")
public class ShortUrlController {

    private IShortUrlService shortUrlService;

    /**
     * 生成短链
     *
     * @param vo
     * @param request
     * @return
     */
    @RequestMapping(value = "/generateShortUrl", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse generateShortUrl(@RequestBody ShortLink vo, HttpServletRequest request) {
        return shortUrlService.generateShortLink(vo, request);
    }

    @RequestMapping("/404")
    @ResponseBody
    public BaseResponse error(HttpServletRequest request, HttpServletResponse response) {
        BaseResponse baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1005.getCode());
        baseResponse.setMessage("此短链无效");
        log.info("地址不存在404");
        return baseResponse;
    }
}
