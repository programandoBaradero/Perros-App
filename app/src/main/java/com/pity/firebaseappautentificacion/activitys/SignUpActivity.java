package com.pity.firebaseappautentificacion.activitys;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pity.firebaseappautentificacion.R;
import com.pity.firebaseappautentificacion.models.Usuario;
import com.pity.firebaseappautentificacion.presenters.SignUpPresenter;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegistrate;
    private EditText edtxEmail;
    private EditText edtxPassword;
    private EditText edtxNombre;
    private EditText edtxApellido;

    private SignUpPresenter presenter;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        inicializarFirebase();

        presenter = new SignUpPresenter(this, firebaseAuth, firebaseDatabase);

        edtxEmail = findViewById(R.id.edit_text_email_registro);
        edtxPassword = findViewById(R.id.edit_text_password_registro);
        edtxNombre = findViewById(R.id.edit_text_nombre_registro);
        edtxApellido = findViewById(R.id.edit_text_apellido_registro);
        btnRegistrate = findViewById(R.id.button_registrar_registro);


        btnRegistrate.setOnClickListener(this);


    }


    public void inicializarFirebase(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
    }




    @Override
    public void onClick(View view) {
        if (view == btnRegistrate){

            final String email = edtxEmail.getText().toString().trim();
            final String password = edtxPassword.getText().toString().trim();
            final String nombre = edtxNombre.getText().toString().trim();
            final String apellido = edtxApellido.getText().toString().trim();

            presenter.registrarUsuario(email,password,nombre,apellido);
        }

    }
}
