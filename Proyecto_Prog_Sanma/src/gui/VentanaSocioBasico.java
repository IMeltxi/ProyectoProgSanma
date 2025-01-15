
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import domain.Usuario;
import domain.Admin;

public class VentanaSocioBasico extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4177202831522599512L;
	private Admin admin; // Referencia al administrador para actualizar los usuarios
    private Usuario usuario; // Usuario actual que está iniciando la sesión

    public VentanaSocioBasico(Admin admin, Usuario usuario) {
        this.admin = admin;
        this.usuario = usuario;

        // Configuración básica de la ventana
        setTitle("VIP");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear la etiqueta de información
        JLabel labelInfo = new JLabel("Como socio VIP tendras entradas para todos los partidos que juguemos de visitante asi como un sitio en el palco de SanMames");
        labelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Crear el botón
        JButton botonHacermesocio = new JButton("Hacerme Socio");
        botonHacermesocio.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir funcionalidad al botón
        botonHacermesocio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(usuario.getTiposocio().equals("SOCIO")) {
            		JOptionPane.showMessageDialog(VentanaSocioBasico.this,"Tu ya eres Socio"
                    );            	}
                usuario.setTiposocio(Usuario.tipoSocio.SOCIO); // Cambiar el tipo de socio a VIP
                JOptionPane.showMessageDialog(
                        VentanaSocioBasico.this,
                        "¡Felicidades! Ahora eres un Socio.",
                        "Socio",
                        JOptionPane.INFORMATION_MESSAGE
                );
                guardarCambios(); // Guardar el usuario actualizado
            }
        });

        // Añadir componentes al panel
        panel.add(labelInfo);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado entre componentes
        panel.add(botonHacermesocio);

        // Añadir el panel a la ventana
        add(panel);

        // Mostrar la ventana
        setVisible(true);
    }

    // Método para guardar cambios en el usuario
    private void guardarCambios() {
        for (int i = 0; i < admin.getUsuarios().size(); i++) {
            if (admin.getUsuarios().get(i).getEmail().equals(usuario.getEmail())) {
                admin.getUsuarios().set(i, usuario); // Actualizar el usuario en la lista
                break;
            }
        }
    }

    // Método principal para ejecutar la ventana (de ejemplo)
    public static void main(String[] args) {
  
       // new VentanaVIP(admin, usuario);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
