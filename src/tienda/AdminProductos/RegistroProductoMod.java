package tienda.AdminProductos;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistroProductoMod extends JPanel {
    private JTextField txNombre;
    private JTextField txPrecio;
    private JTextField txMarca;
    private JLabel lblFoto;
    private JButton btnSubirImagen;
    private JButton btnSave;
    private JLabel lblAñadirMod;
    private JTextField txCodigo;
    private JFrame parentFrame;
    JComboBox cboxTipo;
    private static JFProducts jfProductsInstance;

    public RegistroProductoMod() {
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

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(f);
        lblTipo.setBounds(698, 240, 54, 25);
        add(lblTipo);

        txCodigo = new JTextField();
        txCodigo.setColumns(10);
        txCodigo.setBounds(500, 113, 410, 35);
        add(txCodigo);

        txNombre = new JTextField();
        txNombre.setBounds(500, 172, 410, 35);
        add(txNombre);
        txNombre.setColumns(10);

        txPrecio = new JTextField();
        txPrecio.setColumns(10);
        txPrecio.setBounds(500, 231, 169, 35);
        add(txPrecio);

        txMarca = new JTextField();
        txMarca.setColumns(10);
        txMarca.setBounds(500, 293, 410, 35);
        add(txMarca);

        lblFoto = new JLabel("imagen");
        lblFoto.setIcon(new ImageIcon());
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
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de registrar " + txCodigo.getText() + ": " + txNombre.getText() + "?", "Confirmación de registro",
                        JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    guardarDatosEnArchivo();
                }
            }
        });
        btnSave.setBounds(510, 435, 400, 50);
        add(btnSave);

        lblAñadirMod = new JLabel("Nuevo Mod:");
        lblAñadirMod.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
        lblAñadirMod.setBounds(62, 37, 252, 50);
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

    // Método para limpiar los campos después de guardar
    private void limpiarCampos() {
        txCodigo.setText("");
        txNombre.setText("");
        txMarca.setText("");
        txPrecio.setText("");
        cboxTipo.setSelectedIndex(0);
        lblFoto.setIcon(new ImageIcon(""));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RegistroProductoMod frame = new RegistroProductoMod();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
