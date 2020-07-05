package com.unam.mismascotas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment implements iRecyclerViewFragmentView{


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
        //inicializarListaMascotas();
        //inicializarAdaptador();
        favoritos = (ImageButton) v.findViewById(R.id.ibFavoritos);

        //mascotas = new ConstructorMascotas(getActivity()).obtenerDatos();

        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        //mascotas = new ConstructorMascotas(getActivity()).obtenerDatos();

        return v;
    }


    public void inicializarAdaptador(){
        MascotaAdaptador ma = new MascotaAdaptador(mascotas, getActivity());
        listaMascotas.setAdapter(ma);
    }

    /*
    public void inicializarListaMascotas(){

        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.perro1, "Tobby"));
        mascotas.add(new Mascota(R.drawable.perro2, "Chime"));
        mascotas.add(new Mascota(R.drawable.perro3, "Mimzy"));
        mascotas.add(new Mascota(R.drawable.perro4, "Bobby"));
        mascotas.add(new Mascota(R.drawable.perro5, "Lucky"));
        mascotas.add(new Mascota(R.drawable.perro6, "Taco"));
        mascotas.add(new Mascota(R.drawable.perro7, "Violet"));

    }*/

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
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador ma = new MascotaAdaptador(mascotas, getActivity());
        return ma;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}