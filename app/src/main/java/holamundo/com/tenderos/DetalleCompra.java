package holamundo.com.tenderos;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleCompra extends AppCompatActivity {
    private TextView lblNombreCompra;
    private TextView lblTipoCompra;
    private TextView lblCantidadCompra;
    private TextView lblUnidadCompra;
    private TextView lblPrecioCompra;
    private ImageView fotoCompra;
    private EditText txtCantidadDeseada;
    private TextView lblUnidadCompra2;
    private Intent i;
    private Bundle bundle;

    private String nombre,tipo,unidad,id;
    private long precio;
    private int foto;
    private double cantidad,cantidadDeseada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_compra);

        lblNombreCompra = findViewById(R.id.lblNombreCompra);
        lblTipoCompra = findViewById(R.id.lblTipoCompra);
        lblCantidadCompra = findViewById(R.id.lblUnidadesCompra);
        lblUnidadCompra = findViewById(R.id.lblMedidaCompra);
        lblPrecioCompra = findViewById(R.id.lblPrecioCompra);
        lblUnidadCompra2 = findViewById(R.id.lblMedidaCompra2);
        fotoCompra = findViewById(R.id.imgCompra);
        txtCantidadDeseada = findViewById(R.id.txtCantidadDeseada);
        i = getIntent();
        bundle = i.getBundleExtra("datos");
        nombre = bundle.getString("nombre");
        tipo = bundle.getString("tipo");
        cantidad = bundle.getDouble("cantidad");
        unidad = bundle.getString("medida");
        precio = bundle.getLong("precio");
        foto = bundle.getInt("foto");
        id = bundle.getString("id");
        lblNombreCompra.setText(nombre);
        lblTipoCompra.setText(tipo);
        lblCantidadCompra.setText(""+cantidad+" "+unidad);
        lblUnidadCompra2.setText(unidad);
        lblPrecioCompra.setText("$"+precio);
        fotoCompra.setImageResource(foto);
        cantidadDeseada = 0;
    }



    public void agregarAlCarrito(View v){
        cantidadDeseada = Double.parseDouble(txtCantidadDeseada.getText().toString());
        Producto p = new Producto(id,tipo,nombre,cantidadDeseada,unidad,precio,foto);
        Data.carrito.add(p);
        Snackbar.make(v,R.string.agregado,Snackbar.LENGTH_LONG).setAction("Action",null).show();
    }

    public void onBackPressed(){
        Intent i  = new Intent(DetalleCompra.this,ClienteMain.class);
        finish();
        startActivity(i);
    }
}
