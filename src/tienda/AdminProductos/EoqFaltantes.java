package tienda.AdminProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class EoqFaltantes extends JFrame {

    public EoqFaltantes() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Análisis EOQ Básico - Faltantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));

        getContentPane().add(panel);

        // Panel superior con etiqueta de título
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(50, 50, 50));
        panel.add(topPanel, BorderLayout.NORTH);

        JLabel lblTitulo = new JLabel("Análisis EOQ Básico - Faltantes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        topPanel.add(lblTitulo);

        // Panel central con campos de entrada
        JPanel centerPanel = new JPanel(new GridLayout(8, 2, 10, 10)); // Se ha añadido un renglón para el costo por faltantes
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        centerPanel.setBackground(new Color(240, 240, 240));
        panel.add(centerPanel, BorderLayout.CENTER);

        // Estilo de los campos de entrada
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        Color fieldColor = new Color(70, 70, 70);

        centerPanel.add(createLabel("Demanda Anual:"));
        JTextField txtDemandaAnual = createTextField();
        txtDemandaAnual.setFont(fieldFont);
        txtDemandaAnual.setForeground(fieldColor);
        centerPanel.add(txtDemandaAnual);

        centerPanel.add(createLabel("Costo de Pedido (S):"));
        JTextField txtCostoPedido = createTextField();
        txtCostoPedido.setFont(fieldFont);
        txtCostoPedido.setForeground(fieldColor);
        centerPanel.add(txtCostoPedido);

        centerPanel.add(createLabel("Costo de Almacenamiento por Unidad (H):"));
        JTextField txtCostoAlmacenamiento = createTextField();
        txtCostoAlmacenamiento.setFont(fieldFont);
        txtCostoAlmacenamiento.setForeground(fieldColor);
        centerPanel.add(txtCostoAlmacenamiento);

        centerPanel.add(createLabel("Costo Unitario:"));
        JTextField txtCostoUnitario = createTextField();
        txtCostoUnitario.setFont(fieldFont);
        txtCostoUnitario.setForeground(fieldColor);
        centerPanel.add(txtCostoUnitario);

        centerPanel.add(createLabel("Costo por Faltantes por Unidad (Cf):")); // Nuevo campo para el costo por faltantes
        JTextField txtCostoFaltantes = createTextField();
        txtCostoFaltantes.setFont(fieldFont);
        txtCostoFaltantes.setForeground(fieldColor);
        centerPanel.add(txtCostoFaltantes);

        // Panel inferior con botones
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(240, 240, 240));
        panel.add(bottomPanel, BorderLayout.SOUTH);

        JButton btnCalcular = new JButton("Calcular EOQ");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 14));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setBackground(new Color(50, 150, 50));
        btnCalcular.setFocusPainted(false);
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener valores de los campos de texto
                    double demandaAnual = Double.parseDouble(txtDemandaAnual.getText());
                    double costoPedido = Double.parseDouble(txtCostoPedido.getText());
                    double costoAlmacenamiento = Double.parseDouble(txtCostoAlmacenamiento.getText());
                    double costoUnitario = Double.parseDouble(txtCostoUnitario.getText());
                    double costoFaltantes = Double.parseDouble(txtCostoFaltantes.getText()); // Obtener el costo por faltantes

                    // Calcular EOQ
                    double eoq = Math.sqrt((2 * demandaAnual * costoPedido) / costoAlmacenamiento) * Math.sqrt((costoFaltantes + costoAlmacenamiento) / costoFaltantes);

                    

                    // nivel de inventario optimo
                    double s = Math.sqrt((2 * demandaAnual * costoPedido) / costoAlmacenamiento) * Math.sqrt((costoFaltantes) / (costoFaltantes + costoAlmacenamiento));
                   
                    
                 // Tiempo de ciclo de produccion
                    double tc = eoq / demandaAnual;
                   
                    // Cantidad optima de pedido 
                    double t = eoq - s;
                    
                    // Calcular costo total anual
                    double costoTotalAnual = (demandaAnual * costoPedido / eoq) + (costoUnitario * demandaAnual) + (((costoAlmacenamiento * Math.pow(s, 2))) / (2*eoq)) + ((costoFaltantes*Math.pow(t, 2)))/(2*eoq);

                    
                    // Calcular costo total por ciclo
                    double costoTotalCiclo = (costoPedido + (costoUnitario*eoq)) + (((costoAlmacenamiento * Math.pow(s, 2))) / (2*demandaAnual)) + ((costoFaltantes*Math.pow(t, 2)))/(2*demandaAnual);

                    
                    
                    
                    // Crear un modelo de tabla
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Operación");
                    model.addColumn("Valor");

                    // Calcular los valores de las operaciones
                    double valorSqrt1 = Math.sqrt((2 * demandaAnual * costoPedido) / costoAlmacenamiento);
                    double valorSqrt2 = Math.sqrt((costoFaltantes + costoAlmacenamiento) / costoFaltantes);
                    double valorEOQ = valorSqrt1 * valorSqrt2;

                    // Añadir las operaciones y el resultado a la tabla
                    model.addRow(new Object[]{"EOQ", String.valueOf(eoq)});
                    model.addRow(new Object[]{"Nivel de inventario optimo", String.valueOf(s)});
                    model.addRow(new Object[]{"tiempo ciclo produccion", String.valueOf(tc)});
                    model.addRow(new Object[]{"cantidad faltante maximo", String.valueOf(t)});
                    model.addRow(new Object[]{"costo total unidad de tiempo", String.valueOf(costoTotalAnual)});
                    model.addRow(new Object[]{"costo total unidad de tiempo", String.valueOf(costoTotalCiclo)});


                    // Crear la tabla
                    JTable table = new JTable(model);

                    // Mostrar la tabla en una ventana emergente
                    JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos en los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        bottomPanel.add(btnCalcular);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 14));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setBackground(new Color(150, 50, 50));
        btnLimpiar.setFocusPainted(false);
        bottomPanel.add(btnLimpiar);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(70, 70, 70));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return textField;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EoqFaltantes frame = new EoqFaltantes();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
