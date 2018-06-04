package holamundo.com.tenderos;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class Data {
    private static String productDB = "productos";
    private static String purchaseDB = "compras";
    private static String carritoDB = "carrito";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    public static ArrayList<Producto> productos = new ArrayList<>();
    public static ArrayList<Producto> carrito = new ArrayList<>();
    public static ArrayList<Venta> ventas = new ArrayList<>();
    public static void guardarProducto(Producto p){
        databaseReference.child(productDB).child(p.getId()).setValue(p);
    }

    public static void guardarVenta(Venta v){
        databaseReference.child(purchaseDB).child(v.getId()).setValue(v);
    }
    public static void descontarUnidad(Producto p, int unidades){
        databaseReference.child(productDB).child(p.getId()).child("cantidadDisponible").setValue(unidades);
    }


    public static ArrayList<Producto> obtener(){
        return productos;
    }

    public static int imagenAleatoria(ArrayList<Integer> fotos){
        int fotoActual;
        Random r = new Random();
        fotoActual = r.nextInt(fotos.size());
        return fotos.get(fotoActual);
    }

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void setProductos(ArrayList<Producto> product){
        Data.productos = product;
    }

    public static void eliminarProducto(Producto p){
        databaseReference.child(productDB).child(p.getId()).removeValue();
    }

    public static void eliminarVenta(Venta v){
        databaseReference.child(purchaseDB).child(v.getId()).removeValue();
    }
}
