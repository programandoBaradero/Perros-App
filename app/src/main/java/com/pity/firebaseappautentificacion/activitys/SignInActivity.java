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
import com.google.firebase.database.FirebaseDatabase;
import com.pity.firebaseappautentificacion.R;
import com.pity.firebaseappautentificacion.models.Usuario;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegistrate;
    private EditText edtxEmail;
    private EditText edtxPassword;
    private EditText edtxNombre;
    private EditText edtxApellido;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        inicializarFirebase();

        progressDialog = new ProgressDialog(this);

        edtxEmail = findViewById(R.id.edit_text_email_registro);
        edtxPassword = findViewById(R.id.edit_text_password_registro);
        edtxNombre = findViewById(R.id.edit_text_nombre_registro);
        edtxApellido = findViewById(R.id.edit_text_apellido_registro);
        btnRegistrate = findViewById(R.id.button_registrar_registro);


        btnRegistrate.setOnClickListener(this);


    }


    public void inicializarFirebase(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }


    public void registrarUsuario(){

        final String email = edtxEmail.getText().toString().trim();
        String password = edtxPassword.getText().toString().trim();
        final String nombre = edtxNombre.getText().toString().trim();
        final String apellido = edtxApellido.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(SignInActivity.this,"No se ingreso mail",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(SignInActivity.this,"No se ingreso contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(nombre)){
            Toast.makeText(SignInActivity.this,"No se ingreso nombre",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(apellido)){
            Toast.makeText(SignInActivity.this,"No se ingreso apellido",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registrando...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            final Usuario nuevoUsuario = new Usuario(nombre, apellido, email);
                            final FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                            firebaseDatabase.getReference("Usuarios")
                                    .child(currentUser.getUid())
                                    .setValue(nuevoUsuario)
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
                                        Toast.makeText(SignInActivity.this, "Registro Exitoso, porfavor verifica tu mail",Toast.LENGTH_LONG).show();
                                        finish();
                                        startActivity(new Intent(SignInActivity.this , LoginActivity.class));

                                    }else{
                                        firebaseAuth.signOut();
                                        finish();
                                        progressDialog.dismiss();
                                        Toast.makeText(SignInActivity.this, "No se creo Usuario en la base de datos",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                        }else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(SignInActivity.this, "Registro fallo, prueba de nuevo",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if (view == btnRegistrate){
            registrarUsuario();
        }

    }
}
