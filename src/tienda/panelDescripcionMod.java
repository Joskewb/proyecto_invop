package tienda;

import java.awt.Font;

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

import tienda.AdminProductos.JFProducts;
import tienda.entidades.Cubos;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class panelDescripcionMod extends JPanel {

	private JTextField txNombre;
	private JTextField txPrecio;
	private JTextField txMarca;
	private JLabel lblFoto;
	private JButton btnSubirImagen;
	private JButton btnSave;
	private JLabel lblAñadirMod;
	private JTextField txCodigo, txTipo;
	JComboBox cboxTipo;
	private Cubos modActual;
	public panelDescripcionMod(Cubos mod) {
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
                    eliminarMod(modActual.getCodigo());
                    guardarDatosEnArchivo();
                    limpiarCampos();
                    btnSave.setVisible(false);
                    txNombre.setEditable(true);
                    txPrecio.setEditable(true);
                    txMarca.setEditable(true);
                    txPrecio.setEditable(true);
                }
			}
		});
		btnSave.setBounds(730, 470, 180, 50);
		add(btnSave);
		btnSave.setVisible(false);
		
		lblAñadirMod = new JLabel("Descripcion Mod:");
		lblAñadirMod.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
		lblAñadirMod.setBounds(62, 37, 450, 50);
		add(lblAñadirMod);
		
		
		
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
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el mod?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
		        if (confirmacion == JOptionPane.YES_OPTION) {
		            eliminarMod(modActual.getCodigo());
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
	 private void guardarListaModsEnArchivo(List<Cubos> listaMods) {
	        // Guardar la lista de libros en el archivo
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Mod.txt"))) {
	            for (Cubos cubo : listaMods) {
	            	writer.write("Codigo: " + cubo.getCodigo() + "\n");
	                writer.write("Nombre: " + cubo.getNombre() + "\n");
	                writer.write("Precio: " + cubo.getPrecio() + "\n");
	                writer.write("Marca: " + cubo.getMarca() + "\n");
	                writer.write("Ruta: " + cubo.getRuta() + "\n");
	                writer.write("Tipo: " + cubo.getTipo() + "\n");
	                writer.write("------------------------------\n");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	private void eliminarMod(int codigo) {

        List<Cubos> listaMods = obtenerListaModsDesdeArchivo("Mod.txt");

        Optional<Cubos> modAEliminar = listaMods.stream()
                .filter(cubo -> cubo.getCodigo()==(codigo))
                .findFirst();
 
        // Si se encuentra el libro, eliminarlo de la lista
        modAEliminar.ifPresent(mod -> listaMods.remove(mod));

        // Guardar la lista actualizada en el archivo
        guardarListaModsEnArchivo(listaMods);
    }
	private void guardarDatosEnArchivo() { 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Mod.txt", true))) {
            String nombre = txNombre.getText();
            String marca = txMarca.getText();
            String tipo = cboxTipo.getSelectedItem().toString();
            int codigo;
            double precio;

            // Validar que los campos numéricos contengan valores válidos
            try {
                codigo = Integer.parseInt(txCodigo.getText());
                precio = Double.parseDouble(txPrecio.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos para código y precio", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si hay error
            }
            String rutaImagen = lblFoto.getIcon() != null ? lblFoto.getIcon().toString() : "";

            writer.write("Codigo: " + codigo + "\n");
            writer.write("Nombre: " + nombre + "\n");
            writer.write("Precio: " + precio + "\n");
            writer.write("Marca: " + marca + "\n");
            writer.write("Ruta: " + rutaImagen + "\n");
            writer.write("Tipo: " + tipo + "\n");
            writer.write("------------------------------\n");

            JOptionPane.showMessageDialog(null, "Mod registrado.");
            limpiarCampos();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private List<Cubos> obtenerListaModsDesdeArchivo(String nombreArchivo) {
	    List<Cubos> listaMods = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
	        String linea;
	        while ((linea = reader.readLine()) != null) {
	            try {
	                int codigo = Integer.parseInt(extraerValor(linea, "Codigo:"));
	                int stock = Integer.parseInt(extraerValor(linea, "stock:"));

	                String nombre = extraerValor(reader.readLine(), "Nombre:");
	                double precio = Double.parseDouble(extraerValor(reader.readLine(), "Precio:"));
	                String marca = extraerValor(reader.readLine(), "Marca:");
	                String ruta = extraerValor(reader.readLine(), "Ruta:");

	                String tipo = extraerValor(reader.readLine(), "Tipo:");

	                Cubos mod = new Cubos(codigo, nombre, precio, marca, ruta, tipo, 0, 0, 0,stock);
	                listaMods.add(mod);

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
	  

	    return listaMods;
	}
	private String extraerValor(String linea, String etiqueta) {
        return linea.substring(etiqueta.length()).trim();
    }
    // Método para limpiar los campos después de guardar
    private void limpiarCampos() {
    	txCodigo.setText("");
    	txNombre.setText("");
        txMarca.setText("");
        txPrecio.setText("");
        cboxTipo.setSelectedIndex(0);
        lblFoto.setIcon(new ImageIcon(""));
        
    }
}
