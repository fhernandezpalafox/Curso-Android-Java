package felipehernandex.com.appcine;

/**
 * Created by felipehernandez on 20/11/16.
 */

public class Pelicula {

    private String nombrePelicula;
    private int imagenPelicula;
    private float ratingPelicula;
    private String descripcionPelicula;


    public Pelicula(String _nombrePelicula, int _imagenPelicula, float _ratingpelicula, String _descripcionPelicula){
        nombrePelicula = _nombrePelicula;
        imagenPelicula = _imagenPelicula;
        ratingPelicula = _ratingpelicula;
        descripcionPelicula = _descripcionPelicula;
    }

    public String getDescripcionPelicula() { return descripcionPelicula; }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public int getImagenPelicula() {
        return imagenPelicula;
    }

    public float getRatingPelicula() {
        return ratingPelicula;
    }
}
