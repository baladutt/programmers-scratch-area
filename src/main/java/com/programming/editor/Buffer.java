package com.programming.editor;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Buffer {
	
	private ArrayList<Word> words = new ArrayList<Word>();
	
	Settings settings = new Settings();

	BufferPane bufferPane;
	
	public void addWord(Word word)
	{
		words.add(word);
		bufferPane.render(toHtml());
	}
	
	public ArrayList<Word> getWords()
	{
		return words;
	}
	
	public Word getWordAt(int position)
	{
		//TODO: implement a better mechanism
		for(Word word: words)
			if(position>=word.startPosition && (word.endPosition == -1 || position<=word.endPosition))
			{
				return word;
			}
		return null;
	}
	
	
	public Buffer(MyEditor editor, BufferPane bufferPane)
	{
		this.bufferPane = bufferPane;
		KeyboardInputHandler textHandler = new KeyboardInputHandler() {
			
			@Override
			public boolean handleCharDelete(int position) {
				Word word  = getWordAt(position);
				int offset = position - word.startPosition;
				//if(position >=0)
				word.deleteCharAt(offset);
				return false;
			}
			@Override
			public boolean handleCharInsert(int position, char c) {
				//Find if there is a word at this position - if not create one
				Word currentWord = getWordAt(position);
				
				if(null==currentWord)
				{
					currentWord = new Word(position, settings);
					addWord(currentWord);
				}
				
					if(settings.getWhitespaceChars().indexOf(c) != -1)
					{
						//currentWord is complete, create new one
						currentWord = new Word(""+c, position, settings);
						addWord(currentWord);
					}else
						currentWord.add(c);//TODO: Put at position
				
				return true;
			}
		};
		bufferPane.setKeyboardInputHandler(textHandler);
	}

	public Settings getSettings() {
		return settings;
	}

	public void write(FileWriter writer) {
		
	}

	public void read(FileReader reader, Object object) {
		// TODO Auto-generated method stub
		
	}
	
	public String toHtml()
	{
		StringBuffer html = new StringBuffer("<html><body>");
		for(Word word: words)
			html.append(word.toHtml());
		html.append("</body></html>");
		return html.toString();
	}

}
