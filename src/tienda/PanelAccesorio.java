package tienda;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelAccesorio extends JPanel {
	private List<Accesorio> listaAccesorios;
	public PanelAccesorio(Accesorio acce) {
		setBackground(new Color(105, 105, 105));
		this.listaAccesorios = listaAccesorios;
        setLayout(null);
        
        Font f=new Font("Bahnschrift", Font.PLAIN, 18);
    
        JLabel lblFoto = new JLabel();
        lblFoto.setIcon(new ImageIcon(acce.getRuta()));
        lblFoto.setBounds(73, 72, 250, 300);
        add(lblFoto);
        
        JLabel lblCodigo = new JLabel("C: "+acce.getCodigo());
        lblCodigo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblCodigo.setBounds(49, 37, 127, 24);
        add(lblCodigo);
        
        JLabel lblTamanio = new JLabel("T: "+acce.getTamanio()+" cm");
        lblTamanio.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblTamanio.setBounds(268, 37, 127, 24);
        add(lblTamanio);
        
        JLabel lblNombre = new JLabel(acce.getNombre());
        lblNombre.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        lblNombre.setBounds(35, 383, 312, 30);
        add(lblNombre);
        
        JLabel lblPrecio = new JLabel("Bs. "+acce.getPrecio());
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrecio.setForeground(new Color(128, 0, 0));
        lblPrecio.setFont(new Font("Bahnschrift", Font.BOLD, 25));
        
        lblPrecio.setBounds(232, 412, 115, 42);
        add(lblPrecio);
        
        JLabel lbfondo = new JLabel("New label");
        lbfondo.setIcon(new ImageIcon(PanelAccesorio.class.getResource("/imagenes/blanco(1000x700).jpg")));
        lbfondo.setBounds(20, 15, 355, 450);
        add(lbfondo);
	}
}
