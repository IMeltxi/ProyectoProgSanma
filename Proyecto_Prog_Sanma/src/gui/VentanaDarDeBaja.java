package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domain.Admin;

public class VentanaDarDeBaja extends JFrame {
    private Admin admin;

    public VentanaDarDeBaja(Admin admin) {
        this.admin = admin;

        // Configuración básica
        setTitle("Dar de Baja");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel para datos del socio
        JPanel panelDatosSocio = new JPanel();

        // Número de socio
        JLabel etiquetaNumeroSocio = new JLabel("Número de socio:");
        JTextField TextoNumeroSocio = new JTextField(20);

        // Gmail del socio
        JLabel etiquetaGmailSocio = new JLabel("Gmail del socio:");
        JTextField TextoGmailSocio = new JTextField(20);

        // Añadimos los elementos al panel
        panelDatosSocio.add(etiquetaNumeroSocio);
        panelDatosSocio.add(TextoNumeroSocio);
        panelDatosSocio.add(etiquetaGmailSocio);
        panelDatosSocio.add(TextoGmailSocio);

        // Panel de información sobre el motivo de la baja
        JPanel infobajaPanel = new JPanel();

        // Información
        JLabel etiquetainformacion = new JLabel("Motivo de Baja (opcional):");
        JTextField informacionTexto = new JTextField(40);

        // Añadimos los elementos al panel
        infobajaPanel.add(etiquetainformacion);
        infobajaPanel.add(informacionTexto);

        // Panel para verificar la identidad del usuario con contraseña
        JPanel PanelUsuario = new JPanel();

        // Contraseña del usuario
        JLabel ContraseñaEtiqueta = new JLabel("Contraseña:");
        JPasswordField ContraseñaTexto = new JPasswordField(10);

        // Botón para darse de baja
        JButton DardeBajaBoton = new JButton("Darse de Baja");

        // Añadimos los elementos al panel
        PanelUsuario.add(ContraseñaEtiqueta);
        PanelUsuario.add(ContraseñaTexto);
        PanelUsuario.add(DardeBajaBoton);

        // Añadimos los paneles al panel principal
        panelPrincipal.add(panelDatosSocio, BorderLayout.NORTH);
        panelPrincipal.add(infobajaPanel, BorderLayout.CENTER);
        panelPrincipal.add(PanelUsuario, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);

        // Acción del botón para verificar usuario y contraseña
        DardeBajaBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener el número de socio
                    int numeroSocio = Integer.parseInt(TextoNumeroSocio.getText());
                    String contrasena = new String(ContraseñaTexto.getPassword()); // Convertimos la contraseña a String
                    String gmail = TextoGmailSocio.getText(); // Obtener el correo (Gmail)

                    // Verificar si el usuario existe y si la contraseña es correcta
                    boolean eliminado = admin.eliminarUsuario(numeroSocio, gmail);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el usuario o la contraseña es incorrecta.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El número de socio debe ser numérico.");
                }
            }
        });
    }

    public static void main(String[] args) {
        Admin admin = new Admin();
        new VentanaDarDeBaja(admin);
    }
}
