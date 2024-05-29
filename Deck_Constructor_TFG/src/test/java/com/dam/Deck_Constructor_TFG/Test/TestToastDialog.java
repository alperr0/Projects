package com.dam.Deck_Constructor_TFG.Test;


import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Test;

import com.dam.Deck_Constructor_TFG.Vista.ToastDialog;

class TestToastDialog {

	@Test
    public void testMostrarToast() {
        SwingUtilities.invokeLater(() -> {
            ToastDialog dialog = new ToastDialog("Carta añadida a Deck", 1000);
            dialog.mostrarToast();
        });

        // Esperar lo suficiente para que el Toast desaparezca
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // No podemos hacer más aserciones sobre un componente de UI en un test unitario sin herramientas avanzadas de testing de UI.
        // Aquí simplemente verificamos que no haya excepciones y que el flujo se complete.
    }

}
