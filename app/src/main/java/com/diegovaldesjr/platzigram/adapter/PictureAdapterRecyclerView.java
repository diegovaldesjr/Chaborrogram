package com.diegovaldesjr.platzigram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegovaldesjr.platzigram.R;
import com.diegovaldesjr.platzigram.model.Picture;
import com.diegovaldesjr.platzigram.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by diego on 11/10/2017.
 */

//Se va encargar de sincronizar una lista (arrayList) para reutilizar las tarjetas
//El adapter va a recibir una coleccion de PictureViewHolder porque
public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder>{

    //Arreglo de datos tipo Picture que se manejara
    private ArrayList<Picture> pictures;
    //Recurso que sera el recurso que hicimos (cardview, el xml)
    private int resource;
    //Actividad de donde se esta llamando a esta clase
    private Activity activity;

    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    //Inicializa la clase PictureViewHolder
    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflar es mostrar el layout (xml) en terminos de codigo java (convirtiendo en un View)
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);

        return new PictureViewHolder(view);
    }

    //Se trabajara con toda la lista
    //Realiza el paso de datos de cada objeto de la lista de Picture
    //Se tiene acceso a cada view
    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        //Recorre la lista y asigna los objetos a las cardviews
        final Picture picture = pictures.get(position);

        //A traves de este objeto se puede tener acceso a los views que conforman el cardview
        holder.usernameCard.setText(picture.getUsername());
        holder.timeCard.setText(picture.getTime());
        holder.likeNumberCard.setText(picture.getLike_number());
        //Asigna imagen de internet
        Picasso.with(activity).load(picture.getPicture()).into(holder.imageCard);

        //Agregar un listener, accion que se ejecutara al dar click en la imagen
        holder.imageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se hace asi porque el contexto en el que estamos es el de un Fragment
                Intent intent = new Intent(activity, PictureDetailActivity.class);
                //Pasar datos entre actividad
                //intent.putExtra("picture", picture.getPicture());

                //Pasar objeto, tiene que implementar serializable
                intent.putExtra("picture", picture);

                //Si la version de SDK es mayor o igual a la Lollipop ejecuta la transicion, sino no
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();

                    //Duracion
                    explode.setDuration(1000);

                    //Asignar transicion
                    //Si no fuera personalizado el objeto explode solo se instancia en setExitTransition
                    activity.getWindow().setExitTransition(explode);
                    //contexto, view y nombre de la transicion dependiente
                    activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.transitionname_picture)).toBundle());
                }else{
                    activity.startActivity(intent);
                }
            }
        });
    }

    //Devuelve el conteo de cuantos elementos se tiene
    @Override
    public int getItemCount() {
        return pictures.size();
    }

    //Clase que trabajara los views que componen al cardview, se concentra en todos los views que componen la tarjeta
    //Esta clase solo se concentrara en un card a la vez
    public class PictureViewHolder extends RecyclerView.ViewHolder{

        //Definimos los views que componen al cardview que manejaremos
        private ImageView imageCard;
        private TextView usernameCard;
        private TextView timeCard;
        private TextView likeNumberCard;
        private CheckBox likeCheckCard;

        //Al instancear con la clase PictureViewHolder, lo que se pasa al constructor sera el objeto view (en este caso, el cardview)
        // se usara el objeto view
        public PictureViewHolder(View itemView) {
            super(itemView);

            imageCard = (ImageView) itemView.findViewById(R.id.imageCard);
            usernameCard = (TextView) itemView.findViewById(R.id.usernameCard);
            timeCard = (TextView) itemView.findViewById(R.id.timeCard);
            likeNumberCard = (TextView) itemView.findViewById(R.id.likeNumberCard);
            likeCheckCard = (CheckBox) itemView.findViewById(R.id.likeCheckCard);
        }
    }
}
