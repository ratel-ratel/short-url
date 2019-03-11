
package com.nice.controller;

import com.nice.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by 尹熙成 on 2018/4/17.
 */

@RestController
@Scope("prototype")
@RequestMapping("/usernfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @RequestMapping(value = "/parseExcel", method = RequestMethod.POST)
    public void parseExcel(@RequestParam("uploadFile") MultipartFile file,HttpServletResponse response) throws Exception {
         if (file.isEmpty()){
             try {
                 response.getWriter().print("导入的文件为空");
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         userInfoService.parseExcel(file);
    }

    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public void importExcel(@RequestParam("uploadFile") MultipartFile file,HttpServletResponse response) throws Exception {
        if (file.isEmpty()){
            try {
                response.getWriter().print("导入的文件为空");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userInfoService.importExcel(file);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ResponseBody
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        userInfoService.export(httpServletRequest,httpServletResponse);
    }
}

