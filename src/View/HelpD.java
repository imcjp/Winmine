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
	JLabel jl2 = new JLabel("ɨ�ף�Windows���̰�");
	JLabel jl3 = new JLabel("��Ȩ���У��̽�ƽ");
	JLabel jl4 = new JLabel("��λ�����ݴ�ѧ");
	JLabel jl5 = new JLabel("����ʱ�䣺2022.06.23");
	JButton jb1=new JButton("ȷ��");
	public HelpD(JFrame jf) {
		// TODO Auto-generated constructor stub
		super(jf, "����  ɨ��", true);
		setLocationRelativeTo(jf);
		Box box1=Box.createVerticalBox();
		jl1.setIcon(ImgManager.icon);
		jl1.setText("java ɨ���ǻ۵���ս��");
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
