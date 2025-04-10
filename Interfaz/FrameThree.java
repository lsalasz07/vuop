package Interfaz;

import Pedido.CarritoCompras;
import Servidor.Orden;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameThree extends JFrame {
    // Agregar el carrito como variable de clase para acceder desde cualquier método
    private static CarritoCompras carrito = new CarritoCompras();

    public FrameThree() {

        setSize(new Dimension(1400, 800));
        setMinimumSize(new Dimension(1600, 800));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(0, 70));
        northPanel.setBackground(new Color(255, 255, 255));
        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));

        JButton threeLinesButton = new JButton("≡");
        threeLinesButton.setPreferredSize(new Dimension(60, 40));
        threeLinesButton.setFont(new Font("Arial", Font.BOLD, 30));

        northPanel.add(threeLinesButton, FlowLayout.LEFT);

        JButton octoberEatsButton = new JButton("<html>October <b>Eats</b></html>");
        octoberEatsButton.setPreferredSize(new Dimension(200, 40));
        octoberEatsButton.setFont(new Font("Arial", Font.ITALIC, 20));

        octoberEatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent openMainWindow) {
                dispose();
                InterfazInicial interfazInicial = new InterfazInicial();
            }
        });

        northPanel.add(octoberEatsButton);

        JPanel northSpace = new JPanel();
        northSpace.setPreferredSize(new Dimension(400, 40));
        northSpace.setBackground(new Color(0, 0, 0, 0));

        northPanel.add(northSpace);

        JTextArea searchArea = new JTextArea();
        searchArea.setPreferredSize(new Dimension(400, 40));
        searchArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        northPanel.add(searchArea);

        JButton cart = new JButton();
        cart.setPreferredSize(new Dimension(60, 40));
        cart.setFont(new Font("Arial", Font.BOLD, 20));

        ImageIcon cartImage = new ImageIcon("images/cart.png");
        ImageIcon cartImageEscalada = new ImageIcon(cartImage.getImage().getScaledInstance(20, 20, Image.SCALE_FAST));
        cart.setIcon(cartImageEscalada);

        cart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent openCartWindow) {
                FrameFour fourthFrame = new FrameFour(carrito);
                
                dispose();
            }
        });
        
        cart.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent openCartWindow) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FrameFour fourthFrame = new FrameFour(carrito);
                    fourthFrame.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, 
                        "Error al abrir carrito: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
});

        northPanel.add(cart);

        JButton singIn = new JButton("Iniciar Sesion");
        singIn.setPreferredSize(new Dimension(200, 40));
        singIn.setFont(new Font("Arial", Font.ROMAN_BASELINE, 15));

        singIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent openSingInWindow) {
                dispose();
                InterfazInicioDeSesion interfazInicioDeSesion = new InterfazInicioDeSesion();
            }
        });

        northPanel.add(singIn);

        JButton singUp = new JButton("Registrarse");
        singUp.setPreferredSize(new Dimension(150, 40));
        singUp.setFont(new Font("Arial", Font.ROMAN_BASELINE, 15));

        northPanel.add(singUp);

        JButton[] northButtons = {threeLinesButton, octoberEatsButton, cart, singIn, singUp};

        for (JButton button : northButtons) {
            button.setContentAreaFilled(false); //Hace Transparente el fondo de los botones
            button.setBorderPainted(false);
            button.setFocusPainted(false);
        }

        JPanel centralPanel = new JPanel();
        centralPanel.setSize(new Dimension(1000, 1000));
        centralPanel.setBackground(Color.pink);

        JPanel CCPanel = new JPanel();
        CCPanel.setPreferredSize(new Dimension(1400, 750));
        CCPanel.setBackground(Color.white);
        CCPanel.setLayout(new BorderLayout());

        centralPanel.add(CCPanel);

        JPanel NCCPanel = new JPanel();
        NCCPanel.setPreferredSize(new Dimension(0, 200));
        NCCPanel.setBackground(new Color(0, 0, 0, 0));
        NCCPanel.setLayout(new FlowLayout());

        CCPanel.add(NCCPanel, BorderLayout.NORTH);

        JPanel CCCPanel = new JPanel();
        CCCPanel.setPreferredSize(new Dimension(1400, 550));
        CCCPanel.setBackground(Color.white);

        CCPanel.add(CCCPanel);

        JPanel restaurantNamePanel = new JPanel();
        restaurantNamePanel.setPreferredSize(new Dimension(1400, 70));
        restaurantNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 22));

        CCCPanel.add(restaurantNamePanel);

        JLabel restaurantNameLabel = new JLabel("REST NAME");
        restaurantNameLabel.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));

        restaurantNamePanel.add(restaurantNameLabel);

        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setPreferredSize(new Dimension(1400, 50));
        categoriesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 12));

        CCCPanel.add(categoriesPanel);

        JButton novedadesB, familiaB, combosB, menuB, collectionB, especialB, postresB;

        novedadesB = new JButton("Novedades October Eats");
        familiaB = new JButton("Family");
        combosB = new JButton("Combos");
        menuB = new JButton("Menu");
        collectionB = new JButton("Collection");
        especialB = new JButton("Especialidades");
        postresB = new JButton("Postres");

        JButton[] CCCPanelButtons = {novedadesB, familiaB, combosB, menuB, collectionB, especialB, postresB};

        for (JButton button : CCCPanelButtons) {
            button.setFont(new Font("Arial", Font.ITALIC, 15));
            button.setContentAreaFilled(false); //Hace Transparente el fondo de los botones
            button.setBorderPainted(false);
            button.setFocusPainted(false);

            categoriesPanel.add(button);
        }

        // Agregar productos con sus paneles e imágenes
        String[] productNames = {"Hamburguesa", "Pizza", "Tacos", "Pasta", "Ensalada", "Postre"};
        double[] productPrices = {8.99, 12.99, 7.50, 9.99, 6.50, 5.99};

        JPanel optionOne, optionTwo, optionThree, optionFour, optionFive, optionSix;

        optionOne = createProductPanel(productNames[0], productPrices[0], "Cliente");
        optionTwo = createProductPanel(productNames[1], productPrices[1], "Cliente");
        optionThree = createProductPanel(productNames[2], productPrices[2], "Cliente");
        optionFour = createProductPanel(productNames[3], productPrices[3], "Cliente");
        optionFive = createProductPanel(productNames[4], productPrices[4], "Cliente");
        optionSix = createProductPanel(productNames[5], productPrices[5], "Cliente");

        JPanel[] panels = {optionOne, optionTwo, optionThree, optionFour, optionFive, optionSix};

        for (JPanel panel : panels) {
            CCCPanel.add(panel);
        }

        add(northPanel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Método para crear paneles de producto con funcionalidad para agregar al carrito
    private JPanel createProductPanel(String productName, double price, String clientName) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(695, 200));
        panel.setBackground(Color.white);
        panel.setLayout(new FlowLayout());

        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(211, 211, 211));
        namePanel.setPreferredSize(new Dimension(510, 189));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel(productName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));

        JLabel priceLabel = new JLabel("Precio: $" + price);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 20, 0));

        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        quantitySpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        quantitySpinner.setMaximumSize(new Dimension(80, 30));

        namePanel.add(nameLabel);
        namePanel.add(priceLabel);
        namePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        namePanel.add(quantitySpinner);

        panel.add(namePanel);

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.gray);
        imagePanel.setPreferredSize(new Dimension(170, 189));
        imagePanel.setLayout(new BorderLayout());

        JButton addToCartButton = new JButton("Agregar");
        addToCartButton.setBackground(new Color(70, 70, 70));
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setFocusPainted(false);

        // Acción para agregar al carrito
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad = (int) quantitySpinner.getValue();
                Orden nuevaOrden = new Orden(clientName, productName, cantidad);
                carrito.agregarObjeto(nuevaOrden);
                JOptionPane.showMessageDialog(null, 
                    "Agregado al carrito: " + cantidad + " x " + productName);
            }
        });

        imagePanel.add(addToCartButton, BorderLayout.CENTER);
        panel.add(imagePanel);

        return panel;
    }

    // Método para acceder al carrito desde otras clases
    public static CarritoCompras getCarrito() {
        return carrito;
    }
}