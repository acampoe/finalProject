package holamundo.com.tenderos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetalleVenta extends AppCompatActivity implements AdaptadorCarrito.OnProductoClickListener {
    private RecyclerView lstDetalleVentas;
    private ArrayList<Producto> productos;
    private AdaptadorCarrito adapter;
    private LinearLayoutManager llm;
    private String db = "compras";
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_venta);
        setTitle(getResources().getString(R.string.carrito_title));

        lstDetalleVentas = findViewById(R.id.lstDetalleVentas);
        productos = new ArrayList<>();

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new AdaptadorCarrito(productos, this);

        lstDetalleVentas.setLayoutManager(llm);
        lstDetalleVentas.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference reference = databaseReference.child(db);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Venta v = snapshot.getValue(Venta.class);
                        //productos = v.getProductos(); Se supone que se hace así en mi modelo, pero no en Firebase!
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(DetalleVenta.this, ListaCompra.class);
        startActivity(i);
    }

    @Override
    public void onProductoClickListener(Producto p) {

    }
}
