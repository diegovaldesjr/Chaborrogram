package com.diegovaldesjr.platzigram.view;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegovaldesjr.platzigram.R;
import com.diegovaldesjr.platzigram.model.Picture;
import com.squareup.picasso.Picasso;

public class PictureDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        showToolbar("", true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setEnterTransition(new Fade());
        }
        setImageHeader();

    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //Soporte para versiones anteriores
        setSupportActionBar(toolbar);

        //Actualiza texto del toolbar
        getSupportActionBar().setTitle(tittle);

        //En caso de que tenga boton lo mostrara
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

        //Agregar Collapsing
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
    }

    //Asigna la imagen
    public void setImageHeader(){
        ImageView imageHeader = (ImageView) findViewById(R.id.imageHeader);
        Picture picture = (Picture) getIntent().getSerializableExtra("picture");

        Picasso.with(this).load(picture.getPicture()).into(imageHeader);

        //Actualizo el nombre y numero de likes
        TextView usernameDetail = (TextView) findViewById(R.id.usernameDetail);
        TextView likeNumberDetail = (TextView) findViewById(R.id.likeNumberDetail);

        usernameDetail.setText(picture.getUsername());
        likeNumberDetail.setText(picture.getLike_number());

    }
}
