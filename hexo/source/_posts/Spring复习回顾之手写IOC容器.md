---
title: Spring复习回顾之手写IOC容器
date: 2019-10-04 11:42:31
categories:
	Spring
tags:
	[Spring,java]
---

# Spring复习回顾之手写IOC容器



手写IOC之前，我们先来看看下面问题：

## 1. 请问什么是IoC和DI？并且简要说明一下DI是如何实现的？

​	IOC是控制反转，是Inversion of Contro 的缩写。DI（Dependency Injection） 是依赖注入,是对IOC更简单的诠释。

​	控制反转是把传统上由程序代码直接操控的对象的调用权交给容器，通过容器来实现对象组件的装配和管理。所谓的"控制反转"就是对组件对象控制权的转移，从程序代码本身转移到了外部容器，由容器来创建对象并管理对象之间的依赖关系。

​	依赖注入的基本原则是应用组件不应该负责查找资源或者其他依赖的协作对象。配置对象的工作应该由容器负责，查找资源的逻辑应该从应用组件的代码中抽取出来，交给容器来完成。DI是对IoC更准确的描述，即组件之间的依赖关系由容器在运行期决定，形象的来说，即由容器动态的将某种依赖关系注入到组件之中。

## 2. 请说明一下springIOC原理是什么？如果你要实现IOC需要怎么做？请简单描述一下实现步骤？

### IOC原理：

(约定)

**所有Bean的生命周期交由IoC容器管理**
**所有被依赖的Bean通过构造方法执行注入**
**被依赖的Bean需要优先创建**

### 手写实现IOC：

##### 需求：

通过ioc实现创建汽车类完成用户回家。

先创建一汽车接口。

```java
public interface Car {
    void start();
    void left();
    void right();
    void stop();
}

```

然后实现实现具体的汽车类：

```java
public class BC implements Car {
    public void start() {
        System.out.println("奔驰。。。。star()");
    }

    public void left() {
        System.out.println("奔驰。。。。left()");
    }

    public void right() {
        System.out.println("奔驰。。。。right()");
    }

    public void stop() {
        System.out.println("奔驰。。。。stop()");
    }
}


```

```java
public class DZ implements Car {
    public void start() {
        System.out.println("大众。。。。star()");
    }

    public void left() {
        System.out.println("大众。。。。left()");
    }

    public void right() {
        System.out.println("大众。。。。right()");
    }

    public void stop() {
        System.out.println("大众。。。。stop()");
    }
}
```

人类接口：

```java
public interface Human {
    void goHome();
}

```



```java
public abstract class CarHuman implements Human {
    protected Car car;

    public CarHuman(Car car) {
        this.car = car;
    }

    public abstract void goHome();
}

```

具体个人：

```java
public class Lishi extends CarHuman {
    public Lishi(Car car) {
        super(car);
    }

    @Override
    public void goHome() {
        System.out.println("李四----回家");
        car.start();
        car.left();
        car.right();
        car.stop();
    }
}
```

```java
public class Zhangsan extends CarHuman {
    public Zhangsan(Car car) {
        super(car);
    }

    @Override
    public void goHome() {
        System.out.println("张三----回家");
        car.start();
        car.stop();
    }
}
```

前面是搭建项目需求，现在重点来了（手写IOC）：

```java
public class IocContainer {

    private Map<String, Object> beans = new ConcurrentHashMap<String, Object>();

    /**
     * 根据beanid 获得一个Bean
     *
     * @param beanId id
     * @return 然后一个bean
     */
    public Object getBean(String beanId) {
        return beans.get(beanId);
    }

    /**
     * 通过IoC容器创建一个Bean
     *
     * @param clazz
     * @param beanId
     * @param paramBeanIds
     */
    public void setBean(Class<?> clazz, String beanId, String... paramBeanIds) {

        //1.组装构造方法所需要的参数值
        Object[] paramValues = new Object[paramBeanIds.length];
        for (int i = 0; i < paramBeanIds.length; i++) {
            paramValues[i] = beans.get(paramBeanIds[i]);
        }
        //2. 使用构造方法方法实例化
        Object bean = null;
        for (Constructor<?> constructor : clazz.getConstructors()) {
            try {
                bean = constructor.newInstance(paramValues);
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }
        if (bean == null) {
            throw new RuntimeException("找不到合适的构造方法！");
        }
        //放入beans中
        beans.put(beanId, bean);
    }
}

```

```java
/**
 * @author www.bubbletg.cn * BubbleTg
 * @version 1.0
 * @date 2019/10/5 0:34
 */
public class ClassTest {

    private IocContainer iocContainer = new IocContainer();

    @Before
    public void before(){
        iocContainer.setBean(DZ.class,"dazhong");
        iocContainer.setBean(BC.class,"bengci");
        iocContainer.setBean(Zhangsan.class,"zhangsan","dazhong");
        iocContainer.setBean(Lishi.class,"lisi","bengci");
    }

    @Test
    public void test(){

        Human zhan = (Human) iocContainer.getBean("zhangsan");
        zhan.goHome();

        Human lisi = (Human) iocContainer.getBean("lisi");
        lisi.goHome();

    }
}

```



### 步骤：

②实现IOC的步骤

定义用来描述bean的配置的Java类

解析bean的配置，將bean的配置信息转换为上面的BeanDefinition对象保存在内存中，spring中采用HashMap进行对象存储，其中会用到一些xml解析技术

遍历存放BeanDefinition的HashMap对象，逐条取出BeanDefinition对象，获取bean的配置信息，利用Java的反射机制实例化对象，將实例化后的对象保存在另外一个Map中即可。

参考：

<https://www.nowcoder.com/tutorial/94/36e2c89b4b4641f8b3c566758fdc879d>



