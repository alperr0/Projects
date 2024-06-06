package com.dam.Deck_Constructor_TFG.Test;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.RecuperarMazosYFav;

class TestRecuperarMazo {

	@Test
	void test() {
		ArrayList<String[]> aaa = RecuperarMazosYFav.recuperarCartasMazo("aa", "aa");
		for(String[] aa : aaa) {
			for(String a : aa) {
				System.out.print(a + " ");
			}
			System.out.println();
		}
	}

}
