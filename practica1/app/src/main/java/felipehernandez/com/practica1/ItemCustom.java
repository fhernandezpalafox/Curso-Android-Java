package felipehernandez.com.practica1;

public class ItemCustom {

    public String titulo;
    public String subTitulo;
    public int Imagen;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }

    public ItemCustom(String titulo, String subTitulo, int imagen) {
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        Imagen = imagen;
    }
}
