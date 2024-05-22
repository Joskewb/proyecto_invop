package tienda.AdminProductos;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RegistroProductoAccesorio extends JPanel {

    private JTextField txNombre;
    private JTextField txPrecio;
    private JTextField txMarca;
    private JLabel lblFoto;
    private JButton btnSubirImagen;
    private JButton btnSave; 
    private JLabel lblAñadirMod;
    private JTextField txCodigo;
    private JComboBox<String> cboxTipo;
    private static JFProducts jfProductsInstance;
    private JTextField txTamanio;

    private static final String DB_URL = "jdbc:mysql://localhost/inventario?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public RegistroProductoAccesorio() {
        setLayout(null);
        setBounds(0, 0, 1000, 580);
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

        txTamanio = new JTextField();
        txTamanio.setBounds(795, 231, 110, 35);
        add(txTamanio);
        txTamanio.setColumns(10);

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
                    guardarDatosEnBaseDeDatos();
                }
            }
        });
        btnSave.setBounds(510, 435, 400, 50);
        add(btnSave);

        lblAñadirMod = new JLabel("Nuevo accesorio/lubricante:");
        lblAñadirMod.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
        lblAñadirMod.setBounds(62, 37, 671, 50);
        add(lblAñadirMod);

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

    private void guardarDatosEnBaseDeDatos() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "INSERT INTO accesorios_lubricantes (codigo, nombre, precio, marca, tamaño, ruta_imagen) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(txCodigo.getText()));
            statement.setString(2, txNombre.getText());
            statement.setDouble(3, Double.parseDouble(txPrecio.getText()));
            statement.setString(4, txMarca.getText());
            statement.setDouble(5, Double.parseDouble(txTamanio.getText()));
            statement.setString(6, lblFoto.getIcon() != null ? lblFoto.getIcon().toString() : "");
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Accesorio/lubricante registrado en la base de datos.");
            limpiarCampos();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar el accesorio/lubricante en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
                JFrame frame = new JFrame();
                frame.getContentPane().add(new RegistroProductoAccesorio());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1000, 580);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

