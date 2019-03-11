package com.nice.controller;


import com.nice.domain.ShortLink;
import com.nice.service.IShortLinkService;
import com.nice.service.IShortUrlService;
import com.nice.utils.BackResponseUtil;
import com.nice.utils.BaseResponse;
import com.nice.utils.ReturnCodeEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 短链重定向
 * </p>
 *
 * @author wangliang
 * @since 2017/8/31
 */
@Controller
@Slf4j
@AllArgsConstructor
public class RedirectController extends BaseController {

    private IShortLinkService shortLinkService;

    /**
     * 短链重定向
     * 替换短链中过期accessToken保证分享出去的链接正常访问
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer requestURL = request.getRequestURL();
        log.info("短链地址 : {}", requestURL);
        //获取域名
        String host = requestURL.delete(requestURL.length() - request.getRequestURI().length(), requestURL.length()).append("/").toString();
        String url = host + "/shortUrl/404";

        ShortLink shortLink = new ShortLink();
        shortLink.setShortLink(request.getRequestURL().toString());
        BaseResponse<ShortLink> baseResponse = shortLinkService.queryLink(shortLink);
        if (null != baseResponse && ReturnCodeEnum.CODE_1000.getCode().equals(baseResponse.getReturnCode())) {
            ShortLink  link    = baseResponse.getDataInfo();
            url = URLDecoder.decode(link.getLink(), "UTF-8");
//            String oldAccessToken = "";
//            //从原始链接中获取accessToken
//            Map<String, String> map = getParameterMap(url);
//            if (!map.isEmpty()) {
//                oldAccessToken = map.get("aesToken");
//            }
//            //旧的token过期后刷新获取新的token
//            if (!jwtTokenUtil.validateToken(oldAccessToken, null)) {
//                String newAccessToken = jwtTokenUtil.refreshToken(oldAccessToken);
//                log.info("短链中accessToken已过期: {}，刷新后accessToken : {} ", oldAccessToken, newAccessToken);
//                //替换原始链接中accessToken值
//                if (null != oldAccessToken && null != newAccessToken) {
//                    url = url.replace(oldAccessToken, newAccessToken);
//                }
//            }
        }
        log.info("重定向地址 : {}", url);
        response.sendRedirect(url);
    }



    Map<String, String> getParameterMap(String url) {
        Map<String, String> map = new HashMap();
        String[] parameters = url.substring(url.indexOf("?") + 1).split("&");
        for (String str : parameters) {
            String[] parameter = str.split("=");
            if (parameter.length < 2) {
                map.put(parameter[0], null);
            } else {
                map.put(parameter[0], parameter[1]);
            }
        }
        return map;
    }

}
