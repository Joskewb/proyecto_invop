package tienda;

import java.awt.EventQueue;
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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import tienda.AdminProductos.JFProducts;
import tienda.AdminProductos.RegistroProductoAccesorio;

public class panelDescripcionAccesorio extends JPanel {
	private JTextField txNombre;
    private JTextField txPrecio;
    private JTextField txMarca;
    private JLabel lblFoto;
    private JButton btnSubirImagen;
    private JButton btnSave;
    private JLabel lblAñadirAcce;
    private JTextField txCodigo;
    private JTextField txTamanio;
    private Accesorio accesorioActual;
    public panelDescripcionAccesorio(Accesorio acce) {
    	this.accesorioActual=acce;
        setLayout(null);
        setBounds(0,0,1000,580);
        Font f = new Font("Bahnschrift", Font.PLAIN, 20);

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

        JLabel lblTamanio = new JLabel("Tamaño:");
        lblTamanio.setFont(f);
        lblTamanio.setBounds(698, 240, 88, 25);
        add(lblTamanio);

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
        
        txTamanio = new JTextField();
        txTamanio.setEditable(false);
        txTamanio.setBounds(795, 231, 110, 35);
        add(txTamanio);
        txTamanio.setColumns(10);
        
        txCodigo.setText(""+acce.getCodigo());
        txNombre.setText(acce.getNombre());
        txPrecio.setText(""+acce.getPrecio());
        txMarca.setText(acce.getMarca());
        txTamanio.setText(""+acce.getTamanio());

        lblFoto = new JLabel();
        lblFoto.setIcon(new ImageIcon(acce.getRuta()));
        lblFoto.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        lblFoto.setBounds(64, 113, 250, 300);
        add(lblFoto);

        btnSubirImagen = new JButton("Subir imagen");
        btnSubirImagen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png", "gif", "jfif"));

                int seleccion = fileChooser.showOpenDialog(null);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    // Obtener la ruta del archivo seleccionado
                    String rutaArchivo = fileChooser.getSelectedFile().getPath();

                    // Mostrar la imagen en el JLabel
                    lblFoto.setIcon(new ImageIcon(rutaArchivo));
                }
            }
        });
        btnSubirImagen.setBounds(194, 440, 120, 40);
        add(btnSubirImagen);

        btnSave = new JButton("Guardar");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de guardar los cambios?", "Confirmación de guardado", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    eliminarAccesorio(accesorioActual.getCodigo());
                    guardarDatosEnArchivo();
                    limpiarCampos();
                    btnSave.setVisible(false);
                    txNombre.setEditable(true);
                    txPrecio.setEditable(true);
                    txMarca.setEditable(true);
                    txPrecio.setEditable(true);
                  
                    txTamanio.setEditable(true);
                }
			}
		});
		btnSave.setBounds(730, 470, 180, 50);
		add(btnSave);
		btnSave.setVisible(false);

        lblAñadirAcce = new JLabel("Descripcion accesorio/lubricante:");
        lblAñadirAcce.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
        lblAñadirAcce.setBounds(62, 37, 671, 50);
        add(lblAñadirAcce);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(730, 470, 180, 50);
        btnEditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnSave.setVisible(true);
        		btnEditar.setVisible(false);
        		add(btnSubirImagen);
        		txNombre.setEditable(true);
                txPrecio.setEditable(true);
                txMarca.setEditable(true);
                txPrecio.setEditable(true);
                txTamanio.setEditable(true);
                btnSubirImagen.setVisible(true);

        	}
        });
        add(btnEditar);

        JButton btnBack = new JButton(">");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFProducts p = new JFProducts();
                removeAll();
                p.setVisible(true);
                revalidate();
                repaint();
            }
        });
        btnBack.setBounds(915, 40, 50, 50);
        add(btnBack);
        
        JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el accesorio/lubricante?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
		        if (confirmacion == JOptionPane.YES_OPTION) {
		            eliminarAccesorio(accesorioActual.getCodigo());
		            JFProducts p = new JFProducts();
		            removeAll();
		            p.setVisible(true);
		            revalidate();
		            repaint();
		        }
			}
		});
		btnEliminar.setBounds(10, 470, 50, 50);
		add(btnEliminar);
        
    }
    
    private void guardarListaAccesoriosEnArchivo(List<Accesorio> listaAccesorios) {
        // Guardar la lista de libros en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Accesorios.txt"))) {
            for (Accesorio acce : listaAccesorios) {
            	writer.write("Codigo: " + acce.getCodigo() + "\n");
                writer.write("Nombre: " + acce.getNombre() + "\n");
                writer.write("Precio: " + acce.getPrecio() + "\n");
                writer.write("Marca: " + acce.getMarca() + "\n");
                writer.write("Ruta: " + acce.getRuta() + "\n");
                writer.write("Tamaño: " + acce.getTamanio() + "\n");
                writer.write("------------------------------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
private void eliminarAccesorio(int codigo) {

    List<Accesorio> listaAccesorios = obtenerListaAccesoriosDesdeArchivo("Accesorios.txt");

    Optional<Accesorio> acceAEliminar = listaAccesorios.stream()
            .filter(acce -> acce.getCodigo()==(codigo))
            .findFirst();
  
    acceAEliminar.ifPresent(acce -> listaAccesorios.remove(acce));

    guardarListaAccesoriosEnArchivo(listaAccesorios);
}
private void guardarDatosEnArchivo() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Accesorios.txt", true))) {
        String nombre = txNombre.getText();
        String marca = txMarca.getText();
        int codigo;
        double precio, tamanio;

        // Validar que los campos numéricos contengan valores válidos
        try {
            codigo = Integer.parseInt(txCodigo.getText());
            precio = Double.parseDouble(txPrecio.getText());
            tamanio = Double.parseDouble(txTamanio.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos para código, precio y tamaño", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si hay error
        }
        String rutaImagen = lblFoto.getIcon() != null ? lblFoto.getIcon().toString() : "";

        writer.write("Codigo: " + codigo + "\n");
        writer.write("Nombre: " + nombre + "\n");
        writer.write("Precio: " + precio + "\n");
        writer.write("Marca: " + marca + "\n");
        writer.write("Ruta: " + rutaImagen + "\n");
        
        writer.write("Tamaño: " + tamanio + "\n");
        writer.write("------------------------------\n");

        JOptionPane.showMessageDialog(null, "Accesorio/lubricante registrado.");
        limpiarCampos();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
private List<Accesorio> obtenerListaAccesoriosDesdeArchivo(String nombreArchivo) {
    List<Accesorio> listaAccesorios = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            try {
                int codigo = Integer.parseInt(extraerValor(linea, "Codigo:"));
                String nombre = extraerValor(reader.readLine(), "Nombre:");
                double precio = Double.parseDouble(extraerValor(reader.readLine(), "Precio:"));
                String marca = extraerValor(reader.readLine(), "Marca:");
                String ruta = extraerValor(reader.readLine(), "Ruta:");
  
                double tamanio = Double.parseDouble(extraerValor(reader.readLine(), "Tamaño:"));


                Accesorio acce = new Accesorio(codigo, nombre, precio, marca, ruta, tamanio);
                listaAccesorios.add(acce);

                // Leer la línea en blanco
                reader.readLine();
            } catch (NumberFormatException ex) {
                // Log the error or show a warning, and skip to the next line
                System.err.println("Skipping invalid data: " + linea);
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
  

    return listaAccesorios;
}
private String extraerValor(String linea, String etiqueta) {
    return linea.substring(etiqueta.length()).trim();
}

private void limpiarCampos() {
	txCodigo.setText("");
	txNombre.setText("");
    txMarca.setText("");
    txPrecio.setText("");
    txTamanio.setText("");
    lblFoto.setIcon(new ImageIcon(""));
    
}

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RegistroProductoAccesorio frame = new RegistroProductoAccesorio();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
