package com.programming.editor;

public interface KeyboardInputHandler {
	public boolean handleCharDelete(int position);
	public boolean handleCharInsert(int position, char c);

}
