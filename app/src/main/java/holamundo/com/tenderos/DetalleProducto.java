package holamundo.com.tenderos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleProducto extends AppCompatActivity {
    private TextView lblNombreDetalle;
    private TextView lblTipoDetalle;
    private TextView lblCantidadDetalle;
    private TextView lblUnidadDetalle;
    private TextView lblPrecioDetalle;
    private ImageView fotoDetalle;
    private Intent i;
    private Bundle bundle;

    private String nombre,tipo,unidad,id;
    private double precio;
    private int foto,cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        lblNombreDetalle = findViewById(R.id.lblNombreDetalle);
        lblTipoDetalle = findViewById(R.id.lblTipoDetalle);
        lblCantidadDetalle = findViewById(R.id.lblUnidadesDetalle);
        lblUnidadDetalle = findViewById(R.id.lblMedidaDetalle);
        lblPrecioDetalle = findViewById(R.id.lblPrecioDetalle);
        fotoDetalle = findViewById(R.id.imgFotoDetalle);
        i = getIntent();
        bundle = i.getBundleExtra("datos");
        nombre = bundle.getString("nombre");
        tipo = bundle.getString("tipo");
        cantidad = bundle.getInt("cantidad");
        unidad = bundle.getString("medida");
        precio = bundle.getDouble("precio");
        foto = bundle.getInt("foto");
        id = bundle.getString("id");
        lblNombreDetalle.setText(nombre);
        lblTipoDetalle.setText(tipo);
        lblCantidadDetalle.setText(""+cantidad+" "+unidad);
        lblPrecioDetalle.setText("$"+precio);
        fotoDetalle.setImageResource(foto);
    }

    public void eliminar(View v){
        String positivo,negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.eliminar));
        builder.setMessage(getResources().getString(R.string.pregunta_eliminacion));
        positivo = getResources().getString(R.string.positivo);
        negativo = getResources().getString(R.string.negativo);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Producto p = new Producto(id);
                p.eliminar();
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

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(DetalleProducto.this,ListaProductos.class);
        startActivity(i);
    }
}
