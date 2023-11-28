package tienda;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class PanelInicio extends JPanel {

	public PanelInicio() {
		setLayout(null);
		setBounds(0,200,900,600);
		JLabel lbImagen = new JLabel("imagen1");
		lbImagen.setIcon(new ImageIcon(PanelInicio.class.getResource("/imagenes/Logo1.jpg")));
        lbImagen.setBackground(Color.ORANGE);
        lbImagen.setBounds(0, 0, 815, 300);
        add(lbImagen);
	}

}
