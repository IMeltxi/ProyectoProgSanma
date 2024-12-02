package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaGazteAbono extends JFrame {
    
    public VentanaGazteAbono() {
        // Configuración de la ventana
        setTitle("Gazte Abono");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal con borde y márgenes
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        
        // Panel para la información
        JPanel panelInfo = new JPanel();
        JLabel info = new JLabel("El Gazte Abono solo está disponible para personas entre 14 y 26 años. \n \n Las localidades destinadas al Gazte Abonoa están ubicadas en la Tribuna Norte Baja (Grada Popular de Animación).");
        info.setFont(new Font("Arial", Font.BOLD, 16));
        info.setForeground(new Color(70, 70, 70));
        panelInfo.add(info);
        
        //Panel para el DNI 
        JPanel panelDNI = new JPanel();
        //Etiqueta DNI
        JLabel etiquetaDNI = new JLabel("DNI:");
        //DNI Texto
        JTextField TextoDNI = new JTextField(20);
        
        
        
        panelDNI.add(etiquetaDNI);
        panelDNI.add(TextoDNI);
        
        
        // Panel central para la de fecha de nacimiento
        JPanel panelFecha = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        
        // Etiqueta y selector de día
        JLabel diaEtiqueta = new JLabel("Día:");
        diaEtiqueta.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFecha.add(diaEtiqueta, gbc);
        
        JComboBox<Integer> diaValor = new JComboBox<>();
        for (int d = 1; d <= 31; d++) {
            diaValor.addItem(d);
        }
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelFecha.add(diaValor, gbc);
        
        // Etiqueta y selector de mes
        JLabel mesEtiqueta = new JLabel("Mes:");
        mesEtiqueta.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFecha.add(mesEtiqueta, gbc);
        
        JComboBox<Integer> mesValor = new JComboBox<>();
        for (int m = 1; m <= 12; m++) {
            mesValor.addItem(m);
        }
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelFecha.add(mesValor, gbc);
        
        // Etiqueta y selector de año
        JLabel añoEtiqueta = new JLabel("Año:");
        añoEtiqueta.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFecha.add(añoEtiqueta, gbc);
        
        JComboBox<Integer> añoValor = new JComboBox<>();
        for (int a = 1995; a <= 2025; a++) { // Rango ajustado a personas de 14-26 años
            añoValor.addItem(a);
        }
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelFecha.add(añoValor, gbc);
        
        // Botón de envío 
        JButton botonEnviar = new JButton("Participar en el sorteo:");
        botonEnviar.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panelFecha.add(botonEnviar, gbc);
        
        // Añadir los paneles al panel principal
        panelPrincipal.add(panelDNI ,BorderLayout.CENTER);
        panelPrincipal.add(panelInfo, BorderLayout.NORTH);
        panelPrincipal.add(panelFecha, BorderLayout.SOUTH);
        
        // Añadir panel principal al JFrame
        add(panelPrincipal);
        
        // Hacer visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaGazteAbono();
    }
}
