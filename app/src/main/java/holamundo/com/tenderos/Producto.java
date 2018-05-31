package holamundo.com.tenderos;

public class Producto {
    private int foto;
    private String id;
    private String tipo;
    private String nombre;
    private int cantidadDisponible;
    private String unidadDeMedida;
    private double precio;

    public Producto() {
        this.foto=0;
        this.id = "";
        this.tipo = "";
        this.nombre = "";
        this.cantidadDisponible = 0;
        this.unidadDeMedida = "";
        this.precio = 0;
    }

    public Producto(String id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Producto(String id, String tipo, String nombre, int cantidadDisponible, String unidadDeMedida, double precio, int foto) {
        this.foto = foto;

        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.unidadDeMedida = unidadDeMedida;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void guardar(){
        Data.guardarProducto(this);
    }

    public void eliminar(){
        Data.eliminarProducto(this);
    }
}
