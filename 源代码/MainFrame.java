import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;

import Sorts.*;//自己写的包

/**
 * @author 张扬 A.Z.Y
 * 2016年1月6日
 * MainFrame.java
 */
public class MainFrame extends JFrame{
	
	private Sorter sorter;//排序器
	
	//初始窗口大小
	private static final int DEFAULT_WIDTH = 1200;
	private static final int DEFAULT_HEIGHT = 950;
	//确保鲁棒性，加final
	
	public MainFrame() {
		
		
		this.setTitle("排序算法图形演示程序   by 张扬");
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//滑动选项条
		final ArrayBars CompPic = new ArrayBars();
		JSlider slider = new JSlider();
		sorter = new Sorter(CompPic);

//		add(CompPic, BorderLayout.WEST);
		add(CompPic, BorderLayout.CENTER);//放在窗口中心
		
		slider.setMinimum(5);
		slider.setMaximum(200);
		slider.setValue(45);//默认初始的数据量
		
		final JLabel label =  new JLabel("数据规模: n = " + slider.getValue());
		//
		
		JButton runButton = new JButton("开始");
		runButton.setContentAreaFilled(false);

		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorter.setRun();//开始排序演示
			}
		});
		
		JButton stopButton = new JButton("停止");
		stopButton.setContentAreaFilled(false);
		
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorter.setStop();//停止排序演示
			}
		});

		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				if (!source.getValueIsAdjusting()) {
					label.setText("数据规模: n = " + source.getValue());
					
					sorter.stop();
					sorter.setLength(source.getValue());
					sorter = new Sorter(CompPic);
					new Thread(sorter).start();//每次重设规模都要重置图形
				}
			}
		});

		
		JPanel controlButtons = new JPanel();//控制按钮
		controlButtons.add(runButton);//运行
		controlButtons.add(stopButton);//停止
		add(controlButtons, BorderLayout.SOUTH);//放在窗口下部
		
		//下拉菜单
		final JComboBox<String> combox = new JComboBox<>();
		
//		combox.setBounds(getX(), getY(), 100, 50);
		
		combox.addActionListener(new ActionListener() {//事件监听
			public void actionPerformed(ActionEvent e) {
				String AlgoName = (String)combox.getSelectedItem();
				
				sorter.stop();
				sorter.setAlgor(AlgoName);//算法设置
				
				try 
				{
					Thread.sleep(90);
				} catch (InterruptedException e1) {
					e1.printStackTrace();//添加必要的异常处理
				}
				
				sorter = new Sorter(CompPic);
				new Thread(sorter).start();//新线程
			}
		});
		
		//添加元素
		for(SortBase SB : Sorter.SortAlgos)
			combox.addItem(SB.getName());

		JPanel optionPanel = new JPanel();//选项栏
		add(optionPanel, BorderLayout.NORTH);
		
		optionPanel.add(combox);
		optionPanel.add(label);
		optionPanel.add(slider);
		//布局完成
		new Thread(sorter).start();
	}
		
}
