package com.dam.Deck_Constructor_TFG.Vista;

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
import javax.swing.SwingConstants;

import org.hibernate.Session;

import com.dam.Deck_Constructor_TFG.Modelo.Deck;
import com.dam.Deck_Constructor_TFG.Modelo.Favoritos;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;

public class PanelDetallesDeck extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JLabel lblImagen;
	JLabel lblcartaInfo;
	JFrame parentFrame;
	Color colorPrimario = new Color(41, 41, 41); 
	Color colorPanel = new Color(51, 51, 51);
	Color colorBoton = new Color(34, 34, 34); 
	Color colorTexto = new Color(221, 221, 221); 

	/**
	 * Create the panel.
	 */
	public PanelDetallesDeck(JFrame parentFrame) {
		this.parentFrame=parentFrame;
		setLayout(null);
		setBackground(colorPrimario);
		lblcartaInfo = new JLabel("");
		lblcartaInfo.setVerticalAlignment(SwingConstants.TOP);
		lblcartaInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblcartaInfo.setForeground(colorTexto);
		lblcartaInfo.setBounds(49, 433, 413, 210);
		add(lblcartaInfo);
		
		JButton btnAddFav = new JButton("Añadir a favoritos");
		btnAddFav.setBackground(colorBoton);
		btnAddFav.setForeground(colorTexto);
		btnAddFav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnAddFav.setBounds(343, 657, 119, 23);
		add(btnAddFav);
		
		Favoritos fav = new Favoritos();

		JButton btnAddDeck = new JButton("Eliminar de deck");
		btnAddDeck.setBackground(colorBoton);
		btnAddDeck.setForeground(colorTexto);
		btnAddDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAddDeck.setBounds(112, 607, 119, 23);
		add(btnAddDeck);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(113, 33, 278, 382);
		add(lblImagen);

	}
}
