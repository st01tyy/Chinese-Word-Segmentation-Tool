package core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class WordNode implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char word;
	private Map<Character, WordNode> map;
	
	public WordNode(char word)
	{
		this.word=word;
		this.map=new HashMap<Character, WordNode>();
	}
	
	public WordNode getNextNode(char ch)
	{
		return this.map.get(new Character(ch));
	}
	public void setNextNode(char ch)
	{
		this.map.put(ch, new WordNode(ch));
	}
	public char getWord() 
	{
		return word;
	}
}
