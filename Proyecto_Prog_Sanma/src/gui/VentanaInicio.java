package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import domain.Usuario;

public class VentanaInicio extends JFrame {
    private static final long serialVersionUID = 7528038603007692343L;
    private PanelCargando panelFondo;

    public VentanaInicio(Usuario user) {
        // Configuración del panel de fondo con imagen
        panelFondo = new PanelCargando(new ImageIcon("Imagenes/ImagenesFondo/AthleticFondo.png").getImage());
        setContentPane(panelFondo);

        // Configuración de la ventana
        setTitle("Inicio");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel para botones y establecer diseño
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false); // Hacer transparente para mostrar el fondo

        // Botones de acciones
        JButton botonI = new JButton("Iniciar Sesión");
        JButton botonR = new JButton("Registrarse");
        JButton botonComprar = new JButton("Comprar Entradas");

        // Añadir botones al panel
        panelBotones.add(botonI);
        panelBotones.add(botonR);
        panelBotones.add(botonComprar);

        // Añadir el panel de botones al sur de la ventana
        add(panelBotones, BorderLayout.SOUTH);

        // Configuración de manejadores de eventos
        botonI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaIniciarSesion ventanaIniciarSesion = new VentanaIniciarSesion();
                ventanaIniciarSesion.setVisible(true);
                dispose();
            }
        });

        botonR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaRegistrarse ventanaRegistrarse = new VentanaRegistrarse();
                ventanaRegistrarse.setVisible(true);
                dispose();
            }
        });

        botonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user != null) {
                    VentanaEntradas ventanaEntradas = new VentanaEntradas(user);
                    ventanaEntradas.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                        VentanaInicio.this,
                        "Para comprar una entrada es necesario iniciar sesión primero",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        setVisible(true);
    }
}
