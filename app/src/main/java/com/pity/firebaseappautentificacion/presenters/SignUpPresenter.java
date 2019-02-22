package com.pity.firebaseappautentificacion.presenters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pity.firebaseappautentificacion.activitys.LoginActivity;
import com.pity.firebaseappautentificacion.activitys.SignUpActivity;
import com.pity.firebaseappautentificacion.models.Usuario;

import java.util.HashMap;
import java.util.Map;

public class SignUpPresenter {
    private Context mContext;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    public SignUpPresenter(Context mContext, FirebaseAuth firebaseAuth, DatabaseReference mDatabase) {
        this.mContext = mContext;
        this.firebaseAuth = firebaseAuth;
        this.mDatabase = mDatabase;
    }



    public void registrarUsuario(final String email, String password, final String nombre, final String apellido){

        if(TextUtils.isEmpty(email)){
            Toast.makeText(mContext,"No se ingreso mail",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(mContext,"No se ingreso contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(nombre)){
            Toast.makeText(mContext,"No se ingreso nombre",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(apellido)){
            Toast.makeText(mContext,"No se ingreso apellido",Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Registrando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            final FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                            Map<String, Object> crearUsuario = new HashMap<>();

                            crearUsuario.put("nombre",nombre);
                            crearUsuario.put("apellido", apellido);
                            crearUsuario.put("email", email);

                            mDatabase.child("Usuarios")
                                    .child(currentUser.getUid())
                                    .updateChildren(crearUsuario)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                currentUser.sendEmailVerification()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Log.e("TAG", "Email sent.");
                                                                }else{
                                                                    Log.e("TAG", "Email don't sent.");
                                                                }
                                                            }
                                                        });

                                                progressDialog.dismiss();
                                                Toast.makeText(mContext, "Registro Exitoso, porfavor verifica tu mail",Toast.LENGTH_LONG).show();
                                                mContext.startActivity(new Intent(mContext, LoginActivity.class));

                                            }else{
                                                firebaseAuth.signOut();
                                                progressDialog.dismiss();
                                                Toast.makeText(mContext, "No se creo Usuario en la base de datos",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });


                        }else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(mContext, "Registro fallo, prueba de nuevo",Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }
}
