package cn.bubbletg.blogs.controller;

import cn.bubbletg.blogs.domain.Message;
import cn.bubbletg.blogs.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/8/3 10:13
 */

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    public MessageService messageService;


    @RequestMapping("/add")
    public void add(Message message) {
        messageService.add(message);
    }



}
