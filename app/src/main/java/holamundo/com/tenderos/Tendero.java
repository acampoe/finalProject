package holamundo.com.tenderos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import holamundo.com.tenderos.main.Principal;

public class Tendero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.title).toString());
        setContentView(R.layout.activity_tenderos);
    }


    public void launchListaCompras(View v){
        Intent i = new Intent(Tendero.this,ListaCompra.class);
        startActivity(i);
        finish();
    }
    public void launchListaProductos(View v){
        Intent i = new Intent(Tendero.this,ListaProductos.class);
        startActivity(i);
        finish();
    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(Tendero.this,Principal.class);
        startActivity(i);
    }

    public void fff(View v){

    }
}
