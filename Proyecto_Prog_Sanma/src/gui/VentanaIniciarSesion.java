package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import domain.Admin;
import domain.Usuario;

public class VentanaIniciarSesion extends JFrame {
	private Admin admin;
	private JTextField correoField;
	private JPasswordField contraseñaField;
	public VentanaIniciarSesion(Admin admin) {
		this.admin = admin;
		// Configuracion Ventana
		
		setTitle("Iniciar Sesion");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		 // Panel principal con GridBagLayout para centrar
        JPanel panelprincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        
		//BoxLayaout
		JPanel panelBotones = new JPanel();
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Inicio de Sesion");
        panelBotones.setBorder(BorderFactory.createCompoundBorder(titledBorder,new EmptyBorder(20, 20, 20, 20)));
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		
		//Correo etiqueta
		JLabel correoLabel = new JLabel("Correo:");
		correoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBotones.add(correoLabel);
		
		//Correo Texto
		JTextField correoField = new JTextField(20);
		correoField.setMaximumSize(new Dimension(400,30));//Tamaño maximo para campos
		correoField.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBotones.add(correoField);
		
		panelBotones.add(Box.createRigidArea(new Dimension(0,10)));//Espacio entre los campos
		
		//Contraseña etiqutea
		JLabel contraseñaLabel = new JLabel("Contraseña:");
		contraseñaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBotones.add(contraseñaLabel);
		
        //Contraseña Texto
		JPasswordField contraseñaField = new JPasswordField(20);
		contraseñaField.setMaximumSize(new Dimension(400,30));
		contraseñaField.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBotones.add(contraseñaField);
		
		panelBotones.add(Box.createRigidArea(new Dimension(0,20)));
		
		//Boton iniciar sesion
		
		JButton botonInicioSesion = new JButton("Iniciar Sesion");
		botonInicioSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//etiqueta registrate
		JLabel registroetiqueta = new JLabel("Registrate si no tienes cuenta");
		registroetiqueta.setPreferredSize(new Dimension(200, 50)); // Ancho de 200 y alto de 50
		//registro boton
		JButton botonregistrate = new JButton("registrate");
		botonregistrate.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Agregar boton de inicio de sesion al panel
		panelBotones.add(botonInicioSesion);
		panelBotones.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio horizontal de 20 píxeles
		panelBotones.add(botonregistrate);
		
		// Agregar panel2 centrado en el panel principal
        panelprincipal.add(panelBotones, gbc);
		
		add(panelprincipal);
		
		setVisible(true);
		
		botonInicioSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				verificarUsuario();
			}
		});
		botonregistrate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaRegistrarse ventanaRegistrarse = new VentanaRegistrarse();
				ventanaRegistrarse.setVisible(true);
				dispose(); 
			}
		});
	}
		
		private void verificarUsuario() {
			String email = correoField.getText();
	        String contrasena = new String (contraseñaField.getPassword());
	        
	        Usuario usuarioEncontrado = null;
	        for(Usuario usuario : admin.getUsuarios()) {
	        	if(usuario.getEmail().equals(email)) {
	        		usuarioEncontrado = usuario;
	        		break;
	        	}
	        }
	        if (usuarioEncontrado == null) {
	            JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }else {
	        	if(usuarioEncontrado.getContrasena().equals(contrasena)) {
	        		JOptionPane.showMessageDialog(this, "Inicio de sesion valido","Ongi Etorri", JOptionPane.INFORMATION_MESSAGE);
	        	}else {
	        		JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Vuelve a intentarlo", JOptionPane.ERROR_MESSAGE);
	        	}
	        }		
	}
		
	
	
	public static void main(String[] args) {
		Admin admin = new Admin();
		new VentanaIniciarSesion(admin);
		}

}
