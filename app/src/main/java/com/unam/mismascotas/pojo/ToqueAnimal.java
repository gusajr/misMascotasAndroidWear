package com.unam.mismascotas.pojo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.unam.mismascotas.notificaciones.restApi.Endpoints;
import com.unam.mismascotas.notificaciones.restApi.adapter.RestApiAdapter;
import com.unam.mismascotas.notificaciones.restApi.model.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class ToqueAnimal extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY = "TOQUE_ANIMAL";
        String accion = intent.getAction();

        if(ACCION_KEY.equals(accion)){
            toqueAnimal();
            Toast.makeText(context, "Diste un like/follow a: " + "perro",Toast.LENGTH_LONG).show();
        }

        //Toast.makeText(context, "Diste un toque a: " + "perro",Toast.LENGTH_LONG).show();
    }

    public void toqueAnimal(){
        Log.d("TOQUE_ANIMAL", "true");
        Response response = new Response("-MEFgqImIC4ISAmI8VBO", "123", "perritos1999"); //-ME46iFob6DyrgGmJhGj
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoints endpoints = restApiAdapter.establecerConexionAPI();

        Call<Response> responseCall = endpoints.toqueAnimal(response.getId(), response.getAnimal());
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response response1 = response.body();
                //Log.d("ID_FIREBASE", response1.getId());
                //Log.d("TOKEN_FIREBASE", response1.getToken());
                //Log.d("ANIMAL_FIREBASE", response1.getAnimal());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
