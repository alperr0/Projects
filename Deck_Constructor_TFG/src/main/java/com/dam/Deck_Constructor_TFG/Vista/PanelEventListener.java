package com.dam.Deck_Constructor_TFG.Vista;

/*Esta interfaz va a ser utilizada para notificar a la Ventana principal
 * desde los diferentes paneles que se ha realizado una acción que requiere
 * un determiando cambio de vista  que ha de ser implmentado mediante el método
 * .show() el CardLayout del JFrame. 
 */
public interface PanelEventListener {
    void onPanelAction(String panelName);
}

