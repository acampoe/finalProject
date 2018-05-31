package holamundo.com.tenderos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ProductoViewHolder>{
    private ArrayList<Producto> productos;
    private OnProductoClickListener clickListener;
    public AdaptadorProducto(ArrayList<Producto> productos, OnProductoClickListener onProductoClickListener){
        this.clickListener = onProductoClickListener;
        this.productos = productos;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);
        return new ProductoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        final Producto p = productos.get(position);
        holder.foto.setImageResource(p.getFoto());
        holder.nombre.setText(p.getNombre());
        holder.tipo.setText(p.getTipo());
        holder.cantidad.setText(""+p.getCantidadDisponible());
        holder.precio.setText(""+p.getPrecio());

        holder.v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clickListener.onProductoClickListener(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView nombre;
        private TextView tipo;
        private TextView cantidad;
        private TextView precio;
        private View v;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.imgFoto);
            nombre = v.findViewById(R.id.lblNombre);
            tipo = v.findViewById(R.id.lblTipo);
            cantidad = v.findViewById(R.id.lblUnidades);
            precio = v.findViewById(R.id.lblPrecio);
        }
    }
    public interface OnProductoClickListener{
        void onProductoClickListener(Producto p);
    }
}
