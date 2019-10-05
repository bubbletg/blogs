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



# 树

## **2.1.** **面试题7:重建二叉树**

题目:输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如，输入前序遍历序列{1,2,4, 7, 3, 5, 6, 8}和中序遍历序列{4, 7,2, 1,5,3,8,6}，则重建如图2.6所示的二叉树并输出它的头节点。

**源代码地址**：https://github.com/bubbletg/interviewSkills/blob/master/01_Array/src/cn/bubbletg/tree/BinaryTree.java

思路：

根据前序遍历我们可以找到树的根节点1，然后在中序遍历中可以知道左子树节点有：4,7,2。右子树有5,3,8,6.

然后我们递归，在左子树中知道根节点是2，然后在中序遍历中可以知道左子树节点有：4,7,。右子树没有.

依次递归，然后右子树递归，就可以构建一颗二叉树了。

代码：

```java
public class BinaryTree {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {

        //当长度为空时，返回空
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        //根节点，每次递归把根节点放入
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            //找到中续遍历中的根节点
            if (in[i] == pre[0]) {

                //中序遍历左边是左子树
                root.left = reConstructBinaryTree(
                        //拷贝，注意拷贝函数，第二个参数是开始下标，第三个参数是结束位置，
                        Arrays.copyOfRange(pre, 1, i + 1),
                        Arrays.copyOfRange(in, 0, i));
                //中序遍历右边是右子树
                root.right = reConstructBinaryTree(
                        Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }
}
```



## **2.2.** **面试题8:二叉树的下一个节点**

题目:给定一棵二叉树和其中的一个节点，如何找出中序遍历序列的下一个节点?树中的节点除了有两个分别指向左、右子节点的指针，还有一个指 向父节点的指针。

代码

方式一：

```java
public class BinaryTreeNextNode {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        /*
            第一种情况，结点有右子树，那么他的下一个结点就右子结点的最左子树最后一个节点
         */
        if (pNode.right != null) {
            TreeLinkNode linkNode = pNode.right;
            while (linkNode.left != null) {
                linkNode = linkNode.left;
            }
            return linkNode;
        }
        /*
            第二种情况，没有右节点，但是是父节点的左节点。下一个节点就是父节点
         */
        if (pNode.next != null) {
            //父节点的左节点是当前节点时，表示当前节点时父节点的左节点
            if (pNode.next.left == pNode) {
                //直接返回父节点
                return pNode.next;
            } else {
                /*
            第三种种情况，没有右节点，但是是父节点的右节点。
            下一个节点是 父节点的父节点的左节点 父节点
            （有点绕口）
         */
                TreeLinkNode pNext = pNode.next;
                while (pNext.next != null && pNext.next.right == pNext) {
                    pNext = pNext.next;
                }
                return pNext.next;

            }
        }
        return null;

    }

}

class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
```

方式二：

既然给了二叉树的某个结点，且二叉树存储着指向父结点的指针（next），那我们可以先找到根节点，再对树进行中序遍历，最后根据中序遍历结果找到给定结点的下一结点。

链接：https://www.nowcoder.com/questionTerminal/9023a0c988684a53960365b889ceaf5e?answerType=1&f=discussion来源：牛客网

```
链接：https://www.nowcoder.com/questionTerminal/9023a0c988684a53960365b889ceaf5e?answerType=1&f=discussion
来源：牛客网

import java.util.*;
public class Solution {
    static ArrayList<TreeLinkNode> list = new ArrayList<>();
    public TreeLinkNode GetNext(TreeLinkNode pNode){
        TreeLinkNode par = pNode;
        while(par.next != null){
            par = par.next;
        }
        InOrder(par);
        for(int i=0;i<list.size();i++){
            if(pNode == list.get(i)){
                return i == list.size()-1?null:list.get(i+1);
            }
        }
        return null;
    }
    void InOrder(TreeLinkNode pNode){
        if(pNode!=null){
            InOrder(pNode.left);
            list.add(pNode);
            InOrder(pNode.right);
        }
    }
}
```