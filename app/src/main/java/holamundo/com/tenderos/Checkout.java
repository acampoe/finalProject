package holamundo.com.tenderos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Checkout extends AppCompatActivity {
    private TextView lblCantidadCheckout, lblPrecioCheckout;
    private EditText txtClientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        lblCantidadCheckout = findViewById(R.id.lblCantidadCheckout);
        lblPrecioCheckout = findViewById(R.id.lblPrecioCheckout);
        txtClientName = findViewById(R.id.txtClientName);
        lblPrecioCheckout.setText(String.format("$%s", totalizar()));
        lblCantidadCheckout.setText(String.format("%s", totalProductos()));
    }

    public void purchase(View v) {
        if (validar()) {
            final String id = Data.getId();
            String positivo, negativo;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.confirma_compra));
            builder.setMessage(getResources().getString(R.string.compra_exitosa));
            positivo = getResources().getString(R.string.positivo);
            negativo = getResources().getString(R.string.negativo);

            builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Venta venta = new Venta(id, Data.carrito, totalizar(), txtClientName.getText().toString());
                    venta.guardar();
                    Data.carrito.clear();
                    Intent inicio = new Intent(Checkout.this, ClienteMain.class);
                    finish();
                    startActivity(inicio);
                }
            });

            builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            Snackbar.make(v, R.string.error_nombre_cliente, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    public double totalizar() {
        double res = 0;
        Producto p = new Producto();
        if (Data.carrito.size() > 0) {
            for (int i = 0; i < Data.carrito.size(); i++) {
                p = Data.carrito.get(i);
                res += p.getPrecio() * p.getCantidadDisponible();
            }
            return res;
        } else {
            return 0;
        }
    }

    public int totalProductos() {
        int res = 0;
        Producto p = new Producto();
        if (Data.carrito.size() > 0) {
            for (int i = 0; i < Data.carrito.size(); i++) {
                p = Data.carrito.get(i);
                res += p.getCantidadDisponible();
            }
            return res;
        } else {
            return 0;
        }
    }

    public boolean validar() {
        if (txtClientName.getText().toString().matches("")) {
            return false;
        }
        return true;
    }
}
