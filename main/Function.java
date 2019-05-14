package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import core.Dictionary;
import core.Splitter;

public class Function 
{
	public static Thread[] initialize()
	{
		Thread[] th=new Thread[Runtime.getRuntime().availableProcessors()];
		return th;
	}
	public static void read(String[] input, BufferedReader in) throws IOException
	{
		//System.out.println(input.length);
		for(int a=0;a<input.length;a++)
		{
			String temp=in.readLine();
			if(temp==null)
				break;
			input[a]=temp;
			//System.out.println(temp);
			//Main.countLine++;
		}
	}
	public static void write(String[] arr, PrintWriter out)
	{
		for(int a=0;a<arr.length;a++)
		{
			if(arr[a]!=null)
				out.write(arr[a]+"\r\n");
			else
				break;
		}
	}
	public static void prepareSplitter(String[] input, Splitter[] splitter, Dictionary dictionary)
	{
		for(int a=0;a<input.length;a++)
		{
			splitter[a]=new Splitter(input[a], dictionary);
		}
	}
	public static void prepareThread(Splitter[] splitter, Thread[] th)
	{
		for(int a=0;a<th.length;a++)
		{
			th[a]=new Thread(splitter[a]);
		}
	}
	public static void start(Thread[] th)
	{
		for(int a=0;a<th.length;a++)
		{
			th[a].start();
		}
	}
	public static boolean isFinished(Thread[] th)
	{
		for(int a=0;a<th.length;a++)
		{
			if(th[a].isAlive())
				return false;
		}
		return true;
	}
}
