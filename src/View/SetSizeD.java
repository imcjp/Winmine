package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SetSizeD extends JDialog {
	LeiQu lq;
	JFrame jf;
	JLabel jl1 = new JLabel("设置行数：");
	JLabel jl2 = new JLabel("设置列数：");
	JLabel jl3 = new JLabel("设置雷数：");
	JTextField jt1 = new JTextField();
	JTextField jt2 = new JTextField();
	JTextField jt3 = new JTextField();
	JButton jb1 = new JButton("确定");
	JButton jb2 = new JButton("取消");

	public SetSizeD(JFrame jf, LeiQu lq) {
		// TODO Auto-generated constructor stub
		super(jf, "自定义雷区", true);
		setLocationRelativeTo(jf);
		this.lq=lq;
		this.jf=jf;
		this.setLayout(new GridLayout(4, 2));
		jb1.addActionListener(new ActionListener() {

			public boolean isBound(int d1,int d2,int d3){
				if(!(d1>=9&&d1<=30)){
					return false;
				}
				if(!(d2>=9&&d2<=30)){
					return false;
				}
				if(!(d3>=10&&d3<=d1*d2*4/5)){
					return false;
				}
				return true;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String n1 = jt1.getText();
				String n2 = jt2.getText();
				String n3 = jt3.getText();
				n1=n1.trim();
				n2=n2.trim();
				n3=n3.trim();
				if (!(n1.matches("\\d+") && n2.matches("\\d+") && n3
						.matches("\\d+"))) {
					JOptionPane.showMessageDialog(null, "输入错误，请输入数字", "错误",
							JOptionPane.ERROR_MESSAGE);
				}else{
					int d1,d2,d3;
					d1=Integer.parseInt(n1);
					d2=Integer.parseInt(n2);
					d3=Integer.parseInt(n3);
					if(!isBound(d1, d2, d3)){
						JOptionPane.showMessageDialog(null, "输入错误，请正确范围", "错误",
								JOptionPane.ERROR_MESSAGE);
					}else{
						SetSizeD.this.lq.setLeiSize(d1, d2,d3);
						SetSizeD.this.lq.addOps();
						SetSizeD.this.jf.pack();
						SetSizeD.this.jf.validate();
						SetSizeD.this.dispose();
					}
				}
			}
		});
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SetSizeD.this.dispose();
			}
		});
		this.add(jl1);
		this.add(jt1);
		this.add(jl2);
		this.add(jt2);
		this.add(jl3);
		this.add(jt3);
		this.add(jb1);
		this.add(jb2);
		Border border1 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		((JPanel) this.getContentPane()).setBorder(border1);
		this.pack();
		this.setVisible(true);

	}

}
