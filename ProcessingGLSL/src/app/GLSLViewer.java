package app;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class GLSLViewer extends JFrame  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JEditorPane viewer;
	JScrollPane scroll;
	GLSL_starter app;

	GLSLViewer(GLSL_starter app) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewer = new JEditorPane();
		viewer.setBackground(Color.BLACK);
		viewer.setForeground(Color.WHITE);
		scroll = new JScrollPane(viewer);
		this.app = app;
		add(scroll);
	}
	
	void setText(String text) {
		viewer.setText(text);
	}
	

	
}
