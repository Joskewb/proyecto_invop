package tienda;

public class Llavero extends Catalogo{
	double tamanio;
	public Llavero(int codigo, String nombre, double precio,String marca, String ruta,double tamanio) {
		super(codigo,nombre,precio,marca,ruta);
		this.tamanio=tamanio;
	}
}
