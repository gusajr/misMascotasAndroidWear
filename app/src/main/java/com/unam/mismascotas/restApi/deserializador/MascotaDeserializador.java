package com.unam.mismascotas.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.unam.mismascotas.pojo.Mascota;
import com.unam.mismascotas.restApi.model.JsonKeys;
import com.unam.mismascotas.restApi.model.MascotaResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.RESPONSE_ARRAY);

        mascotaResponse.setMascotas(deserializarMascotadeJson(mascotaResponseData));

        return mascotaResponse;
    }

    private ArrayList<Mascota> deserializarMascotadeJson(JsonArray mascotaResponseData){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size(); i++) {
            JsonObject contactoResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();

            //--En dado caso de tener objetos internos--
            //JsonObject userJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.USERNAME);

            String id = contactoResponseDataObject.get(JsonKeys.USERNAME).getAsString();
            String nombreCompleto = contactoResponseDataObject.get(JsonKeys.USERNAME).getAsString();
            String urlFoto = contactoResponseDataObject.get(JsonKeys.MEDIA_URL).getAsString();
            String likes = contactoResponseDataObject.get(JsonKeys.FOTO_ID).getAsString();
            likes = likes.substring(0,2);
            int ilikes = Integer.parseInt(likes);

            Mascota contactoActual = new Mascota();
            contactoActual.setNombre_u(nombreCompleto);
            contactoActual.setUrlFoto(urlFoto);
            contactoActual.setId(id);
            contactoActual.setLikes(ilikes);

            mascotas.add(contactoActual);
        }
        return mascotas;
    }
}
