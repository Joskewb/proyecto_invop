package tienda;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import tienda.AdminProductos.inicio;
import tienda.Conexion_db.Conexion;
import tienda.UsuarioProductos.UInicio;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class logins extends JFrame {
    private JPanel contentPane;
    private JTextField txUsername;
    private JPasswordField passwordField;
    private JButton btnIngresar;
    private JButton btnRegistrarse;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    logins frame = new logins();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public logins() {
        getContentPane().setBackground(Color.BLACK);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel Fondo = new JLabel("New label");
        Fondo.setIcon(new ImageIcon(logins.class.getResource("/imagenes/Logo1.jpg")));
        Fondo.setBounds(242, 42, 501, 321);
        contentPane.add(Fondo);

        txUsername = new JTextField();
        txUsername.setBounds(422, 404, 310, 30);
        contentPane.add(txUsername);
        txUsername.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(422, 460, 310, 30);
        contentPane.add(passwordField);

        JLabel txContrasenia = new JLabel("Contraseña:");
        txContrasenia.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        txContrasenia.setForeground(Color.WHITE);
        txContrasenia.setBounds(268, 460, 122, 31);
        contentPane.add(txContrasenia);

        JLabel txUsuario = new JLabel("Usuario:");
        txUsuario.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        txUsuario.setForeground(Color.WHITE);
        txUsuario.setBounds(268, 408, 92, 23);
        contentPane.add(txUsuario);

        btnIngresar = new JButton("Iniciar sesión");
        btnIngresar.setBackground(Color.WHITE);
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = txUsername.getText();
                String password = new String(passwordField.getPassword());

                if (usuario.equals("admin") && password.equals("admin")) {
                    dispose();
                    inicio n = new inicio(usuario);
                    n.setVisible(true);
                } else {
                    int idUsuario = autenticarUsuario(usuario, password);
                    if (idUsuario != -1) {
                        String nombreUsuario = obtenerNombreUsuario(idUsuario);
                        dispose();
                        UInicio n = new UInicio(idUsuario, nombreUsuario);
                        n.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(logins.this, "Usuario o contraseña incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnIngresar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnIngresar.setBounds(288, 540, 180, 50);
        contentPane.add(btnIngresar);

        btnRegistrarse = new JButton("¡Regístrate!");
        btnRegistrarse.setBackground(new Color(255, 219, 89));
        btnRegistrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegistroUsuario n = new RegistroUsuario();
                n.setVisible(true);
            }
        });
        btnRegistrarse.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnRegistrarse.setBounds(517, 540, 180, 50);
        contentPane.add(btnRegistrarse);
    }

    private int autenticarUsuario(String nombre, String password) {
        String JDBC_URL = "jdbc:mysql://localhost/inventario?useSSL=false";
        String DB_USER = "root";
        String DB_PASSWORD = "1234";
        int idUsuario = -1;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT idusuario FROM usuario WHERE nombre = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        idUsuario = rs.getInt("idusuario"); // Obtenemos el ID del usuario
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idUsuario;
    }
    
    private String obtenerNombreUsuario(int idUsuario) {
        String nombreUsuario = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.conectar();
            String query = "SELECT nombre FROM usuario WHERE idusuario = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                nombreUsuario = rs.getString("nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return nombreUsuario;
    }}