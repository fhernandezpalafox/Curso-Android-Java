package com.example.felipehernandez.cardviewejemplo;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by FelipeHernandez on 31/05/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>  {


    static class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        Context context;

        public MyMenuItemClickListener(Context _context) {
            context = _context;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu1:
                    Toast.makeText(context, "Action 1", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.menu2:
                    Toast.makeText(context, "Action 2", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;;
        ImageButton overflowMenu;

        PersonViewHolder(View itemView, final List<Person> persons) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            overflowMenu = (ImageButton) itemView.findViewById(R.id.img_menu);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    String nombre  = persons.get(position).name;

                    Snackbar.make(v, "Click detected on item " + position +" nombre "+nombre,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            overflowMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(v.getContext(), v);
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.menu_card, popup.getMenu());
                    popup.setOnMenuItemClickListener(new MyMenuItemClickListener(v.getContext()));
                    popup.show();
                }
            });
        }
    }

    public List<Person> persons;
    public Context context;

    RVAdapter(List<Person> persons,Context _context){
        this.persons = persons;
        this.context  = _context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v,this.persons);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).name);
        personViewHolder.personAge.setText(persons.get(i).age);
        personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

}
