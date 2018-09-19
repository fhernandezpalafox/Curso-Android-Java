package modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by felipehernandez on 14/11/15.
 */
public class EventosOpenHelper  extends SQLiteOpenHelper {

    public  static final  String DATABASE_NAME = "Eventos.db";
    public  static final  int  DATABASE_VERSION  = 1;

    public EventosOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EventosDatasource.CREATE_EVENTOS_SCRIPT);
        db.execSQL(EventosDatasource.INSERT_EVENTOS_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
