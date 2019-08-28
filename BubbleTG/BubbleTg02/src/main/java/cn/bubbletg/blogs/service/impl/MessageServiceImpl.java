package cn.bubbletg.blogs.service.impl;

import cn.bubbletg.blogs.dao.MessageDao;
import cn.bubbletg.blogs.domain.Message;
import cn.bubbletg.blogs.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/8/4 0:21
 */
@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    public MessageDao messageDao;


    @Override
    public void add(Message message) {
        messageDao.add(message);
    }
}
