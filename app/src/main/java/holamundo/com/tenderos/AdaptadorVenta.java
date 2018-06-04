package holamundo.com.tenderos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorVenta extends RecyclerView.Adapter<AdaptadorVenta.VentaViewHolder> {
    private ArrayList<Venta> ventas;
    private OnVentaClickListener clickListener;

    public AdaptadorVenta(ArrayList<Venta> ventas, OnVentaClickListener clickListener) {
        this.ventas = ventas;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(VentaViewHolder holder, int position) {
        final Venta v = ventas.get(position);
        holder.lblNombreCliente.setText(v.getClientName());
        holder.lblCantidadProductos.setText(""+v.getProductos().size());
        holder.lblPrecioTotal.setText("$"+v.getTotal());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onVentaClickListener(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ventas.size();
    }


    @Override
    public VentaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_venta,parent,false);
        return new VentaViewHolder(v);
    }

    public class VentaViewHolder extends RecyclerView.ViewHolder{
        private TextView lblNombreCliente,lblCantidadProductos,lblPrecioTotal;
        View v;
        public VentaViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            lblNombreCliente = v.findViewById(R.id.lblNombreCliente);
            lblCantidadProductos = v.findViewById(R.id.lblTotalProductos);
            lblPrecioTotal = v.findViewById(R.id.lblTotalCompra);
        }
    }

    public interface OnVentaClickListener{
        void onVentaClickListener(Venta v);
    }
}
