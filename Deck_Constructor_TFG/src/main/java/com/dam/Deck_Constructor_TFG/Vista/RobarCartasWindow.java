package com.dam.Deck_Constructor_TFG.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Dimension;

public class RobarCartasWindow extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel imageLabelPanel;
	Map<String, ImageIcon> imageCache;
	Color colorPrimario = new Color(41, 41, 41); 
	Color colorPanel = new Color(51, 51, 51);
	Color colorBoton = new Color(34, 34, 34); 
	Color colorTexto = new Color(221, 221, 221); 
	int i;

	public RobarCartasWindow(Main_Window parentFrame, Map<String, ImageIcon> imageCache) {
			
	        super(parentFrame); // Set modality to true for modal dialog
	        this.imageCache = imageCache;
	        i=7;
	        this.setBackground(colorPanel);
	        // Initialize and configure the dialog
	        setSize(1155, 671);
	        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        getContentPane().setLayout(new BorderLayout(5, 0));
	        
	     // Create panel for buttons
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setBackground(colorPanel);
	        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

	        // Create and add buttons
	        JButton btnNuevaMano = new JButton("Nueva mano");
	        btnNuevaMano.setBackground(colorBoton);
			btnNuevaMano.setForeground(colorTexto);
	        btnNuevaMano.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		generarManoRandom();
	        	}
	        });
	        buttonPanel.add(btnNuevaMano);

	        JButton btnAddCarta = new JButton("Añadir carta");
	        btnAddCarta.setBackground(colorBoton);
			btnAddCarta.setForeground(colorTexto);
	        btnAddCarta.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if(i!=7) {
	        			i++;
	        			generarManoRandom();
	        		}else {
	        			SwingUtilities.invokeLater(() -> {
	        	            ToastDialog dialog = new ToastDialog("No puede haber más de 7 cartas", 2000);
	        	            dialog.mostrarToast();
	        	        });
	        		}
	        	}
	        });
	        buttonPanel.add(btnAddCarta);

	        JButton btnMulligan = new JButton("Mulligan");
	        btnMulligan.setBackground(colorBoton);
			btnMulligan.setForeground(colorTexto);
	        btnMulligan.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if(i!=1) {
	        			i--;
	        			generarManoRandom();
	        		}else {
	        			SwingUtilities.invokeLater(() -> {
	        	            ToastDialog dialog = new ToastDialog("No puede haber menos de 1 carta", 2000);
	        	            dialog.mostrarToast();
	        	        });
	        		}
	        	}
	        });
	        buttonPanel.add(btnMulligan);

	        // Add button panel to the top of the dialog
	        getContentPane().add(buttonPanel, BorderLayout.NORTH);

	        // Create panel for image labels
	        imageLabelPanel = new JPanel();
	        imageLabelPanel.setBackground(colorPanel);

	        // Create and add image labels
	        for (int i = 0; i < 7; i++) {
	            JLabel imageLabel = new JLabel();
	            imageLabel.setIcon(new ImageIcon("https://scryfall.com/card/otj/280/island")); // Replace with your image path
	            imageLabelPanel.add(imageLabel);
	        }

	        // Add image label panel to the center of the dialog
	        getContentPane().add(imageLabelPanel, BorderLayout.CENTER);

	        // Create and add "Cerrar" button
	        JPanel buttonPanel2 = new JPanel();
	        buttonPanel2.setBackground(colorPanel);
	        buttonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
	        JButton cerrarButton = new JButton("Cerrar");
	        cerrarButton.setBackground(colorBoton);
			cerrarButton.setForeground(colorTexto);
	        cerrarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Cerrar button clicked");
	                dispose(); // Dispose the dialog to close it
	            }
	        });
	        buttonPanel2.add(cerrarButton);
	        getContentPane().add(buttonPanel2, BorderLayout.SOUTH);
	        generarManoRandom();
	    }
	private void generarManoRandom() {
        // Clear any existing labels from the panel
        imageLabelPanel.removeAll();

        // Randomly select and display 7 images from the imageCache

        List<ImageIcon> imageList = new ArrayList<>(imageCache.values());
        Collections.shuffle(imageList);

        // Ensure all 7 labels are filled with images (even if fewer images are available)
        for (int k = 0; k < i; k++) {
            int imageIndex = k % imageList.size(); // Wrap around the imageList if necessary
            ImageIcon randomImage = imageList.get(imageIndex);
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(randomImage);
            imageLabelPanel.add(imageLabel);
        }
        getContentPane().add(imageLabelPanel, BorderLayout.CENTER);
        imageLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        imageLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        //revalidate and repaint the dialog to reflect changes
        revalidate();
        repaint();
    }

}
