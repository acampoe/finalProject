package holamundo.com.tenderos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import holamundo.com.tenderos.Principal;

public class Tendero extends AppCompatActivity {
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.title).toString());
        setContentView(R.layout.activity_tenderos);
        reference = FirebaseDatabase.getInstance().getReference();
    }


    public void launchListaCompras(View v) {

        Intent i = new Intent(Tendero.this, ListaCompra.class);
        startActivity(i);
        finish();

    }

    public void launchListaProductos(View v) {
        Intent i = new Intent(Tendero.this, ListaProductos.class);
        startActivity(i);
        finish();
    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(Tendero.this, Principal.class);
        startActivity(i);
    }

    public void fff(View v) {

    }
}
