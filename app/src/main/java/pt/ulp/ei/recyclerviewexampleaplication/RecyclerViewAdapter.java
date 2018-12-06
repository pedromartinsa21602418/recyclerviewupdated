package pt.ulp.ei.recyclerviewexampleaplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    List<Linguagem> valores; // Linguagem[] valores; // String[] valores;
    Context context;
    View view1;
    // RecyclerView.ViewHolder
    ViewHolder      viewHolder1;
    protected TextView textViewDesignacao;
    protected TextView textViewValor;


    public RecyclerViewAdapter(Context context1, List<Linguagem> listaValores){

        valores = listaValores;
        context = context1;
    }
    /***** Creating OnItemClickListener *****/

    // Define listener member variable
    private  OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // ------------------------------------------------------------------
    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewDesignacao;
        public TextView textViewValor;
        private Context context;
        public ViewHolder(View v){

            super(v);

            textViewDesignacao = (TextView)v.findViewById(R.id.textview_item);
            textViewValor = (TextView)v.findViewById(R.id.textview_item_valor);

            this.context = context;
            // Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }


    }
    //----------------------------------------------
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_items,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position){
        Linguagem a = valores.get(position);
        // Log.d("onBindViewHolder", "a.designacao: " + a.getDesignacao());
        Log.d("TAG", "position "+position+" -> " + a.getDesignacao());
        for (Linguagem b : valores) {
            Log.d("TAG", b.getDesignacao()); //.add(new Linguagem(strLing));
            //  v[i].setDesignacao(strLing);
        }
        holder.textViewDesignacao.setText(a.getDesignacao());
        holder.textViewValor.setText(Integer.toString(a.getValor()));// + "...");

        // holder.textViewValor.setText(  Integer.toString(  valores[position].getValor() ));
        // a.getValor();  // int
        // a.getValor() + "  pontos"
    }

    @Override
    public int getItemCount(){
        return valores.size();
    }


}

