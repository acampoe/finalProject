package holamundo.com.tenderos;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AgregarProducto extends AppCompatActivity {
    private Spinner spnTipo;
    private EditText txtNombre;
    private EditText txtCantidad;
    private Spinner spnMedida;
    private EditText txtPrecio;

    private ArrayAdapter<String> adapterTipo;
    private ArrayAdapter<String> adapterMedida;

    private String[] opcTipo, opcMedida;

    private ArrayList<Integer> fotos;

    private Resources r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.agregar));
        setContentView(R.layout.activity_agregar_producto);

        spnTipo = findViewById(R.id.spnTipo);
        txtNombre = findViewById(R.id.txtNombre);
        txtCantidad = findViewById(R.id.txtCantidad);
        spnMedida = findViewById(R.id.spnMedida);
        txtPrecio = findViewById(R.id.txtPrecio);

        r = this.getResources();

        opcTipo = r.getStringArray(R.array.tipos);
        opcMedida = r.getStringArray(R.array.unidad_medidas);

        adapterTipo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcTipo);
        adapterMedida = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcMedida);

        spnMedida.setAdapter(adapterMedida);
        spnTipo.setAdapter(adapterTipo);

        fotos = new ArrayList<>();
        /*fotos.add(R.drawable.arroz);
        fotos.add(R.drawable.desodorante);
        fotos.add(R.drawable.detergente);
        fotos.add(R.drawable.lentejas);
        fotos.add(R.drawable.pan);
        fotos.add(R.drawable.pezcado);
        fotos.add(R.drawable.pollo);
        fotos.add(R.drawable.refresco);*/
        fotos.add(R.drawable.bag);

    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(AgregarProducto.this, ListaProductos.class);
        startActivity(i);
    }

    public void guardar(View v) {
        String tipo, nombre, medida, id;
        long precio;
        int foto;
        double cantidad;
        foto = Data.imagenAleatoria(fotos);
        tipo = spnTipo.getSelectedItem().toString();
        nombre = txtNombre.getText().toString();
        medida = spnMedida.getSelectedItem().toString();

        id = Data.getId();
        if (nombre.matches("")) {
            txtNombre.requestFocus();
            txtNombre.setError(getResources().getString(R.string.error_nombre_producto));
        } else if (txtCantidad.getText().toString().matches("") || txtCantidad.getText().toString().matches("0*")) {
            txtCantidad.requestFocus();
            txtCantidad.setError(getResources().getString(R.string.error_cantidad));
        } else if (txtPrecio.getText().toString().matches("") || txtPrecio.getText().toString().matches("0*")) {
            txtPrecio.requestFocus();
            txtPrecio.setError(getResources().getString(R.string.error_precio));
        } else {
            precio = Long.parseLong(txtPrecio.getText().toString());
            cantidad = Double.parseDouble(txtCantidad.getText().toString());
            Producto p = new Producto(id, tipo, nombre, cantidad, medida, precio, foto);
            Log.i("new product", "guardar: " + precio);
            p.guardar();

            Snackbar.make(v, R.string.registro_exitoso, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            onBackPressed();
        }

    }

    public void limpiar() {
        txtPrecio.setText(null);
        txtCantidad.setText(null);
        txtNombre.setText(null);
    }

    public void clear(View v) {
        limpiar();
    }
}
