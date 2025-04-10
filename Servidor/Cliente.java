package Servidor;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5002);
             ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {
            
            //datos que se le piden al usuario
            System.out.print("Ingrese su nombre: ");
            String cliente = scanner.nextLine();  
            System.out.print("Ingrese el producto: ");
            String producto = scanner.nextLine();
            System.out.print("Ingrese la cantidad: ");
            int cantidad = scanner.nextInt();

            //crea objeto orden con los datos ingresados
            Orden orden = new Orden(cliente, producto, cantidad);

            //se envia la orden al server
            salida.writeObject(orden);
            System.out.println("Orden enviada al servidor!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
