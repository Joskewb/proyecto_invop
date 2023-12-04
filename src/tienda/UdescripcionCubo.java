package tienda;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import javax.swing.SwingConstants;

public class UdescripcionCubo extends JPanel {
	private JTextField txNombre;
	private JTextField txPrecio;
	private JTextField txMarca;
	private JTextField txAlto;
	private JTextField txAncho;
	private JTextField txLargo;
	private JLabel lblFoto;
	private JButton btnCarrito;
	private JLabel lblAñadirCubo;
	private JTextField txCodigo, txTipo;
	private Cubos cuboActual;
	JComboBox cboxCantidad;
	private String archivo="Carrito.txt";
	private List<Pedido> carrito = new ArrayList<>();
	private Pedido productoPedido;

	public UdescripcionCubo(Cubos cubo) {
		setBackground(Color.WHITE);
		this.cuboActual=cubo;
		archivo="Cubos.txt";
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
		
		JLabel lblAlto = new JLabel("Alto:");
		lblAlto.setFont(f);
		lblAlto.setBounds(429, 361, 54, 25);
		add(lblAlto);
		
		JLabel lblAncho = new JLabel("Ancho:");
		lblAncho.setFont(f);
		lblAncho.setBounds(598, 361, 71, 25);
		add(lblAncho);
		
		JLabel lblLargo = new JLabel("Largo:");
		lblLargo.setFont(f);
		lblLargo.setBounds(757, 361, 64, 25);
		add(lblLargo);
		
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
		
		txAlto = new JTextField();
		txAlto.setEditable(false);
		txAlto.setColumns(10);
		txAlto.setBounds(500, 356, 80, 35);
		add(txAlto);
		
		txAncho = new JTextField();
		txAncho.setEditable(false);
		txAncho.setColumns(10);
		txAncho.setBounds(667, 356, 80, 35);
		add(txAncho);
		
		txLargo = new JTextField();
		txLargo.setEditable(false);
		txLargo.setColumns(10);
		txLargo.setBounds(830, 356, 80, 35);
		add(txLargo);
		
		txTipo = new JTextField();
		txTipo.setEditable(false);
        txTipo.setBounds(760, 237, 150, 30);
        add(txTipo);
        txTipo.setColumns(10);
        
        
        txCodigo.setText(""+cubo.getCodigo());
        txNombre.setText(cubo.getNombre());
        txPrecio.setText(""+cubo.getPrecio());
        txMarca.setText(cubo.getMarca());
        txAlto.setText(""+cubo.getAlto());
        txAncho.setText(""+cubo.getAncho());
        txLargo.setText(""+cubo.getLargo());
        txTipo.setText(cubo.getTipo());
		
		lblFoto = new JLabel("imagen");
		lblFoto.setIcon(new ImageIcon(cubo.getRuta()));
		lblFoto.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblFoto.setBounds(64, 113, 250, 300);
		add(lblFoto);
		
		btnCarrito = new JButton("Añadir al carrito");
		btnCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cantidad = (int) cboxCantidad.getSelectedItem();
		        double costoProducto = cubo.getPrecio() * cantidad;
		        productoPedido = new Pedido(cubo.getCodigo(), cubo.getNombre(), cubo.getPrecio(), cantidad, costoProducto);
		        carrito.add(productoPedido);
		        guardarCarritoEnArchivo();
		        JOptionPane.showMessageDialog(UdescripcionCubo.this, "Producto añadido al carrito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnCarrito.setBounds(730, 470, 180, 50);
		add(btnCarrito);
		btnCarrito.setVisible(true);
		
		lblAñadirCubo = new JLabel("Descripcion:");
		lblAñadirCubo.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
		lblAñadirCubo.setBounds(62, 37, 494, 50);
		add(lblAñadirCubo);
        
        JButton btnBack = new JButton(">");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFProducts p= new JFProducts();
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
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
