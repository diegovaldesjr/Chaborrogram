package com.diegovaldesjr.platzigram.view;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diegovaldesjr.platzigram.R;
import com.diegovaldesjr.platzigram.view.fragment.HomeFragment;
import com.diegovaldesjr.platzigram.view.fragment.ProfileFragment;
import com.diegovaldesjr.platzigram.view.fragment.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        //Obtener bottombar
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottombar);

        //Definir fragment default, en este caso home
        bottomBar.setDefaultTab(R.id.home);

        //Estara escuchando cuando se presiona uno de los tabs
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.home:
                        //Creamos el un objeto del fragmento seleccionado
                        HomeFragment homeFragment = new HomeFragment();
                        //Para manejar los fragments (Fragment Manager)
                        //Utilizaremos una transaccion para añadir el fragment
                        //.add es para añadir un fragment pero eso pone un fragment sobre otro
                        //.replace donde quiero insertar el fragment, que fragmento se usara
                        //Se hace el commit para que se refleje el cambio
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment)
                                //Agregamos una transicion
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.profile:
                        ProfileFragment profileFragment = new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.search:
                        SearchFragment searchFragment = new SearchFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                }
            }
        });
    }
}
