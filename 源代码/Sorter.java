import java.awt.*;

import java.util.*;
import java.util.concurrent.Semaphore;

import javax.swing.*;

import Sorts.*;


/**
 * @author 张扬 A.Z.Y
 * 2016年1月4日
 * Sorter.java
 */

public class Sorter implements Runnable{
	
	private Double[] values;
	private ArrayBars component;
	private boolean run,end;//运行停止的状态
	private Semaphore gate;//线程管理
	private static SortBase sortAlgorithm;//所有排序算法的父类

	private static int length = 45;
	//默认数据规模，与MainFrame中Slider显示值保持一致

	private static final int DELAYTIME = 77 ;//默认间隔时间
	
//	private final int DELAYTIME = (130 - this.getLength())>0?(130 - this.getLength()):20;//默认间隔时间
//  这个算式设置并不完美，干脆弃用Orz
	
	public static HashMap<String, Integer> HashMapAlgo = new HashMap<String, Integer>(); 
	//用一个哈希图存映射
	
	public static SortBase[] SortAlgos;
	
	public Sorter(ArrayBars comp) {
		
		values = new Double[this.length];//待排序的数组

		for (int i=0; i<values.length; i++)
			values[i] = new Double(Math.random());//生成随机值
				
		this.component = comp;
		this.run = false;
		this.gate = new Semaphore(1);//保证线程秩序
		this.end = false;
	}
	
	public boolean setAlgo(int index) {
		if(index > SortAlgos.length) 
			return false;
		else {
			sortAlgorithm = SortAlgos[index];
			return true;
		}
	}

	public boolean setAlgor(String algorithmName) {
		Integer index = (this.HashMapAlgo).get(algorithmName);
		if (index == null) 
			return false;
		else 
			return setAlgo(index);//调用setAlgo
	}
	
	public void stop() {
		run = false;
		end = true;
		gate.release();
	}
	
	//一大堆set和get函数
	public void setRun() {
		run = true;
		gate.release();
	}
	
	public void setStop() {
		run = false;
		gate.release();
	}
	
	public void setLength(int length) 
	{
		this.length = length;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public synchronized void run() {
		Comparator<Double> comp = new Comparator<Double>() {
			public int compare(final Double num1, final Double num2) {
				
				EventQueue.invokeLater(new Runnable() {
					// 线程管理
					
					public void run() {
						component.setValues(values, num1, num2);
					}
				});
								
				try{
					if(end) 
						Thread.interrupted();
					else if(run) 
						Thread.sleep(DELAYTIME);//设置睡眠间隔，产生动画效果
					else 
						gate.acquire();
				} 
				catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				
				return num1.compareTo(num2);
			}
		};
		
		//排序
		this.sortAlgorithm.sort(values, comp);
		System.out.println(sortAlgorithm.getName());
		
		component.setValues(values, null, null);
		
		if(!end) 
			JOptionPane.showMessageDialog(null, "排序完成！");
	}
	
	
	// 算法列表

	//用一下static block~
	static{
		// 将所有的排序算法添加到数组
		SortAlgos = new SortBase[] {
				new BubbleSort(),//冒泡
				new MergeSort(),//归并
				new QuickSort(),//快排
				new InsertSort(),//插入
				new SelectionSort(), //选择
				new ShellSort(),//希尔
		};
		
		
		for (int i=0; i<SortAlgos.length; i++) {
			HashMapAlgo.put(SortAlgos[i].getName(), i);
//			HashMapAlgo[i] = SortAlgos[i];
			
		}// 将所有算法类添加到表中，建立映射
		//添加
		
		// 设置默认的排序算法
		sortAlgorithm = SortAlgos[0];//设为冒泡
	}
	
}
