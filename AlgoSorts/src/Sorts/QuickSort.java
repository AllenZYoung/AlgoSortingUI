package Sorts;

import java.util.Comparator;

public class QuickSort extends SortBase{
	//快排
	
	public static <T> void doQuickSort(T[] data, Comparator<? super T> c) {
		quickSort(data,0,data.length-1,c );        
	}
	
    private static <T> void quickSort(T[] data,int i,int j, Comparator<? super T> comp){
    	
        int pivotIndex=(i+j)/2;
     //pivot和partition的设置
        swap(data,pivotIndex,j);
        
        int k=partition(data,i-1,j,data[j], comp);
        swap(data,k,j);
        if((k-i)>1) 
        	quickSort(data,i,k-1, comp);
        if((j-k)>1) 
        	quickSort(data,k+1,j, comp);
    }

    private static <T> int partition(T[] data, int l, int r, T pivot, Comparator<? super T> c) {
        do{
           while(c.compare(data[++l], pivot)<0);
           while((r!=0)&&c.compare(data[--r], pivot)>0);
           swap(data,l,r);
        }
        while(l<r);
        
        swap(data,l,r);        
        return l;
    }
	
	public <T> void sort(T[] data, Comparator<? super T> c) {
		doQuickSort(data, c);
	}
	
	public String getName() {
		return "快速排序";
	}	
}
