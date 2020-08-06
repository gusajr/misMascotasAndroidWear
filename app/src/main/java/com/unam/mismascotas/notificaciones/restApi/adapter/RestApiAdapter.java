package com.unam.mismascotas.notificaciones.restApi.adapter;

import com.unam.mismascotas.notificaciones.restApi.ConstantesRestApi;
import com.unam.mismascotas.notificaciones.restApi.Endpoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    public Endpoints establecerConexionAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        return retrofit.create(Endpoints.class);
    }
}
