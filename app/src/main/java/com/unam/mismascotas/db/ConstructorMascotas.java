package com.unam.mismascotas.db;

import android.content.ContentValues;
import android.content.Context;

import com.unam.mismascotas.pojo.Mascota;
import com.unam.mismascotas.R;

import java.util.ArrayList;

public class ConstructorMascotas {
    private static final int LIKE = 1; //Interactor, intermediario

    private Context context;

    public ConstructorMascotas(Context context) {
        this.context=context;
    }

    public ArrayList<Mascota> obtenerDatos(){

        BaseDatos db = new BaseDatos(context);
        //insertarMascotas(db);
        return db.obtenerTodasLasMascotas();
    }

    public void insertarMascotas(BaseDatos db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TABLE_MASCOTA_FOTO, R.drawable.perro1);
        contentValues.put(ConstantesBD.TABLE_MASCOTA_NOMBRE, "Tobby");
        db.insertarMascota(contentValues);

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(ConstantesBD.TABLE_MASCOTA_FOTO, R.drawable.perro2);
        contentValues1.put(ConstantesBD.TABLE_MASCOTA_NOMBRE, "Chime");
        db.insertarMascota(contentValues1);

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(ConstantesBD.TABLE_MASCOTA_FOTO, R.drawable.perro3);
        contentValues2.put(ConstantesBD.TABLE_MASCOTA_NOMBRE, "Mimzy");
        db.insertarMascota(contentValues2);

        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(ConstantesBD.TABLE_MASCOTA_FOTO, R.drawable.perro4);
        contentValues3.put(ConstantesBD.TABLE_MASCOTA_NOMBRE, "Bobby");
        db.insertarMascota(contentValues3);

        ContentValues contentValues4 = new ContentValues();
        contentValues4.put(ConstantesBD.TABLE_MASCOTA_FOTO, R.drawable.perro5);
        contentValues4.put(ConstantesBD.TABLE_MASCOTA_NOMBRE, "Lucky");
        db.insertarMascota(contentValues4);

        ContentValues contentValues5 = new ContentValues();
        contentValues5.put(ConstantesBD.TABLE_MASCOTA_FOTO, R.drawable.perro6);
        contentValues5.put(ConstantesBD.TABLE_MASCOTA_NOMBRE, "Taco");
        db.insertarMascota(contentValues5);

        ContentValues contentValues6 = new ContentValues();
        contentValues6.put(ConstantesBD.TABLE_MASCOTA_FOTO, R.drawable.perro7);
        contentValues6.put(ConstantesBD.TABLE_MASCOTA_NOMBRE, "Violet");
        db.insertarMascota(contentValues6);

    }

    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues cv = new ContentValues();
        cv.put(ConstantesBD.TABLE_LIKES_MASCOTA_ID, mascota.getId_mascota());
        db.insertarLikeMascota(cv, mascota.getId_mascota());
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

    public ArrayList<Mascota> obtener5FavMascotas(){
        BaseDatos db = new BaseDatos(context);
        return db.obtener5Fav();
    }
}
