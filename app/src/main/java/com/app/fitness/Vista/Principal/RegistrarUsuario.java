package com.app.fitness.Vista.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.fitness.R;
import com.app.fitness.Vista.RegistroUsuario.CodigoInstructor;

public class RegistrarUsuario extends AppCompatActivity {

    Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        initView();
    }

    private void initView() {

        btnRegistro = findViewById(R.id.btnRegistro);
    }

    public void registro(View view){
        Intent intent = new Intent(this, CodigoInstructor.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
