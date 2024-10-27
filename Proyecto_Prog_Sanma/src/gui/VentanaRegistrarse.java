package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class VentanaRegistrarse extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaRegistrarse() {
        // Configuración de la ventana
        setTitle("Registrarse");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal con GridBagLayout para centrar
        JPanel panelprincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        // BoxLayout para el panel2
        JPanel panel2 = new JPanel();
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Registrarse");
        panel2.setBorder(BorderFactory.createCompoundBorder(titledBorder,new EmptyBorder(20, 20, 20, 20)));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        
        // Etiqueta y campo de texto de Nombre
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(nombreLabel);
        
        JTextField nombreField = new JTextField(20);
        nombreField.setMaximumSize(new Dimension(400,30));
        nombreField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(nombreField);
        
        panel2.add(Box.createRigidArea(new Dimension(0,10)));
        
        // Etiqueta y campo de texto de Apellido
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(apellidoLabel);
        
        JTextField apellidoField = new JTextField(20);
        apellidoField.setMaximumSize(new Dimension(400,30));
        apellidoField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(apellidoField);
        
        panel2.add(Box.createRigidArea(new Dimension(0,10)));
        
        // Etiqueta y campo de texto de Telefono
        JLabel tlfLabel = new JLabel("Correo:");
        tlfLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(tlfLabel);
        
        JTextField tlfField = new JTextField(20);
        tlfField.setMaximumSize(new Dimension(400,30));
        tlfField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(tlfField);
        
        panel2.add(Box.createRigidArea(new Dimension(0,10)));
        
        // Etiqueta y campo de texto de Fecha de nacimiento
        JLabel fechaNacimientoLabel = new JLabel("Fecha de nacimiento:");
        fechaNacimientoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(fechaNacimientoLabel);
        
        JTextField fechaNacimientoField = new JTextField(20);
        fechaNacimientoField.setMaximumSize(new Dimension(400,30));
        fechaNacimientoField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(fechaNacimientoField);
        
        panel2.add(Box.createRigidArea(new Dimension(0,10)));
        
        // Etiqueta y campo de texto de Correo
        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(correoLabel);
        
        JTextField correoField = new JTextField(20);
        correoField.setMaximumSize(new Dimension(400,30));
        correoField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(correoField);
        
        panel2.add(Box.createRigidArea(new Dimension(0,10)));
        
        // Etiqueta y campo de texto de Contraseña
        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(contraseñaLabel);
        
        JTextField contraseniaField = new JTextField(20);
        contraseniaField.setMaximumSize(new Dimension(400,30));
        contraseniaField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(contraseniaField);
        
        panel2.add(Box.createRigidArea(new Dimension(0,20)));
        
        // Etiqueta y campo de texto de ConfirmarContraseña
        JLabel contrasenia2Label = new JLabel("Confirmar Contraseña:");
        contrasenia2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(contrasenia2Label);
        
        JTextField contrasenia2Field = new JTextField(20);
        contrasenia2Field.setMaximumSize(new Dimension(400,30));
        contrasenia2Field.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(contrasenia2Field);
        
        panel2.add(Box.createRigidArea(new Dimension(0,10)));
        
        // Botón de Registrarse
        JButton botonInicioSesion = new JButton("Registrarse");
        botonInicioSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonInicioSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gmail = correoField.getText();
                String contraseña = contraseniaField.getText();
                
                if(gmail.equals("ejemplo") && contraseña.equals("ejemplo")) {
                    JOptionPane.showMessageDialog(null, "Inicio valido");
                } else {
                    JOptionPane.showMessageDialog(null, "Inicio no valido");
                   
                }
            }
        });
        
        panel2.add(botonInicioSesion);
        
        // Agregar panel2 centrado en el panel principal
        panelprincipal.add(panel2, gbc);
        
        add(panelprincipal);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaRegistrarse();
    }
}