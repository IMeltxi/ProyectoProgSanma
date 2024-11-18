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
        
        //Panel dar de baja
        JPanel panelDardeBaja = new JPanel(new FlowLayout());
        JButton botonDB = new JButton("Darse de baja");
        panelDardeBaja.add(botonDB);
        
        //añado los paneles al panel principal
        panelPrincipal.add(panelIR, BorderLayout.NORTH);
        panelPrincipal.add(panelDardeBaja ,BorderLayout.SOUTH);
        
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
        
        botonDB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaDarDeBaja ventanadardebaja = new VentanaDarDeBaja(admin);
				ventanadardebaja.setVisible(true);
				dispose(); 
			}
		});
        
	}
	
    public static void main(String[] args) {
        new VentanaInicio();
    }
}
