package com.unam.mismascotas.pojo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageButton;

import com.unam.mismascotas.R;
import com.unam.mismascotas.adapter.MascotaAdaptador;
import com.unam.mismascotas.adapter.MascotaAdaptadorFav;
import com.unam.mismascotas.db.ConstructorMascotas;
import com.unam.mismascotas.pojo.Mascota;

import java.util.ArrayList;

public class MascotasFav extends AppCompatActivity{

    private RecyclerView listaMascotas;
    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_fav);

        //ConstructorMascotas cm = new ConstructorMascotas(getBaseContext());
        //mascotas = cm.obtener5FavMascotas();

        crearMascotas();
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
        MascotaAdaptadorFav ma = new MascotaAdaptadorFav(mascotas, this);
        listaMascotas.setAdapter(ma);
    }

    public void crearMascotas(){
        Mascota mascota = new Mascota(R.drawable.perro1,"Mimzy", 5);
        Mascota mascota1 = new Mascota(R.drawable.perro6, "Lucky", 3);
        Mascota mascota2 = new Mascota(R.drawable.perro3, "Bobby", 2);
        Mascota mascota3 = new Mascota(R.drawable.perro4, "Toby", 1);

        mascotas.add(mascota);
        mascotas.add(mascota1);
        mascotas.add(mascota2);
        mascotas.add(mascota3);
    }
}