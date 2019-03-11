
package com.nice.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nice.domain.UserInfo;
import com.nice.mapper.UserInfoMapper;
import com.nice.service.UserInfoService;
import com.nice.utils.AccessExcelUtil;
import com.nice.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by yxc on 2019/3/1.
 */

@Slf4j
@Service
@AllArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    private UserInfoMapper userInfoMapper;

    @Override
    public void parseExcel(MultipartFile file) throws IOException, ValidationException {
        InputStream inputStream = file.getInputStream();
        Class<UserInfo> userInfoClass = UserInfo.class;
        List<String> list = new ArrayList<String>();
        list.add("id");
        list.add("userName");
        list.add("mobilePhone");
        List<UserInfo> userInfoList = AccessExcelUtil.parseExcel(inputStream, userInfoClass, list, 1, 0);

        ClassLoader classLoader = this.getClass().getClassLoader();
        String url = classLoader.getResource("test.sql").getPath();
        log.info(" url path : " + url);
        String replace = url.replace("test.sql", "");
        log.info(" url replace : " + replace);
        long time = System.currentTimeMillis();
        try {
            FileWriter fw = new FileWriter(url);
            fw.write("INSERT INTO `user_info` (USER_NAME,MOBILE_PHONE)  VALUES ");
            Integer id = 1;
            for (UserInfo userInfo : userInfoList) {
                fw.write("('" + userInfo.getUserName() + "','" + userInfo.getMobilePhone() + "')");
                if (id != userInfoList.size()) {
                    fw.write(",");
                }
                id++;
            }
            fw.write(";");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.runsqlBySpringUtils(url);
    }

    @Override
    public void importExcel(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        Class<UserInfo> userInfoClass = UserInfo.class;
        List<String> list = new ArrayList<String>();
        list.add("id");
        list.add("userName");
        list.add("mobilePhone");
        List<UserInfo> userInfoList = AccessExcelUtil.parseExcel(inputStream, userInfoClass, list, 1, 1);
        Long maxId = userInfoMapper.queryMax();
        maxId = maxId + 1;
        for (UserInfo userInfo : userInfoList) {
            userInfo.setId(maxId);
            maxId++;
        }

        this.insertBatch(userInfoList, 2000);
    }


    /**
     * @param sqlPath
     */

    public void runsqlBySpringUtils(String sqlPath) throws ValidationException {
        try {

            /*SqlSession sqlSession = sqlSessionFactory.openSession();
            Connection conn = sqlSession.getConnection();*/

            Connection conn = this.getConn();

            FileSystemResource rc = new FileSystemResource(sqlPath);
            EncodedResource er = new EncodedResource(rc, "utf-8");
            ScriptUtils.executeSqlScript(conn, er);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // 获得连接对象
    private Connection getConn() throws ValidationException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/github?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true";
        String username = "root";
        String password = "root";
        Connection conn = null;
        if (StringUtils.isEmpty(driver) || StringUtils.isEmpty(url)
                || StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ValidationException("未配置数据源请配置his数据源,或配置缺失");
        }
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 导出
     */
    @Override
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Long startTime = System.currentTimeMillis();
        log.info("高考登记导出start...", startTime.toString());
        List<String> heads = new ArrayList<>();//存放EXCEL文件列表头信息数组
        heads.add("id");
        heads.add("用户名");
        heads.add("手机号");
        //ArrayList对象中存放Excel表格各列对应数据库表的中字段的名称field
        List<String> filedNameList = new ArrayList<>();
        filedNameList.add("id");
        filedNameList.add("userName");
        filedNameList.add("mobilePhone");
        String fileName = "用户数据_" + DateUtil.formatDate(new Date(), DateUtil.YEARMONTHDAYHHMMSS) + ".XLSX";//文件名
        final String userAgent = httpServletRequest.getHeader("USER-AGENT");//获取请求消息头
        try {
            String finalFileName = null;
            if (userAgent.contains("MSIE") || userAgent.contains("like Gecko")) {//IE浏览器
                finalFileName = URLEncoder.encode(fileName, "UTF-8");
            } else if (StringUtils.contains(userAgent, "Mozilla")) {//google,火狐浏览器
                finalFileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            } else {
                finalFileName = URLEncoder.encode(fileName, "UTF-8");//其他浏览器
            }
            httpServletResponse.addHeader("Content-Disposition", "attachment;filename=" + finalFileName);


            List<UserInfo> userInfoList = userInfoMapper.queryAll();
            //开始导出
            OutputStream fileOpStream = httpServletResponse.getOutputStream();
//            AccessExcelUtil.createExcel(fileOpStream, fileName, heads, filedNameList, userInfoList);
            this.exportExcel(httpServletResponse,null,userInfoList);
            long endTime = System.currentTimeMillis();
            log.info("用户数据导出end,共花费" + (endTime - startTime) / 1000 + "秒");

        } catch (Exception e) {
            log.error("导出:", e);
        } finally {
           /* try {
                httpServletResponse.flushBuffer();
            } catch (IOException e) {
                log.error("高考登记记录输出流关闭失败: {}", e);
            }*/
        }
    }

    public void exportExcel(HttpServletResponse response, String name, List<UserInfo> users) throws Exception {

        String[] tableHeaders = {"id", "姓名", "电话"};

        XSSFWorkbook workbook = new XSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet("Sheet1");
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillBackgroundColor(HSSFColor.WHITE.index);

        Font font = workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        cellStyle.setFont(font);

        // 将第一行的三个单元格给合并
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        XSSFRow row = sheet.createRow(0);
        XSSFCell beginCell = row.createCell(0);
        beginCell.setCellValue("通讯录");
        beginCell.setCellStyle(cellStyle);

        row = sheet.createRow(1);
        // 创建表头
        for (int i = 0; i < tableHeaders.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(tableHeaders[i]);
            cell.setCellStyle(cellStyle);
        }
        for (int i = 0; i < users.size(); i++) {
            row = sheet.createRow(i + 2);

            UserInfo user = users.get(i);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getUserName());
            row.createCell(2).setCellValue(user.getMobilePhone());
        }

        OutputStream outputStream = response.getOutputStream();
        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=template.XLSX");

        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
    /**
     * 导出Excel文件流到浏览器
     * @author	黄文君
     * @date	2016年8月8日 上午9:26:21
     *
     * @param	response
     * @param	workbook
     * @param	excelName
     * @throws	Exception
     * @return	void
     */
    public static void sendExcel(HttpServletResponse response, HSSFWorkbook workbook, String excelName) throws Exception {
        if (workbook != null) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(excelName.getBytes("gb2312"), "ISO8859-1") + ".xls");
            OutputStream ouputStream = null;
            try {
                ouputStream = response.getOutputStream();
                workbook.write(ouputStream);
                ouputStream.flush();
            } catch (IOException e) {
                log.info("导出Excel文件流到浏览器的时候异常!", e);
            } finally {
                try {
                    ouputStream.close();
                } catch (IOException e) {
                    log.info("关闭OutputStream异常!", e);
                }
            }
        }
    }


}

