package com.unam.mismascotas.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.unam.mismascotas.pojo.Mascota;
import com.unam.mismascotas.R;

import java.util.ArrayList;

public class MascotaAdaptadorMini extends RecyclerView.Adapter<MascotaAdaptadorMini.MascotaViewHolder>{

    ArrayList <Mascota> mascotas;
    Activity activity;

    public MascotaAdaptadorMini(ArrayList <Mascota> mascotas, Activity activity){
        this.mascotas=mascotas;
        this.activity=activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_mascota_mini, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        Integer nLikes;
        nLikes = mascota.getLikes();
        //mascotaViewHolder.ivFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombre.setText(mascota.getNombre_u());
        mascotaViewHolder.tvnLikes.setText((nLikes.toString()));

        Picasso.with(activity).load(mascota.getUrlFoto()).placeholder(R.drawable.hueso_blanco).into(mascotaViewHolder.ivFoto);
        /*mascotaViewHolder.ibHuesoBlanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer nLikes;
                nLikes = mascota.getLikes();
                nLikes++;
                mascota.setLikes(nLikes);
                mascotaViewHolder.tvnLikes.setText(nLikes.toString());
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFoto;
        //private ImageButton ibHuesoBlanco;
        private TextView tvNombre;
        private TextView tvnLikes;
        private ImageView ivHuesoAmarillo;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = (ImageView) itemView.findViewById(R.id.ivFoto);
            //ibHuesoBlanco = (ImageButton) itemView.findViewById(R.id.ibHuesoBlanco);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvnLikes = (TextView) itemView.findViewById(R.id.tvnLikes);
            ivHuesoAmarillo = (ImageView) itemView.findViewById(R.id.ivHuesoAmarillo);
        }
    }
}
