package tienda.AdminProductos;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tienda.Accesorio;
import tienda.entidades.*;
import tienda.Nosotros;
import tienda.PanelAccesorio;
import tienda.panelDescripcion;
import tienda.panelDescripcionAccesorio;
import tienda.panelDescripcionMod;

import javax.swing.ImageIcon;

public class JFProducts extends JFrame {
	JScrollPane scrollPane;
    private String nombreUsuario;

    private boolean scrolled = false;
    PanelAccesorio panelmods;
    PanelCubos panelcubos;
    JPanel panelTarjetas;
    JPanel panelTarjetasContenido;
    JPanel panelLateral;
    panelDescripcion p;
    panelDescripcionMod pm;
    panelDescripcionAccesorio pa;
    JLabel lblCubos;
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
        panelTarjetas.setBackground(new Color(105, 105, 105));
        panelTarjetas.setBounds(160, 120, 825, 540);
        getContentPane().add(panelTarjetas);
        panelTarjetas.setLayout(null);
        
        panelTarjetasContenido = new JPanel();
        panelTarjetasContenido.setBounds(160, 190, 825, 470);
        panelTarjetasContenido.setLayout(new GridLayout(0, 2));
        
        scrollPane = new JScrollPane(panelTarjetasContenido);
        
        JButton btnAgregarCubo = new JButton("Nuevo");
        btnAgregarCubo.setBackground(new Color(130, 214, 103));
        btnAgregarCubo.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
        btnAgregarCubo.setBounds(715, 11, 100, 40);
        panelTarjetas.add(btnAgregarCubo);
        
        lblCubos = new JLabel();
        lblCubos.setForeground(new Color(255, 255, 255));
        lblCubos.setText("Cubos");
        lblCubos.setFont(new Font("Bahnschrift", Font.BOLD, 30));
        lblCubos.setBounds(21, 26, 513, 36);
        panelTarjetas.add(lblCubos);
        scrollPane.setBounds(160, 190, 825, 470);
        getContentPane().add(scrollPane);
        
        
        List<Cubos> listaCubos = obtenerListaCubosDesdeBaseDeDatos();
        cargarBotonesCubos(listaCubos);
        
        
        //Boton usuario
        JButton btnLogo = new JButton("");
        btnLogo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                Nosotros n = new Nosotros();
                n.setVisible(true);
        	}
        });
        btnLogo.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/user(80x80).jpg")));
        btnLogo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnLogo.setBounds(882, 22, 80, 80);
        getContentPane().add(btnLogo);
        //boton logo
        JButton btnUsuario = new JButton("");
        btnUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                inicio n = new inicio(nombreUsuario);
                n.setVisible(true);
        	}
        });
        btnUsuario.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/logo(100x75).jpg")));
        btnUsuario.setHorizontalTextPosition(SwingConstants.CENTER);
        btnUsuario.setBounds(25, 25, 100, 75);
        getContentPane().add(btnUsuario);

        JButton btnProductos = new JButton("Productos");
        btnProductos.setHorizontalTextPosition(SwingConstants.CENTER);
        btnProductos.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/rojo(1000x700).jpg")));
        btnProductos.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        btnProductos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                JFProducts n = new JFProducts(); 
                n.setVisible(true);
        	}
        });
        btnProductos.setBounds(250, 50, 100, 30);
        getContentPane().add(btnProductos);

        JButton btnInicio = new JButton("Inicio");
        btnInicio.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/amarillo(1000x700).jpg")));
        btnInicio.setHorizontalTextPosition(SwingConstants.CENTER);
        btnInicio.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
        btnInicio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                inicio n = new inicio(nombreUsuario);
                n.setVisible(true);
        	}
        });
        btnInicio.setBounds(175, 50, 75, 30);
        getContentPane().add(btnInicio);

        JButton btnNosotros = new JButton("Nosotros");
        btnNosotros.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                Nosotros n = new Nosotros();
                n.setVisible(true);
        	}
        });
        btnNosotros.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/Azul(1000x700).jpg")));
        btnNosotros.setHorizontalTextPosition(SwingConstants.CENTER);
        btnNosotros.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        btnNosotros.setBounds(350, 50, 100, 30);
        getContentPane().add(btnNosotros);
        
        
        //inferior lateral
        panelLateral= new JPanel();
        panelLateral.setBackground(new Color(30, 34, 35));
        getContentPane().add(panelLateral);
        panelLateral.setBounds(0, 120, 160, 540);
         
        JButton btnCubos = new JButton("Cubos");
        btnCubos.setForeground(new Color(50, 85, 40));
        btnCubos.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/blanco(1000x700).jpg")));
        btnCubos.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCubos.setFont(new Font("Dialog", Font.BOLD, 14));
        btnCubos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Cubos> listaCubos = obtenerListaCubosDesdeBaseDeDatos(); // Aquí se realiza el cambio
                cargarBotonesCubos(listaCubos);
                lblCubos.setText("Cubos");
                btnAgregarCubo.setBackground(new Color(230, 110, 111));
                btnAgregarCubo.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        RegistroProductoCubo p = new RegistroProductoCubo();
                        p.setSize(1000, 540);
                        p.setLocation(0, 120);
                        getContentPane().remove(panelTarjetasContenido);
                        getContentPane().remove(panelTarjetas);
                        getContentPane().remove(panelLateral);
                        getContentPane().remove(scrollPane);
                        panelTarjetasContenido.setVisible(false);
                        panelTarjetas.setVisible(false);
                        panelLateral.setVisible(false);
                        scrollPane.setVisible(false);
                        getContentPane().add(p, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                    }
                });
            }
        });
		panelLateral.setLayout(null);
		btnCubos.setBounds(0, 100, 160, 50);
		panelLateral.add(btnCubos);
		
		JButton btnMods = new JButton("Mods");
		btnMods.setForeground(new Color(50, 85, 40));
		btnMods.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/blanco(1000x700).jpg")));
		btnMods.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMods.setFont(new Font("Dialog", Font.BOLD, 14));
		btnMods.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblCubos.setText("Mods");
        		List<Cubos> listaMods = obtenerListaCubosDesdeBaseDeDatos();
                cargarBotonesMods(listaMods);
                btnAgregarCubo.setBackground(new Color(255,219,89));
                btnAgregarCubo.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		RegistroProductoMod p=new RegistroProductoMod();
                		p.setSize(1000,540);
        				p.setLocation(0,120);
        				getContentPane().remove(panelTarjetasContenido);
        				getContentPane().remove(panelTarjetas);
                        getContentPane().remove(panelLateral);
                        getContentPane().remove(scrollPane);
                        panelTarjetasContenido.setVisible(false);
                        panelTarjetas.setVisible(false);
                        panelLateral.setVisible(false);
                        scrollPane.setVisible(false);
                        getContentPane().add(p, BorderLayout.CENTER);
        				revalidate();
        				repaint();
                		
                	}
                });
        	}
        });
		btnMods.setBounds(0, 150, 160, 50);
		panelLateral.add(btnMods);
		
		JButton btnAccesorios = new JButton("Lubricantes y accesorios");
		btnAccesorios.setForeground(new Color(50, 85, 40));
		btnAccesorios.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/blanco(1000x700).jpg")));
		btnAccesorios.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAccesorios.setFont(new Font("Dialog", Font.BOLD, 10));
		btnAccesorios.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblCubos.setText("Lubricantes y accesorios");
        		List<Accesorio> listaAccesorios = obtenerListaAccesoriosDesdeArchivo("Accesorios.txt");
                cargarBotonesAccesorios(listaAccesorios);
                btnAgregarCubo.setBackground(new Color(113,124,231));
                btnAgregarCubo.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		RegistroProductoAccesorio p=new RegistroProductoAccesorio();
                		p.setSize(1000,540);
        				p.setLocation(0,120);
        				getContentPane().remove(panelTarjetasContenido);
        				getContentPane().remove(panelTarjetas);
                        getContentPane().remove(panelLateral);
                        getContentPane().remove(scrollPane);
                        panelTarjetasContenido.setVisible(false);
                        panelTarjetas.setVisible(false);
                        panelLateral.setVisible(false);
                        scrollPane.setVisible(false);
                        getContentPane().add(p, BorderLayout.CENTER);
        				revalidate();
        				repaint();
                		
                	}
                });

        	}
        });
		btnAccesorios.setBounds(0, 200, 160, 50);
		panelLateral.add(btnAccesorios);
		
		JLabel txProductos = new JLabel("Productos");
		txProductos.setForeground(new Color(255, 255, 255));
		txProductos.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		txProductos.setBounds(30, 31, 97, 25);
		panelLateral.add(txProductos);
		
		JLabel lblContacto = new JLabel("New label");
		lblContacto.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/contacto(155x120).jpg")));
		lblContacto.setBounds(0, 410, 155, 120);
		panelLateral.add(lblContacto);
		
	}
	
	//funciones Cubos
	private void cargarBotonesCubos(List<Cubos> listaCubos) {
		panelTarjetasContenido.removeAll();
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
                	p=new panelDescripcion(cubo);
            		p.setSize(1000,540);
    				p.setLocation(0,120);
    				p.setVisible(true);
    				getContentPane().remove(panelTarjetasContenido);
    				getContentPane().remove(panelTarjetas);
                    getContentPane().remove(panelLateral);
                    getContentPane().remove(scrollPane);
                    panelTarjetasContenido.setVisible(false);
                    panelTarjetas.setVisible(false);
                    panelLateral.setVisible(false);
                    scrollPane.setVisible(false);
    		        getContentPane().add(p, BorderLayout.CENTER);
    		        revalidate();
    		        repaint();
                }
            });

            panelTarjetasContenido.add(btnprod);
        }
        panelTarjetasContenido.revalidate();
        panelTarjetasContenido.repaint();
        
    }
	private List<Cubos> obtenerListaCubosDesdeBaseDeDatos() {
        List<Cubos> listaCubos = new ArrayList<>();
        String url = "jdbc:mysql://localhost/inventario?useSSL=false";
        String usuario = "root";
        String contraseña = "1234";

        try (Connection connection = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "SELECT * FROM cubos";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int idcubos = resultSet.getInt("idcubos");

                        String nombre = resultSet.getString("nombre");
                        double precio = resultSet.getDouble("precio");
                        String marca = resultSet.getString("marca");
                        String ruta_imagen = resultSet.getString("ruta_imagen");
                        String tipo = resultSet.getString("tipo");
                        double alto = resultSet.getDouble("alto");
                        double ancho = resultSet.getDouble("ancho");
                        double largo = resultSet.getDouble("largo");
                        int stock = resultSet.getInt("stock");


                        Cubos cubo = new Cubos(idcubos, nombre, precio, marca, ruta_imagen, tipo, alto, ancho, largo,stock);
                        listaCubos.add(cubo);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaCubos;
    }

    // Otros métodos de la clase JFProducts
	
	//funciones Mods
	
	private void cargarBotonesMods(List<Cubos> listaMods) {
		panelTarjetasContenido.removeAll();
        for (Cubos cubo : listaMods) {
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
                	pm=new panelDescripcionMod(cubo);
            		pm.setSize(1000,540);
    				pm.setLocation(0,120);
    				pm.setVisible(true);
    				getContentPane().remove(panelTarjetasContenido);
    				getContentPane().remove(panelTarjetas);
                    getContentPane().remove(panelLateral);
                    getContentPane().remove(scrollPane);
                    panelTarjetasContenido.setVisible(false);
                    panelTarjetas.setVisible(false);
                    panelLateral.setVisible(false);
                    scrollPane.setVisible(false);
    		        getContentPane().add(pm, BorderLayout.CENTER);
    		        revalidate();
    		        repaint();
                }
            });

            panelTarjetasContenido.add(btnprod);
        }
        panelTarjetasContenido.revalidate();
        panelTarjetasContenido.repaint();
        
    }
	//funciones lubricantes y accesorios
	private List<Accesorio> obtenerListaAccesoriosDesdeArchivo(String nombreArchivo) {
	    List<Accesorio> listaAccesorios = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
	        String linea;
	        while ((linea = reader.readLine()) != null) {
	            try {
	                int codigo = Integer.parseInt(extraerValor(linea, "Codigo:"));
	                String nombre = extraerValor(reader.readLine(), "Nombre:");
	                double precio = Double.parseDouble(extraerValor(reader.readLine(), "Precio:"));
	                String marca = extraerValor(reader.readLine(), "Marca:");
	                String ruta = extraerValor(reader.readLine(), "Ruta:");

	                double tamanio = Double.parseDouble(extraerValor(reader.readLine(), "Tamaño:"));

	                Accesorio acce = new Accesorio(codigo, nombre, precio, marca, ruta, tamanio);
	                listaAccesorios.add(acce);

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
	    cargarBotonesAccesorios(listaAccesorios);

	    return listaAccesorios;
	}
	private void cargarBotonesAccesorios(List<Accesorio> listaAccesorios) {
		panelTarjetasContenido.removeAll();
        for (Accesorio acce : listaAccesorios) {
            JButton btnprod = new JButton();
            btnprod.setLayout(new BorderLayout());
            
            PanelAccesorio panelDetalles= new PanelAccesorio(acce);
            btnprod.add(panelDetalles, BorderLayout.CENTER);
            btnprod.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // Establecer un tamaño preferido para el botón
            btnprod.setPreferredSize(new Dimension(300, 500));
            btnprod.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	pa=new panelDescripcionAccesorio(acce);
            		pa.setSize(1000,540);
    				pa.setLocation(0,120);
    				pa.setVisible(true);
    				getContentPane().remove(panelTarjetasContenido);
    				getContentPane().remove(panelTarjetas);
                    getContentPane().remove(panelLateral);
                    getContentPane().remove(scrollPane);
                    panelTarjetasContenido.setVisible(false);
                    panelTarjetas.setVisible(false);
                    panelLateral.setVisible(false);
                    scrollPane.setVisible(false);
    		        getContentPane().add(pa, BorderLayout.CENTER);
    		        revalidate();
    		        repaint();
                }
            });

            panelTarjetasContenido.add(btnprod);
        }
        panelTarjetasContenido.revalidate();
        panelTarjetasContenido.repaint();
        
    }
    private String extraerValor(String linea, String etiqueta) {
        return linea.substring(etiqueta.length()).trim();
    }
}
