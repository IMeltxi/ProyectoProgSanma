package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.Admin;
import domain.Usuario;

public class VentanaInicio extends JFrame {
	

	public VentanaInicio(Usuario user) {
		
		// Configuración inicial de la ventana
        // Establece el título de la ventana, maximiza su tamaño, define la operación de cierre
        // y centra la ventana en la pantalla.
        setTitle("Inicio");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Configuración del panel principal
        // Este panel principal utiliza un diseño BorderLayout para organizar los componentes.
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        //Panel Incio/Registro
        JPanel panelIR = new JPanel(new FlowLayout());
        JButton botonI = new JButton("IniciarSesion");
        JButton botonR = new JButton("Registrarse");
        panelIR.add(botonI);
        panelIR.add(botonR);
        
        // Configuración del panel para comprar entradas
        // Este panel contiene un botón que permite al usuario acceder a la ventana de compra de entradas.
        JPanel panelcomprar = new JPanel(new FlowLayout());
        JButton botonComprar = new JButton("Comprar entradas");
        panelcomprar.add(botonComprar);
         
        // Agregar los paneles al panel principal
        panelPrincipal.add(panelIR, BorderLayout.NORTH);
        panelPrincipal.add(panelcomprar,BorderLayout.CENTER);
        
        // Configuración de los manejadores de eventos
        // Define las acciones que ocurren cuando los usuarios interactúan con los botones.
        add(panelPrincipal);
        setVisible(true);
        
     // Configuración de los manejadores de eventos
        // Define las acciones que ocurren cuando los usuarios interactúan con los botones.
        botonI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cuando el usuario hace clic en el botón "IniciarSesion",
				// se abre la ventana de inicio de sesión (VentanaIniciarSesion),
				// se pasa el objeto Admin como referencia y se cierra la ventana actual.
				VentanaIniciarSesion ventanaIniciarSesion = new VentanaIniciarSesion();
				ventanaIniciarSesion.setVisible(true);
				dispose(); 
			}
		});
        
        botonR.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cuando el usuario hace clic en el botón de "Registrarse",
				// se abre una nueva ventana de registro (VentanaRegistrarse)
				// y se cierra la ventana actual.
				VentanaRegistrarse ventanaRegistrarse = new VentanaRegistrarse();
				ventanaRegistrarse.setVisible(true);
				dispose(); 
			}
		});
        
        
        botonComprar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cuando el usuario hace clic en el botón "Comprar entradas",
				// se abre una nueva ventana para gestionar la compra de entradas (VentanaEntradas).
				// La ventana actual se cierra automáticamente para dar paso a la nueva.
				if(!(user==null)) {
					VentanaEntradas ventanaentradas = new VentanaEntradas();
					ventanaentradas.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(VentanaInicio.this, "Para comprar una entrada es necesario iniciar sesion primero", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
        
	}
	
}
