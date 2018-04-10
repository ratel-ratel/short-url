//package com.test;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//
//import javax.mail.*;
//import javax.mail.internet.MimeMessage;
//import java.io.UnsupportedEncodingException;
//import java.util.Properties;
///**
// * Created by nice on 2018/3/19.
// */
//@Slf4j
//public class Email {
//    @Test
//    public  void testMessage() throws MessagingException {
//        try {
//            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//            // 设置邮件服务器
//            javaMailSender.setHost("smtp.qq.com");
//            //设置用户名密码
//            javaMailSender.setUsername("535383645@qq.com");
//            javaMailSender.setPassword("y3116784099");//密码
//            javaMailSender.setDefaultEncoding("UTF-8");
//            //设置收发件人信息
//            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//            //目标地址
//            mimeMessageHelper.setTo("3116784099@qq.com");
//            //发送地址，及地址昵称
//            mimeMessageHelper.setFrom("535383645@qq.com", "535383645@qq.com");
//            //邮件标题
//            mimeMessageHelper.setSubject("BeginCode注册验证邮件");
//            //邮件内容
//            StringBuilder str = new StringBuilder("<html><head></head><body>");
//            str.append("<h1>您好:</h1><br/>");
//            str.append("&nbsp;&nbsp;&nbsp;&nbsp;欢迎加入BeginCode<br/>");
//            str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的验证链接如下:<br/>");
//            str.append("<a href=\"链接跳转\">链接地址</a> <br/>");
//            str.append("</body></html>");
//            // true 表示启用html
//            mimeMessageHelper.setText(str.toString(), true);
//            //属性信息
//            Properties properties = new Properties();
//            properties.put("mail.smtp.auth", "true");  // 验证用户名和密码
//            properties.put("mail.smtp.timeout", "250000");
//            properties.put("mail.smtp.port", "465");
//            javaMailSender.setJavaMailProperties(properties);
//            javaMailSender.send(mimeMessage);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            System.out.print("发送失败");
//        }
//        // 发送邮件
//        System.out.print("发送成功");
//    }
//}
