package tienda;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.ActionEvent;

public class login extends JFrame {
	private String archivo="Usuarios.txt";
	private JPanel contentPane;
	private JTextField txUsername;
	private JPasswordField passwordField;
	private JButton btnIngresar;
	private JButton btnRegistrarse;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public login() {
		
		getContentPane().setBackground(Color.BLACK);
    	getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);
        setLocationRelativeTo(null);
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Fondo = new JLabel("New label");
		Fondo.setIcon(new ImageIcon(login.class.getResource("/imagenes/Logo1.jpg")));
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
				String username = txUsername.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("josias") && password.equals("contra")) {
                	dispose();
                    inicio n = new inicio();
                    n.setVisible(true);
                }else {
                	if (autenticarUsuario(username,password)) {
                		dispose();
                		UInicio n=new UInicio();
                		n.setVisible(true);
                	}else {
                		JOptionPane.showMessageDialog(login.this, "Usuario o contraseña incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
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
	private boolean autenticarUsuario(String username, String password) {
		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String storedUsername = extractValue(line, "Usuario:");
                String storedPassword = extractValue(reader.readLine(), "Contraseña:");

                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    return true;
                }

                // Leer la línea en blanco
                reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para extraer el valor de una línea que contiene una etiqueta
    private String extractValue(String line, String label) {
        return line.substring(label.length()).trim();
    }
}
