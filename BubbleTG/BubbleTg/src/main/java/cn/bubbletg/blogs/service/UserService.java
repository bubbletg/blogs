package cn.bubbletg.blogs.service;

import cn.bubbletg.blogs.domain.User;

import java.util.List;

/**
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/8/4 0:21
 */
public interface UserService {

    /**
     * 查询所有用户
     */
    List<User> findAll();

    /**
     * 用户注册
     *
     * @param user
     * @return 邮箱激活码
     */
    String add(User user);
}
