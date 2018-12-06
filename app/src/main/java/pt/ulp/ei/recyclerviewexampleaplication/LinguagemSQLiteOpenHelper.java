package pt.ulp.ei.recyclerviewexampleaplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LinguagemSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final  String DB_NAME="linguagens.db";
    public static final  int DB_VERSION=1;
    public static final  String COLUMN_ID="_id";
    public static final  String COLUMN_DESIGNACAO= "designacao";
    public static final  String COLUMN_VALOR = "valor";
    public static final  String TABLE_LINGUAGEM = "linguagem";

    // instrução SQL para criar a base de dados
    public static final  String DB_CREATE= "create table "+ TABLE_LINGUAGEM +
            "("+
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_DESIGNACAO +  " text not null , " +
            COLUMN_VALOR + " integer " +
            " );";


    public LinguagemSQLiteOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        Log.d("DB-Linguagem", "DB_CREATE = " + DB_CREATE);
        database.execSQL(DB_CREATE);
        Log.d("DB-Linguagem", "fim  database.execSQL(DB_CREATE); ------------------------------------");
    }


   // @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){

    }


}


