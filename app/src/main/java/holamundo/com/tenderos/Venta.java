package holamundo.com.tenderos;

import java.util.ArrayList;

public class Venta {
    private String id;
    //private ArrayList<Producto> productos;
    private double total;
    private String clientName;

    public Venta() {
        this.id = "";
        //this.productos = new ArrayList<>();
        this.total = 0;
        this.clientName = "";
    }

    public Venta(String id) {
        this.id = id;
    }

    public Venta(String id, double total, String clientName) {
        this.id = id;
        //this.productos = productos;
        this.total = total;
        this.clientName = clientName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   // public ArrayList<Producto> getProductos() {
  //      return productos;
   // }

    //public void setProductos(ArrayList<Producto> productos) {
      //  this.productos = productos;
    //}

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void guardar() {
        Producto p = new Producto();
        Producto actual = new Producto();
        for (int i = 0; i < Data.carrito.size(); i++) {
            p = Data.carrito.get(i);
            for (int j = 0; j < Data.productos.size(); j++) {
                actual = Data.productos.get(j);
                if (actual.getId().matches(p.getId())) {
                    double nueva_cantidad = actual.getCantidadDisponible() - p.getCantidadDisponible();
                    if (nueva_cantidad <= 0) {
                        actual.eliminar();
                    } else {
                        actual.updateQuantity(nueva_cantidad);
                    }
                }
            }
        }
        Data.guardarVenta(this);
    }

    public void eliminar() {
        Data.eliminarVenta(this);
    }
}
