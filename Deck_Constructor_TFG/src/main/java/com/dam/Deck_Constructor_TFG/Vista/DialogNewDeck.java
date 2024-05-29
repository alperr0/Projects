package com.dam.Deck_Constructor_TFG.Vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;

public class DialogNewDeck extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField nombreTextField;
    private JComboBox<String> formatoComboBox;
    private JRadioButton mazoPublicoRadioButton;
    private JButton okButton;

    public DialogNewDeck(JFrame parent) {
        super(parent, true);
        setTitle("Nuevo mazo");

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 200));
        panel.setLayout(null);
        JLabel label = new JLabel("Nombre:");
        label.setBounds(31, 48, 55, 14);
        panel.add(label);

        nombreTextField = new JTextField(20);
        nombreTextField.setBounds(96, 45, 166, 20);
        panel.add(nombreTextField);
        JLabel label_1 = new JLabel("Formato:");
        label_1.setBounds(28, 90, 58, 14);
        panel.add(label_1);

        formatoComboBox = new JComboBox<>(new String[]{"Standard", "Modern", "Legacy"});
        formatoComboBox.setModel(new DefaultComboBoxModel(new String[] {"Standard", "Modern", "Legacy", "Commander", "Casual"}));
        formatoComboBox.setBounds(97, 87, 89, 20);
        panel.add(formatoComboBox);
        JLabel label_2 = new JLabel("Mazo público:");
        label_2.setBounds(31, 118, 89, 14);
        panel.add(label_2);

        mazoPublicoRadioButton = new JRadioButton();
        mazoPublicoRadioButton.setBounds(119, 114, 21, 21);
        panel.add(mazoPublicoRadioButton);

        // Botón "Ok"
        
        panel.add(okButton = new JButton("Ok"));
        okButton.setBounds(147, 154, 65, 35);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    public String getNombre() {
        return nombreTextField.getText();
    }

    public String getFormato() {
        return (String) formatoComboBox.getSelectedItem();
    }

    public boolean isMazoPublico() {
        return mazoPublicoRadioButton.isSelected();
    }

    // getResult() method to check if OK button was pressed (modal behavior)
    public int getResult() {
        return getDefaultCloseOperation();
    }
}
