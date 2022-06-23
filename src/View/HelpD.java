package View;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Tools.ImgManager;

public class HelpD extends JDialog {
	JFrame jf;
	JLabel jl1 = new JLabel();
	JLabel jl2 = new JLabel("扫雷，Windows复刻版");
	JLabel jl3 = new JLabel("版权所有：蔡剑平");
	JLabel jl4 = new JLabel("单位：福州大学");
	JLabel jl5 = new JLabel("制作时间：2022.06.23");
	JButton jb1=new JButton("确定");
	public HelpD(JFrame jf) {
		// TODO Auto-generated constructor stub
		super(jf, "关于  扫雷", true);
		setLocationRelativeTo(jf);
		Box box1=Box.createVerticalBox();
		jl1.setIcon(ImgManager.icon);
		jl1.setText("java 扫雷智慧的挑战！");
		JPanel jp1=new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		jp1.add(jl1);
		box1.add(jp1);
		JPanel jp2=new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		jp2.add(jl2);
		box1.add(jp2);
		JPanel jp3=new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		jp3.add(jl3);
		box1.add(jp3);
		JPanel jp4=new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		jp4.add(jl4);
		box1.add(jp4);
		JPanel jp5=new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		jp5.add(jl5);
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HelpD.this.dispose();
			}
		});
		box1.add(jp5);
		box1.add(jb1);
		this.add(box1);
		Border border1 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		((JPanel) this.getContentPane()).setBorder(border1);
		this.pack();
		this.setVisible(true);
	}

}
