package com.dam.Deck_Constructor_TFG.Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.hibernate.Session;

import com.dam.Deck_Constructor_TFG.Modelo.Deck;
import com.dam.Deck_Constructor_TFG.Modelo.Favoritos;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;
import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.RecuperarMazosYFav;

public class PanelDetalles extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JLabel lblImagen;
	JLabel lblcartaInfo;
	Main_Window parentFrame;
	ArrayList<String> mazos;
	JComboBox<String> comboBox ;
	JButton btnAddDeck;
	JButton btnAddFav;
	Color colorPrimario = new Color(41, 41, 41); 
	Color colorPanel = new Color(51, 51, 51);
	Color colorBoton = new Color(34, 34, 34); 
	Color colorTexto = new Color(221, 221, 221); 

	/**
	 * Create the panel.
	 */
	public PanelDetalles(Main_Window parentFrame) {
		this.parentFrame=parentFrame;
		setLayout(null);
		setBackground(colorPrimario);
		lblcartaInfo = new JLabel("");
		lblcartaInfo.setVerticalAlignment(SwingConstants.TOP);
		lblcartaInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblcartaInfo.setForeground(colorTexto);
		lblcartaInfo.setBounds(49, 433, 413, 160);
		add(lblcartaInfo);
		
		btnAddFav = new JButton("Añadir a favoritos");
		btnAddFav.setBackground(colorBoton);
		btnAddFav.setForeground(colorTexto);
		
		btnAddFav.setBounds(343, 557, 119, 23);
		add(btnAddFav);
		
		comboBox = new JComboBox<>();
		comboBox.setBackground(colorBoton);
		comboBox.setForeground(colorTexto);
		comboBox.setBounds(30, 557, 129, 22);
		add(comboBox);
		comboBox.addItem("-Nuevo Deck-");
		mazos = RecuperarMazosYFav.recuperarMazosUsuario(parentFrame.user);
		if(mazos != null) {
			for(String m : mazos) {
				comboBox.addItem(m);
			}
		}
		
		
		Favoritos fav = new Favoritos();

		btnAddDeck = new JButton("Añadir a deck");
		btnAddDeck.setBackground(colorBoton);
		btnAddDeck.setForeground(colorTexto);
		
		
		btnAddDeck.setBounds(184, 557, 119, 23);
		add(btnAddDeck);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(113, 33, 278, 382);
		add(lblImagen);

	}
	public void actualizarMazos() {
		comboBox.removeAllItems();
		comboBox.addItem("-Nuevo Deck-");
		mazos = RecuperarMazosYFav.recuperarMazosUsuario(parentFrame.user);
		if(mazos != null) {
			for(String m : mazos) {
				comboBox.addItem(m);
			}
		}
		
	}
}
