package holamundo.com.tenderos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    private String nombre, tipo, unidad, id;
    private double precio;
    private int foto;
    private double cantidad, cantidadDeseada;

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
        precio = bundle.getDouble("precio");
        foto = bundle.getInt("foto");
        id = bundle.getString("id");
        lblNombreCompra.setText(nombre);
        lblTipoCompra.setText(tipo);
        lblCantidadCompra.setText(String.format("%s %s", cantidad, unidad));
        lblUnidadCompra2.setText(unidad);
        lblPrecioCompra.setText(String.format("$%s", precio));
        fotoCompra.setImageResource(foto);
        cantidadDeseada = 0;
    }


    public void agregarAlCarrito(View v) {
        int sw = 0;
        if (Data.carrito.isEmpty()) {
            sw = 0;
        } else {
            for (int j = 0; j < Data.carrito.size(); j++) {
                if (Data.carrito.get(j).getId().matches(this.id)) {
                    Snackbar.make(v, R.string.error_carrito, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    sw = 1;
                }
            }
        }
        if (sw == 0) {
            if (txtCantidadDeseada.getText().toString().matches("")
                    || txtCantidadDeseada.getText().toString().matches("0*")) {
                txtCantidadDeseada.requestFocus();
                txtCantidadDeseada.setError(getResources().getString(R.string.error_cantidad));
            } else {
                cantidadDeseada = Double.parseDouble(txtCantidadDeseada.getText().toString());
                Producto p = new Producto(id, tipo, nombre, cantidadDeseada, unidad, precio, foto);
                for (int j = 0; j < Data.productos.size(); j++) {
                    if (Data.productos.get(j).getId().matches(p.getId())) {
                        if (Data.productos.get(j).getCantidadDisponible() >= cantidadDeseada) {
                            Data.carrito.add(p);
                            onBackPressed();
                            break;
                        } else {
                            Snackbar.make(v, R.string.error_existencias, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            break;
                        }
                    }
                }
            }
        }
    }

    public void guardar() {

    }

    public void onBackPressed() {
        Intent i = new Intent(DetalleCompra.this, ClienteMain.class);
        finish();
        startActivity(i);
    }
}
