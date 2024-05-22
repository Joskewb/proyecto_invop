package tienda.AdminProductos;
import javax.swing.*;
import java.awt.*;

public class Inventarios extends JFrame {

    public Inventarios() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Gestión de Inventarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
 
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240)); // Color de fondo

        getContentPane().add(panel);

        // Panel superior con etiqueta de título
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(50, 50, 50)); // Color de fondo
        panel.add(topPanel, BorderLayout.NORTH);

        JLabel lblTitulo = new JLabel("Gestión de Inventarios", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE); // Color de texto
        topPanel.add(lblTitulo);

        // Panel central con campos de entrada
        JPanel centerPanel = new JPanel(new GridLayout(11, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen interior
        centerPanel.setBackground(new Color(240, 240, 240)); // Color de fondo
        panel.add(centerPanel, BorderLayout.CENTER);

        // Estilo de los campos de entrada
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        Color fieldColor = new Color(70, 70, 70); // Color de texto

        centerPanel.add(createLabel("Nombre:"));
        JTextField txtNombre = createTextField();
        txtNombre.setFont(fieldFont);
        txtNombre.setForeground(fieldColor);
        centerPanel.add(txtNombre);

        centerPanel.add(createLabel("Código SKU (Opcional):"));
        JTextField txtCodigoSKU = createTextField();
        txtCodigoSKU.setFont(fieldFont);
        txtCodigoSKU.setForeground(fieldColor);
        centerPanel.add(txtCodigoSKU);

        centerPanel.add(createLabel("Marca:"));
        JTextField txtMarca = createTextField();
        txtMarca.setFont(fieldFont);
        txtMarca.setForeground(fieldColor);
        centerPanel.add(txtMarca);

        centerPanel.add(createLabel("Color:"));
        JTextField txtColor = createTextField();
        txtColor.setFont(fieldFont);
        txtColor.setForeground(fieldColor);
        centerPanel.add(txtColor);

        centerPanel.add(createLabel("Cantidad:"));
        JTextField txtCantidad = createTextField();
        txtCantidad.setFont(fieldFont);
        txtCantidad.setForeground(fieldColor);
        centerPanel.add(txtCantidad);

        centerPanel.add(createLabel("Precio Unitario (Bs.):"));
        JTextField txtPrecioUnitario = createTextField();
        txtPrecioUnitario.setFont(fieldFont);
        txtPrecioUnitario.setForeground(fieldColor);
        centerPanel.add(txtPrecioUnitario);

        centerPanel.add(createLabel("Total Bs. Vendido:"));
        JTextField txtTotalBsVendido = createTextField();
        txtTotalBsVendido.setFont(fieldFont);
        txtTotalBsVendido.setForeground(fieldColor);
        centerPanel.add(txtTotalBsVendido);

        centerPanel.add(createLabel("Descuento Vendido:"));
        JTextField txtDescuentoVendido = createTextField();
        txtDescuentoVendido.setFont(fieldFont);
        txtDescuentoVendido.setForeground(fieldColor);
        centerPanel.add(txtDescuentoVendido);

        centerPanel.add(createLabel("Cantidad Stock:"));
        JTextField txtCantidadStock = createTextField();
        txtCantidadStock.setFont(fieldFont);
        txtCantidadStock.setForeground(fieldColor);
        centerPanel.add(txtCantidadStock);

        centerPanel.add(createLabel("Stock Bs.:"));
        JTextField txtStockBs = createTextField();
        txtStockBs.setFont(fieldFont);
        txtStockBs.setForeground(fieldColor);
        centerPanel.add(txtStockBs);

        // Panel inferior con botones
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(240, 240, 240)); // Color de fondo
        panel.add(bottomPanel, BorderLayout.SOUTH);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.setForeground(Color.WHITE); // Color de texto
        btnGuardar.setBackground(new Color(50, 150, 50)); // Color de fondo
        btnGuardar.setFocusPainted(false); // Elimina el borde al obtener el foco
        bottomPanel.add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setForeground(Color.WHITE); // Color de texto
        btnCancelar.setBackground(new Color(150, 50, 50)); // Color de fondo
        btnCancelar.setFocusPainted(false); // Elimina el borde al obtener el foco
        bottomPanel.add(btnCancelar);
        
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(70, 70, 70)); // Color de texto
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30)); // Tamaño preferido
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150)), // Borde
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Margen interior
        return textField;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	Inventarios frame = new Inventarios();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
