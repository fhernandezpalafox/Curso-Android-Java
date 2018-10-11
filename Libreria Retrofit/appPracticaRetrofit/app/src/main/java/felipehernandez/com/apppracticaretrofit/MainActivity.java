package felipehernandez.com.apppracticaretrofit;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.claudiodegio.msv.BaseMaterialSearchView;
import com.claudiodegio.msv.MaterialSearchView;
import com.claudiodegio.msv.OnSearchViewListener;

import java.util.List;

import Controladores.ArticulosController;
import Utilidades.SwipeHelper;

public class MainActivity extends AppCompatActivity {


    private RecyclerView reyclerViewArticulo;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fbButton;
    private BaseMaterialSearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializar();

        ObtenerDatos();

        EventoSwipe();

        SwipeRefresh();

        ToolbarInicializador();

        EventoBoton();

        mSearchView = (MaterialSearchView) findViewById(R.id.sv);
        mSearchView.setOnSearchViewListener(new OnSearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public void onQueryTextChange(String s) {
                Toast.makeText(getApplicationContext(),"Busqueda="+s,Toast.LENGTH_LONG).show();
            }
        });
        //mSearchView.setOnSearchViewListener(this); // this class implements OnSearchViewListener
    }

    public void BuscarEnLista(String queryBusqueda){

    }

    public void EventoBoton(){

        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AgregarActivity.class);
                startActivity(i);
            }
        });
    }

    public void ToolbarInicializador(){

        Toolbar toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Principal");

    }

    public void Inicializar(){

        reyclerViewArticulo =  findViewById(R.id.listaArticulos);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);

        fbButton =  findViewById(R.id.fbAgregar);

    }

    public void ObtenerDatos(){
        // usa esta configuración para mejorar el rendimiento si sabes que cambia
        // en el contenido no cambia el tamaño del diseño de RecyclerView
        reyclerViewArticulo.setHasFixedSize(true);

        // use a linear layout manager
        reyclerViewArticulo.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration divider = new DividerItemDecoration(reyclerViewArticulo.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
        reyclerViewArticulo.addItemDecoration(divider);

        ArticulosController articulosController =  new ArticulosController();
        articulosController.cargarArticulos(reyclerViewArticulo, this, null);
    }

    public void SwipeRefresh(){

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        ObtenerDatos();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                });
    }

    public void EventoSwipe(){

        SwipeHelper swipeHelper = new SwipeHelper(this, reyclerViewArticulo) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Eliminar",
                        0,
                        Color.parseColor("#FF3C30"),
                        new SwipeHelper.UnderlayButton.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: onDelete
                            }
                        }
                ));

                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Editar",
                        0,
                        Color.parseColor("#C7C7CB"),
                        new SwipeHelper.UnderlayButton.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: OnUnshare
                            }
                        }
                ));
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_buscar, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);

        return true;

       /* // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buscar, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        MaterialSearchView searchView = new MaterialSearchView(getApplicationContext());
        searchView.setMenuItem(item);
        */
        //final MenuItem searchItem = menu.findItem(R.id.action_search);

       /* SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();

            final SearchView finalSearchView = searchView;
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if( ! finalSearchView.isIconified()) {
                        finalSearchView.setIconified(true);
                    }
                    searchItem.collapseActionView();
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String s) {
                    Toast.makeText(getApplicationContext(),"Busqueda="+s,Toast.LENGTH_LONG).show();
                    return false;
                }
            });
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }*/

        //return true;
    }

}
