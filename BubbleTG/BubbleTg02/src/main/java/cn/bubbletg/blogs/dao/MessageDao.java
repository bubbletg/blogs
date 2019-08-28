package cn.bubbletg.blogs.dao;

import cn.bubbletg.blogs.domain.Message;
import cn.bubbletg.blogs.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/8/4 0:17
 */
@Repository
public interface MessageDao {

    @Insert("insert into t_message(messageHeadurl,messageName,messageEmail,messageContent)\n" +
            "        values(#{MessageHeadurl},#{MessageName},#{MessageEmail},#{MessageContent})")
    void add(Message message);
}
