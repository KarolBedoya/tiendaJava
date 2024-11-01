package org.example.Model;

public class Producto {
    //creo los atributos de la tabla
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;

    // Creo los constructores
    public Producto(String nombre, String descripcion, double precio, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Producto(int id,String nombre, String descripcion, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Producto(int id, String nombre, String descripcion, int cantidad){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    //Agrego getter y setter con Generate
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //Verifico que realmente esté sobrescribiendo un método existente
    @Override //Muestra los datos, si quiero eliminar que no aparezca uno lo quito de aqui
    public String toString() { //devolver una cadena String.
        //devolviendo la cadena
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' + //comilla simple alrededor del nombre y demas
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
