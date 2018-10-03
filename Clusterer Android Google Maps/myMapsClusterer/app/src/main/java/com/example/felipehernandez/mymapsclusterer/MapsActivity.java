package com.example.felipehernandez.mymapsclusterer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ClusterManager<Marcador> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        configurarClusterer();
    }


    public void configurarClusterer(){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.1523342,-101.7113132),10f));

        mClusterManager =  new ClusterManager<>(this,mMap);

        agregarMarcadores();

    }

    public void agregarMarcadores(){

        CustomClusterRenderers renderers  =  new CustomClusterRenderers(this,mMap,mClusterManager);

        mClusterManager.setRenderer(renderers);


        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(new CustomInfoViewAdapter(LayoutInflater.from(this)));


        mMap.setOnInfoWindowClickListener(mClusterManager);
        mMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);


        mClusterManager.addItem(new Marcador(-101.70421,21.15255,"Luagr 1"));
        mClusterManager.addItem(new Marcador(-101.66181,21.1612,"Luagr 2" ));
        mClusterManager.addItem(new Marcador(-101.67709,21.14855,"Luagr 3"));
        mClusterManager.addItem(new Marcador(-101.70679,21.12533,"Luagr 4"));
        mClusterManager.addItem(new Marcador(-101.64602,21.15095,"Luagr 5"));
        mClusterManager.addItem(new Marcador(-101.68653,21.12789,"Luagr 6"));
        mClusterManager.addItem(new Marcador(-101.6546,21.1343,"Luagr 7"));
        mClusterManager.addItem(new Marcador(-101.66181,21.1612,"Luagr 8"));
        mClusterManager.addItem(new Marcador(-101.69769,21.14391,"Lugar 9","http://www.youtube.com"));



       /* mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<Marcador>() {
            @Override
            public boolean onClusterItemClick(Marcador marcador) {
                Toast.makeText(getApplicationContext(),"Titulo: "+marcador.getTitle(),Toast.LENGTH_LONG).show();
                return true;
            }
        });*/

        mClusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener<Marcador>() {
            @Override
            public void onClusterItemInfoWindowClick(Marcador marcador) {

                String url = (marcador.url != null)?marcador.url:"http://www.google.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });




        mClusterManager.cluster();

    }


    public class CustomClusterRenderers extends DefaultClusterRenderer<Marcador> {

        private Context mContext;
        private GoogleMap map;
        private ClusterManager<Marcador> clusterManager;
        private IconGenerator mClusterIconGenerator;

        public CustomClusterRenderers(Context context, GoogleMap map, ClusterManager<Marcador> clusterManager) {
            super(context, map, clusterManager);
            this.mContext = context;
            this.map = map;
            this.clusterManager = clusterManager;

            mClusterIconGenerator = new IconGenerator(mContext.getApplicationContext());
        }

        @Override
        protected void onBeforeClusterItemRendered(Marcador item, MarkerOptions markerOptions) {
            super.onBeforeClusterItemRendered(item, markerOptions);

            BitmapDescriptor markerDescriptor =   BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
            markerOptions.icon(markerDescriptor).snippet(item.getTitle()).title(item.getTitle());

        }
    }


    public class CustomInfoViewAdapter implements GoogleMap.InfoWindowAdapter{

        private LayoutInflater mInflater;

        public CustomInfoViewAdapter(LayoutInflater mInflater) {
            this.mInflater = mInflater;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            View popup  = mInflater.inflate(R.layout.infowindow,null);
            TextView lblinformacion =  popup.findViewById(R.id.lblTitulo);
            lblinformacion.setText(marker.getSnippet());

            ImageView img = popup.findViewById(R.id.imagen);
            img.setImageDrawable(getResources().getDrawable(R.mipmap.ic_arrow));

            return popup;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View popup  = mInflater.inflate(R.layout.infowindow,null);
            TextView lblinformacion =  popup.findViewById(R.id.lblTitulo);
            lblinformacion.setText(marker.getSnippet().toString());

            ImageView img = popup.findViewById(R.id.imagen);
            img.setImageDrawable(getResources().getDrawable(R.mipmap.ic_arrow));

            return popup;
        }
    }


    public class Marcador implements ClusterItem {

        private LatLng mPosition;

        Double lat;
        Double lng;
        String title;

        String url;

        public Marcador(Double lng,Double lat,String title){
            this.lat  =  lat;
            this.lng  =  lng;
            this.title  = title;

            mPosition  =  new LatLng(this.lat,this.lng);

        }


        public Marcador(Double lng,Double lat,String title, String url){
            this.lat  =  lat;
            this.lng  =  lng;
            this.title  = title;

            this.url  = url;

            mPosition  =  new LatLng(this.lat,this.lng);

        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }
    }

}
