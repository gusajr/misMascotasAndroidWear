package com.unam.mismascotas.fragment;

import com.unam.mismascotas.pojo.Mascota;
import com.unam.mismascotas.adapter.MascotaAdaptador;

import java.util.ArrayList;

public interface iRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public void generarGridLayout();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

}
