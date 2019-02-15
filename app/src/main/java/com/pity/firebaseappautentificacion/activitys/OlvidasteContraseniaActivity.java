package com.pity.firebaseappautentificacion.activitys;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.pity.firebaseappautentificacion.R;

public class OlvidasteContraseniaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etxMail;
    private Button btnEnviar;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidaste_contrasenia);

        etxMail = findViewById(R.id.edit_text_email_olvidate_contrasenia);
        btnEnviar = findViewById(R.id.button_enviar_olvidaste_contrasenia);

        firebaseAuth = FirebaseAuth.getInstance();

        btnEnviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnEnviar){
            enviarMail();
        }
    }

    public void enviarMail(){
        String email = etxMail.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(OlvidasteContraseniaActivity.this,"No se ingreso mail",Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(OlvidasteContraseniaActivity.this, "Revisa tu email para restablecer contraseña",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(OlvidasteContraseniaActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
