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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.JSONArray;

import com.dam.Deck_Constructor_TFG.Modelo.Carta;
import com.dam.Deck_Constructor_TFG.Modelo.Deck;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;
import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.MagicCardPDF;
import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.ModificarCarta;
import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.MtgApiRequest;
import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.PersistenciaCartas;
import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.RecuperarMazosYFav;


public class PanelDecks extends JPanel {

	private static final long serialVersionUID = 1L;
	private PanelEventListener listener;
	private MtgApiRequest api;
	Main_Window parentFrame;
	JSONArray cardsInicial;
	private JList<String[]> cardList;
	private DefaultListModel<String[]> cardListModel;
	private String nombreDeckActual;
	String urlImagen;
	Map<String, ImageIcon> imageCache;
	ArrayList<String> cardNames;
	ArrayList<String[]> deckCards;
	String nomMazo;
	String name;
	Color colorPrimario = new Color(41, 41, 41); 
	Color colorPanel = new Color(51, 51, 51);
	Color colorBoton = new Color(34, 34, 34); 
	Color colorTexto = new Color(221, 221, 221); 

	/**
	 * Create the panel.
	 */
	public PanelDecks(Main_Window parentFrame) {
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
		
		JPanel panel_CENTER = new JPanel();
		panel_CENTER.setBackground(colorPrimario);
		panel_CENTER.setAutoscrolls(true);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BorderLayout(0, 0));
		
		//Paneles auxiliares dentro de panel_CENTER
		JPanel panel_CENTER_NORTE = new JPanel();
		panel_CENTER_NORTE.setBackground(colorPrimario);
		panel_CENTER_NORTE.setPreferredSize(new Dimension(10, 60));
		panel_CENTER.add(panel_CENTER_NORTE, BorderLayout.NORTH);
		panel_CENTER_NORTE.setLayout(null);
		
		JPanel panel_CENTER_CENTER = new JPanel();
		panel_CENTER_CENTER.setBackground(colorPrimario);
		panel_CENTER_CENTER.setBorder(null);
		panel_CENTER.add(panel_CENTER_CENTER, BorderLayout.CENTER);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setBounds(0, 11, 346, 22);
		comboBox.addItem("Eldrazi Incursion DEMO PRUEBA");
		ArrayList<String> mazos = RecuperarMazosYFav.recuperarMazosUsuario(parentFrame.user);
		if(mazos != null) {
			for(String m : mazos) {
				comboBox.addItem(m);
			}
		}
		panel_CENTER_NORTE.add(comboBox);
		
		JButton btnProxiesPDF = new JButton("Generar mazo PDF");
		btnProxiesPDF.setBackground(colorBoton);
		btnProxiesPDF.setForeground(colorTexto);
		btnProxiesPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MagicCardPDF.crearPDF(deckCards);
			}
		});
		btnProxiesPDF.setBounds(419, 13, 137, 23);
		panel_CENTER_NORTE.add(btnProxiesPDF);
			
	
		//Botones de abajo
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(colorPrimario);
		panelBotones.setAlignmentY(Component.TOP_ALIGNMENT);
		panelBotones.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelBotones.setPreferredSize(new Dimension(100, 100));
		panelBotones.setLayout(new GridLayout(0, 2, 0, 0));
		panelBotones.setLayout(null);
		panel_CENTER.add(panelBotones, BorderLayout.SOUTH);
		panel_CENTER_CENTER.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelRelleno = new JPanel();
		panelRelleno.setBackground(colorPrimario);
		panelRelleno.setMaximumSize(new Dimension(120, 32767));
		panelRelleno.setPreferredSize(new Dimension(140, 10));
		panel_IZQ.add(panelRelleno);
		panelRelleno.setLayout(null);
		////
		//TEST
		var sis = HibernateUtil.getSessionFactory().openSession();
		sis.beginTransaction();
		nombreDeckActual = "prueba";
		Deck deck = new Deck(nombreDeckActual, "Standard", true);
		

		// Instanciación de objetos Carta
		Carta carta1 = new Carta("Grindstone");
		Carta carta2 = new Carta("Black lotus");
		carta1.setDeck(deck);
		carta2.setDeck(deck);
		// Vinculación de cartas al deck
		List<Carta> cartas = new ArrayList<>();
		cartas.add(carta1);
		cartas.add(carta2);
		deck.setDeck_cards(cartas);

		sis.merge(deck);
		
		sis.getTransaction().commit();
		sis.close();
		
		//PANEL CARTAS DECK
		MtgApiRequest api = MtgApiRequest.getInstance();
		cardListModel = new DefaultListModel<>();
		//ArrayList<String> cardNames = RecuperarCartasDB.getDeckByName(nombreDeckActual);
		cardNames = new ArrayList<>(Arrays.asList(
			    "Ulalek, Fused Atrocity",
			    "Drowner of Hope",
			    "Morophon, the Boundless",
			    "Void Winnower",
			    "Ugin, the Ineffable",
			    "Eldrazi Monument",
			    "Forsaken Monument",
			    "Crib Swap",
			    "Forest",
			    "Island",
			    "Mountain",
			    "Plains",
			    "Swamp"
			));
		// Cargar las imágenes y almacenarlas en caché
		imageCache = new HashMap<>();
		for (String c : cardNames) {
		    String urlImagen = api.getImagenCarta(c);
		    try {
		        ImageIcon cardImage = new ImageIcon(ImageIO.read(new URL(urlImagen)));
		        // Escalar la imagen
		        int scaledWidth = 200;
		        int scaledHeight = (int) ((double) cardImage.getIconHeight() * scaledWidth / cardImage.getIconWidth());
		        Image scaledImage = cardImage.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
		        ImageIcon scaledIcon = new ImageIcon(scaledImage);
		        imageCache.put(urlImagen, scaledIcon); // Almacenar la imagen escalada en caché
		    } catch (IOException e) {
		        e.printStackTrace();
		        // Manejar errores de carga de imágenes si es necesario
		    }
		    cardListModel.addElement(new String[]{c, urlImagen, "1"}); // Agregar los datos al modelo de lista de cartas
		}

		cardList = new JList<>(cardListModel);
		cardList.setSelectionForeground(colorTexto);
		cardList.setForeground(colorTexto);
		cardList.setSelectionBackground(colorBoton);
		cardList.setBackground(colorPanel);
		cardList.setCellRenderer(new ListCellRenderer<>() {
		    private JLabel lblImage = new JLabel();
		    private JLabel lblnCopias = new JLabel();

		    @Override
		    public Component getListCellRendererComponent(JList<? extends String[]> list, String[] cardData, int index, boolean isSelected, boolean cellHasFocus) {
		        String nombre = cardData[0];
		        String imageUrl = cardData[1];

		        ImageIcon cachedImage = imageCache.get(imageUrl); // Obtener la imagen de la caché
		        lblImage.setIcon(cachedImage); // Establecer la imagen en el JLabel
		        lblnCopias.setText("x"+cardData[2]);
		        lblnCopias.setForeground(colorTexto);
		        lblnCopias.setFont(new Font("Tahoma", Font.BOLD, 14));
		        lblnCopias.setAlignmentX(CENTER_ALIGNMENT);
		        lblnCopias.setAlignmentY(TOP_ALIGNMENT);
		        JPanel panel = new JPanel(new BorderLayout(10, 10));
		        panel.add(lblImage, BorderLayout.CENTER);
		        panel.add(lblnCopias, BorderLayout.SOUTH);


		        if (isSelected) {
		            panel.setBackground(list.getSelectionBackground());
		            panel.setForeground(list.getSelectionForeground());
		        } else {
		            panel.setBackground(list.getBackground());
		            panel.setForeground(list.getForeground());
		        }

		        return panel;
		    }
		    
		});

        cardList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        cardList.setVisibleRowCount(-1);
		////
		PanelDetallesDeck panelDetalles = new PanelDetallesDeck(parentFrame);
		panelDetalles.setMinimumSize(new Dimension(100, 0));
		panelDetalles.setPreferredSize(new Dimension(100, 800));
		
		JScrollPane scrollPane = new JScrollPane(cardList);
		scrollPane.setBackground(colorPanel);
		scrollPane.setPreferredSize(new Dimension(451, 302));
		
		cardList.addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) { // Verificar si la selección ha terminado de cambiar
		            int selectedIndex = cardList.getSelectedIndex();
		            if (selectedIndex != -1) {
		                // Obtener el nombre de la carta seleccionada
		                String[] selectedCardData = cardList.getSelectedValue();
		                name = selectedCardData[0]; // Nombre de la carta
		                String imageUrl = selectedCardData[1]; // URL de la imagen
		                String nCopias = selectedCardData[2];
		                
		                // Actualizar la imagen en el panel de detalles
		                try {
		                    URL url = new URL(api.getImagenCarta(name));
		                    ImageIcon icon = new ImageIcon(url);
		                    int[] dim = {panelDetalles.lblImagen.getWidth(), panelDetalles.lblImagen.getHeight()};
		                    Image image = icon.getImage().getScaledInstance(dim[0], dim[1], Image.SCALE_SMOOTH);
		                    ImageIcon scaledIcon = new ImageIcon(image);

		                    panelDetalles.lblImagen.setIcon(scaledIcon);
		                    panelDetalles.textField.setText(selectedCardData[2]);
		                    
						    String[] auxMeta = api.getMetadatosCarta(name); 
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
		
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JComboBox<?> combo = (JComboBox<?>) e.getSource();
                nomMazo = (String) combo.getItemAt(combo.getSelectedIndex());
                recuperarYReferescarMazo(parentFrame, api);
                
            }

			
        });
		scrollPane.setAutoscrolls(true);
		panel_CENTER_CENTER.add(scrollPane);
		
		
		
		panel_CENTER_CENTER.add(panelDetalles);
		 panelDetalles.btnSumar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				short nCopias = Short.parseShort(panelDetalles.textField.getText());
				nCopias++;
				panelDetalles.textField.setText(String.valueOf(nCopias));
		        ModificarCarta.cambiarCopiasCarta(parentFrame.user, nomMazo, name, nCopias);
		        recuperarYReferescarMazo(parentFrame, api);
			}
		});
         
		 panelDetalles.btnRestar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					short nCopias = Short.parseShort(panelDetalles.textField.getText());
					if(nCopias>1) {
						nCopias--;
						panelDetalles.textField.setText(String.valueOf(nCopias));
				        ModificarCarta.cambiarCopiasCarta(parentFrame.user, nomMazo, name, nCopias);
				        recuperarYReferescarMazo(parentFrame, api);
					}
				}
			});
		 panelDetalles.btnRMDeck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RecuperarMazosYFav.borrarCartaMazo(parentFrame.user, nomMazo, name);
					recuperarYReferescarMazo(parentFrame, api);
				}
			});
		 panelDetalles.btnAddFav.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RecuperarMazosYFav.initFavs(parentFrame.user);
					PersistenciaCartas.addCartaFavoritos(name, parentFrame.user);
					
				}
			});

	}
	private void recuperarYReferescarMazo(Main_Window parentFrame, MtgApiRequest api) {
		deckCards = RecuperarMazosYFav.recuperarCartasMazo(parentFrame.user, nomMazo);
        cardListModel.clear();
        
        for (String[] card : deckCards) {
		    String urlImagen = api.getImagenCarta(card[0]);
		    try {
		        ImageIcon cardImage = new ImageIcon(ImageIO.read(new URL(urlImagen)));
		        // Escalar la imagen
		        int scaledWidth = 200;
		        int scaledHeight = (int) ((double) cardImage.getIconHeight() * scaledWidth / cardImage.getIconWidth());
		        Image scaledImage = cardImage.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
		        ImageIcon scaledIcon = new ImageIcon(scaledImage);
		        imageCache.put(urlImagen, scaledIcon); // Almacenar la imagen escalada en caché
		    } catch (IOException ioe) {
		        ioe.printStackTrace();
		        // Manejar errores de carga de imágenes si es necesario
		    }
		    cardListModel.addElement(new String[]{card[0], urlImagen, card[1]}); // Agregar los datos al modelo de lista de cartas
		}
	}
	public void setPanelActionListener(PanelEventListener listener) {
		this.listener=listener;
	}
	
	public void actionOccurred(String componente) {
        if (listener != null) {
        	switch(componente) {
        	case "btnFavoritos":
        		parentFrame.panelContent.remove(this);
        		listener.onPanelAction("Favoritos",parentFrame.user);
        		break;
        	case "btnPrincipal":
        		parentFrame.panelContent.remove(this);
        		listener.onPanelAction("Principal",parentFrame.user);
        		break;
        	case "btnSocial":
        		parentFrame.panelContent.remove(this);
        		listener.onPanelAction("Social",parentFrame.user);
        		break;
        	case "btnLogout":
        		parentFrame.panelContent.remove(this);
        		 listener.onPanelAction("Login",parentFrame.user);
        		break;
        	}
           
        }
    }
	
	
}
