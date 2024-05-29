package com.dam.Deck_Constructor_TFG.Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.dam.Deck_Constructor_TFG.Modelo.Usuario;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class PanelLogin extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private PanelEventListener listener;
	private JTextField txtfUsuario;
	private JPasswordField passField;

	Color colorPrimario = new Color(41, 41, 41); 
	Color colorPanel = new Color(51, 51, 51);
	Color colorBoton = new Color(34, 34, 34); 
	Color colorTexto = new Color(221, 221, 221); 
	/**
	 * Create the panel.
	 */
	public PanelLogin() {
		
		setBackground(colorPrimario);
		setBounds(0, 0, 746, 601);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Magic deck builder");
		lblTitulo.setForeground(colorTexto);
		lblTitulo.setFont(new Font("Gabriola", Font.BOLD, 52));
		lblTitulo.setBounds(202, 93, 338, 89);
		add(lblTitulo);
		
		//JLayeredPane layeredPane = new JLayeredPane();
		
		JPanel panel = new JPanel();
		panel.setBackground(colorPanel);
		panel.setBounds(177, 181, 399, 234);
		
//		layeredPane.add(this);
//		layeredPane.add(panel, JLayeredPane.PALETTE_LAYER);
		add(panel);
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(47, 58, 113, 38);
		lblUsuario.setFont(new Font("Gabriola", Font.BOLD, 22));
		
		JLabel lblContrasenia = new JLabel("Contraseña: ");
		lblContrasenia.setBounds(47, 113, 99, 35);
		lblContrasenia.setFont(new Font("Gabriola", Font.BOLD, 22));
		
		txtfUsuario = new JTextField();
		txtfUsuario.setBounds(165, 55, 189, 29);
		txtfUsuario.setColumns(10);
		
		passField = new JPasswordField();
		passField.setBounds(165, 108, 189, 29);
		
		JButton btnLogin = new JButton("Iniciar sesión");
		btnLogin.setBounds(60, 194, 141, 29);
		btnLogin.setBackground(colorBoton);
		btnLogin.setForeground(colorTexto);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session sis = HibernateUtil.getSessionFactory().openSession();
				try {
					Query query = sis.createQuery("FROM Usuario WHERE name = '"+ txtfUsuario.getText()+"'");
					Usuario usuario = (Usuario) query.getSingleResult();
					
					if(usuario.getPass().equals(passField.getText())) {
						actionOccurred();
					}else {
						JOptionPane.showMessageDialog(null, "La contraseña no coincide con el usuario.");
					}

				}catch(NoResultException e1){
					JOptionPane.showMessageDialog(null, "El usuario no está registrado.");
				}catch (HibernateException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnLogin.setVerticalAlignment(SwingConstants.TOP);
		btnLogin.setFont(new Font("Gabriola", Font.BOLD, 20));
		
		JButton btnRegistro = new JButton("Registro");
		btnRegistro.setBackground(colorBoton);
		btnRegistro.setForeground(colorTexto);
		btnRegistro.setBounds(221, 194, 133, 29);
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session sis = HibernateUtil.getSessionFactory().openSession();
				Usuario user = new Usuario(txtfUsuario.getText().trim(),passField.getText().trim());
				
				sis.beginTransaction();
				sis.persist(user);
				sis.getTransaction().commit();
			}
		});
		btnRegistro.setVerticalAlignment(SwingConstants.TOP);
		btnRegistro.setFont(new Font("Gabriola", Font.BOLD, 20));
		panel.setLayout(null);
		panel.add(lblUsuario);
		panel.add(lblContrasenia);
		panel.add(txtfUsuario);
		panel.add(passField);
		panel.add(btnLogin);
		panel.add(btnRegistro);
	}
	
	public void setPanelActionListener(PanelEventListener listener) {
		this.listener=listener;
	}
	
	public void actionOccurred() {
        if (listener != null) {
            listener.onPanelAction("Principal");
        }
    }
}


