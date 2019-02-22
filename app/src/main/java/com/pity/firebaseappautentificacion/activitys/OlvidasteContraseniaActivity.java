package com.pity.firebaseappautentificacion.activitys;

import android.content.Intent;
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
import com.pity.firebaseappautentificacion.presenters.OlvidasteContraseniaPresenter;

public class OlvidasteContraseniaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etxMail;
    private Button btnEnviar;

    private FirebaseAuth firebaseAuth;
    private OlvidasteContraseniaPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidaste_contrasenia);

        etxMail = findViewById(R.id.edit_text_email_olvidate_contrasenia);
        btnEnviar = findViewById(R.id.button_enviar_olvidaste_contrasenia);

        firebaseAuth = FirebaseAuth.getInstance();

        presenter = new OlvidasteContraseniaPresenter(this, firebaseAuth);

        btnEnviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnEnviar){
            String email = etxMail.getText().toString().trim();
            presenter.enviarEmail(email);
            startActivity(new Intent(OlvidasteContraseniaActivity.this, LoginActivity.class));
            finish();
        }
    }


}
