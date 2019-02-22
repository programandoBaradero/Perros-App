package com.pity.firebaseappautentificacion.activitys;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.pity.firebaseappautentificacion.R;
import com.pity.firebaseappautentificacion.presenters.LoginPresenter;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnRegistrate;
    private Button btnLogin;
    private EditText etxMail;
    private EditText etxPassword;
    private TextView txOlvidastePassword;

    private LoginPresenter presenter;

    private FirebaseAuth firebaseAuth;

    boolean olvidatePassword;
    boolean regitro;


    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseAuth.getCurrentUser() != null){
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        presenter = new LoginPresenter(this, firebaseAuth);
        olvidatePassword = false;
        regitro = false;

        if (firebaseAuth.getCurrentUser() != null) {
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


    }


    @Override
    public void onClick(View view) {

        if (view == btnRegistrate) {
            regitro = true;
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
        if (view == btnLogin) {
            String email =  etxMail.getText().toString().trim();
            String password = etxPassword.getText().toString().trim();
            presenter.loginUser(email, password);


        }
        if (view == txOlvidastePassword) {
            olvidatePassword = true;
            startActivity(new Intent(LoginActivity.this, OlvidasteContraseniaActivity.class));

        }


    }


}
