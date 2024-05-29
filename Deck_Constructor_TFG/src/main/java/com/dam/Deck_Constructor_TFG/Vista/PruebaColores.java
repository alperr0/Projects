package com.dam.Deck_Constructor_TFG.Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;

public class PruebaColores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaColores frame = new PruebaColores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PruebaColores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 221, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 500));
		panel.setBounds(new Rectangle(0, 0, 400, 500));
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel colorFondo = new JLabel("New label");
		colorFondo.setBackground(new Color(41, 41, 41));
		panel.add(colorFondo);
		
		JLabel colorBoton = new JLabel("New label");
		colorBoton.setBackground(new Color(34, 34, 34));
		panel.add(colorBoton);
		
		JLabel colorPanel = new JLabel("New label");
		colorPanel.setBackground(new Color(51, 51, 51));
		panel.add(colorPanel);
		
		JLabel colorTexto = new JLabel("New label");
		colorTexto.setBackground(new Color(221, 221, 221));
		panel.add(colorTexto);
	}

}
