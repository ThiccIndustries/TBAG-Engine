package tbag.io;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Class for creation of a custom window to be used instead of the native command line.
 * Allows for colors and font scaling.
 * @see <a href=https://www.youtube.com/channel/UCKwypsRGgHj1mrlQXhLXsvg>Vallentin's Java Tutorials</a>
 * @author Trevor Skupien
 */
public class Window {
	public JFrame frame;
	public JTextPane terminalDisplay;
	public JTextPane imageDisplay;	
	public JTextField input;
	public JScrollPane scrollPane;
	private int scale = 1;
	public StyledDocument document;
	private Semaphore semaphore = new Semaphore(0);
	private String inputText;
	private JScrollBar vertical;
	private Style style;
	
	/**
	 * Constructor creating and opening the window
	 * @param sizeX width of the window in pixels
	 * @param sizeY height of the window in pixels
	 */
	public Window(int sizeX, int sizeY) {
		scale = (sizeX / 800);
		frame = new JFrame();
		frame.setTitle("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		imageDisplay = new JTextPane();
		imageDisplay.setEditable(false);
		imageDisplay.setOpaque(false);
		imageDisplay.setForeground(Color.WHITE);
		imageDisplay.setFont(new Font("Courier New", Font.PLAIN, (12 * scale)));
		
		terminalDisplay = new JTextPane();
		terminalDisplay.setEditable(false);
		terminalDisplay.setOpaque(false);
		terminalDisplay.setForeground(Color.WHITE);
		terminalDisplay.setFont(new Font("Courier New", Font.PLAIN, (12 * scale)));
		
		document = terminalDisplay.getStyledDocument();
		style = terminalDisplay.addStyle("Style", null);
		
		input = new JTextField();
		input.setOpaque(false);
		input.setForeground(Color.WHITE);
		input.setCaretColor(Color.WHITE);
		input.setFont(new Font("Courier New", Font.PLAIN, (12 * scale)));
		
		scrollPane = new JScrollPane(terminalDisplay);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.add(input, BorderLayout.SOUTH);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.add(imageDisplay, BorderLayout.NORTH);
		frame.setSize(sizeX, sizeY);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		vertical = scrollPane.getVerticalScrollBar();
		
		input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = input.getText();
				if(text.length() > 0) {
					inputText = text;
					input.setText("");
					semaphore.release();
					drop();
				}
			}
		});
		
		input.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
	}
	
	/**
	 * Prints an object to the window.
	 * @param o Object to print.
	 */
	public void append(Object o) {
		String stringToAppend = o.toString();
		StyleConstants.setForeground(style, Color.WHITE);
		StyleConstants.setBold(style, false);
		try {
			document.insertString(document.getLength(), stringToAppend, style);
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		drop();
	}
	
	/**
	 * Prints a dialog message to the window.
	 * @param o Error object to print.
	 * @param c Color to print in.
	 */
	public void appendColor(Object o, Color c) {
		String stringToAppend = o.toString();
		StyleConstants.setForeground(style, c);
		StyleConstants.setBold(style, false);
		try {
			document.insertString(document.getLength(), stringToAppend, style);
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		drop();
	}
	
	/**
	 * Prints an error to the window.
	 * @param o Error object to print.
	 */
	public void appendError(Object o) {
		String stringToAppend = o.toString();
		StyleConstants.setForeground(style, Color.RED);
		StyleConstants.setBold(style, true);
		try {
			document.insertString(document.getLength(), stringToAppend, style);
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		drop();
	}
	
	public void drop() {
		vertical.setValue(vertical.getMaximum());
	}
	
	/**
	 * Waits for an input from the text input
	 * @return String entered by user
	 */
	public String Listen() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputText;
	}

}
