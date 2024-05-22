package tienda.entidades;

import java.util.ArrayList;
import java.util.List;

public class CarritoDeCompras {
    private Usuario usuario;
    private List<Cubos> cubos;

    public CarritoDeCompras(Usuario usuario) {
        this.usuario = usuario;
        this.cubos = new ArrayList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Cubos> getCubos() {
        return cubos;
    }

    public void agregarCubo(Cubos cubo) {
        cubos.add(cubo);
    }

    public void eliminarCubo(Cubos cubo) {
        cubos.remove(cubo);
    }

    public void vaciarCarrito() {
        cubos.clear();
    }

    public double calcularTotal() {
        double total = 0;
        for (Cubos cubo : cubos) {
            total += cubo.getPrecio();
        }
        return total;
    }
}