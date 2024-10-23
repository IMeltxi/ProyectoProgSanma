package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaIniciarSesion extends JFrame {
	
	public VentanaIniciarSesion() {
		// Configuracion Ventana
		
		setTitle("Iniciar Sesion");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		//Panel principal
		JPanel panelprincipal = new JPanel(new BorderLayout());
		
		//BoxLayaout
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		
		//Correo etiqueta
		JLabel correoLabel = new JLabel("Correo:");
		correoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(correoLabel);
		
		//Correo Texto
		JTextField correoField = new JTextField(20);
		correoField.setMaximumSize(new Dimension(400,30));//Tamaño maximo para campos
		correoField.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(correoField);
		
		panel2.add(Box.createRigidArea(new Dimension(0,10)));//Espacio entre los campos
		
		//Contraseña etiqutea
		JLabel contraseñaLabel = new JLabel("Contraseña:");
		contraseñaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(contraseñaLabel);
		
        //Contraseña Texto
		JTextField contraseñaField = new JTextField(20);
		contraseñaField.setMaximumSize(new Dimension(400,30));
		contraseñaField.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(contraseñaField);
		
		panel2.add(Box.createRigidArea(new Dimension(0,20)));
		
		//Boton iniciar sesion
		
		JButton botonInicioSesion = new JButton("Iniciar Sesion");
		botonInicioSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Action listener
		
		botonInicioSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String gmail = correoField.getText();
				String contraseña = contraseñaField.getText();
				
				//condicion para iniciar sesion
				
				if(gmail.equals("ejemplo") && contraseña.equals("ejemplo")) {
					JOptionPane.showMessageDialog(null, "inicio valido");
				}else {
					JOptionPane.showMessageDialog(null, "inicio no valido");
				}
				
			}
		});
		
		//panel para el boton
		JPanel panelboton =new JPanel();
		panelboton.add(botonInicioSesion);
		
		//paneles
		panelprincipal.add(panel2, BorderLayout.CENTER);
		panelprincipal.add(panelboton, BorderLayout.SOUTH);
		
		add(panelprincipal);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		new VentanaIniciarSesion();
		}

}
