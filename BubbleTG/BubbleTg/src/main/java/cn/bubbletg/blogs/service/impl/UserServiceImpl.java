package cn.bubbletg.blogs.service.impl;

import cn.bubbletg.blogs.dao.UserDao;
import cn.bubbletg.blogs.domain.User;
import cn.bubbletg.blogs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/8/4 0:21
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        System.out.println("UserServiceImpl----findAll---查询所有用户");
        return userDao.findAll();
    }

    /**
     * 用户注册
     * @param user
     * @return 邮箱激活码
     */
    @Override
    public String add(User user) {
        System.out.println("UserServiceImpl----add---用户注册");
        //添加邮箱激活码
        String code = UUID.randomUUID().toString();
        user.setCode(code);
        userDao.add(user);
        return code;
    }
}
