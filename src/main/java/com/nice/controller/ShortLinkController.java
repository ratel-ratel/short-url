package com.nice.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.nice.annotation.RequestJson;
import com.nice.domain.Person;
import com.nice.domain.ShortLink;
import com.nice.service.IShortLinkService;
import com.nice.utils.BaseResponse;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangliang
 * @since 2017-08-28
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/shortLink")
public class ShortLinkController extends BaseController {


    private IShortLinkService shortLinkService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加方法-add method")
    public BaseResponse add(@RequestBody ShortLink vo) {
        return shortLinkService.add(vo);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("修改方法-update method")
    public BaseResponse update(@RequestBody ShortLink vo) {
        return shortLinkService.update(vo);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("查询方法-query method")
    public BaseResponse query(@RequestBody ShortLink vo) {
        return shortLinkService.query(vo);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("删除方法-delete method")
    public BaseResponse delete(@RequestBody ShortLink vo) {
        return shortLinkService.delete(vo);
    }

    // 下载pdf文档
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=ShortLink.pdf");
        //创建 Document
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        //查询数据
        Page<ShortLink> page=new Page();
        page.setSize(100);
        Page<ShortLink> shortLinkPage = shortLinkService.selectPage(page);
        List<ShortLink> list=shortLinkPage.getRecords();
        for (ShortLink shorLink : list) {
            PdfPTable table = new PdfPTable(3);
            PdfPCell cell = new PdfPCell();
            Paragraph elements = new Paragraph(shorLink.getId().toString());
            elements.setRole(new PdfName("id"));
            cell.setPhrase(elements);
            table.addCell(cell);
            document.add(table);
            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(shorLink.getLink()));

            table.addCell(cell);
            document.add(table);
            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(shorLink.getShortLink()));
            table.addCell(cell);
            document.add(table);
        }
        document.close();
    }

}
