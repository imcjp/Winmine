package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import Mine.Hero;
import Tools.AESUtils;
import Tools.ImgManager;

public class HeroD extends JDialog {

	List<List<Hero>> heroList = null;
	JTextArea jta1=new JTextArea();
	JButton jb1=new JButton("÷ÿ÷√");
	JButton jb2=new JButton("»∑∂®");
	int grade=0;
	public HeroD(JFrame jf,int grade) {
		// TODO Auto-generated constructor stub
		super(jf, "…®¿◊”¢–€∞Ê", true);
		this.grade=grade;
//			ObjectInputStream ois = null;
		heroList=HeroD.readHeroList();
		String s;
		List<Hero> ls=heroList.get(grade);
		int p=0;
		for (int i=0;i<ls.size();i++) {
			Hero hr=ls.get(i);
			if (p>0) {
				jta1.append("\n");
			}
			jta1.append(hr.toString());
			p++;
		}
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jta1.setText("");
				HeroD.this.pack();
				HeroD.this.validate();
				heroList.set(HeroD.this.grade, new ArrayList<Hero>());
				HeroD.writeHeroList(heroList);
			}
		});
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HeroD.this.dispose();
			}
		});
		this.add(jta1);
		Box box1=Box.createHorizontalBox();
		Border border1 = BorderFactory.createEmptyBorder(10, 10, 0, 10);
		Border border2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		box1.setBorder(border1);
		box1.add(Box.createHorizontalGlue());
		box1.add(jb1);
		box1.add(Box.createHorizontalGlue());
		box1.add(jb2);
		box1.add(Box.createHorizontalGlue());
		((JPanel)this.getContentPane()).setBorder(border2);
		this.add(box1,BorderLayout.SOUTH);
		this.setLocationRelativeTo(jf);
		this.pack();
		this.setVisible(true);
	}
	public static List<List<Hero>> readHeroList() {
		Scanner cin=null;
		List<List<Hero>> heroList=new ArrayList<List<Hero>>();
		for (int i=0;i<3;i++) {
			heroList.add(new ArrayList<Hero>());		
		}
		try {
			File file=new File("hero.txt");
			if (file.exists()) {
				List<List<JSONObject>> tmp=new ArrayList<List<JSONObject>>();
				cin=new Scanner(file);
				String encStr=cin.nextLine();
				cin.close();
				String str=AESUtils.Decrypt(encStr,"D3!43q(^dDewWP");
				if(!str.equals("√‹¬Î¥ÌŒÛ")) {
					tmp=JSON.parseObject(str,tmp.getClass());
					for (int i=0;i<3;i++) {
						List<JSONObject> ls=tmp.get(i);
						for(int j=0;j<ls.size();j++) {
							heroList.get(i).add(new Hero(ls.get(j).getString("name"), ls.get(j).getIntValue("time")));
						}	
					}
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			if(cin!=null) cin.close();
		}
		return heroList;
	}
	public static void writeHeroList(List<List<Hero>> heroList) {
		File file=new File("hero.txt");
		PrintWriter pw=null;
		try {
			pw = new PrintWriter(file);
			String str1=JSON.toJSONString(heroList);
			String encStr1=AESUtils.Encrypt(str1,"D3!43q(^dDewWP");
			pw.print(encStr1);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (pw!=null) {
				pw.close();
			}
		}
	}
}
