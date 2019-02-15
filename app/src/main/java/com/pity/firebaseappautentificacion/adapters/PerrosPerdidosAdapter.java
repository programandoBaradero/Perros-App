package com.pity.firebaseappautentificacion.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pity.firebaseappautentificacion.R;
import com.pity.firebaseappautentificacion.models.PerroPerdido;

import java.util.List;


public class PerrosPerdidosAdapter extends RecyclerView.Adapter<PerrosPerdidosAdapter.ViewHolder> {
    private Context context;
    private FirebaseDatabase databaseReference;
    private int layout;

    public PerrosPerdidosAdapter(Context context, int layout){
        this.context = context;
        this.layout = layout;
        databaseReference = FirebaseDatabase.getInstance();
    }


    @Override
    public PerrosPerdidosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nombrePerro = databaseReference.

        PerroPerdido = new PerroPerdido();

        holder.nombre.setText();
        holder.descripcion.setText();
        holder.foto_perro.setImageResource();
        holder.foto_perfil.setImageResource();

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nombre;
        TextView descripcion;
        ImageView foto_perfil;
        ImageView foto_perro;


        public ViewHolder(View item) {
            super(item);
            RecyclerView recyclerView = (RecyclerView) item.findViewById(R.id.recyclerView);
            nombre = (TextView) item.findViewById(R.id.nombre);
            descripcion = (TextView) item.findViewById(R.id.descripcion);
            foto_perfil = (ImageView) itemView.findViewById(R.id.foto_perfil);
            foto_perro = (ImageView) itemView.findViewById(R.id.foto_perro);
        }
    }
}

