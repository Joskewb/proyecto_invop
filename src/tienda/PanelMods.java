package tienda;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMods extends JPanel {

	public PanelMods() {
		setLayout(null);
		JLabel lbImagen = new JLabel("imagen1");
		lbImagen.setIcon(new ImageIcon(PanelInicio.class.getResource("/imagenes/Logo1.jpg")));
        lbImagen.setBackground(Color.ORANGE);
        lbImagen.setBounds(0, 0, 815, 300);
        add(lbImagen);
	}
}
