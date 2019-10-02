---
title: 用hexo搭建个人博客托管到GitHub上。
date: 2017-01-23 09:20:54
categories:
	服务与软件安装
tags: 
    服务器
---
（参考博客：[hexo从零开始到搭建完整](https://www.cnblogs.com/visugar/p/6821777.html)）

# 一、准备环境

    已经安装Git Bash
    已经安装NodeJs

# 二、安装hexo

参考官网：[官网](https://hexo.io/zh-cn/docs/)

```
npm install -g hexo-cli
```

安装 Hexo 完成后，请执行下列命令，Hexo 将会在指定文件夹<folder>中新建所需要的文件。

```
$ hexo init <folder>
$ cd <folder>
$ npm install
```

新建完成后，指定文件夹的目录如下：

```
.
├── _config.yml
├── package.json
├── scaffolds
├── source
|   ├── _drafts
|   └── _posts
└── themes
```

以上内容来之官网文档。

现在你可以进入到<folder>，可以修改配置文件：_config.yml

当然这里你看参考官方文档。

# 三、搭桥到github

现在我们来在github 上创建一个Repositories。我创建的是一个bubbletg.github.io

**命名有要求：**

名字.github.io

用编辑器打开你的blog项目，修改`_config.yml`文件的一些配置(冒号之后都是有一个半角空格的)：

```
deploy:
  type: git
  repo: https://github.com/bubbletg/bubbletg.github.io.git
  branch: master
```

使用下面命令：

```
hexo clean
hexo generate
hexo server
```

注：hexo 3.0把服务器独立成个别模块，需要单独安装：`npm i hexo-server`。

上传到github

先输入命令：

`npm install hexo-deployer-git --save`

再输入：

```
hexo clean
hexo generate
hexo deploy
```

回到GitHub你的项目上，看看是否查看提交上了。

浏览器输入：

bubbletg.github.io

看能否访问，如果成功了就Ok了。

当然，有的人想要绑定个人域名。

#### 绑定个人域名

不想绑定的自行忽略

第一步购买域名：随便在哪个网站买一个就好了，我呢是在腾讯云购买的[bubbletg.cn](http://bubbletg.cn).

第二步，解析域名，我是用的是泛解析。用二级域名来指向：bubbletg.github.io。（这里不做多讲解，自己捣鼓一下就懂）。

第三步添加CNAME：在项目的source文件夹下新建一个名为CNAME的文件（没有其他文件后缀），在里面添加你购买的域名，比如我添加的是`bubbletg.cn`，或者blog.bubbletg.cn，只能添加一个哦。

然后通过个人域名访问试试看吧。

# 四、总结

总结一下，有的同学可以搭建完了访问时是出现404.  这很正常。

解决方法（我遇到的）：

1.在GitHub创建项目时命名不是以 .github.io 结尾。修改即可。

2.通过个人域名访问时，出现404，没有添加CNAME文件，或者配做了。检测即可。

-------------------------------------------------------------

如果你没有看懂，或者有什么疑问？？

联系我吧。

QQ：363491343

微信：tiangui_

我的个人网站：www.bubbletg.cn

我的博客：[blog.bubbletg.cn](blog.bubbletg.cn)

我的GitHub：https://github.com/bubbletg





