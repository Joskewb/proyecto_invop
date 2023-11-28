package tienda;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

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
		//josiassirpa
		//contraseña
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Fondo = new JLabel("New label");
		Fondo.setIcon(new ImageIcon(login.class.getResource("/imagenes/Logo1.jpg")));
		Fondo.setBounds(199, 34, 501, 321);
		contentPane.add(Fondo);
		
		textField = new JTextField();
		textField.setBounds(335, 386, 310, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(335, 433, 310, 30);
		contentPane.add(passwordField);
		
		JLabel txContrasenia = new JLabel("Contraseña:");
		txContrasenia.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		txContrasenia.setForeground(Color.WHITE);
		txContrasenia.setBounds(193, 436, 122, 31);
		contentPane.add(txContrasenia);
		
		JLabel txUsuario = new JLabel("Usuario:");
		txUsuario.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		txUsuario.setForeground(Color.WHITE);
		txUsuario.setBounds(230, 397, 92, 23);
		contentPane.add(txUsuario);
	}
}
