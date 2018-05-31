package holamundo.com.tenderos;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private String[] opcTipo,opcMedida;

    private ArrayList<Integer> fotos;

    private Resources r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.agregar).toString());
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
        fotos.add(R.drawable.arroz);
        fotos.add(R.drawable.desodorante);
        fotos.add(R.drawable.detergente);
        fotos.add(R.drawable.lentejas);
        fotos.add(R.drawable.pan);
        fotos.add(R.drawable.pezcado);
        fotos.add(R.drawable.pollo);
        fotos.add(R.drawable.refresco);
        fotos.add(R.drawable.bag);
    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(AgregarProducto.this,ListaProductos.class);
        startActivity(i);
    }

    public void guardar(View v){
        String tipo,nombre,medida,id;
        double precio;
        int cantidad,foto;
        foto = Data.imagenAleatoria(fotos);
        tipo = spnTipo.getSelectedItem().toString();
        nombre = txtNombre.getText().toString();
        medida = spnMedida.getSelectedItem().toString();
        precio = Double.parseDouble(txtPrecio.getText().toString());
        cantidad = Integer.parseInt(txtPrecio.getText().toString());
        id = Data.getId();
        Producto p = new Producto(id,tipo,nombre,cantidad,medida,precio,foto);
        p.guardar();

        Snackbar.make(v,R.string.registro_exitoso,Snackbar.LENGTH_LONG).setAction("Action",null).show();
    }

    public void limpiar(){
        txtPrecio.setText(null);
        txtCantidad.setText(null);
        txtNombre.setText(null);
    }

    public void clear(View v){
        limpiar();
    }
}
