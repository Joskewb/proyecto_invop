package tienda;

import java.awt.Font;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UdescripcionMod extends JPanel {
	private JTextField txNombre;
	private JTextField txPrecio;
	private JTextField txMarca;
	private JLabel lblFoto;
	private JButton btnCarrito;
	private JLabel lblAñadirMod;
	private JTextField txCodigo, txTipo;
	JComboBox cboxCantidad ;
	private String archivo="Carrito.txt";
	private List<Pedido> carrito = new ArrayList<>();
	private Pedido productoPedido;
	
	private Cubos modActual;
	public UdescripcionMod(Cubos mod) {
		this.modActual=mod;
		
		setLayout(null);
		setBounds(0,0,1000,540);
		Font f=new Font("Bahnschrift", Font.PLAIN, 20);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(f);
		lblCodigo.setBounds(402, 120, 88, 20);
		add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(f);
		lblNombre.setBounds(395, 179, 88, 20);
		add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(f);
		lblPrecio.setBounds(409, 240, 71, 25);
		add(lblPrecio);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(f);
		lblMarca.setBounds(409, 302, 71, 25);
		add(lblMarca);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(f);
		lblTipo.setBounds(698, 240, 54, 25);
		add(lblTipo);

		
		txCodigo = new JTextField();
		txCodigo.setEditable(false);
		txCodigo.setColumns(10);
		txCodigo.setBounds(500, 113, 410, 35);
		add(txCodigo);
		
		txNombre = new JTextField();
		txNombre.setEditable(false);
		txNombre.setBounds(500, 172, 410, 35);
		add(txNombre);
		txNombre.setColumns(10);
		
		txPrecio = new JTextField();
		txPrecio.setEditable(false);
		txPrecio.setColumns(10);
		txPrecio.setBounds(500, 231, 169, 35);
		add(txPrecio);
		
		txMarca = new JTextField();
		txMarca.setEditable(false);
		txMarca.setColumns(10);
		txMarca.setBounds(500, 293, 410, 35);
		add(txMarca);
		
		txTipo = new JTextField();
		txTipo.setEditable(false);
        txTipo.setBounds(760, 237, 150, 30);
        add(txTipo);
        txTipo.setColumns(10);
        
        
        txCodigo.setText(""+mod.getCodigo());
        txNombre.setText(mod.getNombre());
        txPrecio.setText(""+mod.getPrecio());
        txMarca.setText(mod.getMarca());
        txTipo.setText(mod.getTipo());
		
		lblFoto = new JLabel();
		lblFoto.setIcon(new ImageIcon(mod.getRuta()));
		lblFoto.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblFoto.setBounds(64, 113, 250, 300);
		add(lblFoto);
		
		
		lblAñadirMod = new JLabel("Descripcion Mod:");
		lblAñadirMod.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
		lblAñadirMod.setBounds(62, 37, 450, 50);
		add(lblAñadirMod);
		
        
		btnCarrito = new JButton("Añadir al carrito");
		btnCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cantidad = (int) cboxCantidad.getSelectedItem();
		        double costoProducto = mod.getPrecio() * cantidad;
		        productoPedido = new Pedido(mod.getCodigo(), mod.getNombre(), mod.getPrecio(), cantidad, costoProducto);
		        carrito.add(productoPedido);
		        guardarCarritoEnArchivo();
		        JOptionPane.showMessageDialog(UdescripcionMod.this, "Producto añadido al carrito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnCarrito.setBounds(730, 470, 180, 50);
		add(btnCarrito);
		btnCarrito.setVisible(true);
        
        JButton btnBack = new JButton(">");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UProductos p= new UProductos();
				removeAll();
				p.setVisible(true);
				revalidate();
				repaint();
				
			}
		});
		btnBack.setBounds(915, 40, 50, 50);
		add(btnBack);
		
		cboxCantidad = new JComboBox();
		cboxCantidad.setBounds(582, 477, 50, 36);
		add(cboxCantidad);
		
		cboxCantidad.addItem(1);
		cboxCantidad.addItem(2);
		cboxCantidad.addItem(3);
		cboxCantidad.addItem(4);
		cboxCantidad.addItem(5);
		
		JLabel lblCantidad = new JLabel("Cantidad: ");
		lblCantidad.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setBounds(465, 484, 91, 18);
		add(lblCantidad);
		
	}
	private void guardarCarritoEnArchivo() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
	        for (Pedido item : carrito) {
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
}
