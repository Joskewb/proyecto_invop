package tienda.UsuarioProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import tienda.entidades.ProductoCarrito;


import tienda.entidades.Cubos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerCarritoCompra extends JFrame {
    private Map<String, ProductoCarrito> carrito;
    private int idUsuario; // Nuevo atributo para almacenar el ID del usuario
    private JLabel lblMontoTotal;
	   private List<Cubos> listaCubos;


    // Constructor que acepta solo la lista de cubos
    public VerCarritoCompra(List<Cubos> carritoCompleto) {
        this(carritoCompleto, 0); // Llama al constructor sobrecargado con un ID de usuario predeterminado
    }

    public VerCarritoCompra(List<Cubos> carritoCompleto, int idUsuario) {
        this.idUsuario = idUsuario; // Asignar el ID del usuario
        this.carrito = new HashMap<>();

        // Construir el mapa de productos únicos y su cantidad
        for (Cubos cubo : carritoCompleto) {
            String nombre = cubo.getNombre();
            if (carrito.containsKey(nombre)) {
                ProductoCarrito producto = carrito.get(nombre);
                producto.setCantidad(producto.getCantidad() + 1);
            } else {
                carrito.put(nombre, new ProductoCarrito(nombre, 1));
            }
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Carrito de Compras");
        setSize(800, 600);
        setLocationRelativeTo(null);
        JLabel lblIdUsuario = new JLabel("ID del Usuario: " + idUsuario);
        getContentPane().add(lblIdUsuario, BorderLayout.NORTH);
        initComponents();

        // Inicializar la etiqueta de monto total
        lblMontoTotal = new JLabel("Monto Total: " + calcularMontoTotal());
        getContentPane().add(lblMontoTotal, BorderLayout.SOUTH);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Cantidad");
        tableModel.addColumn("Precio Unitario");
        tableModel.addColumn("Monto Total");

        for (ProductoCarrito producto : carrito.values()) {
            // Obtener el precio del producto desde la base de datos
            double precioUnitario = obtenerPrecioUnitario(producto.getNombre());
            double montoTotal = precioUnitario * producto.getCantidad();
            Object[] rowData = {producto.getNombre(), producto.getCantidad(), precioUnitario, montoTotal};
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        JButton realizarCompraButton = new JButton("Realizar Compra");
        
        
        realizarCompraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                realizarCompra(); // Llama al método para realizar la compra
                guardarCompraEnBaseDeDatos(); // Llama al método para guardar la compra en la base de datos
                actualizarStock(); // Llama al método para actualizar el stock en la base de datos
                dispose();
                ProductosUI.abrirVentanaProductos(idUsuario); // Abrir la ventana de productos actualizada

            }
        });
        buttonPanel.add(realizarCompraButton);

        JButton eliminarCompraButton = new JButton("Eliminar Compra");
        eliminarCompraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Agregar aquí la lógica para eliminar la compra
            }
        });
        buttonPanel.add(eliminarCompraButton);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana actual
            }
        });
        buttonPanel.add(volverButton);

        // Etiqueta para mostrar el mensaje con el ID del usuario
        JLabel lblIdUsuario = new JLabel("ID del Usuario: " + idUsuario);
        panel.add(lblIdUsuario, BorderLayout.NORTH);
    }

    private double obtenerPrecioUnitario(String nombreProducto) {
        String url = "jdbc:mysql://localhost/inventario?useSSL=false";
        String usuario = "root";
        String contraseña = "1234";

        try (Connection connection = (Connection) DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "SELECT precio FROM cubos WHERE nombre = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nombreProducto);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Si se encuentra el producto, devolver su precio
                        return resultSet.getDouble("precio");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo adecuado de errores, adaptado a tus necesidades
        }
        // Si no se encuentra el producto o hay un error, devolver un valor predeterminado o lanzar una excepción, según tus requisitos
        return 0.0; // Precio predeterminado si no se encuentra el producto
    }

    // Método para realizar la compra
    private void realizarCompra() {
        // Aquí deberías implementar la lógica para realizar la compra
        // Por ahora, simplemente mostraré un mensaje de ejemplo
        JOptionPane.showMessageDialog(this, "Compra realizada correctamente", "Compra Realizada", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para calcular el monto total del carrito
    private double calcularMontoTotal() {
        double montoTotal = 0;
        for (ProductoCarrito producto : carrito.values()) {
            double precioUnitario = obtenerPrecioUnitario(producto.getNombre());
            montoTotal += precioUnitario * producto.getCantidad();
        }
        return montoTotal;
    }

    // Método para guardar la compra en la base de datos
    private void guardarCompraEnBaseDeDatos() {
        String url = "jdbc:mysql://localhost/inventario?useSSL=false";
        String usuario = "root";
        String contraseña = "1234";
        try (Connection connection = (Connection) DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "INSERT INTO carrito_compra (fecha_compra, cantida_comprada, precio_unitario, total_compra, idusuario, idcubo) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (ProductoCarrito producto : carrito.values()) {
                    Timestamp fechaCompra = new Timestamp(System.currentTimeMillis());
                    double precioUnitario = obtenerPrecioUnitario(producto.getNombre());
                    double totalCompra = precioUnitario * producto.getCantidad();
                    int idUsuario = obtenerIdUsuario(); // Debes implementar este método para obtener el ID del usuario actual
                    int idCubo = obtenerIdCubo(producto.getNombre());
                    statement.setTimestamp(1, fechaCompra);
                    statement.setInt(2, producto.getCantidad());
                    statement.setDouble(3, precioUnitario);
                    statement.setDouble(4, totalCompra);
                    statement.setInt(5, idUsuario);
                    statement.setInt(6, idCubo);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener el ID del usuario actual
    private int obtenerIdUsuario() {
	       return idUsuario;
	   }
    
	   private void actualizarStockEnBaseDeDatos(int idCubo, int nuevoStock) {
	       String url = "jdbc:mysql://localhost/inventario?useSSL=false";
	       String usuario = "root";
	       String contraseña = "1234";
	       try (Connection connection = (Connection) DriverManager.getConnection(url, usuario, contraseña)) {
	           String query = "UPDATE cubos SET stock = ? WHERE idcubos = ?";
	           try (PreparedStatement statement = connection.prepareStatement(query)) {
	               statement.setInt(1, nuevoStock);
	               statement.setInt(2, idCubo);
	               statement.executeUpdate();
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	   }
	   
	   private void actualizarStock() {
		    for (ProductoCarrito producto : carrito.values()) {
		        int idCubo = obtenerIdCubo(producto.getNombre());
		        int cantidadComprada = producto.getCantidad();
		        int stockActual = obtenerStock(idCubo);
		        int nuevoStock = stockActual - cantidadComprada;
		        actualizarStockEnBaseDeDatos(idCubo, nuevoStock);
		    }
		}
	   
	// Método para obtener el stock de un cubo desde la lista de cubos
	   private int obtenerStockCuboDesdeLista(int idCubo) {
	       for (Cubos cubo : listaCubos) {
	           if (cubo.getCodigo() == idCubo) {
	               return cubo.getStock();
	           }
	       }
	       return -1; // Retorna -1 si no se encuentra el cubo
	   }
    
	   private int obtenerStock(int idCubo) {
		    String url = "jdbc:mysql://localhost/inventario?useSSL=false";
		    String usuario = "root";
		    String contraseña = "1234";
		    try (Connection connection = (Connection) DriverManager.getConnection(url, usuario, contraseña)) {
		        String query = "SELECT stock FROM cubos WHERE idcubos = ?";
		        try (PreparedStatement statement = connection.prepareStatement(query)) {
		            statement.setInt(1, idCubo);
		            try (ResultSet resultSet = statement.executeQuery()) {
		                if (resultSet.next()) {
		                    return resultSet.getInt("stock");
		                }
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return -1; // Retorna -1 si no se encuentra el cubo o hay un error
		}

    // Método para obtener el ID del cubo por su nombre
    private int obtenerIdCubo(String nombreCubo) {
        String url = "jdbc:mysql://localhost/inventario?useSSL=false";
        String usuario = "root";
        String contraseña = "1234";
        try (Connection connection = (Connection) DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "SELECT idcubos FROM cubos WHERE nombre = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nombreCubo);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("idcubos");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 si no se encuentra el cubo
    }
    
	   private void obtenerProductosDesdeBaseDeDatos() {
	       listaCubos = new ArrayList<>();
	       String url = "jdbc:mysql://localhost/inventario?useSSL=false";
	       String usuario = "root";
	       String contraseña = "1234";
	       try (Connection connection = (Connection) DriverManager.getConnection(url, usuario, contraseña)) {
	           String query = "SELECT * FROM cubos";
	           try (PreparedStatement statement = connection.prepareStatement(query)) {
	               try (ResultSet resultSet = statement.executeQuery()) {
	                   while (resultSet.next()) {
	                       int idcubos = resultSet.getInt("idcubos");
	                       int stock = resultSet.getInt("stock");
	                       String nombre = resultSet.getString("nombre");
	                       double precio = resultSet.getDouble("precio");
	                       String marca = resultSet.getString("marca");
	                       String ruta_imagen = resultSet.getString("ruta_imagen");
	                       String tipo = resultSet.getString("tipo");
	                       double alto = resultSet.getDouble("alto");
	                       double ancho = resultSet.getDouble("ancho");
	                       double largo = resultSet.getDouble("largo");
	                       Cubos cubo = new Cubos(idcubos, nombre, precio, marca, ruta_imagen, tipo, alto, ancho, largo, stock);
	                       listaCubos.add(cubo);
	                   }
	               }
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	   }

    public static void main(String[] args) {
        // Ejemplo de uso
        // Aquí deberías crear una lista de Cubos y pasarla al constructor de VerCarritoCompra
        List<Cubos> carritoCompleto = new ArrayList<>(); // Obtén tu lista de Cubos de alguna manera
        VerCarritoCompra verCarrito = new VerCarritoCompra(carritoCompleto,0);
        verCarrito.setVisible(true);
    }
}