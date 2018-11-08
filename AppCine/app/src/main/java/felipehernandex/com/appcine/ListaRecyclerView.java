package felipehernandex.com.appcine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ListaRecyclerView extends AppCompatActivity {

    RecyclerView Lista;
    private ArrayList<Pelicula> listaPeliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recycler_view);

        Inicializar();
        Eventos();
    }

    public void Inicializar() {
        Lista = (RecyclerView) findViewById(R.id.Lista);
        Lista.setHasFixedSize(true);

        listaPeliculas = new ArrayList<Pelicula>();
        listaPeliculas.add(new Pelicula("Ilucionistas 2", R.drawable.pelicula1, Float.parseFloat("2.5"), "Un año después de engañar al FBI y ganar la adulación del público con sus espectáculos de magia al estilo Robin Hood, Los Cuatro Jinetes reaparecen en una actuación aún más impresionante con la esperanza de exponer las prácticas poco éticas de un magnate de la tecnología. "));
        listaPeliculas.add(new Pelicula("Animales fantasticos", R.drawable.pelicula2, Float.parseFloat("1.5"), "Adaptación del libro homónimo de J.K. Rowling, un spin-of que amplía el mundo de la saga Harry Potter desde el punto de vista de Newt Scamander, un mago a quien le encargan escribir un libro sobre seres fantásticos. Se ambientará setenta años antes de lo narrado en las películas del mago."));
        listaPeliculas.add(new Pelicula("La era de hielo", R.drawable.pelicula3, Float.parseFloat("3.5"), "La épica persecución de Scrat de la escurridiza bellota, lo catapulta a un universo donde accidentalmente desata una serie de eventos cósmicos que transforman y amenazan al mundo de La Era de Hielo. "));
        listaPeliculas.add(new Pelicula("Scuadron suicida", R.drawable.pelicula4, Float.parseFloat("3.0"), "¡ESCUADRÓN SUICIDA: EDICIÓN EXTENDIDA es la versión definitiva con más acción y más Escuadrón! Mira la película con contenido extra nunca antes visto en cines. Ser malo se siente bien. Reúne un equipo de los supervillanos más peligrosos encarcelados, dales el arsenal más poderoso al alcance del gobierno y envíalos en una misión para derrotar a una entidad enigmática insuperable. "));

        AdaptadorPeliculas adaptadorPeliculas = new AdaptadorPeliculas(listaPeliculas);

        adaptadorPeliculas.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Pelicula item = listaPeliculas.get(position);

                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                item.getNombrePelicula(), Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        // Usar un administrador para LinearLayout
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        Lista.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(Lista.getContext(), layoutManager.getOrientation());
        Lista.addItemDecoration(dividerItemDecoration);


        Lista.setItemAnimator(new DefaultItemAnimator());
        Lista.setAdapter(adaptadorPeliculas);


    }

    public void Eventos() {

    }


    public static class AdaptadorPeliculas
            extends RecyclerView.Adapter<AdaptadorPeliculas.PeliculaViewHolder> {

        private View.OnClickListener listener;
        private OnItemClickListener onItemClickListener;
        private ArrayList<Pelicula> listaPeliculas;

        public AdaptadorPeliculas(ArrayList<Pelicula> datos) {
            this.listaPeliculas = datos;
        }


        @Override
        public AdaptadorPeliculas.PeliculaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pelicula, parent, false);
            PeliculaViewHolder viewHolder = new PeliculaViewHolder(item);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder(AdaptadorPeliculas.PeliculaViewHolder holder, int position) {

            final Pelicula item = listaPeliculas.get(position);
            holder.bindPelicula(item);
        }

        @Override
        public int getItemCount() {

            return listaPeliculas.size();
        }


        public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }



        //clase PeliculaViewHolder
        public class PeliculaViewHolder
                   extends RecyclerView.ViewHolder
                       implements View.OnClickListener{

            private TextView TexxtPelicula;
            private TextView TextDescripcionpelicula;
            private ImageView imagen;
            private RatingBar ratingBarPelicula;

            public PeliculaViewHolder(View itemView) {
                super(itemView);

                TexxtPelicula = (TextView) itemView.findViewById(R.id.TextPelicula);
                TextDescripcionpelicula = (TextView) itemView.findViewById(R.id.TextDescPelicula);
                imagen = (ImageView) itemView.findViewById(R.id.ImgPelicula);
                ratingBarPelicula = (RatingBar) itemView.findViewById(R.id.ratingPelicula);

                //Asignar onclick anuestro item
                itemView.setOnClickListener(this);
            }

            public void bindPelicula(Pelicula p) {
                TexxtPelicula.setText(p.getNombrePelicula());
                TextDescripcionpelicula.setText(p.getDescripcionPelicula());
                imagen.setImageResource(p.getImagenPelicula());
                ratingBarPelicula.setRating(p.getRatingPelicula());
            }

            @Override
            public void onClick(View view) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(view,getAdapterPosition());
            }
        }

    }

    //Declaracion de la interfaz pra nuestro onclick
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

}
