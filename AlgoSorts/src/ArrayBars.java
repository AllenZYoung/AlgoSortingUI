import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.color.*;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class ArrayBars extends JComponent{
	//successful version
	
	public synchronized void setValues(Double[] values, Double a, Double b) {
		this.values = values.clone();//直接复制过去
		this.key1 = a;//排序关键字
		this.key2 = b;
		repaint();//绘制
	}
	
	public synchronized void paintComponent(Graphics gra) {
		if (values == null)
			return ;
		Graphics2D g2d = (Graphics2D) gra;
		g2d.setColor(Color.gray);//灰色着色
	
		int width = getWidth()/(values.length);
		
		for (int i=0; i<values.length; i++) 
		{
			double height = values[i] * getHeight();
//			Rectangle2D bar = new Rectangle2D.Double(width*i, 0, width, height);
			//坐标决定矩形队列是正放还是倒放
			Rectangle2D Rectan = new Rectangle2D.Double(width*i, this.getHeight()-height-0.5, width, height);
			if (values[i] == key1 || values[i] == key2)
			{
				g2d.fill(Rectan);
				//俩块比较时着色
			}
			else 
				g2d.draw(Rectan);
		}
	}
	
	private Double key1;//关键值
	private Double key2;
	private Double[] values;//存放值的数组
}
