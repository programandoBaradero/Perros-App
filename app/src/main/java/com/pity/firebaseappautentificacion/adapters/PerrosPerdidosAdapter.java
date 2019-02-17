package com.pity.firebaseappautentificacion.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pity.firebaseappautentificacion.R;
import com.pity.firebaseappautentificacion.models.PerroPerdido;

import java.util.ArrayList;


public class PerrosPerdidosAdapter extends RecyclerView.Adapter<PerrosPerdidosAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PerroPerdido> perroPerdidosList;
    private int layout;

    public PerrosPerdidosAdapter(Context context, int layout, ArrayList<PerroPerdido> list){
        this.context = context;
        this.layout = layout;
        this.perroPerdidosList = list;
    }


    @Override
    public PerrosPerdidosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layout , parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PerroPerdido currentDog = perroPerdidosList.get(position);

        holder.nombre.setText(currentDog.getNombre());
        holder.descripcion.setText(currentDog.getDescripcion());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView descripcion;

        public ViewHolder(View item) {
            super(item);
            nombre =  item.findViewById(R.id.text_view_nombre_rw);
            descripcion = item.findViewById(R.id.text_view_descripcion_rw);

        }
    }
}

