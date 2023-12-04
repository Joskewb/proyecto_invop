package tienda;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PedidoManager {
    private static final String ARCHIVO_PEDIDO = "Pedido.txt";

    public static void guardarPedidoEnArchivo(List<Pedido.ProductoPedido> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PEDIDO, true))) {
            for (Pedido.ProductoPedido producto : productos) {
                writer.write("Código: " + producto.getCodigoProducto() + "\n");
                writer.write("Nombre: " + producto.getNombre() + "\n");
                writer.write("Cantidad: " + producto.getCantidad() + "\n");
                writer.write("Costo: " + producto.getCosto() + "\n");
                writer.write("------------------------------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
