package tienda.UsuarioProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import tienda.entidades.Cubos;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductosUI extends JFrame {
	   private int idUsuario;
	   private List<Cubos> listaCubos;
	   private List<Cubos> carrito;
	   private JTable tableProductos;
	   private int cantidad;
	   private static ProductosUI instanciaActual;

	   
	   
	   public static void main(String[] args) {
	       EventQueue.invokeLater(new Runnable() {
	           public void run() {
	               try {
	                   ProductosUI frame = new ProductosUI(0);
	                   frame.setVisible(true);
	               } catch (Exception e) {
	                   e.printStackTrace();
	               }
	           }
	       });
	   }
	   
	   
	   public ProductosUI(int idUsuario) {
	       this.idUsuario = idUsuario;
	       obtenerProductosDesdeBaseDeDatos();
	       carrito = new ArrayList<>();
	       initComponents();
	       instanciaActual = this; // Establecer la instancia actual

	   }
	   private void obtenerProductosDesdeBaseDeDatos() {
	       listaCubos = new ArrayList<>();
	       String url = "jdbc:mysql://localhost/inventario?useSSL=false";
	       String usuario = "root";
	       String contraseña = "1234";
	       try (Connection connection = DriverManager.getConnection(url, usuario, contraseña)) {
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
	   private void initComponents() {
	       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       setTitle("Productos");
	       setSize(800, 600);
	       setLocationRelativeTo(null);
	       JPanel panel = new JPanel(new BorderLayout());
	       getContentPane().add(panel, BorderLayout.CENTER);
	       // Crear modelo de tabla
	       DefaultTableModel tableModel = new DefaultTableModel();
	       tableModel.addColumn("Nombre");
	       tableModel.addColumn("Precio");
	       tableModel.addColumn("Stock");
	       tableModel.addColumn("Imagen");
	       tableModel.addColumn(""); // Columna vacía para el botón
	       // Agregar productos al modelo de tabla
	       for (Cubos cubo : listaCubos) {
	           ImageIcon imageIcon = new ImageIcon(cubo.getRuta()); // Cargar imagen desde archivo
	           Image image = imageIcon.getImage(); // Obtener la imagen
	           Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH); // Escalar la imagen a un tamaño más pequeño
	           ImageIcon scaledImageIcon = new ImageIcon(scaledImage); // Crear un nuevo ImageIcon con la imagen escalada
	           Object[] rowData = {cubo.getNombre(), cubo.getPrecio(), cubo.getStock(), scaledImageIcon, "Agregar"};
	           tableModel.addRow(rowData);
	       }
	       // Crear tabla con modelo
	       tableProductos = new JTable(tableModel);
	       tableProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	       tableProductos.getColumnModel().getColumn(0).setPreferredWidth(200);
	       tableProductos.getColumnModel().getColumn(1).setPreferredWidth(100);
	       tableProductos.getColumnModel().getColumn(2).setPreferredWidth(50);
	       tableProductos.getColumnModel().getColumn(3).setPreferredWidth(100);
	       tableProductos.getColumnModel().getColumn(4).setPreferredWidth(100); // Botón de agregar
	       tableProductos.setRowHeight(100);
	       tableProductos.getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer()); // Asignar el renderer personalizado para la columna de imágenes
	       tableProductos.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer()); // Asignar el renderer personalizado para la columna del botón
	       tableProductos.addMouseListener(new MouseAdapter() {
	           @Override
	           public void mouseClicked(MouseEvent e) {
	               int column = tableProductos.getColumnModel().getColumnIndexAtX(e.getX());
	               int row = e.getY() / tableProductos.getRowHeight();
	               if (row < tableProductos.getRowCount() && row >= 0 && column < tableProductos.getColumnCount() && column >= 0) {
	                   Object value = tableProductos.getValueAt(row, column);
	                   if (value instanceof String && "Agregar".equals(value)) {
	                       agregarAlCarrito(row);
	                   }
	               }
	           }
	       });
	       JScrollPane scrollPane = new JScrollPane(tableProductos);
	       panel.add(scrollPane, BorderLayout.CENTER);
	       JLabel idUsuarioLabel = new JLabel("Tu ID es el siguiente: " + idUsuario);
	       panel.add(idUsuarioLabel, BorderLayout.SOUTH);
	       
	       
	       
	    // Botón para ver el carrito
	       JButton btnVerCarrito = new JButton("Ver Carrito");
	       btnVerCarrito.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	               // Pasa el idUsuario al constructor de VerCarritoCompra
	               VerCarritoCompra verCarrito = new VerCarritoCompra(carrito, idUsuario);
	               verCarrito.setVisible(true);
	           }
	       });
	       getContentPane().add(btnVerCarrito, BorderLayout.SOUTH);
	   }
	   
	   
	   
	   
	   private void mostrarCarrito() {
	       StringBuilder carritoTexto = new StringBuilder();
	       carritoTexto.append("Carrito de Compras:\n");
	       for (Cubos cubo : carrito) {
	           carritoTexto.append("- ").append(cubo.getNombre()).append("\n");
	       }
	       JOptionPane.showMessageDialog(this, carritoTexto.toString(), "Carrito", JOptionPane.INFORMATION_MESSAGE);
	   }
	   // Renderer personalizado para mostrar imágenes en la tabla
	   private class ImageRenderer extends DefaultTableCellRenderer {
	       @Override
	       public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	           JLabel label = new JLabel();
	           if (value != null) {
	               label.setIcon((Icon) value);
	               label.setHorizontalAlignment(JLabel.CENTER);
	           }
	           return label;
	       }
	   }
	   // Renderer personalizado para mostrar botones en la tabla
	   private class ButtonRenderer extends JButton implements TableCellRenderer {
	       public ButtonRenderer() {
	           setOpaque(true);
	       }
	       public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	           setText((value == null) ? "" : value.toString());
	           setBackground(Color.GREEN); // Color verde
	           return this;
	       }
	   }
	   
	   
	   private void agregarAlCarrito(int row) {
	       Cubos cubo = listaCubos.get(row);
	       if (cubo.getStock() > 0) {
	           // Solicitar al usuario la cantidad de productos a agregar
	           String cantidadStr = JOptionPane.showInputDialog(this, "Ingrese la cantidad de productos a agregar:", "Cantidad", JOptionPane.QUESTION_MESSAGE);
	           if (cantidadStr != null && !cantidadStr.isEmpty()) {
	               try {
	                   int cantidad = Integer.parseInt(cantidadStr);
	                   if (cantidad > 0 && cantidad <= cubo.getStock()) {
	                       // Agregar la cantidad especificada al carrito
	                       for (int i = 0; i < cantidad; i++) {
	                           carrito.add(cubo);
	                       }
	                       cubo.setStock(cubo.getStock() - cantidad); // Reducir la cantidad de stock
	                       JOptionPane.showMessageDialog(this, cantidad + " " + cubo.getNombre() + "(s) agregado(s) al carrito", "Agregado al Carrito", JOptionPane.INFORMATION_MESSAGE);
	                       // Actualizar la tabla después de agregar el producto al carrito
	                       DefaultTableModel tableModel = (DefaultTableModel) tableProductos.getModel();
	                       tableModel.fireTableDataChanged();
	                       // Guardar la compra en la base de datos
	                       // Crear una lista temporal con el producto y la cantidad
	                       List<Cubos> productoCantidad = new ArrayList<>();
	                       for (int i = 0; i < cantidad; i++) {
	                           productoCantidad.add(cubo);
	                       }
	                       // Abrir la ventana de VerCarritoCompra con el producto y la cantidad
	                   	VerCarritoCompra verCarrito = new VerCarritoCompra(carrito,idUsuario);
	                       verCarrito.setVisible(true);

	                   } else {
	                       JOptionPane.showMessageDialog(this, "La cantidad ingresada es inválida o excede el stock disponible.", "Error", JOptionPane.ERROR_MESSAGE);
	                   }
	               } catch (NumberFormatException e) {
	                   JOptionPane.showMessageDialog(this, "Ingrese un número válido para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
	               }
	           }
	       } else {
	           JOptionPane.showMessageDialog(this, "No hay stock disponible para " + cubo.getNombre(), "Error", JOptionPane.ERROR_MESSAGE);
	       }
	   }

	   private int obtenerIdUsuario() {
	       return idUsuario;
	   }
	   
	   public static void cerrarInstanciaActual() {
		    if (instanciaActual != null) {
		        instanciaActual.dispose(); // Cerrar la instancia actual si existe
		    }
		}
	   
	   public static void abrirVentanaProductos(int idUsuario) {
		    EventQueue.invokeLater(new Runnable() {
		        public void run() {
		            try {
		                ProductosUI frame = new ProductosUI(idUsuario);
		                frame.setVisible(true);
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
		    });
		}

	}
