package com.pity.firebaseappautentificacion.presenters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pity.firebaseappautentificacion.activitys.MenuActivity;


public class LoginPresenter {
    private Context mContext;
    private FirebaseAuth firebaseAuth;

    public LoginPresenter(Context loginContext, FirebaseAuth mAuth){
        this.mContext = loginContext;
        this.firebaseAuth = mAuth;
    }

    public void loginUser(String email, String password){

        if(TextUtils.isEmpty(email)){
            Toast.makeText(mContext,"No se ingreso mail",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(mContext,"No se ingreso contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Iniciando Sesion...");
        progressDialog.setCancelable(false);
        progressDialog.show();



        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            mContext.startActivity(new Intent(mContext, MenuActivity.class));
                        }else{
                            Toast.makeText(mContext , "Usuario o contraseña incorrecta" ,Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}
