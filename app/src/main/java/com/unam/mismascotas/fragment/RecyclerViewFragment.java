package com.unam.mismascotas.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.unam.mismascotas.About;
import com.unam.mismascotas.CambiarCuenta;
import com.unam.mismascotas.Contact;
import com.unam.mismascotas.notificaciones.restApi.Endpoints;
import com.unam.mismascotas.notificaciones.restApi.adapter.RestApiAdapter;
import com.unam.mismascotas.notificaciones.restApi.model.Response;
import com.unam.mismascotas.pojo.EnvioNotificacion;
import com.unam.mismascotas.pojo.Mascota;
import com.unam.mismascotas.pojo.MascotasFav;
import com.unam.mismascotas.R;
import com.unam.mismascotas.presentador.RecyclerViewFragmentPresenter;
import com.unam.mismascotas.adapter.MascotaAdaptador;
import com.unam.mismascotas.presentador.iRecyclerViewFragmentPresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class RecyclerViewFragment extends Fragment implements iRecyclerViewFragmentView {


    private RecyclerView listaMascotas;
    ArrayList<Mascota> mascotas;
    private ImageButton favoritos;
    private iRecyclerViewFragmentPresenter presenter;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        setHasOptionsMenu(true);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        favoritos = (ImageButton) v.findViewById(R.id.ibFavoritos);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_mascotas, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.ibFavoritos:
                Intent intent = new Intent (getActivity(), MascotasFav.class);
                //intent.putExtra("mismascotas", (Serializable) mascotas);
                startActivity(intent);
                break;
            case R.id.mContact:
                Intent i = new Intent (getActivity(), Contact.class);
                startActivity(i);
                break;
            case R.id.mAbout:
                Intent i1 = new Intent (getActivity(), About.class);
                startActivity(i1);
                break;
            case R.id.mCuenta:
                Intent i2 = new Intent (getActivity(), CambiarCuenta.class);
                startActivity(i2);
                break;
            case R.id.mNotificaciones:
                //Intent i3 = new Intent (getActivity(), EnvioNotificacion.class);
                //startActivity(i3);
                enviarToken();
                Toast.makeText(getContext(), "Se ha registrado el dispositivo", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        listaMascotas.setLayoutManager(glm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador ma = new MascotaAdaptador(mascotas, getActivity());
        return ma;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }

    public void enviarToken(){
        FirebaseApp.initializeApp(getContext());
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }

    private void enviarTokenRegistro(String token){
        Log.d("TOKEN", token);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoints endpoints = restApiAdapter.establecerConexionAPI();
        Call<Response> responseCall = endpoints.registrarTokenID(token, "perritos1999");

        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response response1 = response.body();
                Log.d("ID_FIREBASE",response1.getId());
                //Log.d("TOKEN_FIREBASE", response1.getToken());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}