package org.example.Dao;
//Dao: Objeto de accesos de datos
//Importaciones nescesarias
import org.example.Model.Producto;
import org.example.database.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Crear un CRUD de productos para una tienda online en Java,
// realice la persistencia utilizando base de datos
// (preferiblemente) en su defecto a través de listas.
public class ProductoDao {

    //Metodo para crear un nuevo producto

    //Recibo un objeto en este caso Producto y lo inserto en la base de datos.
    public void agregarProducto(Producto producto) {

        //hago la instancia de sql
        String sql = "INSERT INTO productos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";//los signos de pregunta son para avisar de que se va a inyectar un dato

        //Obtengo la conexion
        try (Connection connection = ConnectionDatabase.getConnection();

             //Ejecutar las consultas
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Aqui va lo que es la asignacion de parámetros (asignar los valores)
            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getCantidad());
            statement.executeUpdate(); //ejecucion de la consulta update
        } catch (SQLException e) {//excepcion //Atrapar
            e.printStackTrace();//imprime en la consola el error
        }
    }

    public List<Producto> obtenerProductos() { //devolver la lista de objetos productos
        List<Producto> productos = new ArrayList<>();//Para almacernar los productos
        String sql = "SELECT * FROM productos"; //el metodo en sql


        try (Connection connection = ConnectionDatabase.getConnection(); //manejamos lo que son los datos, los recursos
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) { //obtenemos la tabla de datos devuelta por la consulta.

            //Este es para hacer lo que es el recorrido por cada fila hasta que no hallan mas
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");
                int cantidad = resultSet.getInt("cantidad");

                //Aqui extraigo los datos creando un objeto para añadir a lo que es la lista de productos
                productos.add(new Producto(id, nombre,descripcion, precio, cantidad));
            }
        } catch (SQLException e) {
            e.printStackTrace();//las excepciones
        }
        return productos; //ratorna la listo de los productos
    }

    //Metodo de Actualizar un producto
    public void actualizaProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, cantidad=? WHERE id=?"; //update de actualizar y set para especificar las columnas
        try (Connection connection = ConnectionDatabase.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Nuevamente asignacion de parametros con SET
            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getCantidad());
            statement.setInt(5, producto.getId());
            statement.executeUpdate();//se ejecuta la consulta
        } catch (SQLException e) {
            e.printStackTrace();//excepciones
        }
    }

    //metodo ELIMINAR

    //aqui utilizo lo que es lo del identificador unico id para pues eliminar los datos
    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id=?";//consulta sql para eliminar
        try (Connection connection = ConnectionDatabase.getConnection(); //try catch para manejar el cierre de los datos o recursos
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Se indica el parametro en este caso es con id
            statement.setInt(1, id);
            statement.executeUpdate();// se ejecuta
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Escriba un método que obtenga el producto con el menor número de consonantes.
    public int conteoConsonantes(String nombre) {
        final String consonantes = nombre.replaceAll("[aeiouAEIOU]", "");
        return consonantes.length();
    }

    public Producto obtenerproductomenorConsonantes() {
        List<Producto> productos = obtenerProductos();
        if (productos.isEmpty()) {
            return null;
        }

        Producto productoConsonantes = productos.get(0);
        int minConsonantes= conteoConsonantes(productoConsonantes.getNombre());

        for (Producto producto : productos) {
            int cantidadConsonantes = conteoConsonantes(producto.getNombre());
            if (cantidadConsonantes < minConsonantes) {
                minConsonantes = cantidadConsonantes;
                productoConsonantes = producto;
            }
        }
        return productoConsonantes;
    }

    //Escriba un método que muestre el número primo más cercano al precio más bajo de todos los productos

    //Hallo el precio mas bajo

    public double obtenerpreciomasBajo(){
        List<Producto> productos= obtenerProductos();
        if (productos.isEmpty()) {
            return -1;
        }

        double preciomasBajo= productos.get(0).getPrecio();
        for (Producto producto: productos){
            if (producto.getPrecio()<preciomasBajo){
                preciomasBajo=producto.getPrecio();
            }
        }
        return preciomasBajo;
    }

    //primo o no
    //nota: número primo debe ser mayor a 1 y no es divisible entre mas números (solo el 1 y el mismo número)
    public boolean numeroesPrimo(int numero){
        if (numero<2){
            return false;
        }
        for(int i = 2; i< numero; i++) {
            if (numero % i == 0) {
                return false; //no es primo porque es divisible.
            }
        }
        return true;

    }

    //Obtener el número primo mas cercano al precio mas bajo
    public int obtenerNumeroPrimoMasCercano() {
        double preciomasBajo = obtenerpreciomasBajo();
        if (preciomasBajo == -1) {
            return -1;
        }

        int precioInt= (int)Math.floor(preciomasBajo);
        int arriba=precioInt+1;
        int abajo=precioInt;

        while (true){
            if (numeroesPrimo(abajo)){
                return abajo;
            }

            if (numeroesPrimo(arriba)){
                return arriba;
            }
            arriba++;
            abajo--;
        }
    }



    public int contarTotalProductos() {
        List<Producto> productos = obtenerProductos();
        return productos.size(); //
    }
}

