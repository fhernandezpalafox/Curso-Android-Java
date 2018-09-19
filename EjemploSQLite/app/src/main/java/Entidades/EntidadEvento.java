package Entidades;

/**
 * Created by felipehernandez on 26/11/15.
 */
public class EntidadEvento {

    public   int ID_EVENTO = 0;
    public   String DESCRIPCION_EVENTO = "";
    public   String DIA_EVENTO="";

    public EntidadEvento(){}

    public EntidadEvento(int ID_EVENTO, String DESCRIPCION_EVENTO, String DIA_EVENTO) {
        this.ID_EVENTO = ID_EVENTO;
        this.DESCRIPCION_EVENTO = DESCRIPCION_EVENTO;
        this.DIA_EVENTO = DIA_EVENTO;
    }

    public int getID_EVENTO() {
        return ID_EVENTO;
    }

    public void setID_EVENTO(int ID_EVENTO) {
        this.ID_EVENTO = ID_EVENTO;
    }

    public String getDESCRIPCION_EVENTO() {
        return DESCRIPCION_EVENTO;
    }

    public void setDESCRIPCION_EVENTO(String DESCRIPCION_EVENTO) {
        this.DESCRIPCION_EVENTO = DESCRIPCION_EVENTO;
    }

    public String getDIA_EVENTO() {
        return DIA_EVENTO;
    }

    public void setDIA_EVENTO(String DIA_EVENTO) {
        this.DIA_EVENTO = DIA_EVENTO;
    }
}



