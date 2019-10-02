---
title: 剑指offerjava实现01
date: 2019-10-02 23:07:28
categories:
	面试题
tags:
	[面试,算法]

---

# 数组部分

**声明：本文内容的解题思路都是剑指offer第2版上的解题思路，只是我用java代码实现，当然，也有理解有出入，不代表全部，还望理解。**

## **1.1.** **题目一:找出数组中重复的数字。**

在一个长度为n的数组里的所有数字都在0~n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2,3, 1,0,2,5,3},那么对应的输出是重复的数字2或者3。

**源代码地址**：https://github.com/bubbletg/interviewSkills/blob/master/01_Array/src/cn/bubbletg/array/Array01.java

**代码**：

有点遗憾，没能按照原书实现空间复杂度为O(1).如果你知道怎么实现欢迎留言告诉我（qq:363491343）

```java

public class Array01 {
    public static void main(String[] args) {

        //输入数据
        Scanner in = new Scanner(System.in);
        Integer n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        //输出重复元素数组
        int[] duplication = new int[n];
        //返回输出数组长度
        int length = 0;
        //数组长度为空，或小于0,返回0
        if (arr.length <= 0) {
            System.out.println("数组长度大于0");
            return;
        }
        //数组内数字大于长度，返回0
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0 || arr[i] > n - 1) {
                System.out.println("数组不合法");
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            //数组的第i个值不等于下标，则交换
            while (arr[i] != i) {
                //
                if (arr[i] == arr[arr[i]]) {

                    //输出数组长度加一

                    duplication[length++] = arr[i];
                    //跳出当前循环，不然进入死循环
                    break;
                }
                //交换
                int tmp = arr[i];
                arr[i] = arr[tmp];
                arr[tmp] = tmp;

            }
        }
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                System.out.printf("%d ", duplication[i]);
            }
        } else {
            System.out.println("没有重复元素！");
        }

    }

}
```





 

## **1.2.** **面试题4:二维数组中的查找**

题目:在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数， 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

**源代码地址**：https://github.com/bubbletg/interviewSkills/blob/master/01_Array/src/cn/bubbletg/array/Array02.java

**代码**：

```java 

public class Array02 {
    public static void main(String[] args) {

        //输入数据
        Scanner in = new Scanner(System.in);
        //输入要查找的数
        int n = in.nextInt();
        int[][] arr = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}};

        //
        if (find(n, arr, 4, 4)) {
            System.out.println("存在数字" + n);
        } else {
            System.out.println("不存在");
        }
    }

    private static boolean find(int n, int[][] arr, int row, int line) {
        boolean b = false;

        if (arr != null && line > 0 && row > 0) {

            int row_ = 0;
            while (row > 0 && line > 0) {
                //判断右上角是否相等，
                if (arr[row_][line - 1] == n) {
                    b = true;
                    System.out.println("数字 " + n + " :位置：（" + row_ + "," + (line - 1) + "）");
                    break;
                } else if (arr[row_][line - 1] < n) {
                    //在下方
                    row_++;
                    row--;
                } else if (arr[row_][line - 1] > n) {
                    //在前面
                    line--;
                }
            }
        }
        return b;
    }

}

```

## **1.3.** **面试题5:替换空格**

题目:请实现一个函数，把字符串中的每个空格替换成"%20"。例如，输入“We are happy.", 则输出“We%20are%20happy."。

**源代码地址**：https://github.com/bubbletg/interviewSkills/blob/master/01_Array/src/cn/bubbletg/array/Array03.java

**代码**：

```java
public class Array03 {
    public static void main(String[] args) {

        String chars = " We are happy.  ";
        //最简单的做法，利用原生的api
        //System.out.println(chars.replace(" ", "%20"));

        //显然，这里不单单是考验我们使用原生api


        System.out.println(replaces(chars, " ", "%20"));
    }

    /**
     * @param s           原字符串
     * @param target      被替换目标
     * @param replacement 替换的字符串
     * @return
     */
    public static String replaces(String s, String target, String replacement) {

        //计算空格数
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                k++;
            }
        }
        //计算替换字符串长度
        int l = replacement.length();

        //申请新的字符数组
        char[] chars = new char[s.length() + k * l];
        //指向空格前面的下标
        int q = s.length() - 1;
        //指向空格后的下标,因为有k个空格，所以减k
        int p = s.length() + (k * l) - 1;
        //复制替换
        while (p >= 0 && q >= 0) {

            if (s.charAt(q) != ' ') {
                //向前移动并复制
                chars[p--] = s.charAt(q--);
            } else if (s.charAt(q) == ' ') {
                chars[p--] = '0';
                chars[p--] = '2';
                chars[p--] = '%';
                 --q;
            }
        }
        s = String.valueOf(chars);
        return s;
    }
}
```

