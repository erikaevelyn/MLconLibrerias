package ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.R;
import ar.edu.unlam.tp1.taller.erikrodriguez.mercadolibreapp.Modelos.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder>{

    private List<Producto> productos = new ArrayList();

    private int row_index = -1;

    TextView idProductoSeleccionado;

    String URLimagenPortada;




    public ProductosAdapter(List<Producto> productos) {
        this.productos = productos;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_articulo, null);
        return new ProductosAdapter.ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        final Context context;
        context = viewHolder.itemView.getContext();

        Producto productoAMostrar = this.productos.get(position);

        viewHolder.titulo.setText(productoAMostrar.getTitle());

        if(productoAMostrar.getEnvio().isEnvioGratis() == true) {
            viewHolder.envioGratis.setText("Envio gratis");
        }else{
            viewHolder.envioGratis.setVisibility(View.GONE);;
        }

        viewHolder.precio.setText("$ " + productoAMostrar.getPrecio());

        URLimagenPortada = productoAMostrar.getUrlImagenPortada();
        Picasso.with(context.getApplicationContext()).load(URLimagenPortada).placeholder(R.drawable.progress_animation).into(viewHolder.imagenDePortada);



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index=position;
                notifyDataSetChanged();
            }
        });

        if(row_index==position){


            viewHolder.itemView.setBackgroundColor(Color.parseColor("#fbe404"));

            String idString = String.valueOf(productoAMostrar.getId());

            Intent i = new Intent(context.getApplicationContext(), ResultadoActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            i.putExtra("idProducto", idString);
            context.startActivity(i);
        }
        else
        {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.titulo)
        TextView titulo;

        @BindView(R.id.precio)
        TextView precio;

        @BindView(R.id.envioGratis)
        TextView envioGratis;

        @BindView(R.id.imagenDePortada)
        ImageView imagenDePortada;


        TextView idProducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}