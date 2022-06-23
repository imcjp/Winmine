package View;

import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Mine.Mines;
import Ops.MineMouseOp;
import Tools.ImgManager;

public class LeiQu extends JPanel{
	private JLabel lei[][]=new JLabel[9][9];
	public int x,y,count;
	private Mines mine=new Mines();
	public LeiQu() {
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		setLeiSize(9,9,10);
		
	}
	public void setLeiSize(int x,int y,int count){
		this.x=x;
		this.y=y;
		this.count =count;
		resetLeiSize();
	}
	public void resetLeiSize(){
		getMine().reset(x, y, count);
		setLei(new JLabel[x][y]);
		this.removeAll();
		for (int i = 0; i < getLei().length; i++) {
			for (int j = 0; j < getLei()[0].length; j++) {
				getLei()[i][j]=new JLabel(ImgManager.flag[getMine().getMask()[i][j]]);
			}
		}
		this.setLayout(new GridLayout(x,y));
		for (int i = 0; i < getLei().length; i++) {
			for (int j = 0; j < getLei()[0].length; j++) {
				this.add(getLei()[i][j]);
			}
		}
		MainF.mainF.hp.setNum(count, MainF.mainF.hp.getMineCount());
		MainF.mainF.hp.setNum(0, MainF.mainF.hp.getTime());
		MainF.mainF.getTime().stop();
	}
	public void addOps() {
		for (int i = 0; i < getLei().length; i++) {
			for (int j = 0; j < getLei()[0].length; j++) {
				getLei()[i][j].addMouseListener(new MineMouseOp(i,j));
			}
		}
	}
	public void setLei(JLabel lei[][]) {
		this.lei = lei;
	}
	public JLabel[][] getLei() {
		return lei;
	}
	public void setMine(Mines mine) {
		this.mine = mine;
	}
	public Mines getMine() {
		return mine;
	}
}
