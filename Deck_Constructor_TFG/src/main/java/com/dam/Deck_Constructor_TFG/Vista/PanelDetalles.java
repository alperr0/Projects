package com.dam.Deck_Constructor_TFG.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.hibernate.Session;

import com.dam.Deck_Constructor_TFG.Modelo.Deck;
import com.dam.Deck_Constructor_TFG.Modelo.Favoritos;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelDetalles extends JPanel {

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
	public PanelDetalles(JFrame parentFrame) {
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
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBackground(colorBoton);
		comboBox.setForeground(colorTexto);
		comboBox.setBounds(30, 657, 129, 22);
		add(comboBox);
		comboBox.addItem("-Nuevo Deck-");
		
		Favoritos fav = new Favoritos();

		JButton btnAddDeck = new JButton("Añadir a deck");
		btnAddDeck.setBackground(colorBoton);
		btnAddDeck.setForeground(colorTexto);
		
		btnAddDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogNewDeck dialog = new DialogNewDeck(parentFrame);
				dialog.setVisible(true);

				if (dialog.getResult() == JOptionPane.OK_OPTION) {
				    String nombre = dialog.getNombre();
				    String formato = dialog.getFormato();
				    boolean mazoPublico = dialog.isMazoPublico();

				    // Crear el nuevo mazo con los valores introducidos
				    Deck nuevoDeck = new Deck(nombre, formato, mazoPublico);
				    Session sis = HibernateUtil.getSessionFactory().openSession();
					sis.beginTransaction();
					
					sis.persist(nuevoDeck);
					sis.getTransaction().commit();
					sis.close();

				}
			}
		});
		btnAddDeck.setBounds(184, 657, 119, 23);
		add(btnAddDeck);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(113, 33, 278, 382);
		add(lblImagen);

	}
}
