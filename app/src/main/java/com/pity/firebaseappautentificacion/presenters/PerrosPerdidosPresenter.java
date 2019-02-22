package com.pity.firebaseappautentificacion.presenters;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.pity.firebaseappautentificacion.R;
import com.pity.firebaseappautentificacion.activitys.PerrosPerdidosActivity;
import com.pity.firebaseappautentificacion.adapters.PerrosPerdidosAdapter;
import com.pity.firebaseappautentificacion.models.PerroPerdido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PerrosPerdidosPresenter implements View.OnClickListener{
    private Context mContext;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private PerrosPerdidosAdapter mAdapter;

    private Dialog dialogAgregarPerro;
    private ProgressDialog progressDialog;

    private EditText etxNombreDialog;
    private EditText etxDescripcionDialog;
    private Button btnAgregar;
    private Button btnCancelar;

    public PerrosPerdidosPresenter (Context context, DatabaseReference databaseReference, FirebaseAuth firebaseAuth){
        this.mContext = context;
        this.mDatabase = databaseReference;
        this.mAuth = firebaseAuth;

    }

    public void abrirDialog(){
        dialogAgregarPerro = new Dialog(mContext);
        dialogAgregarPerro.setContentView(R.layout.dialog_agregar_perro_perdido);
        dialogAgregarPerro.setTitle("Agregar perro");

        etxNombreDialog = dialogAgregarPerro.findViewById(R.id.edit_text_nombre_perro_perdido);
        etxDescripcionDialog = dialogAgregarPerro.findViewById(R.id.edit_text_descripcion_perro_perdido);
        btnAgregar = dialogAgregarPerro.findViewById(R.id.button_agregar_perro_perdido);
        btnCancelar = dialogAgregarPerro.findViewById(R.id.button_cancelar_perro_perdido);

        btnAgregar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        dialogAgregarPerro.show();
    }

    private void agregarNuevoPerroPerdidoToFirebase(){
        String nombrePerro = etxNombreDialog.getText().toString().trim();
        String descripcionPerro = etxDescripcionDialog.getText().toString().trim();

        Map<String, Object> perroPerdido = new HashMap<>();
        perroPerdido.put("nombre", nombrePerro);
        perroPerdido.put("descripcion" , descripcionPerro);

        mDatabase
                .child("Usuarios")
                .child(mAuth.getCurrentUser().getUid())
                .child("PerroPerdidos")
                .push()
                .updateChildren(perroPerdido).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                dialogAgregarPerro.dismiss();
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(mContext,"Se cargo un nuevo perro",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(mContext, "Error al cargar perro"+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cargarRecycler(final RecyclerView mRecyclerView){
        mDatabase
                .child("Usuarios")
                .child(mAuth.getCurrentUser().getUid())
                .child("PerroPerdidos")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<PerroPerdido> dogsList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    PerroPerdido perroPerdido = snapshot.getValue(PerroPerdido.class);

                    perroPerdido.setNombre(perroPerdido.getNombre());
                    perroPerdido.setDescripcion(perroPerdido.getDescripcion());

                    dogsList.add(perroPerdido);

                }

                Toast.makeText(mContext, "Se cargo la lista", Toast.LENGTH_LONG).show();
                mAdapter = new PerrosPerdidosAdapter(mContext, R.layout.perro_perdido_iteam_recycler_view, dogsList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(mContext, "No anduvo pah xd", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnAgregar){
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("Cargando perro perdido...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            agregarNuevoPerroPerdidoToFirebase();
        }

        if (view == btnCancelar){
            dialogAgregarPerro.dismiss();
        }
    }
}
