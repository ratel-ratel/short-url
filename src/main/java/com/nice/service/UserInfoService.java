
package com.nice.service;

import com.baomidou.mybatisplus.service.IService;
import com.nice.domain.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;

/**
 * Created by yxc on 2019/3/1.
 */

public interface UserInfoService extends IService<UserInfo> {

/**
     * 解析文件
     * @param file
     * @return
     */

    void parseExcel(MultipartFile file) throws IOException, ValidationException;


/**
     * 导入exal
     * @param file
     */

    void importExcel(MultipartFile file) throws IOException;

    void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}

