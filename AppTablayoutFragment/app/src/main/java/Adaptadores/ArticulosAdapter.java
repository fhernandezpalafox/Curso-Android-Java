package Adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import Entidades.Articulo;
import felipehernandez.com.apptablayoutfragment.R;

public class ArticulosAdapter extends  RecyclerView.Adapter<ArticulosAdapter.ViewHolder> {

    private List<Articulo> articuloModel;

    public ArticulosAdapter(List<Articulo> articuloModel) {
        this.articuloModel = articuloModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);
        //  ViewHolder viewHolder = new ViewHolder(v);

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String nombre = articuloModel.get(position).getNombre();
        String descripcion =  articuloModel.get(position).getDescripcion();

        holder.titulo.setText(nombre);
        holder.descripcion.setText(descripcion);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return articuloModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo,descripcion;

        public ViewHolder(View v) {
            super(v);
            titulo = (TextView) v.findViewById(R.id.lblTitulo);
            descripcion = (TextView) v.findViewById(R.id.lblDescripcion);
        }

    }




}