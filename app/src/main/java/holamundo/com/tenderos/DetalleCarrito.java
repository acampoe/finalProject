package holamundo.com.tenderos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleCarrito extends AppCompatActivity {
    private TextView lblNombreCarrito,lblTipoCarrito,lblCantidadCarrito,lblUnidadCarrito,lblPrecioCarrito, cantidad2,lblPrecioTotal;
    private ImageView imgCarrito;
    private Intent i;
    private Bundle bundle;
    private String nombre,tipo,unidad,id,positivo,negativo;
    private double precio,total;
    private int foto,cantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carrito);
        lblNombreCarrito = findViewById(R.id.lblNombreCarrito);
        lblTipoCarrito = findViewById(R.id.lblTipoCarrito);
        lblCantidadCarrito = findViewById(R.id.lblUnidadesCarrito);
        lblUnidadCarrito = findViewById(R.id.lblMedidaCarrito);
        lblPrecioCarrito = findViewById(R.id.lblPrecioCarrito);
        imgCarrito = findViewById(R.id.imgDetalleCarrito);
        cantidad2 = findViewById(R.id.cantidad2);
        lblPrecioTotal = findViewById(R.id.lblPrecioTotal);
        cantidad2.setText(getResources().getString(R.string.cantidad));
        i = getIntent();
        bundle = i.getBundleExtra("datos");
        bundle = i.getBundleExtra("datos");
        nombre = bundle.getString("nombre");
        tipo = bundle.getString("tipo");
        cantidad = bundle.getInt("cantidad");
        unidad = bundle.getString("medida");
        precio = bundle.getDouble("precio");
        foto = bundle.getInt("foto");
        id = bundle.getString("id");
        lblPrecioCarrito.setText("$"+precio);
        lblPrecioTotal.setText("$"+precio*cantidad);
        lblCantidadCarrito.setText(""+cantidad+" "+unidad);
        lblTipoCarrito.setText(tipo);
        lblNombreCarrito.setText(nombre);
        imgCarrito.setImageResource(foto);
    }
    public void onBackPressed(){
        Intent i  = new Intent(DetalleCarrito.this,Carrito.class);
        finish();
        startActivity(i);
    }

    public void eliminarCarrito(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.eliminar));
        builder.setMessage(getResources().getString(R.string.pregunta_eliminacion));
        positivo = getResources().getString(R.string.positivo);
        negativo = getResources().getString(R.string.negativo);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Producto p = new Producto(id);
                Producto actual = new Producto();
                for (int j=0;j<Data.carrito.size();j++){
                    actual = Data.carrito.get(j);
                    if (p.getId().equals(actual.getId())){
                        Data.carrito.remove(j);
                    }
                }
                onBackPressed();
            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
