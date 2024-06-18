package com.dam.Deck_Constructor_TFG.Vista;

	import javax.swing.*;
	import java.awt.*;

	public class ToastDialog extends JWindow {
	    private static final long serialVersionUID = 1L;
	    private final int duracion;

	    public ToastDialog(String mensaje, int duracion) {
	        this.duracion = duracion;
	        JLabel etiqueta = new JLabel(mensaje);
	        etiqueta.setOpaque(true);
	        etiqueta.setBackground(Color.BLACK);
	        etiqueta.setForeground(Color.WHITE);
	        etiqueta.setFont(new Font("Dialog", Font.BOLD, 12));
	        etiqueta.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	        getContentPane().add(etiqueta);
	        pack();
	        setLocationRelativeTo(null);
	        setOpacity(1f); 
	    }

	    public void mostrarToast() {
	        setVisible(true);

	        new Thread(() -> {
	            try {
	                Thread.sleep(duracion);
	                desvanecer();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }).start();
	    }

	    private void desvanecer() {
	        new Thread(() -> {
	            try {
	                for (float i = 1.0f; i >= 0; i -= 0.05f) {
	                    final float opacidad = i;
	                    SwingUtilities.invokeLater(() -> setOpacity(opacidad));
	                    Thread.sleep(50);
	                }
	                SwingUtilities.invokeLater(() -> {
	                    setVisible(false);
	                    dispose();
	                });
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }).start();
	    }
	    //Forma para invocarlo sin erroress
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            ToastDialog dialog = new ToastDialog("Carta añadida a Deck", 2000);
	            dialog.mostrarToast();
	        });
	    }
	}