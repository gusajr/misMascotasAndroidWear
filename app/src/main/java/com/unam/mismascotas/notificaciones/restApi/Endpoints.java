package com.unam.mismascotas.notificaciones.restApi;

import com.unam.mismascotas.notificaciones.restApi.model.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Endpoints {

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<Response> registrarTokenID(@Field("id_dispositivo") String token, @Field("user") String user);
}
