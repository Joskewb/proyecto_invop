package tienda;

import tienda.entidades.Cubos;

public class CuboVelocidad extends Cubos{
	String sistema;//magnetico-maglev-ballcore
	String acabado;//(mate, frosted, uv)
	String accesorios;
	public CuboVelocidad(int codigo, String nombre, double precio,String marca, String ruta, String tipo, double alto, double ancho, double largo, String sistema, String acabado, String accesorios,int stock) {
		super(codigo,nombre,precio,marca,ruta, tipo, alto, ancho, largo,stock);
		this.sistema=sistema;
		this.acabado=acabado;
		this.accesorios=accesorios;
	}
	 
	
}
