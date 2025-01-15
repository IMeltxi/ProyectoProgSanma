package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.Admin;
import domain.Usuario;
import domain.Usuario.tipoSocio;

public class VentanaPrincipal extends JFrame {
    private static final long serialVersionUID = -481611237702984322L;
    private Admin admin;
    private PanelCargando panelFondo;

    public static void main(String[] args) {
        Usuario usuario = new Usuario(tipoSocio.SOCIO, "Juan", "Perez", "123456789", "1990-05-15", "juan.perez@email.com", "miContrasenaSegura");
        new VentanaPrincipal(usuario, true);
    }

    public VentanaPrincipal(Usuario user, boolean esAdmin) {
        // Configuración de la ventana
        setTitle("Principal");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración del panel de fondo con imagen
        panelFondo = new PanelCargando(new ImageIcon("Imagenes/ImagenesFondo/AthleticAficion.png").getImage());
        setContentPane(panelFondo);

        // Crear panel para botones y establecer diseño
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false); // Hacer transparente para mostrar el fondo

        // Botones de acciones
        JButton botonCE = new JButton("Comprar Entradas");
        JButton botonSo = new JButton("Cambiarme Socio");
        JButton botonBaja = new JButton("Darme de Baja");
        JButton botonDatos;

        if (esAdmin) {
            admin = new Admin();
            botonDatos = new JButton("Admin");
            botonDatos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VentanaAdmin ventanaAdmin = new VentanaAdmin(admin);
                    ventanaAdmin.setVisible(true);
                    dispose();
                }
            });
        } else {
            botonDatos = new JButton("Tus Datos");
            botonDatos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VentanaDetallesCuenta ventanaDC = new VentanaDetallesCuenta(user);
                    ventanaDC.setVisible(true);
                    dispose();
                }
            });
        }

        // Añadir los botones al panel
        panelBotones.add(botonCE);
        panelBotones.add(botonSo);
        panelBotones.add(botonBaja);
        panelBotones.add(botonDatos);

        // Añadir el panel de botones al sur de la ventana
        add(panelBotones, BorderLayout.SOUTH);

        // Configurar eventos para los otros botones
        botonCE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaEntradas ventanaEntradas = new VentanaEntradas(null);
                ventanaEntradas.setVisible(true);
                dispose();
            }
        });

        botonSo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaHacermeSocio ventanaSocio = new VentanaHacermeSocio(null, null);
                ventanaSocio.setVisible(true);
                dispose();
            }
        });

        botonBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaDarDeBaja ventanaBaja = new VentanaDarDeBaja(admin);
                ventanaBaja.setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }
}
