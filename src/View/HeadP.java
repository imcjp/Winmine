package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import Tools.ImgManager;

public class HeadP extends JPanel{
	private JLabel mineCount[]=new JLabel[3];
	private JLabel time[]=new JLabel[3];
	JLabel face=null;
	private int imc;
	private int itime;
	public HeadP() {
		// TODO Auto-generated constructor stub
		init();
		
	}
	void init(){
		this.setLayout(new BorderLayout());
		for (int i = 0; i < getMineCount().length; i++) {
			getMineCount()[i]=new JLabel(ImgManager.ds[0]);
			getTime()[i]=new JLabel(ImgManager.ds[0]);
		}
		face=new JLabel(ImgManager.faces[0]);
		face.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				setFace(0);
				MainF.mainF.lq.resetLeiSize();
				MainF.mainF.lq.addOps();
				MainF.mainF.validate();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				setFace(1);
			}
			
		});
		Border border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		Box box=Box.createHorizontalBox();
		Box box1=Box.createHorizontalBox();
		Box box2=Box.createHorizontalBox();
		for (int i = 0; i < getMineCount().length; i++) {
			box1.add(getMineCount()[i]);
		}
		box1.setBorder(border1);
		box.add(box1);
		box.add(Box.createHorizontalGlue());
		box.add(face);
		box.add(Box.createHorizontalGlue());
		for (int i = 0; i < getTime().length; i++) {
			box2.add(getTime()[i]);
		}
		box2.setBorder(border1);
		box.add(box2);
		this.add(box);
		Border border2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border border = BorderFactory.createCompoundBorder(border1, border2);
		this.setBorder(border);
	}
	public void setFace(int i){
		face.setIcon(ImgManager.faces[i]);
	}
	public void  setNum(int n,JLabel dls[]) {
		if(dls==getTime()){
			itime=(n);
		}
		if(dls==getMineCount()){
			imc=(n);
		}
		int a[]=new int [3];
		boolean flag=false;
		if(n<0){
			flag=true;
			n=-n;
		}
		int i;
		for (i = 0; i < a.length&&n>0; i++) {
			a[i]=n%10;
			n/=10;
		}
		if(flag&&i<a.length){
			a[i]=10;
		}
		for (i = 0; i < a.length; i++) {
			dls[i].setIcon(ImgManager.ds[a[2-i]]);
		}
	}
	public int getItime() {
		return itime;
	}
	public int getImc() {
		return imc;
	}
	public void setMineCount(JLabel mineCount[]) {
		this.mineCount = mineCount;
	}
	public JLabel[] getMineCount() {
		return mineCount;
	}
	public void setTime(JLabel time[]) {
		this.time = time;
	}
	public JLabel[] getTime() {
		return time;
	}
}
