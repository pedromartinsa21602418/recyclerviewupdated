package pt.ulp.ei.recyclerviewexampleaplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LinguagemDataSource {
    
    private SQLiteDatabase database;
    private LinguagemSQLiteOpenHelper dbHelper;
    private String[] colunasTabela = {
            LinguagemSQLiteOpenHelper.COLUMN_ID,
            LinguagemSQLiteOpenHelper.COLUMN_DESIGNACAO ,
            LinguagemSQLiteOpenHelper.COLUMN_VALOR
            };

    public LinguagemDataSource(Context context){
        dbHelper = new LinguagemSQLiteOpenHelper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase(); // método herdado de SQLiteOpenHelper
    }

    public void close(){
        dbHelper.close();
    }
    public void criarTabela()
    {
        dbHelper.onCreate(database);
    }

    // insere a designção da linguagem
    // e devolve um objeto linguagem (_id, designação da linguagem e valor )
    public Linguagem createLinguagem(String designacaoLinguagem) {
        ContentValues values = new ContentValues();
        values.put(colunasTabela[1], designacaoLinguagem);
        values.put(colunasTabela[2], 0); // valor = 0
        long inserirId = database.insert(LinguagemSQLiteOpenHelper.TABLE_LINGUAGEM,
                                         null, values);
        Linguagem novaLinguagem = null;
        if (inserirId == -1 ) {
            Log.d("DB-Linguagens", "erro ao inserir:  "+ designacaoLinguagem);
        } else {
            Cursor cursor = database.query(LinguagemSQLiteOpenHelper.TABLE_LINGUAGEM,
                    colunasTabela,
                    LinguagemSQLiteOpenHelper.COLUMN_ID + " = " + inserirId,
                    null, null, null, null);
            cursor.moveToFirst();
            novaLinguagem = cursorToLinguagem(cursor);

            cursor.close();
        }
        return novaLinguagem;
    }

    public void deleteLinguagem(Linguagem linguagem){
        long id = linguagem.getId();
        database.delete(LinguagemSQLiteOpenHelper.TABLE_LINGUAGEM,
                LinguagemSQLiteOpenHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Linguagem> getAllLinguagens(){

        List<Linguagem> todasLinguagens = new ArrayList<Linguagem>();
        Cursor cursor = database.query(LinguagemSQLiteOpenHelper.TABLE_LINGUAGEM,
                colunasTabela, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Linguagem l = cursorToLinguagem(cursor);
            todasLinguagens.add(l);
            cursor.moveToNext();
        }
        cursor.close();
        return todasLinguagens;

    }
    // Converter um registo Cursor (estrutura correspondente a uma linha / registo da tabela)
    //  numa instância da Classe (ou seja, objeto) Linguagem correspondente
    private Linguagem cursorToLinguagem(Cursor cursor){
        Linguagem linguagem = new Linguagem();
        linguagem.setId(cursor.getLong(0));
        linguagem.setDesignacao(cursor.getString(1));
        linguagem.setValor(cursor.getInt(2));
        return linguagem;
    }


    
}
