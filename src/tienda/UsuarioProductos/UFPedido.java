package tienda.UsuarioProductos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tienda.PanelAccesorio;
import tienda.Pedido;
import tienda.panelDescripcionAccesorio;
import tienda.panelDescripcionMod;
import tienda.AdminProductos.JFProducts;
import tienda.AdminProductos.PanelCubos;

public class UFPedido extends JFrame {
	private String archivo="Carrito.txt";
	JScrollPane scrollPane;
    private boolean scrolled = false;
    PanelAccesorio panelmods;
    PanelCubos panelcubos;
    JPanel panelTarjetas;
    JPanel panelTarjetasContenido;
    JPanel panelInferior;
    UdescripcionCubo p;
    panelDescripcionMod pm;
    panelDescripcionAccesorio pa;
    JLabel lblCubos;
    JTable tablePedido;
    JLabel lblTotal;
    private double total = 0;
	public UFPedido() {
		
		getContentPane().setBackground(Color.BLACK);
    	getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);
        setLocationRelativeTo(null);
        
        
        //Barra superior
        JButton btnLogo = new JButton("");
        btnLogo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                UNosotros n = new UNosotros();
                n.setVisible(true);
        	}
        });
        btnLogo.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/user(80x80).jpg")));
        btnLogo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnLogo.setBounds(882, 22, 80, 80);
        getContentPane().add(btnLogo);

        JButton btnUsuario = new JButton("");
        btnUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                UInicio n = new UInicio();
                n.setVisible(true);
        	}
        });
        btnUsuario.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/logo(100x75).jpg")));
        btnUsuario.setHorizontalTextPosition(SwingConstants.CENTER);
        btnUsuario.setBounds(25, 25, 100, 75);
        getContentPane().add(btnUsuario);

        JButton btnProductos = new JButton("Productos");
        btnProductos.setHorizontalTextPosition(SwingConstants.CENTER);
        btnProductos.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/rojo(1000x700).jpg")));
        btnProductos.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        btnProductos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                UProductos n = new UProductos();
                n.setVisible(true);
        	}
        });
        btnProductos.setBounds(250, 50, 100, 30);
        getContentPane().add(btnProductos);

        JButton btnInicio = new JButton("Inicio");
        btnInicio.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/amarillo(1000x700).jpg")));
        btnInicio.setHorizontalTextPosition(SwingConstants.CENTER);
        btnInicio.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
        btnInicio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                UInicio n = new UInicio();
                n.setVisible(true);
        	}
        });
        btnInicio.setBounds(175, 50, 75, 30);
        getContentPane().add(btnInicio);

        JButton btnNosotros = new JButton("Nosotros");
        btnNosotros.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                UNosotros n = new UNosotros();
                n.setVisible(true);
        	}
        });
        btnNosotros.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/Azul(1000x700).jpg")));
        btnNosotros.setHorizontalTextPosition(SwingConstants.CENTER);
        btnNosotros.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        btnNosotros.setBounds(350, 50, 100, 30);
        getContentPane().add(btnNosotros);
        
        
        //inferior lateral
        panelInferior= new JPanel();
        panelInferior.setBackground(new Color(30, 34, 35));
        getContentPane().add(panelInferior);
        panelInferior.setBounds(0, 120, 985, 540);
        panelInferior.setLayout(null);
        
        JLabel lblPedido = new JLabel("Pedido actual:");
        lblPedido.setForeground(Color.WHITE);
        lblPedido.setFont(new Font("Bahnschrift", Font.PLAIN, 45));
        lblPedido.setBounds(53, 51, 328, 61);
        panelInferior.add(lblPedido);
        
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(53, 127, 861, 303);
        panelInferior.add(scrollPane);

        tablePedido = new JTable();
        tablePedido.setEnabled(true);
        tablePedido.setRowSelectionAllowed(true);

        tablePedido.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "CProducto", "Nombre", "Precio", "Cantidad", "Costo" }
        ));
        tablePedido.getColumnModel().getColumn(0).setPreferredWidth(90);
        tablePedido.getColumnModel().getColumn(1).setPreferredWidth(110);
        tablePedido.getColumnModel().getColumn(2).setPreferredWidth(50);
        tablePedido.getColumnModel().getColumn(3).setPreferredWidth(50);
        tablePedido.getColumnModel().getColumn(4).setPreferredWidth(50);
        scrollPane.setViewportView(tablePedido);
        
        JButton btnEliminar = new JButton("Eliminar del carrito");
        btnEliminar.setBackground(new Color(255, 219, 89));
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int filaSeleccionada = tablePedido.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    int confirmacion = JOptionPane.showConfirmDialog(
                            null,
                            "¿Está seguro de eliminar este producto del carrito?",
                            "Confirmación de eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        int cProducto = (int) tablePedido.getValueAt(filaSeleccionada, 0);
                      
                        eliminarProducto(cProducto);
                        
                        // Actualizar la visualización de la tabla
                        cargarDatosDesdeArchivo(archivo);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Seleccione una fila para eliminar",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
        	}
        });
        btnEliminar.setBounds(60, 464, 150, 38);
        panelInferior.add(btnEliminar);
        
        JButton btnBack = new JButton(">");
        btnBack.setBackground(new Color(255, 255, 255));
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		UProductos p=new UProductos();
        		p.setVisible(true);
        	}
        });
        btnBack.setBounds(864, 51, 50, 50);
        panelInferior.add(btnBack);
        
        lblTotal = new JLabel();
        lblTotal.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        lblTotal.setForeground(new Color(255, 255, 255));
        lblTotal.setBounds(679, 464, 235, 38);
        panelInferior.add(lblTotal);
        tablePedido.setDefaultEditor(Object.class, null);
        
        
        cargarDatosDesdeArchivo(archivo);
        
		
	}
	private void cargarDatosDesdeArchivo(String nombreArchivo) {
	    List<Pedido> listaProductos = obtenerListaProductosDesdeArchivo(nombreArchivo);
	    DefaultTableModel modelo = (DefaultTableModel) tablePedido.getModel();
	    modelo.setRowCount(0); 

	    total = 0; 
	    for (Pedido producto : listaProductos) {
	        Object[] fila = new Object[modelo.getColumnCount()];
	        fila[0] = producto.getCodigoProducto();
	        fila[1] = producto.getNombre();
	        fila[2] = producto.getPrecio();
	        fila[3] = producto.getCantidad();
	        fila[4] = producto.getCosto();

	        modelo.addRow(fila);
	        total += producto.getCosto();
	    }

	    // Actualizar el texto del JLabel con el total calculado
	    lblTotal.setText("Total: Bs. " + total);
	}

	 private void eliminarProducto(int cProducto) {
	        List<Pedido> listaProductos = obtenerListaProductosDesdeArchivo(archivo);
	        Optional<Pedido> productoAEliminar = listaProductos.stream()
	                .filter(item -> item.getCodigoProducto() == cProducto)
	                .findFirst();
	        productoAEliminar.ifPresent(item -> listaProductos.remove(item));
	        guardarListaProductosEnArchivo(listaProductos);
	    }
	 private void guardarListaProductosEnArchivo(List<Pedido> listaProductos) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
	            for (Pedido item : listaProductos) {
	            	 writer.write("Código: " + item.getCodigoProducto() + "\n");
	                 writer.write("Nombre: " + item.getNombre() + "\n");
	                 writer.write("Precio: " + item.getPrecio() + "\n");
	                 writer.write("Cantidad: " + item.getCantidad() + "\n");
	                 writer.write("Costo: " + item.getCosto() + "\n");
	                 writer.write("------------------------------\n");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
    private List<Pedido> obtenerListaProductosDesdeArchivo(String nombreArchivo) {
        List<Pedido> listaProductos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Código: ")) {
                    int codigo = Integer.parseInt(extraerValor(linea, "Código: "));
                    String nombre = extraerValor(reader.readLine(), "Nombre: ");
                    double precio = Double.parseDouble(extraerValor(reader.readLine(), "Precio: "));
                    int cantidad = Integer.parseInt(extraerValor(reader.readLine(), "Cantidad: "));
                    double costo = Double.parseDouble(extraerValor(reader.readLine(), "Costo: "));

                    Pedido producto = new Pedido(codigo, nombre, precio, cantidad, costo);
                    listaProductos.add(producto);

                    reader.readLine(); // Leer la línea de separación
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaProductos;
    }
    private String extraerValor(String linea, String etiqueta) {
        return linea.substring(etiqueta.length()).trim();
    }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UFPedido frame = new UFPedido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
