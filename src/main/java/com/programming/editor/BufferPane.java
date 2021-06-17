package com.programming.editor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class BufferPane extends JEditorPane {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	KeyboardInputHandler textHandler = null;

	public BufferPane() {
		super("text/html", "");
		setEditable(true);
		scrollPane = new JScrollPane(this);
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				int caretPosition = getCaretPosition();
				if(caretPosition>1)
					caretPosition--; //Caret position goes like this - 1,2,3,4... very strangely
				System.out.println(""+caretPosition+","+e.getKeyChar());
				switch (e.getKeyCode()) {
				case KeyEvent.VK_DELETE:
					textHandler.handleCharDelete(caretPosition);
					break;
				case KeyEvent.VK_LEFT:
					break;
				case KeyEvent.VK_RIGHT:
					break;
				case KeyEvent.VK_UP:
					break;
				case KeyEvent.VK_DOWN:
					break;
				default:
					textHandler.handleCharInsert(caretPosition, e.getKeyChar());
					break;
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	public JComponent getJComponent() {
		return scrollPane;
	}

	public void render(String txt) {
		setText(txt);
		System.out.println(txt);
	}

	public void setKeyboardInputHandler(KeyboardInputHandler textHandler) {

		this.textHandler = textHandler;
	}
}
