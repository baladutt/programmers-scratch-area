package com.programming.editor;

/**
 * This class could be split into two - view and model, when it gets complex
 * @author bdutt
 *
 */
public class Word {
	String word ="";
	boolean bIsKeyWord = false;
	
	int startPosition;
	int endPosition;

	Settings settings;

	public Word(String word, int startPosition, Settings settings) {
		this.settings = settings;
		this.word = word;
		if (settings.getKeyWordList().contains(word))
			bIsKeyWord = true;
		 this.startPosition = startPosition;
		 this.endPosition = this.startPosition + word.length();
	}
	
	
	public Word(int startPosition, Settings settings) {
		this.settings = settings;
		this.endPosition = this.startPosition = startPosition;
	}

	public void setEndPosition(int endPosition)
	{
		this.endPosition = endPosition;
	}
	

	public void add(char c) {
		this.word+=c;
		if (settings.getKeyWordList().contains(word))
			bIsKeyWord = true;
		else
			bIsKeyWord = false;
		endPosition++;
	}

	public void deleteCharAt(int offset) {
		if(0==offset)
			word = word.substring(1);
		else if(word.length()-1==offset)
			word = word.substring(0, offset);
		else{
			word = word.substring(0, offset)+word.substring(offset+1);	
		}
		
	}

	public String toHtml() {
		String txt = word.replaceAll(" ", "&nbsp;");
		//TODO: do more replacements
		if(bIsKeyWord)
			return "<font color='red'>"+txt+"</font>";
		return txt;
	}

}
