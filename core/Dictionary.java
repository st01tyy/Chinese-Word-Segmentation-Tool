package core;

import java.io.Serializable;
import java.util.Map;

public class Dictionary implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Character,  WordTree> dictionary;
	
	public Dictionary(Map<Character, WordTree> dictionary)
	{
		this.dictionary=dictionary;
	}
	
	public WordTree getWordTree(char ch)
	{
		return this.dictionary.get(ch);
	}
}
