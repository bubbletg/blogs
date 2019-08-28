package cn.bubbletg.blogs.domain;

/**
 * 联系留言类
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/8/5 12:22
 */
public class Message {
    private Integer MessageId;
    /**
     * 留言头像地址
     */
    private String MessageHeadurl;
    /**
     * 昵称
     */
    private String MessageName;

    /**
     * 邮箱
     */
    private String MessageEmail;
    /**
     * 内容
     */
    private String MessageContent;

    public Integer getMessageId() {
        return MessageId;
    }

    public void setMessageId(Integer MessageId) {
        this.MessageId = MessageId;
    }

    public String getMessageHeadurl() {
        return MessageHeadurl;
    }

    public void setMessageHeadurl(String MessageHeadurl) {
        this.MessageHeadurl = MessageHeadurl;
    }

    public String getMessageName() {
        return MessageName;
    }

    public void setMessageName(String MessageName) {
        this.MessageName = MessageName;
    }

    public String getMessageEmail() {
        return MessageEmail;
    }

    public void setMessageEmail(String MessageEmail) {
        this.MessageEmail = MessageEmail;
    }

    public String getMessageContent() {
        return MessageContent;
    }

    public void setMessageContent(String MessageContent) {
        this.MessageContent = MessageContent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "MessageId=" + MessageId +
                ", MessageHeadurl='" + MessageHeadurl + '\'' +
                ", MessageName='" + MessageName + '\'' +
                ", MessageEmail='" + MessageEmail + '\'' +
                ", MessageContent='" + MessageContent + '\'' +
                '}';
    }
}
