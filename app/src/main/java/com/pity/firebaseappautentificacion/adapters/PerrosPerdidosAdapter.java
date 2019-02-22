package com.pity.firebaseappautentificacion.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.annotations.NotNull;
import com.pity.firebaseappautentificacion.R;
import com.pity.firebaseappautentificacion.models.PerroPerdido;

import java.util.ArrayList;


public class PerrosPerdidosAdapter extends RecyclerView.Adapter<PerrosPerdidosAdapter.PerrosPerdidoViewHolder> {
    private Context mContext;
    private ArrayList<PerroPerdido> perroPerdidosList;
    private int layout;

    public PerrosPerdidosAdapter(Context context, int layout, ArrayList<PerroPerdido> list){
        this.mContext = context;
        this.layout = layout;
        this.perroPerdidosList = list;
    }


    @NotNull
    @Override
    public PerrosPerdidoViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View itemView = LayoutInflater.from(mContext).inflate(layout , parent,false);
        return new PerrosPerdidoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull PerrosPerdidoViewHolder holder, int position) {

        PerroPerdido currentDog = perroPerdidosList.get(position);

        holder.nombre.setText(currentDog.getNombre());
        holder.descripcion.setText(currentDog.getDescripcion());

    }

    @Override
    public int getItemCount() {
        if (perroPerdidosList.size() > 0){
            return perroPerdidosList.size();
        }
        return 0;
    }

    public class PerrosPerdidoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView descripcion;

        public PerrosPerdidoViewHolder(@NotNull View item) {
            super(item);
            nombre =  item.findViewById(R.id.text_view_nombre_rw);
            descripcion = item.findViewById(R.id.text_view_descripcion_rw);

        }
    }
}

