package com.dam.Deck_Constructor_TFG.Test;



import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class TestImagenURLrecovery {

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
            String url = cardJson.optString("image_uris");

            System.out.println("Url de la carta: " + url);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
