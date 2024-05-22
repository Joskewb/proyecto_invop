package tienda.entidades;

public class Catalogo {
	int codigo;
	String nombre;
	double precio;
	String marca; 
	String ruta;
	public Catalogo(int codigo, String nombre, double precio, String marca, String ruta) {
		this.codigo=codigo;
		this.nombre=nombre;
		this.precio=precio;
		this.marca=marca;
		this.ruta=ruta;
	}
	public int getCodigo() {
		return codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public String getRuta() {
		return ruta;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca=marca;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public void setPrecio(int precio) {
		this.precio=precio;
	}
	public void setRuta(String ruta){
		this.ruta=ruta;
	}
	public void setCodigo(int codigo) {
		this.codigo=codigo;
	}
}
