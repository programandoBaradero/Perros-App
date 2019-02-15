package com.pity.firebaseappautentificacion.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pity.firebaseappautentificacion.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnRegistrate;
    private Button btnLogin;
    private EditText etxMail;
    private EditText etxPassword;
    private TextView txOlvidastePassword;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            // Actividad del perfil
            finish();
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        }


        btnRegistrate = findViewById(R.id.button_registrate);
        btnLogin = findViewById(R.id.button_login);
        etxMail = findViewById(R.id.edit_text_user);
        etxPassword = findViewById(R.id.edit_text_password);
        txOlvidastePassword = findViewById(R.id.text_view_olvidaste_password);

        btnRegistrate.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        txOlvidastePassword.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

    }


    @Override
    public void onClick(View view) {

        if (view == btnRegistrate){
            Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
            startActivity(intent);
        }
        if (view == btnLogin){
            userLogin();
        }
        if (view == txOlvidastePassword){
            startActivity(new Intent(LoginActivity.this,OlvidasteContraseniaActivity.class));

        }





    }

    private void userLogin() {
        String email = etxMail.getText().toString().trim();
        String password = etxPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(LoginActivity.this,"No se ingreso mail",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"No se ingreso contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Iniciando sesion...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this , "Usuario o contraseña incorrecta" ,Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}
