package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by felipehernandez on 14/11/15.
 */
public class EventosDatasource {
    public static final String EVENTOS_TABLE_NAME="Eventos";
    public static final String STRING_TYPE="text";
    public static final String INT_TYPE="integer";

    public  static class ColumnEventos {
        public static String ID_EVENTO = BaseColumns._ID;
        public static String DESCRIPCION_EVENTO = "descripcion";
        public static String DIA_EVENTO = "dia";
    }

    public static  final String CREATE_EVENTOS_SCRIPT =
            "create table "+ EVENTOS_TABLE_NAME+ "("+
                    ColumnEventos.ID_EVENTO+ " "+INT_TYPE+ "  primary key  autoincrement,"+
                    ColumnEventos.DESCRIPCION_EVENTO+ " "+STRING_TYPE+ " not null,"+
                    ColumnEventos.DIA_EVENTO + " "+STRING_TYPE+ "  not null)";

    public static final  String INSERT_EVENTOS_SCRIPT =
            "insert into "+EVENTOS_TABLE_NAME+ "  values "+
                    "(null,'Ignaguracion del festival del globo','Virenes 13'),"+
                    "(null,'Presentacion de grupos musicales','Sabado 14'),"+
                    "(null,'Viajes en Globo','Domingo 15')";


    private EventosOpenHelper openHelper;
    private SQLiteDatabase database;

    public EventosDatasource(Context context) {
        openHelper =  new EventosOpenHelper(context);
        database  =  openHelper.getWritableDatabase();
    }


    public Cursor consultarEventos()
    {
      return  database.rawQuery("select _id,descripcion,dia  from "+EVENTOS_TABLE_NAME,null);
    }

    public Cursor consultarEventos(int idEvento)
    {
        return  database.rawQuery("select _id,descripcion,dia  from "+EVENTOS_TABLE_NAME+"  where _id = "+idEvento,null);
    }


    public void guardarEvento(String descripcion,String dia)
    {
        ContentValues values  =  new ContentValues();

        values.put(ColumnEventos.DESCRIPCION_EVENTO,descripcion);
        values.put(ColumnEventos.DIA_EVENTO,dia);

        database.insert(EVENTOS_TABLE_NAME,null,values);
    }


    public void modificarEvento(String descripcion,String dia, int IdEvento)
    {
        ContentValues values  =  new ContentValues();
        values.put(ColumnEventos.DESCRIPCION_EVENTO,descripcion);
        values.put(ColumnEventos.DIA_EVENTO, dia);

        String[] a =  {""+IdEvento};
        database.update(EVENTOS_TABLE_NAME, values, "_id=?", a);
    }


    public  boolean eliminarEvento(int IdEvento)
    {

        String[]  whereArgs  = {""+IdEvento};
        try
        {
            database.delete(EVENTOS_TABLE_NAME,"_id=?",whereArgs);
            return true;
        }catch(Exception ex)
        {
            return false;
        }finally {
            database.close();
        }
    }


}
