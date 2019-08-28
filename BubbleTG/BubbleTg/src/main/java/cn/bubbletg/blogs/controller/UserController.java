package cn.bubbletg.blogs.controller;

import cn.bubbletg.blogs.domain.ResultInfo;
import cn.bubbletg.blogs.domain.User;
import cn.bubbletg.blogs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/8/3 10:13
 */

@Controller
@RequestMapping("/user")
public class UserController {


    static ResultInfo resultInfo = new ResultInfo();
    @Autowired
    public UserService userService;

    /**
     * 用户注册
     *
     * @param user
     */
    @RequestMapping("/register")
    public String register(User user, String check, HttpSession session) {
        System.out.println("UserController-----register----用户注册");
        //验证码校验
        //从Session中获取验证码
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //保证验证码使用一次，删除上一次生成的验证码
        session.removeAttribute("CHECKCODE_SERVER");
        //
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
        } else {
            //用户注册业务操作，返回邮箱激活码
            String code = userService.add(user);
            System.out.println(code);
        }
        return "forward:/user/responseBody";
    }

    /**
     * 用户登录
     */
    @RequestMapping("/loginUser")
    public String
    loginUser(User user, String check, HttpSession session) {
        System.out.println("UserController-----loginUser----用户登录信息");
        //验证码校验
        //从Session中获取验证码
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //保证验证码使用一次，删除上一次生成的验证码
        session.removeAttribute("CHECKCODE_SERVER");
        //比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
        } else if (true) {
            //登录成功
            resultInfo.setFlag(true);
        }
        //转发
        //通过@ResponseBody响应前端数据
        return "forward:/user/responseBody";
        //重定向
        //return "redirect:/html/index.html";
    }

    /**
     * 查询所有用户信息
     */
    @RequestMapping("/findAll")
    public void findAll() {
        System.out.println("UserController-----findAll----查询所有用户信息");
        List<User> list = userService.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * 响应前端数据
     *
     * @return
     */
    @RequestMapping("/responseBody")
    public @ResponseBody
    ResultInfo responseBody() {
        System.out.println(resultInfo);
        return resultInfo;
    }

}
