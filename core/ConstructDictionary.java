package core;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructDictionary 
{
	public static void main(String[] args) throws IOException 
	{
		File file=new File("D:\\Files\\现代汉语词典（第五版）全文.txt");
		BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		List<String> list=new ArrayList<String>();
		Map<Character, WordTree> map=new HashMap<Character, WordTree>();
		in.read();
		String str=in.readLine();
		while(judge(str)==false)
			str=in.readLine();
		while(str!=null)
		{
			if(judge(str))
				list.add(format(str));
			str=in.readLine();
		}
		System.out.println(list.size());
		for(int a=0;a<list.size();a++)
		{
			String t=list.get(a);
			if(t.length()>0)
			{
				WordTree temp=map.get(list.get(a).charAt(0));
				if(temp==null)
				{
					temp=new WordTree(list.get(a).charAt(0));
					temp.add(list.get(a));
					map.put(list.get(a).charAt(0), temp);
				}
				else
					temp.add(list.get(a));
			}
		}
		in.close();
		Dictionary dictionary=new Dictionary(map);
		String path="./dictionary.ruben";
		ObjectOutputStream out=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
		out.writeObject(dictionary);
		out.close();
	}
	private static boolean judge(String str)
	{
		if(str.length()==0 || str.charAt(0)!='【')
			return false;
		for(int a=1;a<str.length();a++)
		{
			if(str.charAt(a)=='】')
				return true;
		}
		return false;
	}
	private static String format(String str)
	{
		int a=0;
		while(str.charAt(a)!='】')
			a++;
		return str.substring(1, a);
	}
}
