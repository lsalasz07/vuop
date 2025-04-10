package Pedido;

import Servidor.Orden;
import java.util.ArrayList;
import java.util.List;

public class CarritoCompras {
    private List<Orden> orden;

    public CarritoCompras() {
        this.orden = new ArrayList<>();
    }

    public void agregarObjeto(Orden orden1) {
        orden.add(orden1);
    }

    public void eliminarObjeto(Orden orden1) {
        orden.remove(orden1);
    }

    public List<Orden> verCarrito() {
        return orden;
    }

    public void vaciarCarrito() {
        orden.clear();
    }
}
