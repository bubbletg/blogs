---
title: 剑指offerjava实现02
date: 2019-10-05 19:53:13
categories:
	面试题
tags:
	[面试,算法]
---
# 数组部分

**声明：本文内容的解题思路都是剑指offer第2版上的解题思路，只是我用java代码实现，当然，也有理解有出入，不代表全部，还望理解。**

## **1.1.** **面试题6:从尾到头打印链表**

题目:输入一个链表的头节点，从尾到头反过来打印出每个节点的值。

**源代码地址**：https://github.com/bubbletg/interviewSkills/blob/master/01_Array/src/cn/bubbletg/array/LinkedList.java


代码实现：

方式一：栈结构实现，因为栈是先进后出，可以利用栈实现。

```java
public class LinkedList {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        //方式一，栈结构实现
        //用来当做栈
        ArrayList<Integer> arrayList = new ArrayList<>();
        *//*
        新建一个节点指向原来节点
        *//*
        ListNode newListNode = listNode;

        while (newListNode != null) {
            //向栈里面添加内容
            arrayList.add(0, newListNode.val);
            newListNode = newListNode.next;
        }

        return arrayList;
    }
}
```

方式二：递归实现

```
public class LinkedList {
    ArrayList<Integer> arrayList = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		//先判断是否为空
        if (listNode != null) {
        	//不为空指向下一个节点。
            printListFromTailToHead(listNode.next);
            //添加，从后往前添加
            arrayList.add(listNode.val);
        }
        return arrayList;
    }
}
```



