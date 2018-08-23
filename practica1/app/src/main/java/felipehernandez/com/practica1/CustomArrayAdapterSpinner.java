package felipehernandez.com.practica1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CustomArrayAdapterSpinner extends ArrayAdapter<ItemCustom> {

    private List<ItemCustom> lista;
    private Context context;

    public CustomArrayAdapterSpinner(@NonNull Context context, int resource, List<ItemCustom> objetos) {
        super(context, resource,objetos);

        this.lista = objetos;
        this.context = context;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customArrayAdapter(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customArrayAdapter(position, convertView, parent);
    }


    public View customArrayAdapter(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        int imageAleatorias[] = { R.drawable.ic_imagen1,R.drawable.ic_imagen2, R.drawable.ic_imagen3 };

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        View row = inflater.inflate(R.layout.layout_item_spinner, parent, false);

        ImageView imagen =  row.findViewById(R.id.imgLogo);

        imagen.setImageResource(imageAleatorias[lista.get(position).Imagen - 1]);

        TextView lbltitulo = row.findViewById(R.id.lblTitulo);

        TextView lblSubtitulo = row.findViewById(R.id.lblSubTitulo);

        lbltitulo.setText(lista.get(position).titulo);

        lblSubtitulo.setText(lista.get(position).subTitulo);

        return row;
    }


}
