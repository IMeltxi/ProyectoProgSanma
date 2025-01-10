package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import domain.Usuario;
import gui.VentanaEntradas.PartidoCombo;
import domain.Admin;

public class VentanaVIP extends JFrame {
    private Admin admin; // Referencia al administrador para actualizar los usuarios
    private Usuario usuario; // Usuario actual que está iniciando la sesión

    public VentanaVIP(Admin admin, Usuario usuario) {
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
        JButton botonHacermeVIP = new JButton("Hacerme Socio VIP");
        botonHacermeVIP.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir funcionalidad al botón
        botonHacermeVIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario.setTiposocio(Usuario.tipoSocio.VIP); // Cambiar el tipo de socio a VIP
                JOptionPane.showMessageDialog(
                        VentanaVIP.this,
                        "¡Felicidades! Ahora eres un Socio VIP.",
                        "Socio VIP",
                        JOptionPane.INFORMATION_MESSAGE
                );
                guardarCambios(); // Guardar el usuario actualizado
            }
        });

        // Añadir componentes al panel
        panel.add(labelInfo);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado entre componentes
        panel.add(botonHacermeVIP);

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
        Admin admin = new Admin();
        Usuario usuario = new Usuario(
                Usuario.tipoSocio.SOCIO, 
                "Juan", 
                "Perez", 
                "123456789", 
                "1985-04-15", 
                "juan.perez@example.com", 
                "password123", 
                1
        );
        admin.añadirUsuarios(usuario);
        new VentanaVIP(admin, usuario);
    }
    
    /**
     * 
     * 
     * 
     *   PartidoCombo[] partidos = {
     *   
     *   for (Partidos p:listaPartidos){
     * 		new PartidoCombo(p.getNombrePartido); Como se llame lo de que salga Ath vs ..
     * 		partido[] = p.getNombrePartido.split("_"); Separar el Ath_vs_RM por ejemplo en Ath|vs|RM
     * 				redimensionarIcono("Imagenes/ImagenesEquipos/"+partido[0]+".png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/"+partido[2]+".png"),
          }
          
          Y esto borrarlo:
          
        	new PartidoCombo("Seleccione un partido", null, null),
            new PartidoCombo("Athletic vs Real Madrid",
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/RealMadrid.png")),
            new PartidoCombo("Athletic vs Barcelona", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Barça.png")),
            new PartidoCombo("Athletic vs Real Sociedad", 
            		redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/RealSociedad.png")),
            new PartidoCombo("Athletic vs Alaves", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Alaves.png")),
            new PartidoCombo("Athletic vs Atletico de Madrid", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AtleticoMadrid.png")),
            new PartidoCombo("Athletic vs Betis", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Betis.png")),
            new PartidoCombo("Athletic vs Celta", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Celta.png")),
            new PartidoCombo("Athletic vs RCD Espanyol", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Espanyol.png")),
            new PartidoCombo("Athletic vs Getafe", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Getafe.png")),
            new PartidoCombo("Athletic vs Girona", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Girona.png")),
            new PartidoCombo("Athletic vs UD Las Palmas", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/LasPalmas.png")),
            new PartidoCombo("Athletic vs Leganes", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Leganes.png")),
            new PartidoCombo("Athletic vs RCD Mallorca", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Mallorca.png")),
            new PartidoCombo("Athletic vs Osasuna", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Osasuna.png")),
            new PartidoCombo("Athletic vs Rayo Vallecano", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/RayoVallecano.png")),
            new PartidoCombo("Athletic vs Sevilla", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Sevilla.png")),
            new PartidoCombo("Athletic vs Valencia CF", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Valencia.png")),
            new PartidoCombo("Athletic vs Valladolid", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Valladolid.png")),
            new PartidoCombo("Athletic vs Villareal", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Villareal.png")),
        };
     * 
     * 
     * 
     * */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
