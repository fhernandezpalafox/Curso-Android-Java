package felipehernandex.com.appcine;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaPeliculas extends AppCompatActivity {

    private ListView Lista;
    private ArrayList<Pelicula> listaPeliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_peliculas);

        Inicializar();
        Eventos();
    }


    public void Inicializar() {
        Lista = (ListView) findViewById(R.id.Lista);

        listaPeliculas = new ArrayList<>();
        listaPeliculas.add(new Pelicula("Ilucionistas 2", R.drawable.pelicula1, Float.parseFloat("2.5"), "Un año después de engañar al FBI y ganar la adulación del público con sus espectáculos de magia al estilo Robin Hood, Los Cuatro Jinetes reaparecen en una actuación aún más impresionante con la esperanza de exponer las prácticas poco éticas de un magnate de la tecnología. "));
        listaPeliculas.add(new Pelicula("Animales fantasticos", R.drawable.pelicula2, Float.parseFloat("1.5"), "Adaptación del libro homónimo de J.K. Rowling, un spin-of que amplía el mundo de la saga Harry Potter desde el punto de vista de Newt Scamander, un mago a quien le encargan escribir un libro sobre seres fantásticos. Se ambientará setenta años antes de lo narrado en las películas del mago."));
        listaPeliculas.add(new Pelicula("La era de hielo", R.drawable.pelicula3, Float.parseFloat("3.5"), "La épica persecución de Scrat de la escurridiza bellota, lo catapulta a un universo donde accidentalmente desata una serie de eventos cósmicos que transforman y amenazan al mundo de La Era de Hielo. "));
        listaPeliculas.add(new Pelicula("Scuadron suicida", R.drawable.pelicula4, Float.parseFloat("3.0"), "¡ESCUADRÓN SUICIDA: EDICIÓN EXTENDIDA es la versión definitiva con más acción y más Escuadrón! Mira la película con contenido extra nunca antes visto en cines. Ser malo se siente bien. Reúne un equipo de los supervillanos más peligrosos encarcelados, dales el arsenal más poderoso al alcance del gobierno y envíalos en una misión para derrotar a una entidad enigmática insuperable. "));

        AdaptadorPeliculas adaptadorPeliculas = new AdaptadorPeliculas(this);
        Lista.setAdapter(adaptadorPeliculas);
    }

    public void Eventos() {

    }

    class AdaptadorPeliculas extends ArrayAdapter<Pelicula> {

        AppCompatActivity appCompatActivity;

        public AdaptadorPeliculas(AppCompatActivity context) {
            super(context, R.layout.layout_pelicula, listaPeliculas);
            appCompatActivity = context;
        }


        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            ViewHolder holder;

            if (item == null) {

                LayoutInflater inflater = appCompatActivity.getLayoutInflater();
                item = inflater.inflate(R.layout.layout_pelicula, null);

                holder = new ViewHolder();

                holder.TexxtPelicula = (TextView) item.findViewById(R.id.TextPelicula);
                holder.TextDescripcionpelicula = (TextView) item.findViewById(R.id.TextDescPelicula);
                holder.imagen = (ImageView) item.findViewById(R.id.ImgPelicula);
                holder.ratingBarPelicula = (RatingBar) item.findViewById(R.id.ratingPelicula);


                item.setTag(holder);


            } else {

                holder = (ViewHolder) item.getTag();
            }

            holder.TexxtPelicula.setText(listaPeliculas.get(position).getNombrePelicula());
            holder.TextDescripcionpelicula.setText(listaPeliculas.get(position).getDescripcionPelicula());
            holder.imagen.setImageResource(listaPeliculas.get(position).getImagenPelicula());
            holder.ratingBarPelicula.setRating(listaPeliculas.get(position).getRatingPelicula());

            return item;
        }

    }

    //http://enekodelatorre.com/el-patron-viewholder/

    /*
       Uno de los elementos básicos de cualquier aplicación son las listas. Si nos fijamos, están prácticamente en cada pantalla y realmente son la mejor, si no la única, manera de mostrar una gran cantidad de datos de forma ordenada y clara.

        En Android las listas se suelen crear mediante Adapters a los que añadimos la información y, si queremos personalizar la lista mínimamente, también el diseño con el que se mostrarán.
        Pero esto que parece sencillo, se complica cuando queremos cargar listas muy largas, ya que nuestros dispositivos pueden alcanzar fácilmente ciertos límites de procesamiento y de memoria si no utilizamos ciertos patrones y buenas prácticas.

        Uno de estos es el patrón ViewHolder, con el que podemos conseguir un procesamiento un 15% más rápido de las listas mejorando así la fluidez y, por consiguiente, la experiencia de usuario.

        Después de que un adaptador cree o reutilice una vista, busca el lugar dentro del layout donde tiene que mostrar la información con el método findViewById(). Con el objeto View Holder conseguimos que esa referencia se establezca cuando se crea la vista y se guarde para no tener que volver a buscar.
        Básicamente lo que se consigue con este patrón es evitar utilizar el método findViewById() cada vez que se tenga que mostrar un nuevo elemento de la lista.

        Otro punto a tener en cuenta es que Google, desde Android 5.0, incluyó una nueva clase llamada RecyclerView, que viene a ser el sucesor a largo plazo del ListView, y su adaptador incluye por defecto este patrón. Así que parece interesante ir echándole un vistazo.
        Y como siempre, la mejor manera de verlo es con un ejemplo, así que allá voy:

    */


    static class ViewHolder {
        TextView TexxtPelicula;
        TextView TextDescripcionpelicula;
        ImageView imagen;
        RatingBar ratingBarPelicula;
    }
}
