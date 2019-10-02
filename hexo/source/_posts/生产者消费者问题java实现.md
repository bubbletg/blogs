---
title: 生产者消费者问题java实现
date: 2019-09-26 17:03:58
categories:
	java
tags:
	[java,多线程]
---





# 生产者消费者问题java实现



### 问题描述：

```java
用两个线程实现如下输出 12A 34B 45C ······5152Z 其中一个线程输出数字，数字小于52， 一个线程输出字符，A--Z。
```

### 问题分析：

​	从题目知道，要我们多线程实现，如何用多线程实现？我们看题，一个进程来输出数字，一个来输出字符。且答应格式已经个我们提示了，是一前一后的显示结果，是不是很像生产者消费者啊。我们把输出数字当做生产者生产产品，输出字符当做消费者消费产品。是不是一下子就明了了。

​	如果你还不会生产者消费者问题，建议看看操作系统，或者自行百度一下。现在我们来代码分析实现。

### 代码分析：

​	由于我们已经知道是生产者消费者问题了，我们先来创建一个线程类来生产产品（T0）。显然，我们也要创建一个产品类(Show)，现在就差消费者了（T2）;

​	类创建好了我们没有实现逻辑，现在我们来实现逻辑：

​	产品类（show）需要一个标记判断产品是否消费掉了，消费掉了这是输出字符，我们用flag = flase来标记。而生产了产品，用flag = true来标记这里生产完成表示输出数字。然后就是两个方法来分别显示输出了。

​	生产者类，因为要生产，当我们生产完成后要把flag = true,表示生产完成，即等待消费者消费。如果没有没有消费我们让生产者就等待，让消费者消费。

​	消费者类，与生产则正好相反，消费没有消费，生产者就无法生产产品。

### 代码实现：

###### 产品类

```java
/**
 * 显示打印类
 */
class Show {
    /**
     * 标记  true 打印字母
     * false 打印数字
     */
    public boolean flag = false;

    /**
     * 打印数字
     *
     * @param i
     */
    public void show0(int i) {
        System.out.printf("%d", i);
    }

    /**
     * 打印字符
     *
     * @param c
     */
    public void show2(char c) {
        System.out.printf("%c ", c);
    }

}


```

###### 生产者

```java
/**
 * 打印数字线程
 * 相当于生产者
 */
class T0 extends Thread {
    private Show s;

    public T0(Show s) {
        this.s = s;
    }

    private static int i = 0;

    @Override
    public void run() {
        while (true) {
            synchronized (s) {
                //判断打印谁，s.flag = false 则打印数字
                if (!s.flag && i < 52) {
                    //打印，相当于生产者生产产品
                    s.show0(++i);
                    s.show0(++i);
                    //产品生产完成，我们让生产等待消费者消费
                    //等待
                    try {
                        s.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //当消费者消费后，我们把s.flag = true，表示消费者消费，然后唤醒此进程
                //设置true 打印字母
                s.flag = true;
                s.notify();

            }

        }
    }
}
```

###### 消费者

```java

/**
 * 相当于消费者
 * 打印字符线程
 */
class T2 extends Thread {
    private Show s;

    public T2(Show s) {
        this.s = s;
    }

    private static char c = 'A' - 1;

    // 消费者与生产者刚好相反
    @Override
    public void run() {
        while (true) {
            synchronized (s) {

                //判断打印谁
                if (s.flag && c < 'Z') {
                    //打印
                    s.show2(++c);
                    //等待
                    try {
                        s.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //设置true 打印字母
                s.flag = false;
                s.notify();

            }

        }
    }
}

```

###### 测试

```java
//测试
public class Main {
    public static void main(String[] args) {
        Show show = new Show();
        new T0(show).start();
        new T2(show).start();
    }
}
```

