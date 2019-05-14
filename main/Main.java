package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import core.Dictionary;
import core.MultiThreadSplitter;

public class Main 
{
	/*
	 * 单线程处理行数
	 */
	final static int line=500;

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException 
	{
		Scanner s=new Scanner(System.in);
		
		ObjectInputStream obj = IO.getObjectInputStream();
		if(obj==null)
		{
			s.close();
			return;
		}
		Dictionary dictionary=(Dictionary)obj.readObject();
		
		System.out.println("输入待分词语料文件的绝对路径（文件应为UTF-8编码）：");
		String source=s.next();
		BufferedReader in = IO.getReader(source);
		if(in==null)
		{
			obj.close();
			s.close();
			return;
		}
		in.read();
		
		System.out.println("输入分词结果文件的输出路径：");
		String destination=s.next();
		PrintWriter out = IO.getWriter(destination);
		if(out==null)
		{
			obj.close();
			in.close();
			s.close();
			return;
		}
		
		long time1=System.currentTimeMillis();
		Thread[] th=Function.initialize();
		String[][] arr=new String[th.length][line];
		MultiThreadSplitter[] splitter=new MultiThreadSplitter[th.length];
		for(int a=0;a<arr.length;a++)
		{
			Function.read(arr[a], in);
		}
		
		while(arr[0][0]!=null)
		{
			for(int a=0;a<th.length;a++)
			{
				splitter[a]=new MultiThreadSplitter(arr[a], dictionary);
				th[a]=new Thread(splitter[a]);
			}
			for(int a=0;a<th.length;a++)
			{
				th[a].start();
			}
			for(int a=0;a<th.length;a++)
			{
				th[a].join();
				//if(a==0)
					//time3=System.currentTimeMillis();
				Function.write(splitter[a].getResult(), out);
			}
			arr=new String[th.length][line];
			for(int a=0;a<arr.length;a++)
			{
				Function.read(arr[a], in);
			}
		}
		long time2=System.currentTimeMillis();
		
		System.out.println("运行时间："+(time2-time1)+"ms");
		in.close();
		out.close();
		obj.close();
		s.close();
	}
}
