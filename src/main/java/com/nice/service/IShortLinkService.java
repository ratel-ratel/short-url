package com.nice.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.nice.domain.ShortLink;
import com.nice.utils.BaseResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangliang
 * @since 2017-08-28
 */
public interface IShortLinkService extends IService<ShortLink> {
    public BaseResponse add(ShortLink model);

    public BaseResponse delete(ShortLink model);

    public BaseResponse update(ShortLink model);

    public BaseResponse query(ShortLink model);
    public BaseResponse queryLink(ShortLink model);
}
