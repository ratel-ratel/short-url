//package com.nice.controller;
//
//import cn.vpclub.moses.core.model.response.BaseResponse;
//import cn.vpclub.moses.core.model.response.PageResponse;
//import cn.vpclub.moses.share.consumer.entity.ShortLink;
//import cn.vpclub.moses.share.consumer.model.request.ShortLinkPageParam;
//import cn.vpclub.moses.share.consumer.service.ShortLinkService;
//import cn.vpclub.moses.web.controller.AbstractController;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * <p>
// * 前端控制器
// * </p>
// *
// * @author wangliang
// * @since 2017-08-28
// */
//@RestController
//@Slf4j
//@Scope("prototype")
//@AllArgsConstructor
//@RequestMapping("/shortLink")
//public class ShortLinkController extends BaseController {
//
//
//    private ShortLinkService shortLinkService;
//
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation("添加方法-add method")
//    public BaseResponse add(@RequestBody ShortLink vo) {
//        return shortLinkService.add(vo);
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation("修改方法-update method")
//    public BaseResponse update(@RequestBody ShortLink vo) {
//        return shortLinkService.update(vo);
//    }
//
//    @RequestMapping(value = "/query", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation("查询方法-query method")
//    public BaseResponse query(@RequestBody ShortLink vo) {
//        return shortLinkService.query(vo);
//    }
//
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation("删除方法-delete method")
//    public BaseResponse delete(@RequestBody ShortLink vo) {
//        return shortLinkService.delete(vo);
//    }
//
//    @RequestMapping(value = "/page", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation("条件查询-page method")
//    public PageResponse page(@RequestBody ShortLinkPageParam vo) {
//        return shortLinkService.page(vo);
//    }
//
//    @RequestMapping(value = "/generateShortLink", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation("生成短链-generateShortLink method")
//    public BaseResponse generateShortLink(@RequestBody ShortLink vo, HttpServletRequest request) {
//        return shortLinkService.generateShortLink(vo, request);
//    }
//
//    @RequestMapping(value = "/generateShortLinkXYYD", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation("生成短链-generateShortLinkXYYD method")
//    public BaseResponse generateShortLinkXYYD(@RequestBody ShortLink vo) {
//        return shortLinkService.generateShortLinkXYYD(vo);
//    }
//
//    @RequestMapping(value = "/queryLink", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation("根据短链查询原始链接-queryLink method")
//    public BaseResponse queryLink(@RequestBody ShortLink vo) {
//        return shortLinkService.queryLink(vo);
//    }
//}
