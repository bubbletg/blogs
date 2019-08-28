package cn.bubbletg.blogs.service;


import cn.bubbletg.blogs.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/8/5 17:47
 */
public class TestMybatis {
    @Test
    public void  run() throws IOException {
        //加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建一个SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        //创建SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        //获取代理对象
        UserDao dao = sqlSession.getMapper(UserDao.class);
        //查询
        System.out.println(dao.findAll());
        //关闭资源
        sqlSession.close();
        inputStream.close();

    }

}
