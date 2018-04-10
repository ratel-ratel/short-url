package com.nice.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.nice.domain.ShortLink;
import com.nice.mapper.ShortLinkMapper;
import com.nice.service.IShortLinkService;
import com.nice.utils.BackResponseUtil;
import com.nice.utils.BaseResponse;
import com.nice.utils.ResponseConvert;
import com.nice.utils.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

;

/**
 * <p>
 * 服务实现类
 * </p>
 * @author wangliang
 * @since 2017-08-28
 */
@Slf4j
@Service
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLink> implements IShortLinkService {
    public ShortLinkServiceImpl() {
        super();
    }

    public ShortLinkServiceImpl(ShortLinkMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public BaseResponse add(ShortLink model) {
        log.info("add ShortLink request is : {}",model);
        boolean back = this.insert(model);
        BaseResponse baseResponse = ResponseConvert.convert(back);
        log.info("add ShortLink response is : {}",baseResponse);
        return baseResponse;
    }

    @Override
    public BaseResponse delete(ShortLink model) {
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
    public BaseResponse update(ShortLink model) {
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
    public BaseResponse query(ShortLink model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            ShortLink data = this.selectById(model.getId());
            if (null != data) {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
                baseResponse.setDataInfo(data);
            } else {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
            }
        }
        return baseResponse;
    }

//    @Override
//    public Page page(ShortLinkPageParam pageParam) {
//        Page<ShortLink> page = new Page<ShortLink>();
//
//        //封装条件
//        EntityWrapper<ShortLink> ew = new EntityWrapper<ShortLink>();
//        if (StringUtil.isNotEmpty(pageParam.getAppId())) {
//            ew.eq("app_id", pageParam.getAppId());
//        }
//        if (StringUtil.isNotEmpty(pageParam.getLink())) {
//            ew.like("link", pageParam.getLink());
//        }
//        if (StringUtil.isNotEmpty(pageParam.getShortLink())) {
//            ew.like("short_link", pageParam.getShortLink());
//        }
//
//        Page<ShortLink> pageResponse;
//        //针对分页判断
//        if (null != pageParam && null != pageParam.getPageNumber() && null != pageParam.getPageSize()) {
//            page.setCurrent(pageParam.getPageNumber());
//            page.setSize(pageParam.getPageSize());
//            pageResponse = this.selectPage(page, ew);
//        } else {
//            List<ShortLink> selectList = this.selectList(ew);
//            pageResponse = new Page<>();
//            pageResponse.setRecords(selectList);
//            pageResponse.setTotal(this.selectCount(ew));
//        }
//
//        return pageResponse;
//    }

    @Override
    public BaseResponse queryLink(ShortLink model) {
        log.info("queryLink request is : {}",model);
        BaseResponse baseResponse;
        if (null == model || null == model.getShortLink()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            EntityWrapper<ShortLink> ew = new EntityWrapper<ShortLink>();
            ew.eq("short_link", model.getShortLink());
            ShortLink data = this.selectOne(ew);
            if (null != data) {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
                baseResponse.setDataInfo(data);
            } else {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
            }
        }
        log.info("queryLink response is : {}",baseResponse);
        return baseResponse;
    }

}
