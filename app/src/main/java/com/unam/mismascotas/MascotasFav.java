package com.unam.mismascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MascotasFav extends AppCompatActivity{

    private ImageButton ibFlecha;
    private RecyclerView listaMascotas;
    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
    //ArrayList<Mascota> ordenado = new ArrayList<Mascota>();

    ArrayList<Integer> fotos = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    ArrayList<Integer> likes = new ArrayList<>();

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_fav);

        ConstructorMascotas cm = new ConstructorMascotas(getBaseContext());
        mascotas = cm.obtener5FavMascotas();

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotasFav);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

        inicializarAdaptador();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mascotas_fav, menu);
        return true;
    }

    public void inicializarAdaptador(){
        MascotaAdaptador ma = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(ma);
    }

}