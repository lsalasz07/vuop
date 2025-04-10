package Interfaz;

import Pedido.CarritoCompras;
import Servidor.Orden;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.List;
import javax.swing.*;

public class FrameFour extends JFrame {

    private CarritoCompras carrito;
    private JPanel detailsPanel;
    private JPanel totalPanel;
    private double total = 0.0;

    public FrameFour(CarritoCompras carrito) {
        this.carrito = carrito;

        setSize(new Dimension(1400, 800));
        setMinimumSize(new Dimension(1600, 800));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(0, 70));
        northPanel.setBackground(new Color(255, 255, 255));
        northPanel.setLayout(new GridLayout(1, 3));

        JButton backB, octoberEatsB;

        backB = new JButton("Regresar al negocio");
        backB.setFont(new Font("Arial", Font.ROMAN_BASELINE, 15));

        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                FrameThree thirdFrame = new FrameThree();
            }
        });

        octoberEatsB = new JButton("<html>October <b>Eats</b></html>");
        octoberEatsB.setFont(new Font("Arial", Font.ITALIC, 25));

        octoberEatsB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent openMainWindow) {
                dispose();
                InterfazInicioDeSesion interfazInicioDeSesion = new InterfazInicioDeSesion();
            }
        });

        JButton[] buttonsNorth = {backB, octoberEatsB};

        for (JButton button : buttonsNorth) {
            button.setContentAreaFilled(false); //Hace Transparente el fondo de los botones
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setForeground(Color.BLACK);

            northPanel.add(button);
        }

        JPanel space = new JPanel();
        space.setBackground(Color.white);
        northPanel.add(space);

        JPanel centralPanel = new JPanel();
        centralPanel.setSize(new Dimension(1000, 1000));

        JPanel CCPanel = new JPanel();
        CCPanel.setPreferredSize(new Dimension(1400, 750));
        CCPanel.setLayout(new BorderLayout());

        centralPanel.add(CCPanel);

        JPanel westPanel, eastPanel;

        westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(970, 0));

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setPreferredSize(new Dimension(960, 400));
        detailsPanel.setBackground(new Color(211, 211, 211));

        // Título para el panel de detalles
        JLabel cartTitleLabel = new JLabel("Tu Carrito de Compras");
        cartTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        cartTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cartTitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        detailsPanel.add(cartTitleLabel);

        // Llenar el carrito con los elementos
        actualizarCarrito();

        westPanel.add(detailsPanel);

        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        paymentPanel.setPreferredSize(new Dimension(960, 200));
        paymentPanel.setBackground(new Color(211, 211, 211));

        JButton payButton = new JButton("Pagar");
        payButton.setFont(new Font("Arial", Font.BOLD, 18));
        payButton.setPreferredSize(new Dimension(200, 50));
        payButton.setBackground(new Color(0, 150, 0));
        payButton.setForeground(Color.WHITE);
        payButton.setFocusPainted(false);

        // Acción para el botón de pagar
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (carrito.verCarrito().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "El carrito está vacío. Agrega productos antes de pagar.",
                            "Carrito Vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                enviarPedidoAlServidor();
            }
        });

        paymentPanel.add(payButton);
        westPanel.add(paymentPanel);

        eastPanel = new JPanel(new FlowLayout());
        eastPanel.setPreferredSize(new Dimension(415, 0));

        JPanel subscription, cart, codes, total;

        subscription = new JPanel();
        subscription.setPreferredSize(new Dimension(400, 250));

        cart = new JPanel();
        cart.setPreferredSize(new Dimension(400, 70));

        codes = new JPanel();
        codes.setPreferredSize(new Dimension(400, 120));

        totalPanel = new JPanel();
        totalPanel.setPreferredSize(new Dimension(400, 180));
        totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
        totalPanel.setBackground(Color.white);

        JLabel totalTitleLabel = new JLabel("Resumen del Pedido");
        totalTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalTitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // Actualizar el total
        actualizarTotal();

        totalPanel.add(totalTitleLabel);

        JPanel[] panelsEast = {subscription, cart, codes, totalPanel};

        for (JPanel panel : panelsEast) {
            panel.setBackground(Color.white);
            eastPanel.add(panel);
        }

        CCPanel.add(westPanel, BorderLayout.WEST);
        CCPanel.add(eastPanel, BorderLayout.EAST);

        add(northPanel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Para compatibilidad con el constructor original si se llama sin argumentos
    public FrameFour() {
        this(FrameThree.getCarrito());
    }

    // Actualizar el contenido del carrito en la interfaz
    private void actualizarCarrito() {
        Component titleComponent = null;
        if (detailsPanel.getComponentCount() > 0) {
            titleComponent = detailsPanel.getComponent(0);
        }

        detailsPanel.removeAll();

        if (titleComponent != null) {
            detailsPanel.add(titleComponent);
        } else {
            JLabel cartTitleLabel = new JLabel("Tu Carrito de Compras");
            cartTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            cartTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cartTitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
            detailsPanel.add(cartTitleLabel);
        }

        List<Orden> ordenesEnCarrito = carrito.verCarrito();

        if (ordenesEnCarrito.isEmpty()) {
            JLabel emptyCartLabel = new JLabel("No hay productos en el carrito");
            emptyCartLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            emptyCartLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            detailsPanel.add(emptyCartLabel);
        } else {
            // Agregar cada producto al panel
            for (Orden orden : ordenesEnCarrito) {
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
                itemPanel.setMaximumSize(new Dimension(900, 60));
                itemPanel.setBackground(new Color(230, 230, 230));
                itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

                String infoText = orden.toString();
                JLabel itemLabel = new JLabel(infoText);
                itemLabel.setFont(new Font("Arial", Font.PLAIN, 16));

                JButton removeButton = new JButton("Eliminar");
                removeButton.setFocusPainted(false);
                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        carrito.eliminarObjeto(orden);
                        actualizarCarrito();
                        actualizarTotal();
                    }
                });

                itemPanel.add(itemLabel);
                itemPanel.add(Box.createHorizontalGlue());
                itemPanel.add(removeButton);

                detailsPanel.add(itemPanel);
                detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    // Actualizar el total del pedido
    private void actualizarTotal() {
        Component titleComponent = null;
        if (totalPanel.getComponentCount() > 0) {
            titleComponent = totalPanel.getComponent(0);
        }

        totalPanel.removeAll();

        if (titleComponent != null) {
            totalPanel.add(titleComponent);
        } else {
            // Create a new title if it didn't exist
            JLabel totalTitleLabel = new JLabel("Resumen del Pedido");
            totalTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            totalTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            totalTitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
            totalPanel.add(totalTitleLabel);
        }

        // Agregar detalles del total
        JLabel subtotalLabel = new JLabel("Subtotal: $" + String.format("%.2f", total));
        JLabel ivaLabel = new JLabel("IVA (16%): $" + String.format("%.2f", total * 0.16));
        JLabel totalLabel = new JLabel("Total: $" + String.format("%.2f", total * 1.16));

        subtotalLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        ivaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));

        subtotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ivaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        subtotalLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        ivaLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));

        totalPanel.add(subtotalLabel);
        totalPanel.add(ivaLabel);
        totalPanel.add(totalLabel);

        totalPanel.revalidate();
        totalPanel.repaint();
    }

    // Extraer la cantidad de un producto a partir de su descripción
    private int extraerCantidad(String descripcion) {
        try {
            // Intentar extraer el número antes de la palabra "producto"
            String[] partes = descripcion.split(":");
            if (partes.length > 1) {
                String cantidadStr = partes[1].trim().split(" ")[0];
                return Integer.parseInt(cantidadStr);
            }
        } catch (Exception e) {
            // Si hay error, devolvemos 1 como cantidad predeterminada
        }
        return 1;
    }

    // Enviar el pedido al servidor
    private void enviarPedidoAlServidor() {
        try {
            List<Orden> ordenesEnCarrito = carrito.verCarrito();
            if (ordenesEnCarrito.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El carrito está vacío");
                return;
            }

            // Conectar con el servidor (asegúrate de que está corriendo)
            Socket socket = new Socket("localhost", 5002);

            // Enviar cada orden al servidor
            for (Orden orden : ordenesEnCarrito) {
                ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
                salida.writeObject(orden);
                salida.flush();
            }

            // Cerrar la conexión
            socket.close();

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null,
                    "Pedido enviado correctamente. Total: $" + String.format("%.2f", total * 1.16));

            // Vaciar el carrito
            carrito.vaciarCarrito();
            actualizarCarrito();
            actualizarTotal();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al enviar pedido: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
