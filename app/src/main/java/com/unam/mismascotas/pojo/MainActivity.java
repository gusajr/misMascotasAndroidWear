package com.unam.mismascotas.pojo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.unam.mismascotas.R;
import com.unam.mismascotas.adapter.PageAdapter;
import com.unam.mismascotas.db.BaseDatos;
import com.unam.mismascotas.db.ConstantesBD;
import com.unam.mismascotas.db.ConstructorMascotas;
import com.unam.mismascotas.fragment.PerfilFragment;
import com.unam.mismascotas.fragment.RecyclerViewFragment;
import com.unam.mismascotas.notificaciones.restApi.Endpoints;
import com.unam.mismascotas.notificaciones.restApi.adapter.RestApiAdapter;
import com.unam.mismascotas.notificaciones.restApi.model.Response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "notificacion";

    private Toolbar tb;
    private TabLayout tl;
    private ViewPager vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = (Toolbar) findViewById(R.id.toolbar);
        tl = (TabLayout) findViewById(R.id.tabLayout);
        vp = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mascotas, menu);
        return true;
    }*/

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new RecyclerViewFragment());
        //perfil fragment
        return fragments;
    }

    public void setUpViewPager() {
        vp.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tl.setupWithViewPager(vp);

        //tl.getTabAt(0).setIcon(R.drawable.telefono);
        //tl.getTabAt(1).setIcon(R.drawable.correo);
    }
}