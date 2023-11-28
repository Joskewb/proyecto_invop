package tienda;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class JFProducts extends JFrame {
	//CatalogoGestion catalogoGestion;
	JScrollPane scrollPane;
    private boolean scrolled = false;
    PanelMods panelmods;
    PanelCubos panelcubos;
    JPanel panelTarjetas;
    JPanel panelTarjetasContenido;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFProducts frame = new JFProducts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFProducts() {
		
		getContentPane().setBackground(Color.BLACK);
    	getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);
        setLocationRelativeTo(null);
        
        
        //Muestra de productos
        panelTarjetas = new JPanel();
        panelTarjetas.setBounds(160, 120, 825, 540);
        getContentPane().add(panelTarjetas);
        panelTarjetas.setLayout(null);
        
        panelTarjetasContenido = new JPanel();
        panelTarjetasContenido.setBounds(160, 190, 825, 470);
        panelTarjetasContenido.setLayout(new GridLayout(0, 2));
        
        JScrollPane scrollPane = new JScrollPane(panelTarjetasContenido);
        
        JButton btnAgregarCubo = new JButton("Agregar");
        btnAgregarCubo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RegistroProductoCubo p=new RegistroProductoCubo();
        		p.setSize(1000,680);
				p.setLocation(0,0);
				getContentPane().removeAll();
				getContentPane().add(p, BorderLayout.CENTER);
				revalidate();
				repaint();
        		
        	}
        });
        btnAgregarCubo.setBounds(715, 11, 100, 40);
        panelTarjetas.add(btnAgregarCubo);
        
        JLabel lblCubos = new JLabel("Cubos");
        lblCubos.setFont(new Font("Bahnschrift", Font.BOLD, 30));
        lblCubos.setBounds(21, 26, 215, 36);
        panelTarjetas.add(lblCubos);
        scrollPane.setBounds(160, 190, 825, 470);
        getContentPane().add(scrollPane);
        
        
        List<Cubos> listaCubos = obtenerListaCubosDesdeArchivo("Cubos1.txt");
        cargarBotonesCubos(listaCubos);
        
        
        //Barra superior
        JButton btnLogo = new JButton("usuario");
        btnLogo.setBounds(890, 10, 80, 80);
        getContentPane().add(btnLogo);

        JButton btnUsuario = new JButton("logo");
        btnUsuario.setBounds(25, 25, 101, 75);
        getContentPane().add(btnUsuario);

        JButton btnProductos = new JButton("Productos");
        btnProductos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                JFProducts n = new JFProducts();
                n.setVisible(true);
        	}
        });
        btnProductos.setBounds(250, 50, 80, 30);
        getContentPane().add(btnProductos);

        JButton btnInicio = new JButton("Inicio");
        btnInicio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                inicio n = new inicio();
                n.setVisible(true);
        	}
        });
        btnInicio.setBounds(175, 50, 75, 30);
        getContentPane().add(btnInicio);

        JButton btnOfertas = new JButton("Ofertas");
        btnOfertas.setBounds(330, 50, 75, 30);
        getContentPane().add(btnOfertas);

        JButton btnPaquetes = new JButton("Paquetes");
        btnPaquetes.setBounds(405, 50, 80, 30);
        getContentPane().add(btnPaquetes);

        JButton btnNosotros = new JButton("Nosotros");
        btnNosotros.setBounds(485, 50, 80, 30);
        getContentPane().add(btnNosotros);
        
        
        //inferior lateral
        JPanel panelLateral= new JPanel();
        getContentPane().add(panelLateral);
        panelLateral.setBounds(0, 120, 160, 540);
        
        
        JButton btnCubos = new JButton("Cubos");
        btnCubos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		List<Cubos> listaCubos = obtenerListaCubosDesdeArchivo("Cubos1.txt");
                cargarBotonesCubos(listaCubos);
        	}
        });
		panelLateral.setLayout(null);
		btnCubos.setBounds(0, 50, 160, 50);
		panelLateral.add(btnCubos);
		
		JButton btnMods = new JButton("Mods");
		btnMods.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//scrollPane.setViewportView(panelmods);
        	}
        });
		btnMods.setBounds(0, 100, 160, 50);
		panelLateral.add(btnMods);
		
		JButton btnLlaveros = new JButton("Llaveros");
		btnLlaveros.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
		btnLlaveros.setBounds(0, 150, 160, 50);
		panelLateral.add(btnLlaveros);
		
		JButton btnLubri = new JButton("Lubricantes y Accesorios");
		btnLubri.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
		btnLubri.setBounds(0, 200,160, 50);
		panelLateral.add(btnLubri);
		
		JLabel txProductos = new JLabel("Productos");
		txProductos.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		txProductos.setBounds(30, 15, 97, 25);
		panelLateral.add(txProductos);
		
	}
	private void cargarBotonesCubos(List<Cubos> listaCubos) {
        for (Cubos cubo : listaCubos) {
            JButton btnprod = new JButton();
            btnprod.setLayout(new BorderLayout());
            
            PanelCubos panelDetalles= new PanelCubos(cubo);
            btnprod.add(panelDetalles, BorderLayout.CENTER);
            btnprod.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // Establecer un tamaño preferido para el botón
            btnprod.setPreferredSize(new Dimension(300, 500));
            btnprod.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	dispose();
                	//JFMLibrosDescripcion frameDescripcion = new JFMLibrosDescripcion(cubo);
                    
                	//frameDescripcion.setVisible(true);
                    dispose();
                }
            });

            panelTarjetasContenido.add(btnprod);
        }
        panelTarjetasContenido.revalidate();
        panelTarjetasContenido.repaint();
        
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
	    panelTarjetasContenido.removeAll();
	    panelTarjetasContenido.revalidate();
	    panelTarjetasContenido.repaint();
	    cargarBotonesCubos(listaCubos);

	    return listaCubos;
	}

    private String extraerValor(String linea, String etiqueta) {
        return linea.substring(etiqueta.length()).trim();
    }
}
