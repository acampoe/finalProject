package holamundo.com.tenderos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ListaProductos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    public void launchRegistrar(View v){
        Intent i = new Intent(ListaProductos.this,AgregarProducto.class);
        startActivity(i);
    }
    public void onBackPressed() {
        finish();
        Intent i = new Intent(ListaProductos.this,Tendero.class);
        startActivity(i);
    }
}
