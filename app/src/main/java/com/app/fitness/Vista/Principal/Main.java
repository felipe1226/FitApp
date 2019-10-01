package com.app.fitness.Vista.Principal;

import android.content.Intent;
import android.os.Bundle;

import com.app.fitness.R;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    Button btnRegistro;
    Button btnSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        btnRegistro = findViewById(R.id.btnRegistro);
        btnSession = findViewById(R.id.btnSession);
    }

    public void registro(View view) {

        Intent intent = new Intent(this, RegistrarUsuario.class);
        startActivity(intent);
    }

    public void login(View view){

        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}

