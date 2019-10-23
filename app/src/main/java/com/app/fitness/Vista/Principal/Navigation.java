package com.app.fitness.Vista.Principal;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.fitness.R;
import com.app.fitness.Vista.Entreno.Entrenar;
import com.app.fitness.Vista.Hoy.Hoy;
import com.app.fitness.Vista.Perfil.Perfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Navigation extends AppCompatActivity {

    Hoy fragmentHoy = new Hoy();
    Entrenar fragmentEntrenar = new Entrenar();
    Perfil fragmentPerfil= new Perfil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        addFragment(fragmentHoy, "Hoy");

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            String tag = "";
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_hoy:
                    fragment = fragmentHoy;
                    tag = "Hoy";
                    break;
                case R.id.navigation_entrena:
                    fragment = fragmentEntrenar;
                    tag = "Entreno";
                    break;
                case R.id.navigation_nutricion:
                    tag = "Nutricion";
                    break;
                case R.id.navigation_perfil:
                    fragment = fragmentPerfil;
                    tag = "Perfil";
                    break;
            }

            replaceFragment(fragment, tag);

            return true;
        }
    };

    private void addFragment(Fragment fragment, String tag){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment, tag);
        fragmentTransaction.commit();
    }

    private void replaceFragment(Fragment fragment, String tag){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentManager fm = getSupportFragmentManager();
        androidx.fragment.app.Fragment currentFragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .show(fragment);
        } else {
            transaction
                    .hide(currentFragment)
                    .add(R.id.fragment_container, fragment, tag);
        }
        transaction.commit();
    }
}