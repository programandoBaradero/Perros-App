package com.pity.firebaseappautentificacion.activitys;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pity.firebaseappautentificacion.R;
import com.pity.firebaseappautentificacion.adapters.PerrosPerdidosAdapter;
import com.pity.firebaseappautentificacion.models.PerroPerdido;
import com.pity.firebaseappautentificacion.presenters.PerrosPerdidosPresenter;

import java.util.ArrayList;

public class PerrosPerdidosActivity extends AppCompatActivity implements View.OnClickListener  {

    private RecyclerView mRecyclerView;
    private PerrosPerdidosAdapter adapter;

    private FloatingActionButton fabAgregarPerro;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;
    private FirebaseUser currentUser;

    private PerrosPerdidosPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perros_perdidos);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        currentUser = firebaseAuth.getCurrentUser();

        presenter = new PerrosPerdidosPresenter(this, firebaseDatabase, firebaseAuth);

        fabAgregarPerro = findViewById(R.id.fab_agregar_perro_perdido);
        fabAgregarPerro.setOnClickListener(this);

        inicializarRecycler();


    }



    public void inicializarRecycler(){
        mRecyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        presenter.cargarRecycler(mRecyclerView);
    }


    @Override
    public void onClick(View view) {
        if (view == fabAgregarPerro){
            presenter.abrirDialog();
        }

    }

}
