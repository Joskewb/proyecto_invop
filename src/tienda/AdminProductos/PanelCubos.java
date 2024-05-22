package tienda.AdminProductos;

import javax.swing.*;

import tienda.PanelAccesorio;
import tienda.entidades.Cubos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanelCubos extends JPanel {
    private List<Cubos> listaCubos;
    private JLabel lblAlerta;

    public PanelCubos(Cubos cubo) {
        setBackground(new Color(105, 105, 105));
        this.listaCubos = obtenerListaCubosDesdeArchivo("archivo.txt");
        setLayout(null);

        Font f = new Font("Bahnschrift", Font.PLAIN, 18);

        JLabel lblFoto = new JLabel();
        lblFoto.setIcon(new ImageIcon(cubo.getRuta()));
        lblFoto.setBounds(71, 62, 250, 300);
        add(lblFoto);

        JLabel lblCodigo = new JLabel("STOCK ACTUAL: " + cubo.getStock());
        lblCodigo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblCodigo.setBounds(44, 27, 127, 24);
        add(lblCodigo);

        if (cubo.getStock() < 11) {
            JButton btnEOQ = new JButton("EOQ");
            btnEOQ.setBounds(44, 60, 127, 24);
            btnEOQ.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EventQueue.invokeLater(() -> {
                        try {
                            Eoq frame = new Eoq();
                            frame.setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            });
            add(btnEOQ);
            JButton btnEOQF = new JButton("EOQ Faltantes");
            btnEOQF.setBounds(44, 100, 127, 24);
            btnEOQF.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EventQueue.invokeLater(() -> {
                        try {
                            EoqFaltantes frame = new EoqFaltantes();
                            frame.setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            });
            add(btnEOQF);
            
            
            
            
        }
        
        
        
        
        
        JLabel lblTipo = new JLabel(cubo.getTipo());
        lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipo.setFont(f);
        lblTipo.setBounds(181, 22, 165, 32);
        add(lblTipo);

        JLabel lblNombre = new JLabel(cubo.getNombre());
        lblNombre.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        lblNombre.setBounds(46, 384, 312, 30);
        add(lblNombre);

        JLabel lblPrecio = new JLabel("Bs. " + cubo.getPrecio());
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrecio.setForeground(new Color(128, 0, 0));
        lblPrecio.setFont(new Font("Bahnschrift", Font.BOLD, 25));
        lblPrecio.setBounds(231, 412, 115, 42);
        add(lblPrecio);

        lblAlerta = new JLabel();
        lblAlerta.setForeground(Color.RED);
        lblAlerta.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        lblAlerta.setBounds(46, 430, 300, 30);
        add(lblAlerta);

        // Verificar el nivel de stock
        if (cubo.getStock() < 10) {
            lblAlerta.setText("¡Nivel de stock muy bajo!");
        }

        JLabel lbfondo = new JLabel("New label");
        lbfondo.setIcon(new ImageIcon(PanelAccesorio.class.getResource("/imagenes/blanco(1000x700).jpg")));
        lbfondo.setBounds(20, 15, 355, 450);
        add(lbfondo);
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
                    int stock = Integer.parseInt(extraerValor(linea, "stock:"));

                    String tipo = extraerValor(reader.readLine(), "Tipo:");
                    double alto = Double.parseDouble(extraerValor(reader.readLine(), "Alto:"));
                    double ancho = Double.parseDouble(extraerValor(reader.readLine(), "Ancho:"));
                    double largo = Double.parseDouble(extraerValor(reader.readLine(), "Largo:"));

                    Cubos cubo = new Cubos(codigo, nombre, precio, marca, ruta, tipo, alto, ancho, largo, stock);
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
}