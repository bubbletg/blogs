package cn.bubbletg.blogs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/8/3 22:37
 */
public class User implements Serializable {
    /**
     * id 用户唯一ID
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 邮箱
     */
    private String userEmail;
    /**
     * 手机
     */
    private String userTelephone;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 用户注册创造日期
     */
    private Date userCreateDate;
    /**
     * 邮箱是否激活
     */
    private char emailStatus = 'N';
    /**
     * 激活码
     */
    private String code;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userTelephone='" + userTelephone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userCreateDate=" + userCreateDate +
                ", emailStatus=" + emailStatus +
                ", code='" + code + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getUserCreateDate() {
        return userCreateDate;
    }

    public void setUserCreateDate(Date userCreateDate) {
        this.userCreateDate = userCreateDate;
    }


    public char getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(char emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
