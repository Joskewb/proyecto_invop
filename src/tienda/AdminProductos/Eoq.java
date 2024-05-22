package tienda.AdminProductos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Eoq extends JFrame {

    public Eoq() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Análisis EOQ Básico");
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

        JLabel lblTitulo = new JLabel("Análisis EOQ Básico", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        topPanel.add(lblTitulo);

        // Panel central con campos de entrada
        JPanel centerPanel = new JPanel(new GridLayout(8, 2, 10, 10)); // Se ha añadido un renglón para el tiempo de entrega
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

        centerPanel.add(createLabel("Tiempo de Entrega:")); // Nuevo campo para el tiempo de entrega
        JTextField txtTiempoEntrega = createTextField();
        txtTiempoEntrega.setFont(fieldFont);
        txtTiempoEntrega.setForeground(fieldColor);
        centerPanel.add(txtTiempoEntrega);

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
                    double tiempoEntrega = Double.parseDouble(txtTiempoEntrega.getText()); // Obtener el tiempo de entrega

                    // Calcular EOQ
                    double eoq = Math.sqrt((2 * demandaAnual * costoPedido) / costoAlmacenamiento);

                    // Calcular tiempo de ciclo de producción
                    double tiempoCicloProduccion = demandaAnual / eoq;

                    // Calcular punto de reorden
                    double puntoReorden = demandaAnual * tiempoEntrega;

                    // Calcular costo total por ciclo
                    double costoTotalCiclo = costoPedido + (costoUnitario * eoq) + (costoAlmacenamiento * Math.pow(eoq, 2)) / (2 * demandaAnual);

                    // Calcular costo total anual
                    double costoTotalAnual = (demandaAnual * costoPedido / eoq) + (costoUnitario * demandaAnual) + ((costoAlmacenamiento * eoq)) / (2);

                    // Calcular costo de producir un lote
                    double costoProducirLote = costoPedido + (costoUnitario * eoq);

                    // Calcular costo de mantener en inventario un lote
                    double costoInventarioLote = (costoAlmacenamiento * Math.pow(eoq, 2)) / (2 * demandaAnual);

                    // Formatear los resultados con dos decimales
                    DecimalFormat df = new DecimalFormat("#.##");
                    String eoqFormatted = df.format(eoq);
                    String tiempoCicloFormatted = df.format(tiempoCicloProduccion);
                    String costoTotalCicloFormatted = df.format(costoTotalCiclo);
                    String costoTotalAnualFormatted = df.format(costoTotalAnual);
                    String puntoReordenFormatted = df.format(puntoReorden); // Formatear el punto de reorden
                    String costoProducirLoteFormatted = df.format(costoProducirLote); // Formatear el costo de producir un lote
                    String costoInventarioLoteFormatted = df.format(costoInventarioLote); // Formatear el costo de mantener en inventario un lote

                    // Mostrar los resultados en una ventana emergente
                    String[] columnNames = {"Operación", "Valor", "Unidades"}; // Se añadió la columna "Unidades"
                    String[][] data = {
                            {"EOQ", eoqFormatted, "(U)"},
                            {"Tiempo de Ciclo de Producción", tiempoCicloFormatted, "(lote/año)"},
                            {"Costo Total por Ciclo", costoTotalCicloFormatted, "($/lote)"},
                            {"Costo Total Anual", costoTotalAnualFormatted, "($/año)"},
                            {"Punto de Reorden", puntoReordenFormatted, "(U/año)"}, // Mostrar el punto de reorden
                            {"Costo de Producir un Lote", costoProducirLoteFormatted, "($/lote)"}, // Mostrar el costo de producir un lote
                            {"Costo de Mantener en Inventario un Lote", costoInventarioLoteFormatted, "($/lote/año)"} // Mostrar el costo de mantener en inventario un lote
                    };
                    JTable table = new JTable(data, columnNames);
                    JScrollPane scrollPane = new JScrollPane(table);
                    JOptionPane.showMessageDialog(null, scrollPane, "Resultados", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos en los campos.");
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
                Eoq frame = new Eoq();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

