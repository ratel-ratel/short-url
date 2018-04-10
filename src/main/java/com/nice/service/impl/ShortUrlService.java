package com.nice.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.nice.domain.ShortLink;
import com.nice.domain.ShortUrl;
import com.nice.mapper.ShortUrlMapper;
import com.nice.service.IShortLinkService;
import com.nice.service.IShortUrlService;
import com.nice.utils.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 短链生成业务层
 * </p>
 *
 * @author wangliang
 * @since 2017/8/31
 */
@Slf4j
@Service
@AllArgsConstructor
public class ShortUrlService extends ServiceImpl<ShortUrlMapper, ShortUrl> implements IShortUrlService {

    private IShortLinkService shortLinkService;
    /**
     * 生成短链同时存储（存储的短链是host+加密字符串，返回前端短链是域名host+加密字符串）
     *
     * @param request
     * @return
     */
    @Override
    public BaseResponse generateShortLink(ShortLink shortLink, HttpServletRequest request) {
        BaseResponse response;
        //业务操作
        log.info("生成短链请求参数 : {}", shortLink);
        try {
            String longLink = encodeUrl(shortLink.getLink());//url编码
            shortLink.setLink(longLink);

            //生成短链
            String shortLinkStr = ShortUtil.shortUrl(longLink, shortLink.getAppId().toString());
            StringBuffer requestURL = request.getRequestURL();
            //获取域名
            String host = requestURL.delete(requestURL.length() - request.getRequestURI().length(), requestURL.length()).append("/").toString();
            //存储的短链是host+加密字符串
            shortLink.setShortLink(host + shortLinkStr);
            log.info("shortUrl is : {}", shortLink.getShortLink());
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());

            //校验唯一性、数据库中不存在该短链才保存
            BaseResponse<ShortLink> baseResponse = shortLinkService.queryLink(shortLink);
            if (null == baseResponse || !ReturnCodeEnum.CODE_1000.getCode().equals(baseResponse.getReturnCode())) {
                Long time = System.currentTimeMillis();
                shortLink.setCreatedTime(time);
                shortLink.setUpdatedBy(shortLink.getCreatedBy());
                shortLink.setUpdatedTime(time);
                shortLink.setDeleted(1);
                response = shortLinkService.add(shortLink);
            }
            //返回短链
            String shortUrl = host + shortLinkStr;
            //返回前端短链是域名host+加密字符串
            response.setDataInfo(shortUrl);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1004.getCode());
        }
        log.info("生成短链返回结果 : {}", response);
        return response;
    }

    /**
     * 查询长链
     *
     * @param shortUrl
     * @return
     */
    public String queryLongUrl(String shortUrl) {
        String longUrl = "";
        try {
            if (null == shortUrl || "".equals(shortUrl)) {
                longUrl = "";
            } else {
                //根据短链从关系型数据库或缓存中获取长链
                ShortUrl mode=new ShortUrl();
                mode.setShortUrl(shortUrl);
                BaseResponse query = query(mode);
                if (null!=query&&null!=query.getDataInfo()){
                    longUrl=((ShortUrl)query.getDataInfo()).getLongUrl();
                }else {
//                    longUrl = "https://github.com/silingwang";
                }
                //todo
                //url解码
                longUrl = URLDecoder.decode(longUrl, "UTF-8");
            }
        } catch (Exception e) {
            log.error("error", e);
        }
        return longUrl;
    }

    /**
     * url编码
     *
     * @param longUrl
     * @return
     * @throws Exception
     */
    protected String encodeUrl(String longUrl) throws Exception {
        String result;
        if (longUrl.indexOf("?") > -1) {
            String host = longUrl.substring(0, longUrl.indexOf("?"));
            String param = longUrl.substring(longUrl.indexOf("?") + 1, longUrl.length());
            result = host + "?" + URLEncoder.encode(param, "UTF-8");
        } else {
            result = longUrl;
        }
        return result;
    }

    @Override
    public BaseResponse add(ShortUrl model) {
        boolean back = this.insert(model);
        BaseResponse baseResponse = ResponseConvert.convert(back);
        return baseResponse;
    }

    @Override
    public BaseResponse delete(ShortUrl model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            boolean back = this.deleteById(model.getId());
            baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse update(ShortUrl model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            boolean back = this.updateById(model);
            baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse query(ShortUrl model) {
        BaseResponse baseResponse;
        if (null == model) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else if (null != model.getId()) {
            ShortUrl data = this.selectById(model.getId());
            if (null != data) {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
                baseResponse.setDataInfo(data);
            } else {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
            }
        }else {
            EntityWrapper<ShortUrl> shortUrlEw = new EntityWrapper();
            //查询条件
            if (StringUtils.isNotEmpty(model.getShortCode())) {
                shortUrlEw.eq("short_code", model.getShortCode());
            }
            if (StringUtil.isNotEmpty(model.getShortUrl())) {
                shortUrlEw.eq("short_url", model.getShortUrl());
            }
            if (StringUtil.isNotEmpty(model.getLongUrl())) {
                shortUrlEw.eq("long_url", model.getLongUrl());
            }
            List<ShortUrl> shortUrlList = this.selectList(shortUrlEw);//记录表
            if (CollectionUtils.isEmpty(shortUrlList)){
                baseResponse=BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
            }else {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
                baseResponse.setDataInfo(shortUrlList.get(0));
            }
        }
        return baseResponse;
    }
}
