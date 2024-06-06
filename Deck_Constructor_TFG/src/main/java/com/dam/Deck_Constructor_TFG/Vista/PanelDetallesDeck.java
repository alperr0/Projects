package com.dam.Deck_Constructor_TFG.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hibernate.Session;

import com.dam.Deck_Constructor_TFG.Modelo.Deck;
import com.dam.Deck_Constructor_TFG.Modelo.Favoritos;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;
import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.ModificarCarta;
import java.awt.Component;
import javax.swing.JTextField;

public class PanelDetallesDeck extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JLabel lblImagen;
	JLabel lblcartaInfo;
	JFrame parentFrame;
	SpinnerModel spinnerModel;
	JButton btnSumar;
	JButton btnRestar;
	JButton btnRMDeck;
	JButton btnAddFav;
	Color colorPrimario = new Color(41, 41, 41); 
	Color colorPanel = new Color(51, 51, 51);
	Color colorBoton = new Color(34, 34, 34); 
	Color colorTexto = new Color(221, 221, 221); 
	JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelDetallesDeck(JFrame parentFrame) {
		this.parentFrame=parentFrame;
		setLayout(null);
		setBackground(colorPrimario);
		lblcartaInfo = new JLabel("");
		lblcartaInfo.setVerticalAlignment(SwingConstants.TOP);
		lblcartaInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblcartaInfo.setForeground(colorTexto);
		lblcartaInfo.setBounds(49, 436, 413, 160);
		add(lblcartaInfo);
		
		spinnerModel = new SpinnerNumberModel(1,1,4,1);
	    
		
		btnAddFav = new JButton("Añadir a favoritos");
		btnAddFav.setBackground(colorBoton);
		btnAddFav.setForeground(colorTexto);
		btnAddFav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnAddFav.setBounds(317, 607, 119, 23);
		add(btnAddFav);
		
		Favoritos fav = new Favoritos();

		btnRMDeck = new JButton("Eliminar Carta");
		btnRMDeck.setBackground(colorBoton);
		btnRMDeck.setForeground(colorTexto);
		btnRMDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRMDeck.setBounds(188, 607, 119, 23);
		add(btnRMDeck);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(113, 33, 278, 382);
		add(lblImagen);
		
		textField = new JTextField();
		textField.setBackground(colorPanel);
		textField.setForeground(colorTexto);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBounds(59, 607, 69, 23);
		add(textField);
		textField.setColumns(10);
		
		btnSumar = new JButton("+");
		btnSumar.setBackground(colorBoton);
		btnSumar.setForeground(colorTexto);
		btnSumar.setBounds(20, 607, 41, 23);
		add(btnSumar);
		
		btnRestar = new JButton("-");
		btnRestar.setBackground(colorBoton);
		btnRestar.setForeground(colorTexto);
		btnRestar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRestar.setBounds(125, 607, 41, 23);
		add(btnRestar);
		
	}
}
