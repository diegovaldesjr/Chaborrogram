package com.diegovaldesjr.platzigram.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diegovaldesjr.platzigram.R;
import com.diegovaldesjr.platzigram.adapter.PictureAdapterRecyclerView;
import com.diegovaldesjr.platzigram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        showToolbar("", false, view);

        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureProfileRecycler);

        //Darle forma al recyclerview, este tiene la orientacion y la forma de mostrar las tarjetas
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //Actualizamos el layoutManager del recyclerview
        picturesRecycler.setLayoutManager(linearLayoutManager);

        //Ponemos nuestro adapter
        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(buidPictures(), R.layout.cardview_picture, getActivity());

        //Actualizamos el adapter
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        //Soporte para versiones anteriores
        //Obtengo el activity que esta del fragment y lo casteo a un APPCompatActivity para obtener el soporte del toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        //Actualiza texto del toolbar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);

        //En caso de que tenga boton lo mostrara
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


    public ArrayList<Picture> buidPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://www.construccionescamarena.com/images/phocagallery/negocios/instalaciones_deportivas/skate_park/skate_park_02.jpg", "Diego Valdes", "2 dias", "11 Me Gusta"));
        pictures.add(new Picture("http://lorempixel.com/580/250/nature/1", "Diego Valdes", "3 dias", "118 Me Gusta"));
        pictures.add(new Picture("http://lorempixel.com/580/250/nature/2", "Diego Valdes", "20 dias", "2021 Me Gusta"));
        return pictures;
    }
}
