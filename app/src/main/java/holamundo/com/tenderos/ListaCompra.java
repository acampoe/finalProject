package holamundo.com.tenderos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaCompra extends AppCompatActivity implements AdaptadorVenta.OnVentaClickListener {
    private RecyclerView lstCompras;
    private ArrayList<Venta> ventas;
    private AdaptadorVenta adapter;
    private LinearLayoutManager llm;
    private String db = "ventas";
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lstCompras = findViewById(R.id.lstCompras);
        ventas = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new AdaptadorVenta(ventas, this);
        lstCompras.setLayoutManager(llm);
        lstCompras.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ventas.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Venta v = snapshot.getValue(Venta.class);
                        ventas.add(v);
                    }
                }
                adapter.notifyDataSetChanged();
                Data.ventas = ventas;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(ListaCompra.this, Tendero.class);
        startActivity(i);
    }

    @Override
    public void onVentaClickListener(Venta v) {
        Intent i = new Intent(ListaCompra.this, DetalleVenta.class);
        startActivity(i);

    }
}
