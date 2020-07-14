package com.unam.mismascotas.pojo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.unam.mismascotas.R;
import com.unam.mismascotas.adapter.PageAdapter;
import com.unam.mismascotas.db.BaseDatos;
import com.unam.mismascotas.db.ConstantesBD;
import com.unam.mismascotas.db.ConstructorMascotas;
import com.unam.mismascotas.fragment.PerfilFragment;
import com.unam.mismascotas.fragment.RecyclerViewFragment;

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