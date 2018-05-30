package holamundo.com.tenderos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AgregarProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.agregar).toString());
        setContentView(R.layout.activity_agregar_producto);
    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(AgregarProducto.this,ListaProductos.class);
        startActivity(i);
    }
}
