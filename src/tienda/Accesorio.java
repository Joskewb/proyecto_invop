package tienda;

import tienda.entidades.Catalogo;

public class Accesorio extends Catalogo{
	double tamanio;
	public Accesorio(int codigo, String nombre, double precio,String marca, String ruta,double tamanio) {
		super(codigo,nombre,precio,marca,ruta);
		this.tamanio=tamanio;
	}
	public double getTamanio() {
		return tamanio;
	}
}
