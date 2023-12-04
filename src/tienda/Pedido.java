package tienda;

public class Pedido {
    private int codigoProducto;
    private String nombre;
    private int cantidad;
    private double costo, precio;
   public Pedido(int codigoProducto, String nombre, double precio, int cantidad, double costo) {
            this.codigoProducto = codigoProducto;
            this.nombre = nombre;
            this.precio=precio;
            this.cantidad = cantidad;
            this.costo = costo;
   }

        public int getCodigoProducto() {
            return codigoProducto;
        }

        public String getNombre() {
            return nombre;
        }

        public int getCantidad() {
            return cantidad;
        }
        public double getPrecio() {
            return precio;
        }
        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getCosto() {
            return costo;
        }

        public void setCosto(double costo) {
            this.costo = costo;
        }

        // Otras operaciones y métodos de la clase ProductoPedido
}



