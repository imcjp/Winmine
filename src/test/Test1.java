package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import Mine.Hero;
import Tools.AESUtils;

public class Test1 {
	public static void main(String[] args) throws Exception {

		File file=new File("hero.txt");
		if (file.exists()) {
			Scanner cin=new Scanner(file);
			String encStr=cin.nextLine();
			cin.close();
			String str=AESUtils.Decrypt(encStr,"D3!43q(^dDewWP");
			if(!str.equals("√‹¬Î¥ÌŒÛ")) {
//				List<List<JSONObject>> heroList=new ArrayList<List<JSONObject>>();
//				heroList=JSON.parseObject(str,heroList.getClass());
//				System.out.println( heroList.get(0).get(0).get("name"));
//				Hero hr=new Hero((String) heroList.get(0).get(0).get("name"),((Integer)heroList.get(0).get(0).get("time")));
//				System.out.println(hr);
			}
		}
		List<List<Hero>> heroList1=new ArrayList<List<Hero>>();
		List<Hero> ls=null;
		ls=new ArrayList();
		ls.add(new Hero("ab", 3));
		ls.add(new Hero("∞¢ÀπµŸ∑“ab", 7));
		ls.add(new Hero("∞¢ÀπµŸ11", 4));
		
		heroList1.add(ls);
		ls=new ArrayList();
		ls.add(new Hero("a2b", 31));
		ls.add(new Hero("∞¢ÀπµŸ∑“ab", 72));
		ls.add(new Hero("∞¢ÀπµŸ11", 43));
		
		heroList1.add(ls);
		ls=new ArrayList();
		ls.add(new Hero("aasdb", 323));
		ls.add(new Hero("∞¢ÀπµŸ∑“ab", 733));
		ls.add(new Hero("∞¢ÀπµŸ11", 422));
		heroList1.add(ls);
		PrintWriter pw=new PrintWriter(file);
		String str1=JSON.toJSONString(heroList1);
		String encStr1=AESUtils.Encrypt(str1,"D3!43q(^dDewWP");
		pw.print(encStr1);
		pw.close();
	}
}
