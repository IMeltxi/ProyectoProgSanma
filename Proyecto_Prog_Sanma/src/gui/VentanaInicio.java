package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.Admin;

public class VentanaInicio extends JFrame {
	
	private Admin admin;


	public VentanaInicio() {
		
		
        // Configuración de la ventana
        setTitle("Inicio");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //Panel principal
        
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        //Panel Incio/Registro
        JPanel panelIR = new JPanel(new FlowLayout());
        //creo klos botones
        JButton botonI = new JButton("IniciarSesion");
        JButton botonR = new JButton("Registrarse");
        //añado los botones al panel
        panelIR.add(botonI);
        panelIR.add(botonR);
        
        
        //Panel para comprar entradas
        JPanel panelcomprar = new JPanel(new FlowLayout());
        JButton botonComprar = new JButton("Comprar entradas");
        panelcomprar.add(botonComprar);
        
        
        
        //añado los paneles al panel principal
        panelPrincipal.add(panelIR, BorderLayout.NORTH);
        panelPrincipal.add(panelcomprar,BorderLayout.CENTER);
        
        add(panelPrincipal);
        setVisible(true);
        
        //añado los action listener
        botonI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaIniciarSesion ventanaIniciarSesion = new VentanaIniciarSesion(admin);
				ventanaIniciarSesion.setVisible(true);
				dispose(); 
			}
		});
        
        botonR.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaRegistrarse ventanaRegistrarse = new VentanaRegistrarse();
				ventanaRegistrarse.setVisible(true);
				dispose(); 
			}
		});
        
        
        botonComprar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaEntradas ventanaentradas = new VentanaEntradas();
				ventanaentradas.setVisible(true);
				dispose();
			}
		});
        
	}
	
    public static void main(String[] args) {
        new VentanaInicio();
    }
}
