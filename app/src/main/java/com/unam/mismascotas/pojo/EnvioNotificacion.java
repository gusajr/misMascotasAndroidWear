package com.unam.mismascotas.pojo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.unam.mismascotas.R;
import com.unam.mismascotas.notificaciones.restApi.Endpoints;
import com.unam.mismascotas.notificaciones.restApi.adapter.RestApiAdapter;
import com.unam.mismascotas.notificaciones.restApi.model.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class EnvioNotificacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio_notificacion);
    }
}