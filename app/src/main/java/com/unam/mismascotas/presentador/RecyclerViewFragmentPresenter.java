package com.unam.mismascotas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.unam.mismascotas.db.ConstructorMascotas;
import com.unam.mismascotas.fragment.iRecyclerViewFragmentView;
import com.unam.mismascotas.pojo.Mascota;
import com.unam.mismascotas.restApi.adapter.RestApiAdapter;
import com.unam.mismascotas.restApi.model.EndPointApi;
import com.unam.mismascotas.restApi.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewFragmentPresenter implements iRecyclerViewFragmentPresenter{

    private com.unam.mismascotas.fragment.iRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(iRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){

        this.iRecyclerViewFragmentView=iRecyclerViewFragmentView;
        this.context = context;
        //obtenerMascotasBD();
        obtenerMascotasRetrofit();
    }

    @Override
    public void obtenerMascotasBD() {

        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarGridLayout();
    }

    @Override
    public void obtenerMascotasRetrofit() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMedia = restApiAdapter.construirGsonDeserializadorMediaRecent();
        EndPointApi endPointAdapter = restApiAdapter.establecerConexionRestApiInstagram(gsonMedia);
        Call<MascotaResponse> contactoResponseCall = endPointAdapter.getRecentMedia();

        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse contactoResponse = response.body();
                mascotas = contactoResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexion", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });

    }
}
