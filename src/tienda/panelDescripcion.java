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

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class panelDescripcion extends JPanel {
	private JTextField txNombre;
	private JTextField txPrecio;
	private JTextField txMarca;
	private JTextField txAlto;
	private JTextField txAncho;
	private JTextField txLargo;
	private JLabel lblFoto;
	private JButton btnSubirImagen;
	private JButton btnSave;
	private JLabel lblAñadirCubo;
	private JTextField txCodigo, txTipo;
	JComboBox cboxTipo;
	private Cubos cuboActual;
	private String archivo="Cubos.txt";
	public panelDescripcion(Cubos cubo) {
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
		
		btnSubirImagen = new JButton("Subir imagen");
		btnSubirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png", "gif","jfif"));

                int seleccion = fileChooser.showOpenDialog(null);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    // Obtener la ruta del archivo seleccionado
                    String rutaArchivo = fileChooser.getSelectedFile().getPath();

                    // Mostrar la imagen en el JLabel
                    lblFoto.setIcon(new ImageIcon(rutaArchivo));
                }
			}
		});
		btnSubirImagen.setBounds(210, 424, 120, 40);
		add(btnSubirImagen);
		btnSubirImagen.setVisible(false);
		
		btnSave = new JButton("Guardar");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de guardar los cambios?", "Confirmación de guardado", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    eliminarCubo(cuboActual.getCodigo());
                    guardarDatosEnArchivo();
                    limpiarCampos();
                    btnSave.setVisible(false);
                    txNombre.setEditable(true);
                    txPrecio.setEditable(true);
                    txMarca.setEditable(true);
                    txPrecio.setEditable(true);
                    txAlto.setEditable(true);
                    txAncho.setVisible(true);
                    txLargo.setVisible(true);
                }
			}
		});
		btnSave.setBounds(730, 470, 180, 50);
		add(btnSave);
		btnSave.setVisible(false);
		
		lblAñadirCubo = new JLabel("Descripcion Cubo:");
		lblAñadirCubo.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
		lblAñadirCubo.setBounds(62, 37, 494, 50);
		add(lblAñadirCubo);
		
		
		
		cboxTipo = new JComboBox();
		cboxTipo.setBounds(759, 235, 151, 35);
		add(cboxTipo);
		cboxTipo.addItem("2x2");
		cboxTipo.addItem("3x3");
		cboxTipo.addItem("4x4");
		cboxTipo.addItem("5x5");
		cboxTipo.addItem("6x6");
		cboxTipo.addItem("7x7");
		cboxTipo.addItem("Pyraminx");
		cboxTipo.addItem("Megaminx");
		cboxTipo.addItem("Square-1");
		cboxTipo.addItem("Clock");
		cboxTipo.setVisible(false);
		
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
                txAlto.setEditable(true);
                txAncho.setEditable(true);
                txLargo.setEditable(true);
                remove(txTipo);
                
                btnSubirImagen.setVisible(true);
        		add(cboxTipo);
        		cboxTipo.setVisible(true);
        	}
        });
        add(btnEditar);
        
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
		
		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el cubo?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
		        if (confirmacion == JOptionPane.YES_OPTION) {
		            eliminarCubo(cuboActual.getCodigo());
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
	 private void guardarListaCubosEnArchivo(List<Cubos> listaCubos) {
	        // Guardar la lista en el archivo
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
	            for (Cubos cubo : listaCubos) {
	            	writer.write("Codigo: " + cubo.getCodigo() + "\n");
	                writer.write("Nombre: " + cubo.getNombre() + "\n");
	                writer.write("Precio: " + cubo.getPrecio() + "\n");
	                writer.write("Marca: " + cubo.getMarca() + "\n");
	                writer.write("Ruta: " + cubo.getRuta() + "\n");
	                writer.write("Tipo: " + cubo.getTipo() + "\n");
	                writer.write("Alto: " + cubo.getAlto() + "\n");
	                writer.write("Ancho: " + cubo.getAncho() + "\n");
	                writer.write("Largo: " + cubo.getLargo() + "\n");
	                writer.write("------------------------------\n");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	private void eliminarCubo(int codigo) {

        List<Cubos> listaCubos = obtenerListaCubosDesdeArchivo(archivo);

        Optional<Cubos> cuboAEliminar = listaCubos.stream()
                .filter(cubo -> cubo.getCodigo()==(codigo))
                .findFirst();
      
        cuboAEliminar.ifPresent(cubo -> listaCubos.remove(cubo));

        guardarListaCubosEnArchivo(listaCubos);
    }
	private void guardarDatosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            String nombre = txNombre.getText();
            String marca = txMarca.getText();
            String tipo = cboxTipo.getSelectedItem().toString();
            int codigo;
            double precio, alto, ancho, largo;

            // Validar que los campos numéricos contengan valores válidos
            try {
                codigo = Integer.parseInt(txCodigo.getText());
                precio = Double.parseDouble(txPrecio.getText());
                alto = Double.parseDouble(txAlto.getText());
                ancho = Double.parseDouble(txAncho.getText());
                largo = Double.parseDouble(txLargo.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos para código, precio, alto, ancho y largo.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si hay error
            }
            String rutaImagen = lblFoto.getIcon() != null ? lblFoto.getIcon().toString() : "";

            writer.write("Codigo: " + codigo + "\n");
            writer.write("Nombre: " + nombre + "\n");
            writer.write("Precio: " + precio + "\n");
            writer.write("Marca: " + marca + "\n");
            writer.write("Ruta: " + rutaImagen + "\n");
            writer.write("Tipo: " + tipo + "\n");
            writer.write("Alto: " + alto + "\n");
            writer.write("Ancho: " + ancho + "\n");
            writer.write("Largo: " + largo + "\n");
            writer.write("------------------------------\n");

            JOptionPane.showMessageDialog(null, "Cubo registrado.");
            limpiarCampos();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private List<Cubos> obtenerListaCubosDesdeArchivo(String nombreArchivo) {
	    List<Cubos> listaCubos = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
	        String linea;
	        while ((linea = reader.readLine()) != null) {
	            try {
	                int codigo = Integer.parseInt(extraerValor(linea, "Codigo:"));
	                String nombre = extraerValor(reader.readLine(), "Nombre:");
	                double precio = Double.parseDouble(extraerValor(reader.readLine(), "Precio:"));
	                String marca = extraerValor(reader.readLine(), "Marca:");
	                String ruta = extraerValor(reader.readLine(), "Ruta:");

	                String tipo = extraerValor(reader.readLine(), "Tipo:");
	                double alto = Double.parseDouble(extraerValor(reader.readLine(), "Alto:"));
	                double ancho = Double.parseDouble(extraerValor(reader.readLine(), "Ancho:"));
	                double largo = Double.parseDouble(extraerValor(reader.readLine(), "Largo:"));

	                Cubos cubo = new Cubos(codigo, nombre, precio, marca, ruta, tipo, alto, ancho, largo);
	                listaCubos.add(cubo);

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
	  

	    return listaCubos;
	}
	private String extraerValor(String linea, String etiqueta) {
        return linea.substring(etiqueta.length()).trim();
    }

    private void limpiarCampos() {
    	txCodigo.setText("");
    	txNombre.setText("");
        txMarca.setText("");
        txPrecio.setText("");
        txAlto.setText("");
        txAncho.setText("");
        txLargo.setText("");
        cboxTipo.setSelectedIndex(0);
        lblFoto.setIcon(new ImageIcon(""));
        
    }
}
