package com.pity.firebaseappautentificacion.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pity.firebaseappautentificacion.R;
import com.pity.firebaseappautentificacion.activitys.LoginActivity;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private TextView txUserMail;
    private Button btnCerrarSesion;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        txUserMail = findViewById(R.id.text_view_user_mail);
        txUserMail.setText("Bienvenido " + user.getEmail());

        btnCerrarSesion = findViewById(R.id.button_cerrar_sesion);

        btnCerrarSesion.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);


    }

    @Override
    public void onClick(View view) {

        if (view == btnCerrarSesion){
            progressDialog.setMessage("Cerrando sesion...");
            progressDialog.show();
            firebaseAuth.signOut();
            progressDialog.dismiss();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}
