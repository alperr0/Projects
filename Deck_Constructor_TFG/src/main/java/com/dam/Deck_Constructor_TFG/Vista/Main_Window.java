package com.dam.Deck_Constructor_TFG.Vista;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class Main_Window extends JFrame implements PanelEventListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private JPanel panelContent;
	private PanelLogin loginPane;
	private PanelPrincipal mainPane;
	private PanelDecks decksPane;
	private PanelFavoritos favPane;
	private PanelSocial socialPane;
	Color colorPrimario = new Color(41, 41, 41); 
	Color colorPanel = new Color(51, 51, 51);
	Color colorBoton = new Color(34, 34, 34); 
	Color colorTexto = new Color(221, 221, 221); 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Window window = new Main_Window();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 762, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);		
		panelContent = new JPanel();
		panelContent.setBounds(0, 0, 746, 601);
		getContentPane().add(panelContent);
		cardLayout = new CardLayout(0,0);
		panelContent.setLayout(cardLayout);
		setResizable(false);
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		loginPane = new PanelLogin();
		loginPane.setBackground(colorPrimario);
		loginPane.setPanelActionListener(this);
		
		
		panelContent.add("Login", loginPane);
		cardLayout.show(panelContent, "Login");
		
		
		//Este escuchador permite que cuando se ajuste la ventana lo haga también el panel donde se pintan otros paneles
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Dimension newSize = getSize(); // Obtenemos las nuevas dimensiones del JFrame
                panelContent.setSize(newSize); // Establecemos las mismas dimensiones en el panelContent
            }
        });
		
	}

	@Override
	public void onPanelAction(String panelName) {
		Dimension newSize;
		switch(panelName) {
		case "Login":
			setResizable(false);
			break;
		case "Principal":
			mainPane = new PanelPrincipal(this);
			mainPane.setPanelActionListener(this);
			panelContent.add("Principal", mainPane);
			cardLayout.show(panelContent, panelName);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			newSize = getSize(); // Obtenemos las nuevas dimensiones del JFrame
            panelContent.setSize(newSize); // Establecemos las mismas dimensiones en el panelContent
            setResizable(true);
			break;
		case "Mis Decks":
			System.out.println("Cambia mis decks");
			decksPane = new PanelDecks(this);
    		decksPane.setPanelActionListener(this);
			panelContent.add(panelName, decksPane);
			cardLayout.show(panelContent, panelName);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			newSize = getSize(); // Obtenemos las nuevas dimensiones del JFrame
            panelContent.setSize(newSize); // Establecemos las mismas dimensiones en el panelContent
            
			break;
		case "Favoritos":
			favPane = new PanelFavoritos(this);
			favPane.setPanelActionListener(this);
			panelContent.add(panelName, favPane);
			cardLayout.show(panelContent, panelName);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			newSize = getSize(); // Obtenemos las nuevas dimensiones del JFrame
            panelContent.setSize(newSize); // Establecemos las mismas dimensiones en el panelContent
			break;
			
		case "Social":
			socialPane = new PanelSocial(this);
			socialPane.setPanelActionListener(this);
			panelContent.add(panelName, socialPane );
			cardLayout.show(panelContent, panelName);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			newSize = getSize(); // Obtenemos las nuevas dimensiones del JFrame
            panelContent.setSize(newSize); // Establecemos las mismas dimensiones en el panelContent
			break;
		}
	}
	
}
