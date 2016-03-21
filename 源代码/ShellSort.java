package Sorts;

import java.util.Comparator;

public class ShellSort extends SortBase {
	public static <T> void ToShellSort(T[] data, Comparator<? super T> c) {
		for(int i=data.length/2;i>2;i/=2){
			for(int j=0;j<i;j++){
				insertSort(data,j,i, c);
			}
		}
		insertSort(data,0,1, c);
	}
	
	 
	 // 进行shell 排序时使用的 insertSort
	 
	private static <T> void insertSort(T[] data, int start, int incre, Comparator<? super T> c) {
		for(int i=start+incre;i<data.length;i+=incre){
			for(int j=i;(j>=incre)&&(c.compare(data[j], data[j-incre]) < 0);j-=incre){
				swap(data,j,j-incre);
			}
			//希尔的板子，注意步长
		}
	}
		
	public <T> void sort(T[] data, Comparator<? super T> c) {
		ToShellSort(data, c);
	}
	
	public String getName() {
		return "希尔排序";
	}
}
