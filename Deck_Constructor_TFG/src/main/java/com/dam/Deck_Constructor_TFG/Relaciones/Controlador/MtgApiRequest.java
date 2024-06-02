package com.dam.Deck_Constructor_TFG.Relaciones.Controlador;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MtgApiRequest {
	private static MtgApiRequest instance;
	OkHttpClient cliente;
	JSONObject cardInfo;
	
	//CONSTRUCTOR
	private MtgApiRequest() {
		cliente = new OkHttpClient();
		
	}
	// Método estático para obtener la instancia
    public static synchronized MtgApiRequest getInstance() {
        if (instance == null) {
            instance = new MtgApiRequest();
        }
        return instance;
    }
	public JSONArray getInitialCards() throws IOException {
		String url = "https://api.scryfall.com/cards/search?order=name&q=type:creature";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = cliente.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            JSONObject jsonResponse = new JSONObject(response.body().string());
            return jsonResponse.getJSONArray("data");
        }
    }
	public ArrayList<JSONObject> getFavoritosCards(ArrayList<String> favsCards) throws IOException {
		ArrayList<JSONObject> cards = new ArrayList<JSONObject>();
		for(String name : favsCards) {
			Request req = new Request.Builder()
					.url("https://api.scryfall.com/cards/named?fuzzy=" + name).build();

	        try (Response response = cliente.newCall(req).execute()) {
	            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

	            JSONObject jsonResponse = new JSONObject(response.body().string());
	            cards.add(jsonResponse);
	        }
		}
		return cards;
		
    }
	//MÉTODOS
	public JSONObject getInfoCartas() {
		
		return cardInfo;
	}
	public JSONObject getInfoCartas(String name) {
		Request req = new Request.Builder()
				.url("https://api.scryfall.com/cards/named?fuzzy=" + name).build();
		
		try {
            Response response = cliente.newCall(req).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error al realizar la solicitud: " + response);
            }

            String responseData = response.body().string();
            cardInfo = new JSONObject(responseData);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return cardInfo;
	}
	public JSONArray getCartas(String keyword) {
        JSONArray cards = new JSONArray();

        try {
        	String aux = keyword = "oracle:" + keyword + " or name:" + keyword + " or type:" + keyword + " or text:" + keyword;
            Request request = new Request.Builder()
                    .url("https://api.scryfall.com/cards/search?q=" + aux)
                    .build();

            Response response = cliente.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error al realizar la solicitud: " + response);
            }

            String responseData = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseData);
            cards = jsonResponse.getJSONArray("data");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cards;
    }
	//GET TEXTO CARTAS
	public String getTextoCartas() {
		return "";
	}
	
	public String getTextoCartas(String name) {
		Request req = new Request.Builder()
				.url("https://api.scryfall.com/cards/named?fuzzy=" + name).build();
		try {
            Response response = cliente.newCall(req).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error al realizar la solicitud: " + response);
            }

            String responseData = response.body().string();
            cardInfo = new JSONObject(responseData);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "";
	}
	
	//GET IMAGEN CARTA
	public String getImagenCarta() {
		return cardInfo.optString("image_uris");
	}
	public String getImagenCarta(String name) {
		Request req = new Request.Builder()
				.url("https://api.scryfall.com/cards/named?fuzzy=" + name).build();
		try {
            Response response = cliente.newCall(req).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error al realizar la solicitud: " + response);
            }

            String responseData = response.body().string();
            cardInfo = new JSONObject(responseData);
            JSONObject jsonImagenes;
			try {
				jsonImagenes = cardInfo.getJSONObject("image_uris");
			} catch (Exception e) {
				JSONArray cardFace = cardInfo.getJSONArray("card_faces");
				jsonImagenes = cardFace.getJSONObject(0).getJSONObject("image_uris");
			}
            return jsonImagenes.optString("border_crop");
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "https://via.placeholder.com/400x300.png?text=Error+Image";
	}
	public String getImagenCartaPng(String name) {

		Request req = new Request.Builder()
				.url("https://api.scryfall.com/cards/named?fuzzy=" + name).build();
		try {
            Response response = cliente.newCall(req).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error al realizar la solicitud: " + response);
            }

            String responseData = response.body().string();
            cardInfo = new JSONObject(responseData);
            JSONObject jsonImagenes;
			try {
				jsonImagenes = cardInfo.getJSONObject("image_uris");
			} catch (Exception e) {
				JSONArray cardFace = cardInfo.getJSONArray("card_faces");
				jsonImagenes = cardFace.getJSONObject(0).getJSONObject("image_uris");
			}
            return jsonImagenes.optString("png");
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "https://via.placeholder.com/400x300.png?text=Error+Image";
	}
	public String getImagenCartaSmall(String name) {
		Request req = new Request.Builder()
				.url("https://api.scryfall.com/cards/named?fuzzy=" + name).build();
		try {
            Response response = cliente.newCall(req).execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error al realizar la solicitud: " + response);
            }

            String responseData = response.body().string();
            cardInfo = new JSONObject(responseData);
            JSONObject jsonImagenes;
			try {
				jsonImagenes = cardInfo.getJSONObject("image_uris");
			} catch (Exception e) {
				JSONArray cardFace = cardInfo.getJSONArray("card_faces");
				jsonImagenes = cardFace.getJSONObject(0).getJSONObject("image_uris");
			}
            return jsonImagenes.optString("small");
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "https://via.placeholder.com/400x300.png?text=Error+Image";
	}
	public String[] getMetadatosCarta(String cardName) {
		 String[] cardData = new String[4];
	        String url = "https://api.scryfall.com/cards/named?fuzzy=" + cardName;

	        Request request = new Request.Builder()
	                .url(url)
	                .build();

	        try (Response response = cliente.newCall(request).execute()) {
	            if (!response.isSuccessful()) {
	                throw new IOException("Unexpected code " + response);
	            }

	            String responseData = response.body().string();
	            JSONObject json = new JSONObject(responseData);

	           

	            // Edición y número de la carta en la edición
	            cardData[0] = json.optString("set_name");
	            String collectorNumber = json.optString("collector_number");
	            String setSearchUri = json.getString("set_search_uri");
	            int totalCardsInSet = getTotalCardsInSet(setSearchUri);
	            cardData[1] =collectorNumber + "/" + totalCardsInSet;
	            
	            // Rareza
	            cardData[2] = json.optString("rarity");

	            // Formatos admitidos
	            JSONObject legalities = json.getJSONObject("legalities");
	            StringBuilder formats = new StringBuilder();
	            for (String key : legalities.keySet()) {
	                if (legalities.getString(key).equals("legal")) {
	                    if (formats.length() > 0) {
	                        formats.append(", ");
	                    }
	                    formats.append(key);
	                }
	            }
	            cardData[3] = formats.toString()+".";
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        return cardData;
	}
	private int getTotalCardsInSet(String setSearchUri) throws IOException {
	    Request request = new Request.Builder()
	            .url(setSearchUri)
	            .build();

	    try (Response response = cliente.newCall(request).execute()) {
	        if (!response.isSuccessful()) {
	            throw new IOException("Unexpected code " + response);
	        }

	        String responseData = response.body().string();
	        JSONObject jsonResponse = new JSONObject(responseData);
	        return jsonResponse.getInt("total_cards");
	    }
	}
}
