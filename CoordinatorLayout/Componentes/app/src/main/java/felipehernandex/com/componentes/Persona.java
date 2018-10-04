package felipehernandex.com.componentes;

import android.media.Image;

/**
 * Created by felipehernandez on 03/11/16.
 */

public class Persona {

    private String Nombre;
    private String Empresa;
    private int Imagen;
    private int ImagenGrande;

    public Persona(String nombre,String empresa, int imagen,int imagenGrande){
        Nombre = nombre;
        Empresa = empresa;
        Imagen  =  imagen;
        ImagenGrande = imagenGrande;
    }

    public int getImagen() {
        return Imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public int getImagenGrande() {
        return ImagenGrande;
    }
}
