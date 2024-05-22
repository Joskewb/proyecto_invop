package tienda.UsuarioProductos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tienda.logins;
import tienda.AdminProductos.JFProducts;
import tienda.AdminProductos.inicio;

    
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UInicio extends JFrame {
    private int idUsuario;
    private String nombreUsuario;

    JPanel panelInferior;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UInicio frame = new UInicio(0, "Usuario");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UInicio(int idUsuario, String nombreUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;

        getContentPane().setBackground(Color.BLACK);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        panelInferior = new JPanel();
        panelInferior.setBounds(0, 130, 985, 540);
        panelInferior.setBackground(new Color(128, 128, 128));
        panelInferior.setLayout(null);
        getContentPane().add(panelInferior);

        JLabel lblFoto = new JLabel("New label");
        lblFoto.setIcon(new ImageIcon(UInicio.class.getResource("/imagenes/inicio(960x300).jpg")));
        lblFoto.setBounds(10, 144, 960, 300);
        panelInferior.add(lblFoto);

        JLabel lblBienvenido = new JLabel("¡Bienvenido a Sir Cube Store, " + nombreUsuario + "!");
        lblBienvenido.setForeground(new Color(255, 255, 255));
        lblBienvenido.setFont(new Font("Bahnschrift", Font.BOLD, 50));
        lblBienvenido.setBounds(10, 64, 916, 69);
        panelInferior.add(lblBienvenido);

        JLabel lblID = new JLabel("Tu ID de usuario es: " + idUsuario);
        lblID.setForeground(new Color(255, 255, 255));
        lblID.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        lblID.setBounds(10, 454, 500, 30);
        panelInferior.add(lblID);

        // Botón de Logout
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana actual
                logins login = new logins(); // Crear una nueva instancia de la ventana de login
                login.setVisible(true); // Mostrar la ventana de login
            }
        });
        btnLogout.setBounds(880, 50, 100, 30);
        getContentPane().add(btnLogout);

        // Panel superior
        JButton btnLogo = new JButton("");
        btnLogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                UNosotros n = new UNosotros();
                n.setVisible(true);
            }
        });
        btnLogo.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/user(80x80).jpg")));
        btnLogo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnLogo.setBounds(882, 22, 80, 80);
        getContentPane().add(btnLogo);

        JButton btnUsuario = new JButton("");
        btnUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                UNosotros n = new UNosotros();
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
                UProductos n = new UProductos();
                n.setVisible(true);
            }
        });
        btnProductos.setBounds(250, 50, 100, 30);
        getContentPane().add(btnProductos);
        
        JButton btnProductosCubos = new JButton("Productos Cubos");
        btnProductosCubos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana actual
                ProductosUI productosUI = new ProductosUI(idUsuario); // Crear instancia de ProductosUI
                productosUI.setVisible(true); // Mostrar la ventana de productos
            }
        });
        btnProductosCubos.setBounds(475, 50, 150, 30);
        getContentPane().add(btnProductosCubos);

        JButton btnInicio = new JButton("Inicio");
        btnInicio.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/amarillo(1000x700).jpg")));
        btnInicio.setHorizontalTextPosition(SwingConstants.CENTER);
        btnInicio.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
        btnInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                UInicio n = new UInicio(idUsuario, nombreUsuario);
                n.setVisible(true);
            }
        });
        btnInicio.setBounds(175, 50, 75, 30);
        getContentPane().add(btnInicio);

        JButton btnNosotros = new JButton("Nosotros");
        btnNosotros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                UNosotros n = new UNosotros();
                n.setVisible(true);
            }
        });
        btnNosotros.setIcon(new ImageIcon(JFProducts.class.getResource("/imagenes/Azul(1000x700).jpg")));
        btnNosotros.setHorizontalTextPosition(SwingConstants.CENTER);
        btnNosotros.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
        btnNosotros.setBounds(350, 50, 100, 30);
        getContentPane().add(btnNosotros);
    }
}
