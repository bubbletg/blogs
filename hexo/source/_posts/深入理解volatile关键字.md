---
title: 深入理解volatile关键字
date: 2019-09-02 10:24:28
categories:
	java
tags:
    [java,多线程]

---

<u>（声明：本文为java高并发编程详解 汪文君 著 的学习笔记，绝大多数来源原书）</u>



# 深入理解volatile关键字

## 1. 并发编程的三个重要特性

### 1.1 原子性

​		所谓原子性是指在- - 次的操作或者多次操作中，要么所有的操作全部都得到了执行并且不会受到任何因素的干扰而中断，要么所有的操作都不执行。

###### 银行转账：

比如从A的账号往T的账号转入1000元，这个动作将包含两个最基本的操作:从A的账号上扣除1000元;给T的账号增加1000元。这两个操作必须符合原子性的要求，要么都成功要么都失败，总之不能出现A的账号扣除了1000元，但是T的账号并未增加1000元或者A账号未扣除1000元，T的账号反倒增加了1000元的情况。

### 1.2 可见性

​		当一个线程对共享变量修改值后，另一个线程可以立即看到修改的最新值。

### 1.3 有序性

​		指代码执行过程中的先后顺序。



## 2. JMM如何保证三大特性

### 2.1 JMM与原子性

​		在Java语言中，对基本数据类型的变量读取赋值操作都是原子性的，对引用类型的变量读取和赋值的操作也是原子性的，因此诸如此类的操作是不可被中断的，要么执行，要么不执行，正所谓一荣俱荣一损俱损。

{asset_img JMM与原子性.png}

{asset_img JMM与原子性01.png}

##### 结论：

综合上面的四个例子，我们可以发现只有第一种操作即赋值操作具备原子性，其余的均不具备原子性，由此我们可以得出以下几个结论。

* **多个原子性的操作在一-起就不再是原子性操作了。**

* **简单的读取与赋值操作是原子性的，将-一个变量赋给另外一个变量的操作不是原子性的。**

* **Java内存模型(JMM)只保证了基本读取和赋值的原子性操作，其他的均不保证，如果想要使得某些代码片段具备原子性,需要使用关键字synchronized,或者JUC中的lock。如果想要使得int等类型自增操作具备原子性，可以使用JUC包下的原子封装类型java.util.concurrent.atomic.***

* **总结: volatile 关键字不具备保证原子性的语义。**

  

### 2.2 JMM与可见性

​	java提供了三种方式来保证可见性。

* **使用volatile 关键字，一个变量被volatile修饰时，对共享变量读操作直接在主存中进行，对共享资源的写操作先修改工作内存，修改后立即刷新到主存。**

* **通过synchronized关键字保证可见性，synchronized 关键字能够保证同一时刻只有一个线程获得锁，然后执行同步方法，并且还会确保在锁释放之前，会将对变量的修改刷新到主内存当中。**

 * **通过JUC提供的显式锁Lock也能够保证可见性，Lock的lock方法能够保证在同一时刻只有一个线程获得锁然后执行同步方法，并且会确保在锁释放( Lock的unlock方法)之前会将对变量的修改刷新到主内存当中。**
 * **总结: volatile 关键字不具备保证原子性的语义。**



### 2.3 jMM与有序性

​	三种方式保证有序性。

* 使用volatile关键字来保证有序性。
* 使用synchronized关键字来保证有序性。
* 使用显式锁Lock来保证有序性。

后两者采用了同步的机制，同步代码在执行的时候与在单线程情况下一样自然能够保证顺序性(最终结果的顺序性)。

##### Happens-before原则

Java的内存模型具备--些天生的有序性规则，不需要任何同步手段就能够保证有序性，这个规则被称为Happens-before原则。

* 程序次序规则:在一个线程内，代码按照编写时的次序执行，编写在后面的操作发生于编写在前面的操作之后。
  这句话的意思看起来是程序按照编写的顺序来执行，但是虚拟机还是可能会对程序代码的指令进行重排序，只要确保在一个线程内最终的结果和代码顺序执行的结果一致即可。
* 锁定规则: 一个unlock操作要先行发生于对同一个锁的lock操作。
  这句话的意思是，无论是在单线程还是在多线程的环境下，如果同一个锁是锁定状态,那么必须先对其执行释放操作之后才能继续进行lock操作。
* volatile 变量规则:对一个变量的写操作要早于对这个变量之后的读操作。
* 传递规则:如果操作A先于操作B,而操作B又先于操作C，则可以得出操作A肯定要先于操作C,这一点说明了happens-before原则具备传递性。
* 线程启动规则:Thread对象的start()方法先行发生于对该线程的任何动作。
* 线程中断规则:对线程执行interrupt() 方法肯定要优先于捕获到中断信号。
* 线程的终结规则:线程中所有的操作都要先行发生于线程的终止检测，通俗地讲，线程的任务执行、逻辑单元执行肯定要发生于线程死亡之前。
* 对象的终结规则:一个对象初始化的完成先行发生于finalize()方法之前。（先生后死）
  **总结:volatile关键字具有保证顺序性的语义。**



## 3. volatile 关键字深入解析

### 3.1 volatile 关键字的语义

* 保证了不同线程之间对共享变量操作时的可见性，也就是说当-一个线程修改volatile修饰的变量，另外一个线程会立即看到最新的值。
* 禁止对指令进行重排序操作。

（1）理解volatile保证可见性

（2）理解volatile保证顺序性

volatile关键字对顺序性的保证就比较霸道一- 点，直接禁止JVM和处理器对volatile关键字修饰的指令重排序，但是对于volatile前后无依赖关系的指令则可以随便怎么排序。

（3）理解volatile不保证原子性

### 3.2 volatile 的原理和实现机制

{%asset_img volatile实现原理.png%}

### 3.3 volatile的使用场景

(1 )开关控制利用可见性的特点

(2 )状态标记利用顺序性特点
(3 ) Singleton 设计模式的double-check也是利用了顺序性特点

### 3.4 volatile和synchronized区别

#### 使用上：

* volatile关键字**只能用于修饰实例变量或者类变量**，不能用于修饰方法以及方法参数和局部变量、常量等。
* synchronized 关键字不能用于对变量的修饰，**只能用于修饰方法或者语句块**。
* volatile修饰的变量可以为null, synchronized 关键字同步语句块的monitor对象不能为null。

#### 对原子性的的保证：

* volatile无法保证原子性。
* 由于synchronized是一种排他的机制，因此被synchronized关键字修饰的同步代码是无法被中途打断的，因此其能够保证代码的原子性。

#### 对可见性的保证

* 两者均可以保证共享资源在多线程间的可见性，但是实现机制完全不同。
* synchronized借助于JVM指令monitorenter和monitorexit对通过排他的方式使得同步代码串行化，在monitorexit时所有共享资源都将会被刷新到主内存中。
* 相比较于synchronized关键字volatile 使用机器指令(偏硬件)“lock ;”的方式迫使其他线程工作内存中的数据失效，不得到主内存中进行再次加载。

#### 对有序性的保证

* volatile关键字禁止JVM编译器以及处理器对其进行重排序，所以它能够保证有序性。

* 虽然synchronized关键字所修饰的同步方法也可以保证顺序性，但是这种顺序性是以程序的串行化执行换来的，在synchronized关键字所修饰的代码块中代码指令也会发生指令重排序的情况，比如:

  ~~~
  synchronized（this）{
  	int x =10;
  	int y = 20;
  	x++;
  	y = y+1;
  }
  
  
  ~~~

  x和y谁最先定义以及谁最先进行运算，对程序来说没有任何的影响，另外x和y之间也没有依赖关系，但是由于synchronized关键字同步的作用，在synchronized的作用域结束时x必定是11, y必定是21,也就是说达到了最终的输出结果和代码编写顺序的一致性。

#### 其他

* volatile 不会使线程陷入阻塞。
* synchronized 关键字会使线程陷入阻塞。















 

































