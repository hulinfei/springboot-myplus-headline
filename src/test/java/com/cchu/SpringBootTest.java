package com.cchu;

import com.cchu.mapper.HeadlineMapper;
import com.cchu.pojo.Headline;
import com.cchu.utils.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
@Slf4j

@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private HeadlineMapper headlineMapper;
    @Test
    public void test(){
        String token = jwtHelper.createToken(1L);
        System.out.println("token = " + token);
        int userId = jwtHelper.getUserId(token).intValue();
        System.out.println("userId = " + userId);
        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);
    }
    @Test
    public void test1(){
        Headline headline = new Headline();
        headline.setHid(1);
        headline.setTitle("测试标题");
        headline.setArticle("这是文章");
        int insert = headlineMapper.insert(headline);
        log.info("------------charu{}",insert);
    }
}
