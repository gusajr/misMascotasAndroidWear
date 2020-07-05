package com.unam.mismascotas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBD.DATABASE_NAME, null, ConstantesBD.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCrearTablaMascota="CREATE TABLE "+ConstantesBD.TABLE_MASCOTA+"("
                +ConstantesBD.TABLE_MASCOTA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ConstantesBD.TABLE_MASCOTA_FOTO+" INTEGER, "
                +ConstantesBD.TABLE_MASCOTA_NOMBRE+ " TEXT"
                +")";

        String queryCrearTablaLikesMascota = "CREATE TABLE "+ConstantesBD.TABLE_LIKES_MASCOTA+"("
                +ConstantesBD.TABLE_LIKES_MASCOTA_ID+ " INTEGER PRIMARY KEY,"
                +ConstantesBD.TABLE_LIKES_MASCOTA_NUM_LIKES+ " INTEGER, "
                +"FOREIGN KEY ("+ConstantesBD.TABLE_LIKES_MASCOTA_ID+")"+" REFERENCES "
                +ConstantesBD.TABLE_MASCOTA+"("+ConstantesBD.TABLE_MASCOTA_ID+")"+
                ")";

        sqLiteDatabase.execSQL(queryCrearTablaMascota);
        sqLiteDatabase.execSQL(queryCrearTablaLikesMascota);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ConstantesBD.TABLE_MASCOTA);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ConstantesBD.TABLE_LIKES_MASCOTA);
        //onCreate(sqLiteDatabase);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM "+ConstantesBD.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId_mascota(registros.getInt(0));
            mascotaActual.setFoto(registros.getInt(1));
            mascotaActual.setNombre(registros.getString(2));

            String queryLikes = "SELECT "+ConstantesBD.TABLE_LIKES_MASCOTA_NUM_LIKES+" FROM "
                    +ConstantesBD.TABLE_LIKES_MASCOTA+ " WHERE "+ConstantesBD.TABLE_LIKES_MASCOTA_NUM_LIKES+
                    " = "+mascotaActual.getId_mascota();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if(registrosLikes.moveToNext()){
                mascotaActual.setLikes(registrosLikes.getInt(0));
            }else{
                mascotaActual.setLikes(0);
            }

            mascotas.add(mascotaActual);
        }

        db.close();

        return mascotas;
    }

    public ArrayList<Mascota> obtener5Fav(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM "+ConstantesBD.TABLE_MASCOTA
                +" AS tm INNER JOIN "+ConstantesBD.TABLE_LIKES_MASCOTA+ " AS tlm ON tm.id_mascota=tlm.id_mascota"
                +" ORDER BY "+ConstantesBD.TABLE_LIKES_MASCOTA_NUM_LIKES+" DESC "+" LIMIT 5";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId_mascota(registros.getInt(0));
            mascotaActual.setFoto(registros.getInt(1));
            mascotaActual.setNombre(registros.getString(2));
            mascotaActual.setLikes(registros.getInt(4));
            /*String queryLikes = "SELECT "+ConstantesBD.TABLE_LIKES_MASCOTA_NUM_LIKES+" FROM "
                    +ConstantesBD.TABLE_LIKES_MASCOTA+ " WHERE "+ConstantesBD.TABLE_LIKES_MASCOTA_NUM_LIKES+
                    " = "+mascotaActual.getId_mascota();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if(registrosLikes.moveToNext()){
                mascotaActual.setLikes(registrosLikes.getInt(0));
            }else{
                mascotaActual.setLikes(0);
            }*/

            mascotas.add(mascotaActual);
        }

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues cv){

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_MASCOTA, null, cv);

        db.insert(ConstantesBD.TABLE_LIKES_MASCOTA, null, cv);
        db.close();
    }

    public void insertarLikeMascota(ContentValues cv, int id_mascota){

        SQLiteDatabase db = this.getWritableDatabase();
        //db.insert(ConstantesBD.TABLE_LIKES_MASCOTA, null, cv);

        int likes=0;

        String query = "SELECT "+ConstantesBD.TABLE_LIKES_MASCOTA_NUM_LIKES+" FROM "+ConstantesBD.TABLE_LIKES_MASCOTA+" WHERE "+ConstantesBD.TABLE_LIKES_MASCOTA_ID+" ="+id_mascota;
        Cursor registro = db.rawQuery(query, null);
        while(registro.moveToNext()) {
            likes = registro.getInt(0);
        }
        likes++;
        //cv.put(ConstantesBD.TABLE_LIKES_MASCOTA_ID, id_mascota);
        cv.put(ConstantesBD.TABLE_LIKES_MASCOTA_NUM_LIKES, likes);

        db.replace(ConstantesBD.TABLE_LIKES_MASCOTA, ConstantesBD.TABLE_LIKES_MASCOTA_NUM_LIKES, cv);
        db.close();

    }

    public int obtenerLikesMascota(Mascota mascota){

        int likes=0;
        String query = "SELECT "+ConstantesBD.TABLE_LIKES_MASCOTA_NUM_LIKES+" FROM "
                +ConstantesBD.TABLE_LIKES_MASCOTA+ " WHERE "+ConstantesBD.TABLE_LIKES_MASCOTA_ID+
                " = "+mascota.getId_mascota();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if(registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }

}