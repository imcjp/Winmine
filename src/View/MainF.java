package View;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import Mine.Hero;
import Mine.Mines;
import Tools.ImgManager;

public class MainF extends JFrame {
	private JMenuBar jmb1;
	private JMenu jm1;
	private JMenu jm2;
	private JMenuItem jmi1;
	private JMenuItem jmi2;
	private JMenuItem jmi3;
	private JMenuItem jmi4;
	private JMenuItem jmi5;
	private JMenuItem jmi6;
	private JPanel jp1;
	public HeadP hp;
	private Box box1;
	public LeiQu lq;
	private Box box2;
	private JMenuItem jmi7;
	public static MainF mainF;
	private Timer time;
	private JMenu jm3;
	private JMenuItem jmi8;
	private JMenuItem jmi9;
	private JMenuItem jmi10;
	public MainF() {
		// TODO Auto-generated constructor stub
		timeOps();
	}

	void init() {
		jmb1 = new JMenuBar();
		jm1 = new JMenu("游戏(G)");
		jm2 = new JMenu("帮助(H)");
		jmi1 = new JMenuItem("开始");
		jmi2 = new JMenuItem("退出");
		jmi3 = new JMenuItem("初级");
		jmi4 = new JMenuItem("中级");
		jmi5 = new JMenuItem("高级");
		jmi6 = new JMenuItem("关于");
		jm3=new JMenu("扫雷英雄榜");
		jmi7 = new JMenuItem("自定义");
		jmi8 = new JMenuItem("初级英雄榜");
		jmi9 = new JMenuItem("中级英雄榜");
		jmi10 = new JMenuItem("高级英雄榜");
		jm1.setMnemonic('G');
		jm2.setMnemonic('H');
		jm1.add(jmi1);
		jm1.addSeparator();
		jm1.add(jmi3);
		jm1.add(jmi4);
		jm1.add(jmi5);
		jm1.add(jmi7);
		jm1.addSeparator();
		jm1.add(jm3);
		jm3.add(jmi8);
		jm3.add(jmi9);
		jm3.add(jmi10);
		jm1.add(jmi2);
		jm2.add(jmi6);
		jmb1.add(jm1);
		jmb1.add(jm2);
		this.setJMenuBar(jmb1);
		jp1 = (JPanel) this.getContentPane();
		setHp(new HeadP());
		box1 = Box.createVerticalBox();
		box1.add(getHp());
		setLq(new LeiQu());
		Border border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		lq.addOps();
		box2 = Box.createHorizontalBox();
		box2.setBorder(border1);
		box2.add(getLq());
		box1.add(Box.createVerticalStrut(10));
		box1.add(box2);
		this.add(box1);
		Border border2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		jp1.setBorder(border2);
		menuOp();
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				int b = JOptionPane.showConfirmDialog(null, "你确定退出吗", "退出",
						JOptionPane.YES_NO_OPTION);
				if (b == 0) {
					System.exit(0);
				}
			}
		});
		this.setTitle("扫雷");
		this.setIconImage(ImgManager.icon.getImage());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	void timeOps() {
		setTime(new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (getHp().getItime() < 999)
					getHp().setNum(getHp().getItime() + 1, getHp().getTime());
			}
		}));
	}

	void menuOp() {
		jmi1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainF.mainF.hp.setFace(0);
				MainF.mainF.lq.resetLeiSize();
				MainF.mainF.lq.addOps();
				MainF.mainF.validate();
			}
		});
		jmi2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int b = JOptionPane.showConfirmDialog(null, "你确定退出吗", "退出",
						JOptionPane.YES_NO_OPTION);
				if (b == 0) {
					System.exit(0);
				}
			}
		});
		jmi3.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * 初级
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainF.mainF.hp.setFace(0);
				getLq().setLeiSize(9, 9,10);
				Mines.grade=0;
				getLq().addOps();
				MainF.this.pack();
				MainF.this.validate();
			}
		});
		jmi4.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * 中级
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainF.mainF.hp.setFace(0);
				getLq().setLeiSize(16, 16,40);
				Mines.grade=1;
				getLq().addOps();
				MainF.this.pack();
				MainF.this.validate();
			}
		});
		jmi5.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * 高级
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainF.mainF.hp.setFace(0);
				getLq().setLeiSize(25, 25,100);
				Mines.grade=2;
				getLq().addOps();
				MainF.this.pack();
				MainF.this.validate();
			}
		});
		jmi6.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * 自定义
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HelpD ssf=new HelpD(MainF.this);
			}
		});
		jmi7.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * 关于
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainF.mainF.hp.setFace(0);
				Mines.grade=-1;
				System.out.println(Mines.grade);
				SetSizeD ssf=new SetSizeD(MainF.this, getLq());
			}
		});
		jmi8.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * 初级英雄榜
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HeroD(MainF.this, 0);
			}
		});
		jmi9.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * 中级英雄榜
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HeroD(MainF.this, 1);
			}
		});
		jmi10.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * 高级英雄榜
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HeroD(MainF.this, 2);
			}
		});
	}
	public static void main(String[] args) {
		mainF=new MainF();
		mainF.init();
	}

	public void setLq(LeiQu lq) {
		this.lq = lq;
	}

	public LeiQu getLq() {
		return lq;
	}

	public void setHp(HeadP hp) {
		this.hp = hp;
	}

	public HeadP getHp() {
		return hp;
	}

	public void setTime(Timer time) {
		this.time = time;
	}

	public Timer getTime() {
		return time;
	}
}
