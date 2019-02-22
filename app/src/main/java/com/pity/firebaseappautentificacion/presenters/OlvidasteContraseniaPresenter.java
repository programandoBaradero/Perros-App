package com.pity.firebaseappautentificacion.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.pity.firebaseappautentificacion.activitys.OlvidasteContraseniaActivity;

public class OlvidasteContraseniaPresenter {
    private Context mContext;
    private FirebaseAuth mAuth;

    public OlvidasteContraseniaPresenter(Context context, FirebaseAuth firebaseAuth){
        this.mContext = context;
        this.mAuth = firebaseAuth;
    }

    public void enviarEmail(String email){
        if(TextUtils.isEmpty(email)){
            Toast.makeText(mContext,"No se ingreso mail",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(mContext, "Revisa tu email para restablecer contraseña",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(mContext, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
