package Interfaz;

import BaseDeDatos.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class InterfazInicioDeSesion extends JFrame {

    private int intentos = 0; // Contador de intentos de inicio de sesión

    public InterfazInicioDeSesion() {

        setSize(new Dimension(1400, 800));
        setMinimumSize(new Dimension(1600, 800));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(0, 70));
        northPanel.setBackground(new Color(0, 0, 0));
        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));

        JButton octoberEatsButton = new JButton("<html>October <b>Eats</b></html>");
        octoberEatsButton.setPreferredSize(new Dimension(200, 40));
        octoberEatsButton.setFont(new Font("Arial", Font.ITALIC, 25));
        octoberEatsButton.setForeground(new Color(255, 255, 255));
        octoberEatsButton.setContentAreaFilled(false);
        octoberEatsButton.setBorderPainted(false);
        octoberEatsButton.setFocusPainted(false);

        octoberEatsButton.addActionListener(openMainWindow -> {
            dispose();
            InterfazInicial interfazInicial = new InterfazInicial();
        });

        northPanel.add(octoberEatsButton);

        JPanel centralPanel = new JPanel();
        centralPanel.setSize(new Dimension(1000, 1000));
        centralPanel.setBackground(new Color(255, 255, 255));

        JPanel CCPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 12));
        CCPanel.setPreferredSize(new Dimension(350, 750));
        CCPanel.setBackground(new Color(0, 0, 0, 0));

        centralPanel.add(CCPanel);

        JPanel space = new JPanel();
        space.setPreferredSize(new Dimension(290, 150));
        space.setBackground(new Color(0, 0, 0, 0));

        CCPanel.add(space);

        JLabel titleOne = new JLabel("<html>What's your phone number or<br>email?</html>");
        titleOne.setFont(new Font("Arial", Font.ROMAN_BASELINE, 21));
        titleOne.setPreferredSize(new Dimension(290, 40));

        CCPanel.add(titleOne);

        JTextArea inputEmailArea = new JTextArea();
        inputEmailArea.setPreferredSize(new Dimension(290, 40));
        inputEmailArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        CCPanel.add(inputEmailArea);

        JButton continueButton = new JButton("Continue");
        continueButton.setPreferredSize(new Dimension(290, 40));
        continueButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 15));
        continueButton.setForeground(Color.WHITE);
        continueButton.setBackground(Color.BLACK);
        continueButton.setBorderPainted(false);
        continueButton.setFocusPainted(false);

        // ActionListener para verificar correo
        continueButton.addActionListener(e -> {
            String email = inputEmailArea.getText();
            email = email.trim();
            
            AutenticadorUsuarios autenticador = new AutenticadorUsuarios();
            boolean existe = autenticador.verificarUsuario(email, null); // Verifica solo si existe el correo

            if (existe) {
                mostrarDialogoContraseña(email, autenticador); // Llama al diálogo de contraseña
            } else {
                JOptionPane.showMessageDialog(this, "El correo no existe. Por favor, verifica.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        CCPanel.add(continueButton);

        add(centralPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void mostrarDialogoContraseña(String email, AutenticadorUsuarios autenticador) {
        JDialog dialog = new JDialog(this, "Ingresar Contraseña", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Ingrese su contraseña:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        panel.add(label);
        panel.add(passwordField);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String password = new String(passwordField.getPassword());
            boolean acceso = autenticador.verificarUsuario(email, password); // Verifica correo y contraseña

            if (acceso) {
                JOptionPane.showMessageDialog(dialog, "¡Inicio de sesión exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                dispose(); // Cierra FrameTwo si inicia sesión correctamente
                InterfazInicial interfazInicial = new InterfazInicial(); // Abre otro frame
            } else {
                intentos++;
                if (intentos >= 4) {
                    JOptionPane.showMessageDialog(dialog, "Has alcanzado el límite de intentos. Acceso bloqueado.", "Bloqueo", JOptionPane.ERROR_MESSAGE);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Contraseña incorrecta. Intentos restantes: " + (4 - intentos), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dialog.add(panel);
        dialog.setVisible(true);
    }
}

