package cn.bubbletg.blogs.dao;

import cn.bubbletg.blogs.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/8/4 0:17
 */
@Repository
public interface UserDao {
    /**
     * 查询全部
     */
    @Select("select * from t_user")
    List<User> findAll();

    /**
     * 用户添加啊
     *
     * @param user
     */
    @Select("insert into t_user(userName,userEmail,userPassword,userTelephone,emailStatus,code,userCreateDate)" +
            "values(#{userName},#{userEmail},#{userPassword},#{userTelephone},#{emailStatus},#{code},#{userCreateDate})")
    void add(User user);
}
