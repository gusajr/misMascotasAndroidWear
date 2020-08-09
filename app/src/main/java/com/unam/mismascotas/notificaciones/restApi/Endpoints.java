package com.unam.mismascotas.notificaciones.restApi;

import com.unam.mismascotas.notificaciones.restApi.model.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Endpoints {

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<Response> registrarTokenID(@Field("id_dispositivo") String token, @Field("usuario_instagram") String user);

    @GET(ConstantesRestApi.KEY_TOQUE_ANIMAL)
    Call<Response> toqueAnimal(@Path("id") String id, @Path("usuario_instagram") String animal);
}
