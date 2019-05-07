package core;

public class Splitter implements Runnable
{
	private String source;
	private Dictionary dictionary;
	private StringBuffer result;
	
	public Splitter(String source, Dictionary dictionary)
	{
		this.source=source;
		this.dictionary=dictionary;
		this.result=new StringBuffer("");
	}

	@Override
	public void run() 
	{
		int index=0;
		while(index<source.length())
		{
			WordTree tree=dictionary.getWordTree(source.charAt(index));
			int temp=index;
			if(tree!=null)
				temp=tree.match(source, index);
			this.result.append(source.substring(index, temp+1));
			this.result.append(" ");
			index=temp+1;
		}
	}
	
	public String getResult()
	{
		return this.result.toString();
	}
}
