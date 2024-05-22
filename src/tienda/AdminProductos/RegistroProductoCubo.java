package tienda.AdminProductos;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistroProductoCubo extends JPanel {

    private JTextField txNombre;
    private JTextField txPrecio;
    private JTextField txMarca;
    private JTextField txAlto;
    private JTextField txAncho;
    private JTextField txLargo;
    private JTextField txStock; // Nuevo campo para el stock
    private JLabel lblFoto;
    private JButton btnSubirImagen;
    private JButton btnSave;
    private JLabel lblAñadirCubo;
    private JComboBox<String> cboxTipo;
    private static JFProducts jfProductsInstance;
    private Connection connection;

    public RegistroProductoCubo() {
        setLayout(null);
        setBounds(0, 0, 1000, 580);
        Font f = new Font("Bahnschrift", Font.PLAIN, 20);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(f);
        lblNombre.setBounds(395, 79, 88, 20); // Movido hacia arriba
        add(lblNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(f);
        lblPrecio.setBounds(409, 140, 71, 25); // Movido hacia arriba
        add(lblPrecio);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setFont(f);
        lblMarca.setBounds(409, 202, 71, 25); // Movido hacia arriba
        add(lblMarca);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(f);
        lblTipo.setBounds(698, 140, 54, 25); // Movido hacia arriba
        add(lblTipo);

        JLabel lblAlto = new JLabel("Alto:");
        lblAlto.setFont(f);
        lblAlto.setBounds(429, 261, 54, 25); // Movido hacia arriba
        add(lblAlto);

        JLabel lblAncho = new JLabel("Ancho:");
        lblAncho.setFont(f);
        lblAncho.setBounds(598, 261, 71, 25); // Movido hacia arriba
        add(lblAncho);

        JLabel lblLargo = new JLabel("Largo:");
        lblLargo.setFont(f);
        lblLargo.setBounds(757, 261, 64, 25); // Movido hacia arriba
        add(lblLargo);

        JLabel lblStock = new JLabel("Stock:"); // Nuevo campo para el stock
        lblStock.setFont(f);
        lblStock.setBounds(409, 323, 71, 25); // Movido hacia arriba y ajustado
        add(lblStock);

        txNombre = new JTextField();
        txNombre.setBounds(500, 72, 410, 35); // Movido hacia arriba
        add(txNombre);
        txNombre.setColumns(10);

        txPrecio = new JTextField();
        txPrecio.setColumns(10);
        txPrecio.setBounds(500, 131, 169, 35); // Movido hacia arriba
        add(txPrecio);

        txMarca = new JTextField();
        txMarca.setColumns(10);
        txMarca.setBounds(500, 193, 410, 35); // Movido hacia arriba
        add(txMarca);

        txAlto = new JTextField();
        txAlto.setColumns(10);
        txAlto.setBounds(500, 256, 80, 35); // Movido hacia arriba
        add(txAlto);

        txAncho = new JTextField();
        txAncho.setColumns(10);
        txAncho.setBounds(667, 256, 80, 35); // Movido hacia arriba
        add(txAncho);

        txLargo = new JTextField();
        txLargo.setColumns(10);
        txLargo.setBounds(830, 256, 80, 35); // Movido hacia arriba
        add(txLargo);

        txStock = new JTextField(); // Nuevo campo para el stock
        txStock.setBounds(500, 316, 169, 35); // Movido hacia arriba y ajustado
        add(txStock);
        txStock.setColumns(10);

        lblFoto = new JLabel("imagen");
        lblFoto.setIcon(new ImageIcon());
        lblFoto.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        lblFoto.setBounds(64, 113, 250, 300);
        add(lblFoto);

        btnSubirImagen = new JButton("Subir imagen");
        btnSubirImagen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png",
                        "gif", "jfif"));

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
                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro de registrar " + txNombre.getText() + "?",
                        "Confirmación de registro", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    guardarDatosEnBaseDeDatos();
                }
            }
        });
        btnSave.setBounds(510, 435, 400, 50);
        add(btnSave);

        lblAñadirCubo = new JLabel("Nuevo Cubo:");
        lblAñadirCubo.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
        lblAñadirCubo.setBounds(62, 37, 252, 50);
        add(lblAñadirCubo);

        cboxTipo = new JComboBox<String>();
        cboxTipo.setBounds(759, 131, 151, 35); // Movido hacia arriba
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

    private void guardarDatosEnBaseDeDatos() {
        String url = "jdbc:mysql://localhost/inventario?useSSL=false";
        String usuario = "root";
        String contraseña = "1234";

        try {
            connection = DriverManager.getConnection(url, usuario, contraseña);

            String query = "INSERT INTO cubos (nombre, precio, marca, tipo, alto, ancho, largo, stock, ruta_imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, txNombre.getText());
            statement.setDouble(2, Double.parseDouble(txPrecio.getText()));
            statement.setString(3, txMarca.getText());
            statement.setString(4, (String) cboxTipo.getSelectedItem());
            statement.setDouble(5, Double.parseDouble(txAlto.getText()));
            statement.setDouble(6, Double.parseDouble(txAncho.getText()));
            statement.setDouble(7, Double.parseDouble(txLargo.getText()));
            statement.setInt(8, Integer.parseInt(txStock.getText())); // Establece el valor del stock
            statement.setString(9, lblFoto.getIcon().toString()); // Ajusta la obtención de la ruta de la imagen

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Cubo registrado correctamente en la base de datos.");
                limpiarCampos();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Método para limpiar los campos después de guardar
    private void limpiarCampos() {
        txNombre.setText("");
        txMarca.setText("");
        txPrecio.setText("");
        txAlto.setText("");
        txAncho.setText("");
        txLargo.setText("");
        txStock.setText(""); // Limpia el campo de entrada del stock
        cboxTipo.setSelectedIndex(0);
        lblFoto.setIcon(new ImageIcon(""));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RegistroProductoCubo frame = new RegistroProductoCubo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
