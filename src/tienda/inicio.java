package tienda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inicio extends JFrame {

    JScrollPane scrollPane;
    private boolean scrolled = false;
    PanelInicio panelinicio;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    inicio frame = new inicio();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public inicio() {
    	getContentPane().setBackground(Color.BLACK);
    	setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);
        
        panelinicio= new PanelInicio();
        scrollPane= new JScrollPane();
        scrollPane.setBounds(0,130,890,500);
        getContentPane().add(scrollPane);
        
     
        scrollPane.setViewportView(panelinicio);
        
        
        JButton btnLogo = new JButton("usuario");
        btnLogo.setBounds(775, 20, 80, 80);
        getContentPane().add(btnLogo);

        JButton btnUsuario = new JButton("logo");
        btnUsuario.setBounds(25, 25, 100, 75);
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


        

    }
}
