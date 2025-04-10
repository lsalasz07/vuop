package Servidor;

import java.io.*;
import java.net.*;

public class Servidor {

        public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5002)) {
            System.out.println("Servidor en espera de conexiones");
            
            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado");

                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                Orden orden = (Orden) entrada.readObject();

                System.out.println("Orden recibida: " + orden);

                socket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
