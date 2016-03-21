package Sorts;

import java.util.Comparator;

public class SelectionSort extends SortBase{
	//选择排序
	 
	public static <T> void soSelectSort(T[] data, Comparator<? super T> c) {
		for (int i = 0; i < data.length; i++) {
			int lowIndex = i;
			for (int j = data.length - 1; j > i; j--) {
//				if (data[j] < data[lowIndex]) {
				if (c.compare(data[j], data[lowIndex]) < 0) {
					lowIndex = j;
				}
			}
	        swap(data,i,lowIndex);
		}
	}
	
	public <T> void sort(T[] data, Comparator<? super T> c) {
		soSelectSort(data, c);
	}
	
	public String getName() {
//		String s = "选择排序";
		return "选择排序";
	}
}
