package org.example;

import org.example.Dao.ProductoDao;
import org.example.Model.Producto;
import org.example.database.ConnectionDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            try {
                Connection connection = ConnectionDatabase.getConnection();
                if (connection != null) {
                    System.out.println("Conexión a la base de datos exitosa!");
                }
            } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            }

        ProductoDao productoDao = new ProductoDao();
        Scanner entrada=new Scanner(System.in);

        //creamos un while para que sea como un bucle infinito hasta que el usuario quera salir
        System.out.println("Bienvenido a la tienda!!!");

        while (true) {
            //Sacamos las opciones
            System.out.println("Seleccione que quiere hacer ");
            System.out.println("1. Agregar un nuevo producto");
            System.out.println("2. Mostrar los productos");
            System.out.println("3. Actualizar algun producto");
            System.out.println("4. Eliminar un producto");
            System.out.println("5. Quiero Salir");

            //leemos
            int opcion = entrada.nextInt();
            entrada.nextLine();

            //hacemos un switch para que dependiendo lo que escribio poder que le salga lo que escogio en los casos

            switch (opcion){

                //el caso 1 es de agregar
                case 1:
                    System.out.print("Nombre: ");
                    String nombre =entrada.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion =entrada.nextLine();
                    System.out.print("Precio: ");
                    double precio = entrada.nextDouble();
                    System.out.print("Cantidad: ");
                    int cantidad = entrada.nextInt();
                    Producto newProduct  = new Producto(nombre,descripcion,precio,cantidad);
                    productoDao.agregarProducto(newProduct);
                    System.out.println("Se agragó el producto con éxito!!!");
                    break;//indicar que ya termine

                //Listamos los productos
                case 2:
                    // Listar productos
                    System.out.println("Lista de productos:");
                    List<Producto> productos = productoDao.obtenerProductos();
                    for (Producto producto : productos) {//para mostrar los productos
                        System.out.println(producto);
                    }
                    break;

                //Caso para actualizar el producto
                case 3:
                    System.out.print("Ingrese el id del producto que quiere actualizar: ");
                    int idNew = entrada.nextInt();
                    entrada.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese el nuevo Nombre ");
                    String newNombre = entrada.nextLine();
                    System.out.print("Ingrese la nueva Descripción: ");
                    String newDescripcion = entrada.nextLine();
                    System.out.print("Ingrese el nuevo Precio: ");
                    double newPrecio = entrada.nextDouble();
                    System.out.print("Ingrese la nueva Cantidad: ");
                    int newCantidad = entrada.nextInt();

                    Producto productoActualizar = new Producto(idNew, newNombre, newDescripcion, newPrecio, newCantidad);
                    productoDao.actualizaProducto(productoActualizar);
                    System.out.println("El producto se actualizo con exito!!!");
                    break;

                //Aqui eliminamos el producto
                case 4:
                    System.out.print("Ingrese el id del producto que quiere eliminar: ");
                    int idDelete = entrada.nextInt();
                    productoDao.eliminarProducto(idDelete);
                    System.out.println("El producto se ha eliminado con exito!!!");
                    break;

                //Este ya es para que pueda salir
                case 5:
                    System.out.println("Cerrandose...");
                    entrada.close();
                    return;

                default://ya en caso de que coloque algo que no es
                    System.out.println("Opción no válida. Inténtalo de nuevo.");

            }

        }

    }
}