package Ops;

import java.awt.event.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.alibaba.fastjson.JSON;

import Mine.Hero;
import Mine.Mines;
import Tools.ImgManager;
import View.HeadP;
import View.HeroD;
import View.LeiQu;
import View.MainF;

public class MineMouseOp implements MouseListener {
	static int doublesta = 0;
	static int fx=0, fy=0;
	static boolean isPress=false;
	int x, y;
	LeiQu lq;
	HeadP hp;
	Mines mine;

	public MineMouseOp(int x, int y) {
		// TODO Auto-generated constructor stub
		this.lq = MainF.mainF.getLq();
		this.hp = MainF.mainF.getHp();
		this.mine = MainF.mainF.getLq().getMine();
		this.x = x;
		this.y = y;
	}

	boolean isBound(int x, int y) {
		return x >= 0 && x < lq.x && y >= 0 && y < lq.y;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	boolean isRespond(){
		return !(mine.win == mine.PLAYING);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isRespond()||!isPress) {
			return;
		}
		fx = x;
		fy = y;
		if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK
				+ InputEvent.BUTTON3_DOWN_MASK) {
			doublesta = 1;
			for (int i = 0; i < Mines.dx.length; i++) {
				int xx = fx + Mines.dx[i];
				int yy = fy + Mines.dy[i];
				if (!isBound(xx, yy)) {
					continue;
				}
				JLabel jl = lq.getLei()[xx][yy];
				if (ImgManager.flag[0] == (ImageIcon) jl.getIcon()) {
					jl.setIcon(ImgManager.flagDown[0]);
				} else if (ImgManager.flag[3] == (ImageIcon) jl.getIcon()) {
					jl.setIcon(ImgManager.flagDown[1]);
				}
			}
			JLabel jl = lq.getLei()[fx][fy];
			if (ImgManager.flag[0] == (ImageIcon) jl.getIcon()) {
				jl.setIcon(ImgManager.flagDown[0]);
			} else if (ImgManager.flag[3] == (ImageIcon) jl.getIcon()) {
				jl.setIcon(ImgManager.flagDown[1]);
			}
		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			JLabel jl = lq.getLei()[fx][fy];
			if (ImgManager.flag[0] == (ImageIcon) jl.getIcon()) {
				jl.setIcon(ImgManager.flagDown[0]);
			}

		}
		JLabel jl = lq.getLei()[fx][fy];
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isRespond()||!isPress) {
			return;
		}
		if (doublesta == 1) {
			doublesta = 0;
			for (int i = 0; i < Mines.dx.length; i++) {
				int xx = fx + Mines.dx[i];
				int yy = fy + Mines.dy[i];
				if (!isBound(xx, yy)) {
					continue;
				}
				JLabel jl = lq.getLei()[xx][yy];
				if (ImgManager.flagDown[0] == (ImageIcon) jl.getIcon()) {
					jl.setIcon(ImgManager.flag[0]);
				} else if (ImgManager.flagDown[1] == (ImageIcon) jl.getIcon()) {
					jl.setIcon(ImgManager.flag[3]);
				}
			}
			JLabel jl = lq.getLei()[fx][fy];
			if (ImgManager.flagDown[0] == (ImageIcon) jl.getIcon()) {
				jl.setIcon(ImgManager.flag[0]);
			} else if (ImgManager.flagDown[1] == (ImageIcon) jl.getIcon()) {
				jl.setIcon(ImgManager.flag[3]);
			}
		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			JLabel jl = lq.getLei()[fx][fy];
			if (ImgManager.flagDown[0] == (ImageIcon) jl.getIcon()) {
				jl.setIcon(ImgManager.flag[0]);
			}
		}
		fx = fy = -1;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isRespond()) {
			return;
		}
		hp.setFace(2);
		fx=x;fy=y;
		if (!isBound(fx, fy)) {
			return;
		}
		if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK
				+ InputEvent.BUTTON3_DOWN_MASK) {
			doublesta = 1;
			isPress=true;
			for (int i = 0; i < Mines.dx.length; i++) {
				int xx = fx + Mines.dx[i];
				int yy = fy + Mines.dy[i];
				if (!isBound(xx, yy)) {
					continue;
				}
				JLabel jl = lq.getLei()[xx][yy];
				if (ImgManager.flag[0] == (ImageIcon) jl.getIcon()) {
					jl.setIcon(ImgManager.flagDown[0]);
				} else if (ImgManager.flag[3] == (ImageIcon) jl.getIcon()) {
					jl.setIcon(ImgManager.flagDown[1]);
				}
			}
			JLabel jl = lq.getLei()[fx][fy];
			if (ImgManager.flag[0] == (ImageIcon) jl.getIcon()) {
				jl.setIcon(ImgManager.flagDown[0]);
			} else if (ImgManager.flag[3] == (ImageIcon) jl.getIcon()) {
				jl.setIcon(ImgManager.flagDown[1]);
			}
		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			isPress=true;
			JLabel jl = lq.getLei()[fx][fy];
			if (ImgManager.flag[0] == (ImageIcon) jl.getIcon()) {
				jl.setIcon(ImgManager.flagDown[0]);
			}
		} else if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
			mine.doFlag(fx, fy);
			hp.setNum(mine.getLtag(), hp.getMineCount());
			change(fx, fy);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isRespond()) {
			return;
		}
		hp.setFace(0);
		if (!isBound(fx, fy)) {
			return;
		}
		isPress=false;
		if (doublesta == 1) {
			doublesta = 0;
			for (int i = 0; i < Mines.dx.length; i++) {
				int xx = fx + Mines.dx[i];
				int yy = fy + Mines.dy[i];
				if (!isBound(xx, yy)) {
					continue;
				}
				JLabel jl = lq.getLei()[xx][yy];
				if (ImgManager.flagDown[0] == (ImageIcon) jl.getIcon()) {
					jl.setIcon(ImgManager.flag[0]);
				} else if (ImgManager.flagDown[1] == (ImageIcon) jl.getIcon()) {
					jl.setIcon(ImgManager.flag[3]);
				}
			}
			JLabel jl = lq.getLei()[fx][fy];
			if (ImgManager.flagDown[0] == (ImageIcon) jl.getIcon()) {
				jl.setIcon(ImgManager.flag[0]);
			} else if (ImgManager.flagDown[1] == (ImageIcon) jl.getIcon()) {
				jl.setIcon(ImgManager.flag[3]);
			}
			mine.resetChange();
			int res = mine.open(fx, fy);
			ArrayList<Integer> ari = mine.getChanges();
			afterDo(res, ari);
		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			if (mine.getClickTime()==0) {
				MainF.mainF.getTime().start();
			}
			JLabel jl = lq.getLei()[fx][fy];
			if (ImgManager.flagDown[0] == (ImageIcon) jl.getIcon()) {
				jl.setIcon(ImgManager.flag[0]);
			}
			mine.resetChange();
			int res = mine.doIt(fx, fy);
			ArrayList<Integer> ari = mine.getChanges();
			afterDo(res, ari);
		}
	}
	void writeHero(){
		System.out.println("Mines.grade:"+Mines.grade);
		if (Mines.grade != -1&&mine.win==Mines.WIN) {
			
			List<List<Hero>> heroList= HeroD.readHeroList();
			String name=JOptionPane.showInputDialog("¹§Ï²Äã£¬³É¼¨²»´í¡£ÇëÊäÈëÃû×Ö");
			int newtime=MainF.mainF.hp.getItime();
			Hero hero=new Hero(name, newtime);
			heroList.get(Mines.grade).add(hero);
			Collections.sort(heroList.get(Mines.grade));
			while (heroList.get(Mines.grade).size()>5) {
				heroList.get(Mines.grade).remove(heroList.get(Mines.grade).size()-1);
			}
			HeroD.writeHeroList(heroList);
//			int oldtime;
//			System.out.println(set);
//			if(set.size()==0){
//				oldtime=Integer.MAX_VALUE;
//			}else oldtime=set.last().time;
//			int newtime=MainF.mainF.hp.getItime();
//			System.out.println(oldtime+"   "+newtime);
//			if(oldtime>newtime||set.size()<5){
//				String name=JOptionPane.showInputDialog("¹§Ï²Äã£¬³É¼¨²»´í¡£ÇëÊäÈëÃû×Ö");
//				Hero hero=new Hero(name, newtime);
//				set.add(hero);
//				System.out.println("set.size():"+set.size());
//				if(set.size()>5){
//					set.remove(set.last());
//				}
//				System.out.println("set.size():"+set.size());
//			}
			
//			try {
//				File file=new File("hero.txt");
//				if (file.exists()) {
//					Scanner cin=new Scanner(file);
//					String str=cin.nextLine();
//					List<List<Hero>> heroList=new ArrayList<List<Hero>>();
//					heroList=JSON.parseObject(str,heroList.getClass());
//				}
//				
////				ObjectInputStream ois=null;
////				SortedSet<Hero> set=null;
////				try {
////					 ois= new ObjectInputStream(
////							new FileInputStream("hero"+Mines.grade));
////					set = (SortedSet<Hero>) ois.readObject();
////				} catch (EOFException e) {
////					// TODO Auto-generated catch block
////					set=new TreeSet<Hero>();
////				}
////				int oldtime;
////				System.out.println(set);
////				if(set.size()==0){
////					oldtime=Integer.MAX_VALUE;
////				}else oldtime=set.last().time;
////				int newtime=MainF.mainF.hp.getItime();
////				System.out.println(oldtime+"   "+newtime);
////				if(oldtime>newtime||set.size()<5){
////					String name=JOptionPane.showInputDialog("¹§Ï²Äã£¬³É¼¨²»´í¡£ÇëÊäÈëÃû×Ö");
////					Hero hero=new Hero(name, newtime);
////					set.add(hero);
////					System.out.println("set.size():"+set.size());
////					if(set.size()>5){
////						set.remove(set.last());
////					}
////					System.out.println("set.size():"+set.size());
////				}
////				if(ois!=null)ois.close();
////				System.out.println(JSON.toJSONString(set));
////				ObjectOutputStream oos = new ObjectOutputStream(
////						new FileOutputStream("hero"+Mines.grade));
////				oos.writeObject(set);
////				oos.close();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
		}
	}
	public void afterDo(int res,ArrayList<Integer> ari){
		if (res != mine.PLAYING) {
			MainF.mainF.getTime().stop();
			mine.over();
			if(res==Mines.WIN){
				hp.setFace(4);
			}else if (res==Mines.LOST) {
				hp.setFace(3);
			}
			change();
			writeHero();
		} else
			change(ari);
	}
	void change(int xx, int yy) {
		JLabel jl = lq.getLei()[xx][yy];
		int fg = mine.getMask()[xx][yy];
		if (fg == Mines.OPENED) {
			jl.setIcon(ImgManager.count[mine.getMine()[xx][yy]]);
		} else {
			jl.setIcon(ImgManager.flag[fg]);
		}
	}

	void change(ArrayList<Integer> arr) {
		for (int i = 0; i < arr.size(); i += 2) {
			int xx = arr.get(i);
			int yy = arr.get(i + 1);
			JLabel jl = lq.getLei()[xx][yy];
			int fg = mine.getMask()[xx][yy];
			if (fg == Mines.OPENED) {
				jl.setIcon(ImgManager.count[mine.getMine()[xx][yy]]);
			} else {
				jl.setIcon(ImgManager.flag[fg]);
			}
		}
	}

	void change() {
		for (int i = 0; i < mine.getMine().length; i++) {
			for (int j = 0; j < mine.getMine()[0].length; j++) {
				int xx = i;
				int yy = j;
				JLabel jl = lq.getLei()[xx][yy];
				int fg = mine.getMask()[xx][yy];
				if (fg == Mines.OPENED) {
					jl.setIcon(ImgManager.count[mine.getMine()[xx][yy]]);
				} else {
					jl.setIcon(ImgManager.flag[fg]);
				}
			}
		}
	}
}
