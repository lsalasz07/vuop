/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.io.Serializable;

/**
 * Clase orden de compra que se envia por cliente Serializable para permitir su
 * envio en formato binario
 *
 * @author CamilaAlfaro
 */
public class Orden implements Serializable {
    //atributos
    private String cliente;
    private String producto;
    private int cantidad;

    /**
     * Contruct para crear una nueva orden
     *
     * @param cliente nombre del cliente que realiza la orden
     * @param producto nombre del producto a pdir
     * @param cantidad cantidad del producto a pedir
     */
    public Orden(String cliente, String producto, int cantidad) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Orden de " + cliente + ": " + cantidad + producto;
    }
}
