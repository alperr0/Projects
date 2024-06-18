package com.dam.Deck_Constructor_TFG.Relaciones.Controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecuperarEstadísticas {
    private static final OkHttpClient cliente = new OkHttpClient();
    private static final String[] tiposDeCarta = {
            "Creature", "Sorcery", "Instant", "Enchantment",
            "Artifact", "Planeswalker", "Land"
    };

    public static Map<String, Integer> getColoresMazo(ArrayList<String[]> cardData) throws IOException {
        Map<String, Integer> coloresMazo = new HashMap<>();

        for (String[] data : cardData) {
            String name = data[0];
            int copies = Integer.parseInt(data[1]);

            Request req = new Request.Builder()
                    .url("https://api.scryfall.com/cards/named?fuzzy=" + name.replace(" ", "+"))
                    .build();
            try {
                Response response = cliente.newCall(req).execute();
                if (!response.isSuccessful()) {
                    throw new IOException("Error al realizar la solicitud: " + response);
                }

                String responseData = response.body().string();
                JSONObject cardInfo = new JSONObject(responseData);
                JSONArray colors = cardInfo.getJSONArray("colors");

                for (int i = 0; i < colors.length(); i++) {
                    String color = colors.getString(i);
                    coloresMazo.put(color, coloresMazo.getOrDefault(color, 0) + copies);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return coloresMazo;
    }

    public static Map<String, Integer> getTiposMazo(ArrayList<String[]> cardData) throws IOException {
        Map<String, Integer> tiposMazo = new HashMap<>();
        String[] tiposDeCarta = {
                "Creature", "Sorcery", "Instant", "Enchantment",
                "Artifact", "Planeswalker", "Land"
        };

        for (String[] data : cardData) {
            String name = data[0];
            int copies = Integer.parseInt(data[1]);

            Request req = new Request.Builder()
                    .url("https://api.scryfall.com/cards/named?fuzzy=" + name.replace(" ", "+"))
                    .build();
            try {
                Response response = cliente.newCall(req).execute();
                if (!response.isSuccessful()) {
                    throw new IOException("Error al realizar la solicitud: " + response);
                }

                String responseData = response.body().string();
                JSONObject cardInfo = new JSONObject(responseData);
                String typeLine = cardInfo.getString("type_line");

                for (String type : tiposDeCarta) {
                    if (typeLine.contains(type)) {
                        tiposMazo.put(type, tiposMazo.getOrDefault(type, 0) + copies);
                        break; // Only count the first type found
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tiposMazo;
    }

    public static Map<String, Integer> getCostesConvertidosMazo(ArrayList<String[]> cardData) throws IOException {
        Map<String, Integer> costesConvertidos = new HashMap<>();

        for (String[] data : cardData) {
            String name = data[0];
            int copies = Integer.parseInt(data[1]);

            Request req = new Request.Builder()
                    .url("https://api.scryfall.com/cards/named?fuzzy=" + name.replace(" ", "+"))
                    .build();
            try {
                Response response = cliente.newCall(req).execute();
                if (!response.isSuccessful()) {
                    throw new IOException("Error al realizar la solicitud: " + response);
                }

                String responseData = response.body().string();
                JSONObject cardInfo = new JSONObject(responseData);
                int cmc = cardInfo.getInt("cmc");
                String key = "CC" + cmc;
                costesConvertidos.put(key, costesConvertidos.getOrDefault(key, 0) + copies);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return costesConvertidos;
    }

   
}
