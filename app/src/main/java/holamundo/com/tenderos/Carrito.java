package holamundo.com.tenderos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Carrito extends AppCompatActivity implements AdaptadorCarrito.OnProductoClickListener{
    private RecyclerView lstCarrito;
    private ArrayList<Producto> productos;
    private AdaptadorCarrito adapter;
    private LinearLayoutManager llm;
    private String db = "compras";
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.carrito_title));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        lstCarrito = findViewById(R.id.lstCarrito);
        productos = Data.carrito;

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new AdaptadorCarrito(productos,this);

        lstCarrito.setLayoutManager(llm);
        lstCarrito.setAdapter(adapter);
    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(Carrito.this,ClienteMain.class);
        startActivity(i);
    }

    @Override
    public void onProductoClickListener(Producto p){
        Intent i = new Intent(Carrito.this, DetalleCarrito.class);
        Bundle b = new Bundle();
        b.putString("id",p.getId());
        b.putString("nombre",p.getNombre());
        b.putString("tipo",p.getTipo());
        b.putString("medida",p.getUnidadDeMedida());
        b.putInt("cantidad",p.getCantidadDisponible());
        b.putDouble("precio",p.getPrecio());
        b.putInt("foto",p.getFoto());

        i.putExtra("datos",b);
        startActivity(i);
    }
}
