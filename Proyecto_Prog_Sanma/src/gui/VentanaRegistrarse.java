package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import domain.Admin;
import domain.Usuario;

public class VentanaRegistrarse extends JFrame {

    private static final long serialVersionUID = 1L;
    private Admin admin;

    public VentanaRegistrarse() {
        admin = new Admin(); // Inicializamos el objeto Admin
        admin.cargarUsuarios(); // Cargamos los usuarios

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
        panel2.setBorder(BorderFactory.createCompoundBorder(titledBorder, new EmptyBorder(20, 20, 20, 20)));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));

		// Crear el botón "Atrás"
		JButton botonAtras = new JButton("Atrás");

		// Configurar el evento del botón "Atrás"
		botonAtras.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        VentanaInicio inicio = new VentanaInicio(null);
		        inicio.setVisible(true);
		        dispose();
		    }
		});
		// Añadir el botón al panel superior
		panelSuperior.add(botonAtras);

		// Añadir el panel superior al contenedor principal (por ejemplo, usando BorderLayout)
		add(panelSuperior, BorderLayout.NORTH);
		
        
        @SuppressWarnings("unused")
		JLabel panelLateral = new JLabel("Tipo de Socio:");
        JComboBox<String> cbTipoSocio = new JComboBox<>(new String[] { "SocioMensual", "Socio", "VIP", "GAZTEABONO" });
        panel2.add(cbTipoSocio);

        // Etiqueta y campo de texto de Nombre
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(nombreLabel);

        JTextField nombreField = new JTextField(20);
        nombreField.setMaximumSize(new Dimension(400, 30));
        nombreField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(nombreField);

        panel2.add(Box.createRigidArea(new Dimension(0, 10)));

        // Etiqueta y campo de texto de Apellido
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(apellidoLabel);

        JTextField apellidoField = new JTextField(20);
        apellidoField.setMaximumSize(new Dimension(400, 30));
        apellidoField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(apellidoField);

        panel2.add(Box.createRigidArea(new Dimension(0, 10)));

        // Etiqueta y campo de texto de Telefono
        JLabel tlfLabel = new JLabel("Telefono:");
        tlfLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(tlfLabel);

        JTextField tlfField = new JTextField(20);
        tlfField.setMaximumSize(new Dimension(400, 30));
        tlfField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(tlfField);

        panel2.add(Box.createRigidArea(new Dimension(0, 10)));

        // Etiqueta y campo de texto de Fecha de nacimiento
        JLabel fechaNacimientoLabel = new JLabel("Fecha de nacimiento:(yyyy-MM-dd)");
        fechaNacimientoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(fechaNacimientoLabel);

        JTextField fechaNacimientoField = new JTextField(20);
        fechaNacimientoField.setMaximumSize(new Dimension(400, 30));
        fechaNacimientoField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(fechaNacimientoField);

        panel2.add(Box.createRigidArea(new Dimension(0, 10)));

        // Etiqueta y campo de texto de Correo
        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(correoLabel);

        JTextField correoField = new JTextField(20);
        correoField.setMaximumSize(new Dimension(400, 30));
        correoField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(correoField);

        panel2.add(Box.createRigidArea(new Dimension(0, 10)));

        // Etiqueta y campo de texto de Contraseña
        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(contraseñaLabel);

        JPasswordField contraseniaField = new JPasswordField(20);
        contraseniaField.setMaximumSize(new Dimension(400, 30));
        contraseniaField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(contraseniaField);

        panel2.add(Box.createRigidArea(new Dimension(0, 20)));

        // Etiqueta y campo de texto de ConfirmarContraseña
        JLabel contrasenia2Label = new JLabel("Confirmar Contraseña:");
        contrasenia2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(contrasenia2Label);

        JPasswordField contrasenia2Field = new JPasswordField(20);
        contrasenia2Field.setMaximumSize(new Dimension(400, 30));
        contrasenia2Field.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(contrasenia2Field);

        panel2.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botón de Registrarse
        JButton botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		String tipoSocioStr = (String) cbTipoSocio.getSelectedItem();
                    Usuario.tipoSocio tipoSocio = Usuario.tipoSocio.valueOf(tipoSocioStr.toUpperCase());
                    String nombre = nombreField.getText();
                    String apellido = apellidoField.getText();
                    String telefono = tlfField.getText();
                    String fechanac = fechaNacimientoField.getText();
                    String gmail = correoField.getText();

                    // Obtener contraseñas desde JPasswordField
                    char[] contraseñaArray = contraseniaField.getPassword();
                    String contraseña = new String(contraseñaArray); // Convertir el arreglo de char a String
                    char[] confirmarContraseñaArray = contrasenia2Field.getPassword();
                    String confirmarContraseña = new String(confirmarContraseñaArray); // Convertir el arreglo de char a String

                    // Confirmar si es un nuevo usuario
                    boolean verificador = false;
                    for (Usuario lista : admin.getUsuarios()) {
                        if (gmail.equals(lista.getEmail())) {
                            verificador = true;
                        }
                    }

                    if (verificador) {
                        // Usuario ya registrado
                        JOptionPane.showMessageDialog(null, "Este usuario ya está registrado ", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (contraseña.equals(confirmarContraseña)) {
                        // Verificar edad para GAZTEABONO
                        if (tipoSocio == Usuario.tipoSocio.GAZTEABONO) {
                            String fechaActual = LocalDate.now().toString();
                            if (!admin.Menor26(fechanac, fechaActual)) {
                                JOptionPane.showMessageDialog(null,
                                        "El GAZTEABONO solo está disponible para personas menores de 26 años.",
                                        "Restricción de edad", JOptionPane.WARNING_MESSAGE);
                                return; // Detener el proceso de registro
                            }
                        }

                        // Crear y registrar el nuevo usuario
                        Usuario u = new Usuario(tipoSocio, nombre, apellido, telefono, fechanac, gmail, contraseña);
                        u.setNumeroSocio(admin.getUsuarios().size());
                        admin.añadirUsuarios(u);
                        System.out.println(u);
                        JOptionPane.showMessageDialog(null, "Registrado");
                    } else {
                        JOptionPane.showMessageDialog(null, "La contraseña no coincide");
                    }
				} catch (Exception e2) {
					 JOptionPane.showMessageDialog(null, "Error, verifica que los datos cumplen los requisitos y estan todos los campos llenos ", "Error",
                             JOptionPane.ERROR_MESSAGE);
				}
                

            }
        });

        panel2.add(botonRegistrarse);

        // Agregar panel2 centrado en el panel principal
        panelprincipal.add(panel2, gbc);

        add(panelprincipal);

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaRegistrarse();
    }
}
