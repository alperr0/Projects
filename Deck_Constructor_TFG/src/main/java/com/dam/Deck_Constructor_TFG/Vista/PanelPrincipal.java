package com.dam.Deck_Constructor_TFG.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.MtgApiRequest;
import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.PersistenciaCartas;
import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.RecuperarMazosYFav;


public class PanelPrincipal extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private PanelEventListener listener;
	private JTextField textFetchbar;
	private JTable tablaRegistros;
	DefaultTableModel model;
	private MtgApiRequest api;
	Main_Window parentFrame;
	JSONArray cardsInicial;
	String nomCarta;
	//Colores
	Color colorPrimario = new Color(41, 41, 41); 
	Color colorPanel = new Color(51, 51, 51);
	Color colorBoton = new Color(34, 34, 34); 
	Color colorTexto = new Color(221, 221, 221); 

	/**
	 * Create the panel.
	 */
	public PanelPrincipal(Main_Window parentFrame) {
		setBackground(colorPrimario);
		this.parentFrame=parentFrame;
		setAutoscrolls(true);
		setBounds(0, 0, 1252, 913);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_IZQ = new JPanel();
		panel_IZQ.setBackground(colorPrimario);
		panel_IZQ.setPreferredSize(new Dimension(150, 10));
		add(panel_IZQ, BorderLayout.WEST);
		
		JButton btnMisDecks = new JButton("Mis decks");
		btnMisDecks.setBackground(colorBoton);
		btnMisDecks.setForeground(colorTexto);
		btnMisDecks.setMargin(new Insets(2, 20, 2, 20));
		btnMisDecks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOccurred("btnMisDecks");
			}
		});
		panel_IZQ.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		panel.setBackground(colorPrimario);
		panel.setPreferredSize(new Dimension(140, 50));
		panel_IZQ.add(panel);
		btnMisDecks.setPreferredSize(new Dimension(120, 40));
		panel_IZQ.add(btnMisDecks);
		
		JButton btnFavoritos = new JButton("Favoritos");
		btnFavoritos.setBackground(colorBoton);
		btnFavoritos.setForeground(colorTexto);
		btnFavoritos.setMargin(new Insets(2, 20, 2, 20));
		btnFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOccurred("btnFavoritos");
			}
		});
		btnFavoritos.setPreferredSize(new Dimension(120, 40));
		panel_IZQ.add(btnFavoritos);
		
		JButton btnSocial = new JButton("Social");
		btnSocial.setBackground(colorBoton);
		btnSocial.setForeground(colorTexto);
		btnSocial.setMargin(new Insets(2, 20, 2, 20));
		btnSocial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOccurred("btnSocial");
			}
		});
		btnSocial.setPreferredSize(new Dimension(120, 40));
		panel_IZQ.add(btnSocial);
		
		JButton btnLogout = new JButton("Log out");
		btnLogout.setBackground(colorBoton);
		btnLogout.setForeground(colorTexto);
		btnLogout.setPreferredSize(new Dimension(120, 40));
		btnLogout.setMargin(new Insets(2, 20, 2, 20));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOccurred("btnLogout");
			}
		});
		panel_IZQ.add(btnLogout);
		
		JPanel panel_NORTE = new JPanel();
		panel_NORTE.setBackground(colorPrimario);
		panel_NORTE.setPreferredSize(new Dimension(500, 80));
		add(panel_NORTE, BorderLayout.NORTH);
		panel_NORTE.setLayout(null);
		
//		JLabel lblImagenUser = new JLabel("");
//		lblImagenUser.setPreferredSize(new Dimension(24, 24));
//		lblImagenUser.setBounds(new Rectangle(0, 0, 24, 24));
//		lblImagenUser.setIcon(new ImageIcon("C:\\Users\\admin\\Desktop\\icons8-user-24.png"));
//		lblImagenUser.setBounds(677, 11, 31, 35);
//		panel_NORTE.add(lblImagenUser);
		
		JLabel lblNameUser = new JLabel("");
		lblNameUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNameUser.setForeground(colorTexto);
		lblNameUser.setBounds(705, 21, 84, 14);
		String aux = parentFrame.user;
		lblNameUser.setText(aux);
		panel_NORTE.add(lblNameUser);
		
		JPanel panel_CENTER = new JPanel();
		panel_CENTER.setAutoscrolls(true);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BorderLayout(0, 0));
		
		//Paneles auxiliares dentro de panel_CENTER
		JPanel panel_CENTER_NORTE = new JPanel();
		panel_CENTER_NORTE.setBackground(colorPrimario);
		panel_CENTER_NORTE.setPreferredSize(new Dimension(10, 60));
		panel_CENTER.add(panel_CENTER_NORTE, BorderLayout.NORTH);
		panel_CENTER_NORTE.setLayout(null);
		
		JPanel auxCenter = new JPanel();
		auxCenter.setPreferredSize(new Dimension(83, 10));
		panel_CENTER.add(auxCenter, BorderLayout.CENTER);
		
		JPanel panel_CENTER_CENTER = new JPanel();
		panel_CENTER_CENTER.setOpaque(false);
		panel_CENTER_CENTER.setBorder(null);
		panel_CENTER.add(panel_CENTER_CENTER, BorderLayout.CENTER);
		
		textFetchbar = new JTextField();
		textFetchbar.setBounds(73, 11, 338, 20);
		panel_CENTER_NORTE.add(textFetchbar);
		textFetchbar.setPreferredSize(new Dimension(50, 20));
		textFetchbar.setColumns(10);
		
		
		model = new DefaultTableModel();
		model.addColumn("Nombre");
		model.addColumn("Año");
		model.addColumn("Tipo");
		
		
		try {
			
			api = MtgApiRequest.getInstance();
			cardsInicial = api.getInitialCards();

	        for (int i = 0; i < cardsInicial.length(); i++) {
	            JSONObject o = cardsInicial.getJSONObject(i);
	         // Extraer información relevante de la respuesta JSON
	            String nombre = o.getString("name");
	            String año = o.optString("released_at", "Desconocido");
	            String tipo = o.optString("type_line", "Desconocido");

	            model.addRow(new Object[]{nombre, año,tipo});
	        }
		} catch (IOException e1) {
			e1.printStackTrace();
		}

       
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(colorBoton);
		btnBuscar.setForeground(colorTexto);
		ImageIcon iconLupa = new ImageIcon("C:\\Users\\admin\\Desktop\\25313.png");
        Image imgLupa = iconLupa.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH); // Escala la imagen
        btnBuscar.setIcon(new ImageIcon(imgLupa));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					api = MtgApiRequest.getInstance();
					JSONArray cartas = api.getCartas(textFetchbar.getText());
					
					model.setRowCount(0);
					for (int i = 0; i < cartas.length(); i++) {
						
			            JSONObject o = cartas.getJSONObject(i);
			            String nombre = o.getString("name");
			            String año = o.optString("released_at", "Desconocido");
			            String tipo = o.optString("type_line", "Desconocido");
			            
			            model.addRow(new Object[]{nombre, año,tipo});
			            textFetchbar.setText("");
			        }
						
			           
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnBuscar.setBounds(433, 10, 89, 23);
		panel_CENTER_NORTE.add(btnBuscar);
		
		textFetchbar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Verificar si la tecla presionada es "Enter"
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Realizar la acción deseada, por ejemplo, hacer clic en el botón
                    btnBuscar.doClick();
                }
            }
        });
		//Botones de abajo
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(colorPrimario);
		panelBotones.setAlignmentY(Component.TOP_ALIGNMENT);
		panelBotones.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelBotones.setPreferredSize(new Dimension(100, 100));
		
		JButton btnIzquierda = new JButton();
		btnIzquierda.setBackground(colorBoton);
		btnIzquierda.setForeground(colorTexto);
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIzquierda.setBounds(33, 5, 30, 30);
		btnIzquierda.setPreferredSize(new Dimension(30, 30)); // Tamaño del botón
        ImageIcon iconIzquierda = new ImageIcon("C:\\Users\\admin\\Desktop\\flecha.png");
        Image imgIzquierda = iconIzquierda.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH); // Escala la imagen
        btnIzquierda.setIcon(new ImageIcon(imgIzquierda));

		JButton btnDerecha = new JButton();
		btnDerecha.setBackground(colorBoton);
		btnDerecha.setForeground(colorTexto);
		btnDerecha.setBounds(68, 5, 30, 30);
		btnDerecha.setPreferredSize(new Dimension(30, 30)); // Tamaño del botón
        ImageIcon iconDerecha = new ImageIcon("C:\\Users\\admin\\Desktop\\flecha-correcta.png");
        Image imgDerecha = iconDerecha.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH); // Escala la imagen
        btnDerecha.setIcon(new ImageIcon(imgDerecha));
		panelBotones.setLayout(null);
		
		panelBotones.add(btnIzquierda);
		panelBotones.add(btnDerecha);
		panel_CENTER.add(panelBotones, BorderLayout.SOUTH);
		
		JPanel panelRelleno = new JPanel();
		panelRelleno.setBackground(colorPrimario);
		panelRelleno.setMaximumSize(new Dimension(120, 32767));
		panelRelleno.setPreferredSize(new Dimension(140, 10));
		panel_IZQ.add(panelRelleno);
		panelRelleno.setLayout(null);
		
		tablaRegistros = new JTable(model){
			private static final long serialVersionUID = 1L;
			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer todas las celdas no editables
            }
        };
        tablaRegistros.setOpaque(true);
		tablaRegistros.setFillsViewportHeight(true);
		tablaRegistros.setSelectionForeground(colorTexto);
		tablaRegistros.setForeground(colorTexto);
		tablaRegistros.setSelectionBackground(colorBoton);
		tablaRegistros.setBackground(colorPanel);
		tablaRegistros.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Permite seleccionar una sola fila
		PanelDetalles panelDetalles = new PanelDetalles(parentFrame);
		panelDetalles.setBackground(colorPrimario);
		panelDetalles.setMinimumSize(new Dimension(100, 0));
		panelDetalles.setPreferredSize(new Dimension(350, 800));
		
		JScrollPane scrollPane = new JScrollPane(tablaRegistros);
		tablaRegistros.getTableHeader().setBackground(colorPanel);
		scrollPane.setBackground(colorPanel);
		scrollPane.setForeground(colorPanel);
		scrollPane.setPreferredSize(new Dimension(451, 302));
		
		tablaRegistros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Verificar si la selección ha terminado de cambiar
                    int selectedRow = tablaRegistros.getSelectedRow();
                    if (selectedRow != -1) {
                        // Obtener el nombre de la fila seleccionada
                        nomCarta = tablaRegistros.getValueAt(selectedRow, 0).toString();
                        // Actualizar la imagen en el panel de detalles
                        URL url;
                        try {
                        	url = new URL(api.getImagenCarta(nomCarta));
							ImageIcon icon = new ImageIcon(url);
							int [] dim = {panelDetalles.lblImagen.getWidth(),panelDetalles.lblImagen.getHeight()};
						    
						    Image image = icon.getImage().getScaledInstance(dim[0], dim[1], Image.SCALE_SMOOTH);
						    
						    ImageIcon scaledIcon = new ImageIcon(image);
						    panelDetalles.lblImagen.setIcon(scaledIcon);
						    //Añadimos el texto de la carta 
						    String[] auxMeta = api.getMetadatosCarta(nomCarta); 
						    for(String a : auxMeta) {
								System.out.println(a);
							}
						    panelDetalles.lblcartaInfo.setText(
						    		"<html><b>Edición: </b>"+auxMeta[0]+
						    		"<br><br><b>Número de carta: </b>"+auxMeta[1]+
						    		"<br><br><b>Rareza: </b>"+auxMeta[2]+
						    		"<br><br><b>Formatos legales: </b>"+auxMeta[3]
						    		+"</html>"
						    		);
                        } catch (MalformedURLException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
            }
        });
		panel_CENTER_CENTER.setLayout(new GridLayout(0, 2, 0, 0));
		scrollPane.setAutoscrolls(true);
		panel_CENTER_CENTER.add(scrollPane);
		
		
		
		panel_CENTER_CENTER.add(panelDetalles);
		panelDetalles.actualizarMazos();
		panelDetalles.btnAddDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) panelDetalles.comboBox.getSelectedItem();
				if("-Nuevo Deck-".equals(selectedItem)) {
					DialogNewDeck dialog = new DialogNewDeck(parentFrame, parentFrame.user);
					dialog.setBackground(colorPrimario);
					dialog.setForeground(colorTexto);
					dialog.setVisible(true);  
					panelDetalles.actualizarMazos();
				}else {
					PersistenciaCartas.addCartaMazo(nomCarta, selectedItem);
				}
				
			}
		});
		
		panelDetalles.btnAddFav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecuperarMazosYFav.initFavs(parentFrame.user);
				PersistenciaCartas.addCartaFavoritos(nomCarta, parentFrame.user);
			}
		});
		
		
		

	}
	public void setPanelActionListener(PanelEventListener listener) {
		this.listener=listener;
	}
	
	public void actionOccurred(String componente) {
        if (listener != null) {
        	switch(componente) {
        	case "btnMisDecks":
        		parentFrame.panelContent.remove(this);
        		listener.onPanelAction("Mis Decks", parentFrame.user);
        		break;
        	case "btnSocial":
        		parentFrame.panelContent.remove(this);
        		listener.onPanelAction("Social", parentFrame.user);
        		break;
        	case "btnFavoritos":
        		parentFrame.panelContent.remove(this);
        		listener.onPanelAction("Favoritos",parentFrame.user);
        		break;
        	case "btnLogout":
        		parentFrame.panelContent.remove(this);
        		 listener.onPanelAction("Login",parentFrame.user);
        		break;
        	}
        }
    }
}
