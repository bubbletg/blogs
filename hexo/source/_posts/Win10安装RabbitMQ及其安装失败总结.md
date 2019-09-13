---
title: Win10安装RabbitMQ及其安装失败总结
date: 2019-09-13 23:49:39
categories:
	服务与软件安装
tags:
	RabbitMQ
---

# 1. windows下安装

## 1.1. 下载

下载RabbitMQ地址：http://www.rabbitmq.com/download.html

下载Erlang：<http://www.erlang.org/download/otp_win64_17.3.exe>

## 1.2. 安装

先安装Erlang

### 1.2.1. 安装Erlang

{%asset_img clip_image002.png%}

直接一直下一步：

{%asset_img clip_image005.png%}

安装完成。

如果出现：

{%asset_img clip_image006.png%}

请选择忽略。

### 1.2.2. 安装RabbitMQ

{%asset_img clip_image007.png%}

{%asset_img clip_image008.png%}

{%asset_img clip_image009.png%}

安装完成。

启动、停止、重新安装等。

 

按下win + R 然后运行命令：services.msc

{%asset_img clip_image010.png%}

查看RabbitMQ服务有没有启动：

{%asset_img clip_image011.png%}

## 1.3. 安装的注意事项

**1）推荐使用默认的安装路径**

**2）系统用户名必须是英文**

可以查看下用户目录：

 {%asset_img clip_image012.png%}

也可以在桌面打开cmd查看路径：

{%asset_img clip_image013.png%}

**3**）计算机名必须是英文

{%asset_img clip_image014.png%}

**4）系统的用户必须是管理员**

 

## 1.4.启用管理工具

 {%asset_img clip_image015.png%}

1、 点击{%asset_img clip_image016.png%}

2、 输入命令：

rabbitmq-plugins enable rabbitmq_management
{%asset_img clip_image017.png%}

 

3、 在浏览器中输入地址查看：<http://127.0.0.1:15672/>

{%asset_img clip_image018.png%}

4、 使用默认账号登录：guest/ guest

## 1.5. 安装失败

在安装RabbitMQ时提示：

Your installed version of Erlang (6.2) is too old. Please install a more recent version.

您安装的Erlang(6.2)版本太旧了。请安装最新版本。

显然：我们安装的太旧了。

从新下载安装解决。



# 2.Linux下安装RabbitMQ

这里不介绍了，网上很多，可自行搜索。





