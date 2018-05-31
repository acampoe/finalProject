package holamundo.com.tenderos;

import java.util.ArrayList;

public class Venta {
    private String id;
    private ArrayList<Producto> productos;
    private double total;
    private String clientName;

    public Venta() {
        this.id = "";
        this.productos = new ArrayList<>();
        this.total = 0;
        this.clientName = "";
    }

    public Venta(String id) {
        this.id = id;
    }

    public Venta(String id, ArrayList<Producto> productos, double total, String clientName) {
        this.id = id;
        this.productos = productos;
        this.total = total;
        this.clientName = clientName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

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

    public void guardar(){
        Data.guardarVenta(this);
    }

    public void eliminar(){
        Data.eliminarVenta(this);
    }
}
