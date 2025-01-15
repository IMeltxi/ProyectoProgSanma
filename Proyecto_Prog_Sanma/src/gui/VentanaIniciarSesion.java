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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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
import domain.Usuario.tipoSocio;


public class VentanaIniciarSesion extends JFrame {
	private Admin admin;
	private JTextField correoField;
	private JPasswordField contraseñaField;
	
	private Properties properties;
	private String adminName;
	private String adminPassword;
	
	private static Logger logger = Logger.getLogger(VentanaIniciarSesion.class.getName());
	public VentanaIniciarSesion() {
		this.admin = new Admin();
		admin.cargarUsuarios();
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
		this.correoField = new JTextField(20);
		correoField.setMaximumSize(new Dimension(400,30));//Tamaño maximo para campos
		correoField.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBotones.add(correoField);
		
		panelBotones.add(Box.createRigidArea(new Dimension(0,10)));//Espacio entre los campos
		
		//Contraseña etiqutea
		JLabel contraseñaLabel = new JLabel("Contraseña:");
		contraseñaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBotones.add(contraseñaLabel);
		
        //Contraseña Texto
        this.contraseñaField = new JPasswordField(20);
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
	
		Properties properties = new Properties();
        
		// Obtener el administrador
		try (FileInputStream fis = new FileInputStream("conf/admin.properties")) {
		    // Lectura del fichero properties
		    properties.load(fis);  // Cargar las propiedades del archivo

		    // Obtener las propiedades
		    adminName = properties.getProperty("user");
		    adminPassword = properties.getProperty("password");

		} catch (IOException ex) {
		    logger.warning(String.format("Error al cargar el archivo de configuración: %s", ex.getMessage()));
		}
    
        

		
		
		
		botonInicioSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Usuario user = verificarUsuario();
				if(!(user==null)) {
					VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(user,false);
	                ventanaPrincipal.setVisible(true);
				}
				
				if (correoField.getText().trim().equals(adminName) && 
				    Arrays.equals(contraseñaField.getPassword(), adminPassword.trim().toCharArray())) {
					VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(new Usuario
							(tipoSocio.SOCIO, "admin", "admin", "123", "2004-01-01", adminName, adminPassword),true);
					ventanaPrincipal.setVisible(true);
				} 
				
				
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
		
			private Usuario verificarUsuario() {
			    // Validar campos vacíos antes de continuar
			    if (correoField.getText().trim().isEmpty() || contraseñaField.getPassword().length == 0) {
			        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
			        return null;
			    }

			    // Obtener el correo y la contraseña ingresados
			    String email = correoField.getText().trim();
			    char[] contrasena = contraseñaField.getPassword();

			    // Buscar usuario en la lista de usuarios del administrador
			    Usuario usuarioEncontrado = admin.buscarUsuarioPorEmail(email);
			    boolean esAdmin=false;
			    if (correoField.getText().trim().equals(adminName) && 
					    Arrays.equals(contraseñaField.getPassword(), adminPassword.trim().toCharArray())) {
						esAdmin = true;
				} 
			    
			    // Validar si el usuario existe y la contraseña es correcta
			    if (usuarioEncontrado == null || !usuarioEncontrado.verificarContrasena(contrasena)) {
			        if(!(esAdmin)) {
			        	JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
				        
			        }else {
			        	JOptionPane.showMessageDialog(this, "Inicio de sesión válido", "Ongi Etorri Admin", JOptionPane.INFORMATION_MESSAGE);
			        }
			        return null;
			    } else {
			        // Inicio de sesión exitoso
			        JOptionPane.showMessageDialog(this, "Inicio de sesión válido", "Ongi Etorri", JOptionPane.INFORMATION_MESSAGE);
			        
			        // Cerrar la ventana actual
			        dispose();
			        return usuarioEncontrado;
			    }
				
			    
			}


}
