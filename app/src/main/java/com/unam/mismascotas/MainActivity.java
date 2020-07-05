package com.unam.mismascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

        BaseDatos baseDatos = new BaseDatos(getBaseContext());
        SQLiteDatabase sqLiteDatabase = baseDatos.getWritableDatabase();
        Cursor mCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ConstantesBD.TABLE_MASCOTA, null);
        Boolean rowExists;

        if (mCursor.moveToFirst()) {
            rowExists = true;

        } else {
            rowExists = false;
        }

        if(!rowExists){
            ConstructorMascotas constructorMascotas = new ConstructorMascotas(getBaseContext());
            constructorMascotas.insertarMascotas(baseDatos);
        }

        //if (tb != null) {
        //    setSupportActionBar(tb);
        //}
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
        fragments.add(new PerfilFragment());
        return fragments;
    }

    public void setUpViewPager() {
        vp.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tl.setupWithViewPager(vp);

        //tl.getTabAt(0).setIcon(R.drawable.telefono);
        //tl.getTabAt(1).setIcon(R.drawable.correo);
    }
}