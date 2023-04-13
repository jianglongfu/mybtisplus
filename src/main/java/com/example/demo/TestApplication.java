package com.example.demo;

import com.example.demo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    private MailService mailService;

    @Test
    public void test() {
        ThreadLocal t=new ThreadLocal();
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("xx",1);
        map.get("xx");
        ConcurrentHashMap cmap=new ConcurrentHashMap<>();
        cmap.put("1",1);

        String to = "1632994945@qq.com";
        String subject = "猜猜我今天买了啥";
        String content = "<html><head></head><body><h3>哈哈，什么都没有</h3></body></html>";
        try {
            mailService.sendHtmlMail(to, subject, content);
            System.out.println("成功了");
        } catch (MessagingException e) {
            System.out.println("失败了");
            e.printStackTrace();
        }
    }

}
