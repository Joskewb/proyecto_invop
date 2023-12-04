package tienda;

import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanelCubos extends JPanel {
    private List<Cubos> listaCubos;

    public PanelCubos(Cubos cubo) {
    	setBackground(new Color(105, 105, 105));
        this.listaCubos = listaCubos;
        setLayout(null);
        
        Font f=new Font("Bahnschrift", Font.PLAIN, 18);
    
        JLabel lblFoto = new JLabel();
        lblFoto.setIcon(new ImageIcon(cubo.getRuta()));
        lblFoto.setBounds(71, 62, 250, 300);
        add(lblFoto);
        
        JLabel lblCodigo = new JLabel("C: "+cubo.getCodigo());
        lblCodigo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblCodigo.setBounds(44, 27, 127, 24);
        add(lblCodigo);
        
        JLabel lblTipo = new JLabel(cubo.getTipo());
        lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipo.setFont(f);
        lblTipo.setBounds(181, 22, 165, 32);
        add(lblTipo);
        
        JLabel lblNombre = new JLabel(cubo.getNombre());
        lblNombre.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        lblNombre.setBounds(46, 384, 312, 30);
        add(lblNombre);
        
        JLabel lblPrecio = new JLabel("Bs. "+cubo.getPrecio());
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrecio.setForeground(new Color(128, 0, 0));
        lblPrecio.setFont(new Font("Bahnschrift", Font.BOLD, 25));
        
        lblPrecio.setBounds(231, 412, 115, 42);
        add(lblPrecio);
        
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

    
}

