package com.pity.firebaseappautentificacion.activitys;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pity.firebaseappautentificacion.R;
import com.pity.firebaseappautentificacion.adapters.PerrosPerdidosAdapter;
import com.pity.firebaseappautentificacion.models.PerroPerdido;

import java.util.ArrayList;

public class PerrosPerdidosActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private PerrosPerdidosAdapter adapter;

    private FirebaseDatabase firebaseDatabase;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perros_perdidos);

        firebaseDatabase = FirebaseDatabase.getInstance();


        inicializarRecycler();
        cargarRecycler();

    }



    public void inicializarRecycler(){
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void cargarRecycler(){
        firebaseDatabase.getReference().child("Usuarios").child("PerroPerdidos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<PerroPerdido> dogsList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    PerroPerdido perroPerdido = snapshot.getValue(PerroPerdido.class);

                    perroPerdido.setNombre(perroPerdido.getNombre());
                    perroPerdido.setDescripcion(perroPerdido.getDescripcion());

                    dogsList.add(perroPerdido);

                }

                adapter = new PerrosPerdidosAdapter(PerrosPerdidosActivity.this,R.layout.perro_perdido_iteam_recycler_view,dogsList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PerrosPerdidosActivity.this, "No anduvo pah xd", Toast.LENGTH_LONG).show();
            }
        });
    }
}
