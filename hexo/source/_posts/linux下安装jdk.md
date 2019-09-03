---
title: linux下安装jdk
date: 2019-01-27 11:03:11
categories:
	服务器
tags:
	Linux
	服务器
---

# 一、首先下载linux版本jdk上传到服务器

jdk官网：http://www.oracle.com/technetwork/java/javase/downloads/index.html

将下载好的jdk压缩包，通过ftp上传到linux系统的当前用户下，我当前登录的用户为root用户

# 二、将上传后的jdk，解压到/usr/local/目录下，命令如下：

```
cd /usr/local/
tar -zxvf jdk-8u221-linux-x64.tar.gz

x:表示从tar包中解压出来
z：表示 tar 包是被 gzip 压缩过的，所以解压时需要用 gunzip 解压
v:显示详细信息
f:指定被解压的文件是***.tar.gz
-C表示将解压后的文件放在-C指定的路径下
```

{%asset_img jdk解压.png%}

# 三、配置环境变量，有两种配置方法

在rofile文件中添加

```
cd /etc
vi profile
```

```
export JAVA_HOME=/usr/local/jdk1.8.0_221;
export PATH=$PATH:$JAVA_HOME/bin;
export CLASS_PATH=.:$JAVA_HOME/1ib/dt.jar:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/jre/lib/rt.jar;
```

然后执行source profile,对文件做生效处理。执行java -version,出现下面内容，表示环境变量配置成功

{%asset_img QQ截图20190903113155.png%}



四、错误解决方法

如果安装jdk 时出现：cannot execute binary file 错误。

一般由于系统与jdk版本不一致导致的。

```
getconf LONG_BIT
```

执行上面命令：

发现系统是32位的。而我们jdk是64位的。

现在知道问题了，我们就知道解决的方法了。下载jdk32位版本，上传到原来目录，把原来解压的jdk文件夹一起删除，然后修改为原来jdk 一样的名字。就可以不用修改目录了。

**当然，不建议这么做，直接建议后修改配置文件才是正确的选择。**



