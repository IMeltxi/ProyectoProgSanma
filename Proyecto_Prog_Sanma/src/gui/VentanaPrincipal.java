package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.Admin;
import domain.Usuario;
import domain.Usuario.tipoSocio;

public class VentanaPrincipal extends JFrame {
    
    private Admin admin;
    private Usuario usuario;
    
    public static void main(String[] args) {
        // Asegúrate de pasar un Usuario válido y esAdmin en true/false según sea necesario
    	Usuario usuario = new Usuario(tipoSocio.SOCIO, "Juan", "Perez", "123456789", "1990-05-15", "juan.perez@email.com", "miContrasenaSegura");

    	new VentanaPrincipal(usuario, true);
        
    }

    public VentanaPrincipal(Usuario user, boolean esAdmin) {
        // Configuración de la ventana
        setTitle("Principal");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel Principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        add(panelPrincipal);

        // Panel Comprar entradas
        JPanel panelCE = new JPanel();
        JButton botonCE = new JButton("Comprar entradas");
        panelCE.add(botonCE);

        // Panel Hacerme socio
        JPanel panelSo = new JPanel();
        JButton botonSo = new JButton("Cambiarme Socio");
        panelSo.add(botonSo);

        // Mostrar el botón adecuado dependiendo si es Admin o no
        if (esAdmin) {
        	Admin admin = new Admin();
            // Botón Admin
            JPanel panelDatos = new JPanel();
            JButton botonDatos = new JButton("Admin");
            panelDatos.add(botonDatos);
            panelPrincipal.add(panelDatos, BorderLayout.NORTH);
            
            botonDatos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Llamar a la ventana de admin
                    VentanaAdmin ventanaAdmin = new VentanaAdmin(admin);
                    ventanaAdmin.setVisible(true);
                    dispose();
                }
            });
        } else {
            // Botón de tus datos (Usuario normal)
            JPanel panelDatos = new JPanel();
            JButton botonDatos = new JButton("Tus datos");
            panelDatos.add(botonDatos);
            panelPrincipal.add(panelDatos, BorderLayout.NORTH);
            
            botonDatos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Llamar a la ventana de detalles de cuenta del usuario
                    VentanaDetallesCuenta ventanaDC = new VentanaDetallesCuenta(user);
                    ventanaDC.setVisible(true);
                    dispose();
                }
            });
        }
        
        // Panel Darme de baja
        JPanel panelBaja = new JPanel();
        JButton botonBaja = new JButton("Darme de baja");
        panelBaja.add(botonBaja);

        // Añadir paneles al panel principal (Cada uno en su respectiva posición)
        panelPrincipal.add(panelCE, BorderLayout.WEST);  // Ajustado a BorderLayout.WEST
        panelPrincipal.add(panelSo, BorderLayout.CENTER);  // Ajustado a BorderLayout.CENTER
        panelPrincipal.add(panelBaja, BorderLayout.SOUTH); // Ajustado a BorderLayout.SOUTH

        setVisible(true);  // Asegúrate de llamar a setVisible después de agregar todos los componentes
        
        // Action listener de cada botón
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
                VentanaHacermeSocio ventanaSocio = new VentanaHacermeSocio();
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
    }
}
