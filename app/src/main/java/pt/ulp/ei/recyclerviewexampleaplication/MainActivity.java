package pt.ulp.ei.recyclerviewexampleaplication;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    RecyclerView recyclerView;
    LinearLayoutManager recylerViewLinearLayoutManager;

    RecyclerView.Adapter recyclerViewAdapter;
    String[] linguagens; //=
          //    {  "C", "Java", "Python" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewLinguagens);
        recylerViewLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager( recylerViewLinearLayoutManager);


        // consultar a base de dados (SQLite): obter todos os registos
        LinguagemDataSource linguagemDataSource =
                new LinguagemDataSource(this);

        linguagemDataSource.open();
        final List<Linguagem> todas = linguagemDataSource.getAllLinguagens();
        for(Linguagem l : todas) {
            Log.d(" DB-Linguagens",l.getDesignacao());
        }

        linguagemDataSource.close();

        // // preencher através de uma consulta à base de dados (SQLite)
        recyclerViewAdapter =
                new RecyclerViewAdapter(getApplicationContext(), todas);


        // In the activity or fragment
        //ContactsAdapter adapter = ...;
        ((RecyclerViewAdapter) recyclerViewAdapter).setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        final Linguagem atual = (Linguagem)  todas.get( position);
                        final int pos_atual = position;
                        Toast.makeText( context, atual.getDesignacao() + Integer.toString(atual.getValor()) + "votos ", Toast.LENGTH_LONG).show();
                        Log.d("RecyclerView", atual.getDesignacao() );

                        //Mostrar alert dialog
                        //"Construtor"
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                        //definir mensagem a ser apresentada
                        builder.setMessage("Deseja votar em " + atual.getDesignacao() + "?");
                        builder.setCancelable(true);
                        builder.setPositiveButton("Sim",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(context, "Escolheu Sim", Toast.LENGTH_LONG).show();
                                        //atual.votar();
                                        ((RecyclerViewAdapter) recyclerViewAdapter).valores.get(pos_atual).votar();
                                        ((RecyclerViewAdapter) recyclerViewAdapter).notifyItemChanged(pos_atual);
                                    }
                                });

                        builder.setNegativeButton("Não",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(context, "Escolheu Não", Toast.LENGTH_LONG).show();
                                    }
                                });

                        //Recorrer ao construtor para criar um AlertDialog
                        AlertDialog alert = builder.create();
                        //pedir para mostrar o AlertDialog
                        alert.show();
                    }
                }
        );


        recyclerView.setAdapter(recyclerViewAdapter);

 //

        //linguagemDataSource.criarTabela();
        // ---
        /* --Inserir Dados--
        Linguagem nova = null;
        nova = linguagemDataSource.createLinguagem("C");
        if (nova != null) {
            Log.d("DB-Linguagens", "nova linguagem -> "+ nova.getDesignacao());

        }
        linguagemDataSource.createLinguagem("C++");
        linguagemDataSource.createLinguagem("Java");

        linguagemDataSource.close();
        */
        // ----
        /*List<Linguagem> todas = linguagemDataSource.getAllLinguagens();
        for(Linguagem l : todas) {
            Log.d(" DataBase Linguagens",l.getDesignacao());
        }

        linguagemDataSource.close();
        */







    }
    private void inicializaBD() {
        LinguagemDataSource linguagemDataSource =
                new LinguagemDataSource(this);

        linguagemDataSource.open();

        linguagemDataSource.createLinguagem("C");
        linguagemDataSource.createLinguagem("C++");
        linguagemDataSource.createLinguagem("Java");
        linguagemDataSource.createLinguagem("Php");
        linguagemDataSource.createLinguagem("Python");

        linguagemDataSource.close();
    }

    private void consultarBD() {
        LinguagemDataSource linguagemDataSource =
                new LinguagemDataSource(this);

        linguagemDataSource.open();

        List<Linguagem> todas = linguagemDataSource.getAllLinguagens();
        for(Linguagem l : todas) {
            Log.d(" DataBase Linguagens",l.getDesignacao());
        }
        linguagemDataSource.close();
    }
}
