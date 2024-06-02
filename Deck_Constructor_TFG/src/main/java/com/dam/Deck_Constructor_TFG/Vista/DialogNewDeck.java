package com.dam.Deck_Constructor_TFG.Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.hibernate.Session;

import com.dam.Deck_Constructor_TFG.Modelo.Deck;
import com.dam.Deck_Constructor_TFG.Modelo.Usuario;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;

import jakarta.persistence.Query;

public class DialogNewDeck extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField nombreTextField;
    private JComboBox<String> formatoComboBox;
    private JRadioButton mazoPublicoRadioButton;
    private JButton okButton;
    Main_Window parent;
    Color colorPrimario = new Color(41, 41, 41); 
	Color colorPanel = new Color(51, 51, 51);
	Color colorBoton = new Color(34, 34, 34); 
	Color colorTexto = new Color(221, 221, 221); 

    public DialogNewDeck(Main_Window parent, String user) {
        super(parent, true);
        setTitle("Nuevo mazo");

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 200));
        panel.setLayout(null);
        JLabel label = new JLabel("Nombre:");
        label.setForeground(colorTexto);
        label.setBounds(31, 48, 55, 14);
        panel.add(label);
        panel.setBackground(colorPrimario);
        panel.setForeground(colorTexto);

        nombreTextField = new JTextField(20);
        nombreTextField.setBounds(96, 45, 166, 20);
        panel.add(nombreTextField);
        JLabel label_1 = new JLabel("Formato:");
        label_1.setForeground(colorTexto);
        label_1.setBounds(31, 90, 58, 14);
        panel.add(label_1);

        formatoComboBox = new JComboBox<>();
        formatoComboBox.setModel(new DefaultComboBoxModel(new String[] {"Standard", "Modern", "Legacy", "Commander", "Casual"}));
        formatoComboBox.setBounds(97, 87, 126, 20);
        panel.add(formatoComboBox);
        JLabel label_2 = new JLabel("Mazo público:");
        label_2.setForeground(colorTexto);
        label_2.setBounds(31, 128, 89, 14);
        panel.add(label_2);

        mazoPublicoRadioButton = new JRadioButton();
        mazoPublicoRadioButton.setBounds(111, 121, 21, 21);
        panel.add(mazoPublicoRadioButton);

        // Botón "Ok"
        okButton = new JButton("Ok");
        okButton.setBounds(147, 154, 65, 35);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println(user);
            	String nombre = getNombre();
			    String formato = getFormato();
			    boolean mazoPublico = isMazoPublico();
			    
			    Session sis = HibernateUtil.getSessionFactory().openSession();
			    // Crear el nuevo mazo con los valores introducidos
			    Deck nuevoDeck = new Deck(nombre, formato, mazoPublico);
			    //
			    String hql = "SELECT u FROM Usuario u WHERE u.name = :nombre";
			    Usuario usuario = sis.createQuery(hql, Usuario.class)
				                      .setParameter("nombre", user)
				                      .getSingleResult();
			    nuevoDeck.setUsuario(usuario);
			    
		        usuario.getDecks().add(nuevoDeck);
		        
		        sis.beginTransaction();
		        sis.persist(nuevoDeck);
				sis.merge(usuario);
				sis.getTransaction().commit();
				sis.close();
				SwingUtilities.invokeLater(() -> {
		            ToastDialog dialogT = new ToastDialog("Mazo '"+nombre+"' añadido", 2000);
		            dialogT.mostrarToast();
		        });
				cerrarDialogo();
            }
        });
        panel.add(okButton);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    // Métodos para obtener los valores del diálogo
    public String getNombre() {
        return nombreTextField.getText();
    }

    public String getFormato() {
        return (String) formatoComboBox.getSelectedItem();
    }

    public boolean isMazoPublico() {
        return mazoPublicoRadioButton.isSelected();
    }

    // Método para cerrar el diálogo
    public void cerrarDialogo() {
        setVisible(false);
        dispose();
    }
}