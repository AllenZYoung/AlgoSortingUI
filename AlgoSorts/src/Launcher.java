import java.awt.EventQueue;


import javax.swing.JFrame;


/**
 * @author 张扬 A.Z.Y
 * 2016年1月6日
 * Launcher.java
 */

public class Launcher {
	public static void main(String[] args) {
		
		//添加到进程线程中
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame f1 = new MainFrame();
				f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭退出
				//frame.show();
				f1.setVisible(true);//等效于frame.show()
			}
		});
	}
}
