package com.dam.Deck_Constructor_TFG.Test;


import org.junit.jupiter.api.Test;

import com.dam.Deck_Constructor_TFG.Relaciones.Controlador.MtgApiRequest;

class TestGetMetadatosCarta {

	@Test
	void test() {
		MtgApiRequest api = MtgApiRequest.getInstance();
		String[] meta = api.getMetadatosCarta("Grindstone");
		for(String a : meta) {
			System.out.println(a);
		}
		String[] meta2 = api.getMetadatosCarta("Corrupt");
		for(String a : meta2) {
			System.out.println(a);
		}
	}

}
