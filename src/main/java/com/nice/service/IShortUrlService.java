package com.nice.service;

import com.baomidou.mybatisplus.service.IService;
import com.nice.domain.ShortLink;
import com.nice.domain.ShortUrl;
import com.nice.utils.BaseResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by nice on 2018/3/16.
 */
public interface IShortUrlService extends IService<ShortUrl> {
    public BaseResponse add(ShortUrl model);

    public BaseResponse delete(ShortUrl model);

    public BaseResponse update(ShortUrl model);
    public BaseResponse query(ShortUrl model);
    public BaseResponse generateShortLink(ShortLink shortLink, HttpServletRequest request);

}
