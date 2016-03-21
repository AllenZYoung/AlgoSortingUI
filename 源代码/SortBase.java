package Sorts;

import java.util.*;

//实现对于每种排序通用的函数和成员

abstract public class SortBase {
	//抽象方法待具体类实现
	abstract public <Type> void sort(Type[] data, Comparator<? super Type> c);
	abstract public String getName();
	
	//交换元素
	
	protected static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }
}
