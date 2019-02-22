package com.pity.firebaseappautentificacion.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.pity.firebaseappautentificacion.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardViewAdoptar;
    private CardView cardViewPerdidos;
    private CardView cardViewApadrinar;
    private CardView cardViewHogaresTransicion;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        firebaseAuth = FirebaseAuth.getInstance();


        cardViewAdoptar = findViewById(R.id.cardViewAdoptar);
        cardViewPerdidos = findViewById(R.id.cardViewPerdidos);
        cardViewApadrinar = findViewById(R.id.cardViewApadrinar);
        cardViewHogaresTransicion = findViewById(R.id.cardViewHogaresTransicion);

        cardViewAdoptar.setOnClickListener(this);
        cardViewPerdidos.setOnClickListener(this);
        cardViewApadrinar.setOnClickListener(this);
        cardViewHogaresTransicion.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);


    }

    @Override
    public void onClick(View view) {
        if (view == cardViewAdoptar){
            Toast.makeText(MenuActivity.this, "Adoptar, soon!", Toast.LENGTH_LONG).show();
        }
        if (view == cardViewPerdidos){
            startActivity(new Intent(MenuActivity.this, PerrosPerdidosActivity.class));
        }
        if (view == cardViewHogaresTransicion){
            Toast.makeText(MenuActivity.this, "Hogares de Transicion, soon!", Toast.LENGTH_LONG).show();
        }
        if (view == cardViewApadrinar){
            Toast.makeText(MenuActivity.this, "Apadrinar, soon!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_profile:
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                return true;
            case R.id.item_logout:
                progressDialog.setMessage("Cerrando sesion...");
                progressDialog.show();
                firebaseAuth.signOut();
                progressDialog.dismiss();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
