package core;

import java.io.Serializable;

public class WordTree implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WordNode root;
	@SuppressWarnings("unused")
	private char word;
	
	public WordTree(char word)
	{
		this.word=word;
		this.root=new WordNode(word);
	}
	
	public int match(String str, int startIndex)
	{
		int index=startIndex;
		WordNode pointer=this.root;
		while(index+1<str.length())
		{
			WordNode next=pointer.getNextNode(str.charAt(index+1));
			if(next!=null)
			{
				pointer=next;
				index++;
			}
			else
				break;
		}
		return index;
	}
	public void add(String str)
	{
		int index=0;
		WordNode pointer=this.root;
		while(index+1<str.length())
		{
			WordNode next=pointer.getNextNode(str.charAt(index+1));
			if(next!=null)
				pointer=next;
			else
			{
				pointer.setNextNode(str.charAt(index+1));
				pointer=pointer.getNextNode(str.charAt(index+1));
			}
			index++;
		}
	}
}
