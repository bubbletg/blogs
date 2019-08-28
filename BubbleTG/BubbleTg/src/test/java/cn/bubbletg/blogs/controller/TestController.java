package cn.bubbletg.blogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/8/4 1:01
 */
@Controller
public class TestController {

    @RequestMapping("/testController")
    public String testController(){
        return "success";
    }
}
