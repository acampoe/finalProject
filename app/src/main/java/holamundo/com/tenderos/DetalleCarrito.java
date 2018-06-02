package holamundo.com.tenderos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleCarrito extends AppCompatActivity {
    private TextView lblNombreCarrito,lblTipoCarrito,lblCantidadCarrito,lblUnidadCarrito,lblPrecioCarrito, cantidad2;
    private ImageView imgCarrito;
    private Intent i;
    private Bundle bundle;
    private String nombre,tipo,unidad,id;
    private double precio;
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
        lblPrecioCarrito.setText(""+precio);
        lblUnidadCarrito.setText(unidad);
        lblCantidadCarrito.setText(""+cantidad);
        lblTipoCarrito.setText(tipo);
        lblNombreCarrito.setText(nombre);
        imgCarrito.setImageResource(foto);
    }
    public void onBackPressed(){
        Intent i  = new Intent(DetalleCarrito.this,Carrito.class);
        finish();
        startActivity(i);
    }
}
