package tienda.entidades;

import java.awt.image.BufferedImage;

import tienda.entidades.*;

public class Cubos extends Catalogo {
    
    String tipo;
    double alto;
    double ancho;
    double largo;
    int stock; // Nuevo campo

    public Cubos(int codigo, String nombre, double precio, String marca, String ruta, String tipo, double alto, double ancho, double largo, int stock) {
        super(codigo, nombre, precio, marca, ruta);
        this.tipo = tipo;
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
        this.stock = stock; // Asignar el valor de stock al nuevo campo
    }

    // Getter y setter para el campo stock
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Resto de los métodos...
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
        this.tipo = tipo;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }
    
    public String toString() {
        return "Nombre: " + nombre + "\n" +
               "Precio: " + precio + "\n" +
               "Tipo: " + tipo + "\n" +
               "Alto: " + alto + "\n" +
               "Ancho: " + ancho + "\n" +
               "Largo: " + largo + "\n" +
               "Stock: " + stock + "\n"; // Asegúrate de incluir el nombre del producto
    }
    
    public Cubos() {
        super(0, "", 0.0, "", ""); // Llama al constructor de la superclase con valores predeterminados
        this.tipo = "";
        this.alto = 0.0;
        this.ancho = 0.0;
        this.largo = 0.0;
        this.stock = 0;
    }

}
