---
title: HTTP协议
date: 2018-04-25 11:01:09
categories:
	计算机网络
tags:
    [计算机网络]

---

 

# 请求响应步骤

 

1. 客户端连接到Web服务器
2. 发送HTTP请求
3. 服务器接受请求并返回HTTP响应
4. 释放连接TCP连接
5. 客户端浏览器解析HTML内容

 

# 在浏览器地址栏键入URL ,按下回车之后经历的流程

➢DNS解析

➢TCP连接

➢发送HTTP请求

➢服务器处理请求并返回HTTP报文

➢浏览器解析渲染页面

➢连接结束

 

# HTTP状态码

五种可能的取值：

 

​	1XX:指示信息，表示请求已接收，继续处理

​	2XX:成功，表示请求已被成功接收，理解，接受

​	3XX:重定向，要完成请求必须进行更进一步的操作

​	4XX:客服端错误，请求语法错误或请求无法实现

​	5XX:服务端错误，服务端未能实现合法请求

 

常见状态码：

1. 200OK:正常返回信息
2. 400 Bad Request:客户端请求有语法错误，不能被服务器所理解
3. 401 Unauthorized:请求未经授权，这个状态代码必须和WWW-Authenticate报头域一起使用
4. 403 Forbidden:服务器收到请求，但是拒绝提供服务
5. 404 Not Found:请求资源不存在，eg,输入了错误的URL
6. 500 Internal Server Error:服务器发生不可预期的错误
7. 503 Server Unavailable:服务器当前不能处理客户端的请求，一段时间后可能恢复正常

 

# Get和Post请求的区别

从三个层面来回答：

 

**Http报文层面：**

​	Get将请求信息放在URL,POST放在报文体中。相对表面上来说，post安全一些，长度没有限定。

 

**数据库层面：**

​	GET符合幂等性和安全性，POST不符合。 

​	幂等性：对数据库的一次操作或多次操作是一致的。

​	安全性：对数据库的操作没有改变数据库数据。

​	post会向数据库提交数据，会改变数据，所以不安全，post请求获得的结果每次有可能不一样。

 

**其他层面：**

​	GET可以被缓存，被存储，post不行。

 

 

# Cookie和Session的区别

 

Cookie简介：

是由服务器发给客，户端的特殊信息, 以文本的形式存放在客户端;

客户端再次请求的时候,会把Cookie回发

服务器接收到后,会解析Cookie生成与客户端相对应的内容

 

 

Cookie的设置以及发送过程

 

{%asset_img Cook设置发送过程.png%}





Session 简介

- 服务器端的机制,在服务器上保存的信息
- 解析客户端请求并操作session id ,按需保存状态信息

 

区别：

- Cookie数据存放在客户的浏览器上, Session数据放在服务器上
- Session相对于Cookie更安全
- 若考虑减轻服务器负担,应当使用Cookie

 

 

 

# HTTP与HTTPS的区别

 

HTTPS简介：

 

{%asset_img HTTPS.png%}

 

SSL

为网络通信提供安全及数据完整性的一-种安全协议

是操作系统对外的API , SSL3.0后更名为TLS

采用身份验证和数据加密保证网络通信的安全和数据的完整性

 

加密方式

对称加密:加密和解密都使用同一个密钥

非对称加密:加密使用的密钥和解密使用的密钥是不相同的

哈希算法:将任意长度的信息转换为固定长度的值,算法不可逆

数字签名:证明某个消息或者文件是某人发出/认同的

 

HTTPS数据传输流程

浏览器将支持的加密算法信息发送给服务器

服务器选择一套浏览器支持的加密算法,以证书的形式回发浏览器

浏览器验证证书合法性,并结合证书公钥加密信息发送给服务器

服务器使用私钥解密信息,验证哈希,加密响应消息回发浏览器

浏览器解密响应消息,并对消息进行验真,之后进行加密交互数据

 

区别

HTTPS需要到CA申请证书, HTTP不需要

HTTPS密文传输, HTTP明文传输

连接方式不同, HTTPS默认使用443端口, HTTP使用80端口

HTTPS=HTTP+加密+认证+完整性保护, 较HTTP安全