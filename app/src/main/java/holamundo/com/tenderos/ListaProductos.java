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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaProductos extends AppCompatActivity implements AdaptadorProducto.OnProductoClickListener{
    private RecyclerView lstProductos;
    private ArrayList<Producto> productos;
    private AdaptadorProducto adapter;
    private LinearLayoutManager llm;
    private String db = "productos";
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lstProductos = findViewById(R.id.lstProductos);
        productos = new ArrayList<>();

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new AdaptadorProducto(productos,this);

        lstProductos.setLayoutManager(llm);
        lstProductos.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productos.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Producto p = snapshot.getValue(Producto.class);
                        productos.add(p);
                    }
                }
                adapter.notifyDataSetChanged();
                Data.setProductos(productos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

    @Override
    public void onProductoClickListener(Producto p) {
        Intent i = new Intent(ListaProductos.this,DetalleProducto.class);
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
