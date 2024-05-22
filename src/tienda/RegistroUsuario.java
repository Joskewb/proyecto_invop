package tienda;
import javax.swing.*;

import tienda.entidades.Usuario;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistroUsuario extends JFrame {
    private JTextField txUsuario;
    private JTextField txCorreo;
    private JTextField txDireccion;
    private JPasswordField txPassword;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegistroUsuario frame = new RegistroUsuario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RegistroUsuario() {
        getContentPane().setBackground(new Color(30, 34, 35));
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(240, 50, 531, 400); // Modificamos la ubicación y el tamaño del panel
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblUsuario = new JLabel("Ingrese usuario:");
        lblUsuario.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblUsuario.setBounds(32, 30, 129, 18);
        panel.add(lblUsuario);

        txUsuario = new JTextField();
        txUsuario.setText("");
        txUsuario.setBounds(32, 55, 462, 34); // Ajustamos la ubicación y el tamaño del campo de texto
        panel.add(txUsuario);
        txUsuario.setColumns(10);

        JLabel lblPassword = new JLabel("Ingrese contraseña:");
        lblPassword.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblPassword.setBounds(32, 105, 150, 18);
        panel.add(lblPassword);

        txPassword = new JPasswordField();
        txPassword.setBounds(32, 130, 462, 34); // Ajustamos la ubicación y el tamaño del campo de contraseña
        panel.add(txPassword);

        JLabel lblCorreo = new JLabel("Ingrese correo electrónico:");
        lblCorreo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblCorreo.setBounds(32, 180, 200, 18);
        panel.add(lblCorreo);

        txCorreo = new JTextField();
        txCorreo.setText("");
        txCorreo.setBounds(32, 205, 462, 34); // Ajustamos la ubicación y el tamaño del campo de texto de correo
        panel.add(txCorreo);
        txCorreo.setColumns(10);

        JLabel lblDireccion = new JLabel("Ingrese dirección:");
        lblDireccion.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblDireccion.setBounds(32, 255, 150, 18);
        panel.add(lblDireccion);

        txDireccion = new JTextField();
        txDireccion.setText("");
        txDireccion.setBounds(32, 280, 462, 34); // Ajustamos la ubicación y el tamaño del campo de texto de dirección
        panel.add(txDireccion);
        txDireccion.setColumns(10);

        JButton btnRegistrar = new JButton("Regístrate");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de registrarse con estos datos?", "Confirmación de registro", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    registrarUsuario();
                }
            }
        });
        btnRegistrar.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        btnRegistrar.setForeground(SystemColor.window);
        btnRegistrar.setBackground(new Color(100, 100, 100));
        btnRegistrar.setBounds(132, 340, 300, 50); // Ajustamos la ubicación y el tamaño del botón
        panel.add(btnRegistrar);

        JLabel lblRegistrate = new JLabel("¡REGÍSTRATE!");
        lblRegistrate.setForeground(new Color(255, 255, 255));
        lblRegistrate.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
        lblRegistrate.setBounds(395, 84, 280, 49);
        getContentPane().add(lblRegistrate);

        JButton btnBack = new JButton(">");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                logins n = new logins();
                n.setVisible(true);
            }
        });
        btnBack.setBackground(SystemColor.window);
        btnBack.setBounds(866, 68, 50, 50);
        getContentPane().add(btnBack);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500); // Reducimos la altura de la ventana
        setLocationRelativeTo(null);
    }

    private void registrarUsuario() {
        String usuario = txUsuario.getText();
        String password = new String(txPassword.getPassword());
        String correo = txCorreo.getText();
        String direccion = txDireccion.getText();

        // Validar que los campos no estén vacíos
        if (usuario.isEmpty() || password.isEmpty() || correo.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un objeto Usuario
        Usuario nuevoUsuario = new Usuario(0, usuario, correo, direccion, password);

        // Insertar el usuario en la base de datos
        String JDBC_URL = "jdbc:mysql://localhost/inventario?useSSL=false";
        String DB_USER = "root";
        String DB_PASSWORD = "1234";
        String INSERT_QUERY = "INSERT INTO usuario (nombre, correoElectronico, direccion, password) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
            stmt.setString(1, nuevoUsuario.getNombre());
            stmt.setString(2, nuevoUsuario.getCorreoElectronico());
            stmt.setString(3, nuevoUsuario.getDireccion());
            stmt.setString(4, nuevoUsuario.getPassword()); // Incluimos el valor del campo de contraseña
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                logins n = new logins();
                n.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

