package com.unam.mismascotas.db;

public class ConstantesBD {
    public static final String DATABASE_NAME = "mascota";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MASCOTA = "mascota";
    public static final String TABLE_MASCOTA_ID = "id_mascota";
    public static final String TABLE_MASCOTA_FOTO = "foto";
    public static final String TABLE_MASCOTA_NOMBRE = "nombre";

    public static final String TABLE_LIKES_MASCOTA = "mascota_likes";
    public static final String TABLE_LIKES_MASCOTA_ID = "id_mascota";
    public static final String TABLE_LIKES_MASCOTA_NUM_LIKES = "likes";
}

/*  TABLA MASCOTA
*   int id_mascota
*   int foto
*   String nombre
* */

/*  TABLA DETALLE MASCOTA
*   int id_mascota
*   int likes
* */