package main.java.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.springframework.stereotype.Component;

@Component
public class ColorConsole {

//	public static void main(String[] args) {
//		new ColorConsole();
//	}

	private JFrame frame;
	private JTextPane console;
	private JTextField input;
	private JScrollPane scrollPane;
	private String response;
	private boolean hasResponded;

	private StyledDocument document;
	


	public ColorConsole() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		frame = new JFrame("Color Console");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		console = new JTextPane();
		console.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		console.setEditable(false);
		console.setFont(new Font("Courier New", Font.PLAIN, 18));
		console.setOpaque(false);
		console.setForeground((Color.green));
		console.setCaretColor(Color.green);
		

		document = console.getStyledDocument();

		input = new JTextField();
		input.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		input.setEditable(true);
		input.setFont(new Font("Courier New", Font.PLAIN, 18));
		input.setOpaque(false);
		input.setForeground((Color.ORANGE));
		input.setCaretColor(Color.ORANGE);

		scrollPane = new JScrollPane(console);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setVisible(true);

		frame.add(input, BorderLayout.SOUTH);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane().setBackground(new Color(50, 50, 50));

		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				response = input.getText();
				hasResponded = true;

			}
		});

	}
	
	public void print(String message, Color color) {
		// display main text

		Style style = console.addStyle("Style", null);
		StyleConstants.setForeground(style, color);

		try {
			document.insertString(document.getLength(),"\n"+ message, style);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		scrollBotton();
	}
	

	public String printResponse(String message, String defaultResponse, Color color) {
		// display main text

		Style style = console.addStyle("Style", null);
		StyleConstants.setForeground(style, color);

		try {
			document.insertString(document.getLength(),"\n"+ message, style);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}

		scrollBotton();

		// set response default text and highlight
		response = defaultResponse;
		if (response == null)
			response = ">";
	

		input.setText(response);
		input.requestFocusInWindow();
		input.selectAll();

		// loop that checks for a response with a 30 second timeout
		hasResponded = false;
		int timeout = 120;  // 120 x 250 milliseconds  = 30 sec
		while (!hasResponded && (timeout > 0)) {
			timeout--;
			try {
				Thread.sleep(250);
			} catch (InterruptedException e1) {
			}
		}
		if (timeout == 0)
			response = "Timeout!";

		return response;

	}
	
	public void clear() {
	
		try {
			document.remove(0,document.getLength());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	public void scrollBotton() {
		console.setCaretPosition(console.getDocument().getLength());
	}

	public void exit() {
		frame.setVisible(false);
		frame.dispose();
	}

}
