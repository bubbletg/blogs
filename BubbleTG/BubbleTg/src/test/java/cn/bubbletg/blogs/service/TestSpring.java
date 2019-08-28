package cn.bubbletg.blogs.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试Spring
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/8/4 0:25
 */
public class TestSpring {

    /**
     * 测试Spring独立运行
     */
    @Test
    public void testSpring(){
        //加载Spring文件
        ApplicationContext applicationContext  =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //获取对象
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.findAll();

    }
}
