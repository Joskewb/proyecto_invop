package tienda;

import java.awt.image.BufferedImage;

public class Cubos extends Catalogo {
	
	String tipo; 
	double alto;
	double ancho;
	double largo;
	public Cubos(int codigo, String nombre, double precio,String marca, String ruta, String tipo, double alto, double ancho, double largo) {
		super(codigo,nombre,precio,marca,ruta);
		this.tipo=tipo;
		this.alto=alto;
		this.ancho=ancho;
		this.largo=largo;
	}
	public String getTipo() {
		return tipo;
	}
	public double getAlto() {
		return alto;
	}
	public double getLargo() {
		return largo;
	}
	public double getAncho() {
		return ancho;
	}
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	public void setAlto(double alto) {
		this.alto=alto;
	}
	public void setLargo(double largo) {
		this.largo=largo;
	}
}
