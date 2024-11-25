package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.Admin;

public class VentanaPrincipal extends JFrame{
	
	private Admin admin;
	
	public static void main(String[] args) {
        new VentanaPrincipal();
    }
	public VentanaPrincipal() {
		
		 // Configuración de la ventana
        setTitle("Principal");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //Panel Principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        add(panelPrincipal);
        //Panel Comprar entradas
        JPanel panelCE = new JPanel();
        JButton botonCE = new JButton("comprar entradas");
        panelCE.add(botonCE);
        //Panel hacerme socio
        JPanel panelSo = new JPanel();
        JButton botonSo = new JButton("Hacerme Socio");
        panelSo.add(botonSo);
        //Darse de baja
        JPanel panelBaja = new JPanel();
        JButton botonBaja = new JButton("Darme de baja");
        panelBaja.add(botonBaja);
        
        //Añadir paneles al panel principal
        panelPrincipal.add(panelCE, BorderLayout.NORTH);
        panelPrincipal.add(panelSo, BorderLayout.CENTER);
        panelPrincipal.add(panelBaja, BorderLayout.SOUTH);
        
        setVisible(true);
        
        //Action listener de cada boton
        botonCE.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaEntradas ventanaentradas = new VentanaEntradas();
				ventanaentradas.setVisible(true);
				dispose();
			}
		});
        
        botonSo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaHacermeSocio ventanasocio = new VentanaHacermeSocio();
				ventanasocio.setVisible(true);
				dispose();
			}
		});
        
        botonBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaDarDeBaja ventanabaja = new VentanaDarDeBaja(admin);
				ventanabaja.setVisible(true);
				dispose();
			}
		});
	}
}
