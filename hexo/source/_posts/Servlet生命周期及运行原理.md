---
title: Servlet生命周期及运行原理
date: 2018-04-20 07:17:56
categories:
	java
tags:
    [java]
---

# Servlet生命周期及运行原理

## 1. Servlet生命周期

servlet生命周期可以是创建到毁灭过程：

​	Servlet 通过调用 **init ()** 方法进行初始化。

​	Servlet 调用 **service()** 方法来处理客户端的请求。

​	Servlet 通过调用 **destroy()** 方法终止（结束）。



### init()方法



init 方法只执行一次。它在第一次创建 Servlet 时被调用，在后续每次用户请求时不再调用。因此，它是用于一次性初始化。

创建servlet,默认情况下，第一次访问时，servlet被创建

在web.xml中配置：

` <load-on-startup>` 值为负数，第一次访问时创建。

` <load-on-startup>` 值为正数或零，服务器启动时创建。

init 方法只执行一次。说明一个servlet只存在一个对象，servlet是单例的。

### service()方法

service() 方法是执行实际任务的主要方法。Servlet 容器（即 Web 服务器）调用 service() 方法来处理来自客户端（浏览器）的请求，并把格式化的响应写回给客户端。

每次服务器接收到一个 Servlet 请求时，服务器会产生一个新的线程并调用服务。service() 方法检查 HTTP  请求类型（GET、POST、PUT、DELETE 等），并在适当的时候调用 doGet、doPost、doPut，doDelete 等方法。

### destroy()方法

destroy() 方法只会被调用一次，在 Servlet 生命周期结束时被调用。destroy() 方法可以让您的 Servlet 关闭数据库连接、停止后台线程、把 Cookie 列表或点击计数器写入到磁盘，并执行其他类似的清理活动。

服务器关闭执行destroy方法，只有正常关闭时，才会执行destroy方法。

## 2.servlet运行原理

1.客户端发起请求，服务端获取请求后，解析请求url路径，获取访问的servlet资源路径

2.查找容器（web.xml文件），看是否有响应的 `<url-pattern>` 标签内容

3.根据 `<url-pattern>` 标签内容查找 `<serlet-class>` 全类名

4.tomcat 将字节码文件加入内存，创建对象，调用方法。