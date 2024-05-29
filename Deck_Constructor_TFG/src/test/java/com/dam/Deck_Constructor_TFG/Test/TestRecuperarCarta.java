package com.dam.Deck_Constructor_TFG.Test;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


class TestRecuperarCarta {

	@Test
	void test() {
		OkHttpClient cliente = new OkHttpClient();
		String carta = "Grindstone";
		Request req = new Request.Builder()
				.url("https://api.scryfall.com/cards/named?fuzzy=" + carta).build();
		
		try {
            Response response = cliente.newCall(req).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error al realizar la solicitud: " + response);
            }

            String responseData = response.body().string();
            JSONObject cardJson = new JSONObject(responseData);

            // Extraer información relevante de la respuesta JSON
            String name = cardJson.getString("name");
            String releaseYear = cardJson.optString("released_at", "Desconocido");
            String type = cardJson.optString("type_line", "Desconocido");

            System.out.println("Nombre de la carta: " + name);
            System.out.println("Año de lanzamiento: " + releaseYear);
            System.out.println("Tipo: " + type);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
