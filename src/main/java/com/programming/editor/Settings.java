package com.programming.editor;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;

/**
 * This class would be made settable by a .rc file or options in menu
 * 
 * @author bdutt
 *
 */
public class Settings {
	private String whitespaceChars = " "+'\t'+'\r'+'\n';
	private static HashSet<String> keyWordList = new HashSet<String>();
	 
	public Settings()
	{
		//Just for test
		keyWordList.add("abc");
	}
	public Color getKeywordColor()
	{
		return new Color(255,0,0); 
	}

	public String getWhitespaceChars() {
		
		return whitespaceChars;
	}
	
	public Collection<String> getKeyWordList()
	{
		return keyWordList;
	}

}
