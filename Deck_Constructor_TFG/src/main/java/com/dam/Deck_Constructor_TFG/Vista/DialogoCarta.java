package com.dam.Deck_Constructor_TFG.Vista;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Rectangle;

public class DialogoCarta extends JDialog {

    private JTextField txtNombreCarta;
    private JButton btnAnadirCarta;
    private JButton btnCancelar;

    public DialogoCarta(JFrame parent) {
        super(parent, true);
        getContentPane().setBounds(new Rectangle(0, 0, 400, 300));
        setBounds(new Rectangle(0, 0, 400, 300));
        setTitle("Añadir carta");

        txtNombreCarta = new JTextField();
        btnAnadirCarta = new JButton("Añadir carta");
        btnCancelar = new JButton("Cancelar");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(txtNombreCarta);
        panel.add(btnAnadirCarta);
        panel.add(btnCancelar);
        getContentPane().add(panel);

        btnAnadirCarta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCarta = txtNombreCarta.getText();
                // Aquí puedes implementar la lógica para añadir la carta
                System.out.println("Se ha añadido la carta: " + nombreCarta);
                dispose();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
    }
}