package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaConfirmacionPago extends JFrame {
    private JTextArea areaResumen;
    private JTextField campoNombre;
    private JTextField campoNumeroTarjeta;
    private JTextField campoFechaCaducidad;
    private JTextField campoCVV;

    public VentanaConfirmacionPago(String resumenAsientos, double total) {
        setTitle("Confirmación de Pago");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        areaResumen = new JTextArea("Resumen de Asientos:\n" + resumenAsientos + "\n\nTotal: €" + total);
        areaResumen.setEditable(false);
        // panel donde se ve lo siguiente
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new FlowLayout());
        //Nombre Comprador:
        panelFormulario.add(new JLabel("Nombre del Titular:"));
        campoNombre = new JTextField(20);
        panelFormulario.add(campoNombre);
        //Apartado num tarjeta
        panelFormulario.add(new JLabel("Número de Tarjeta:"));
        campoNumeroTarjeta = new JTextField(20);
        panelFormulario.add(campoNumeroTarjeta);
        //PArte fecha tarjeta
        panelFormulario.add(new JLabel("Fecha de Caducidad (MM/YY):"));
        campoFechaCaducidad = new JTextField(5);
        panelFormulario.add(campoFechaCaducidad);

        //Apartado Contraseña tarjwta
        panelFormulario.add(new JLabel("CVV Tarjeta"));
        campoCVV = new JTextField(3);
        panelFormulario.add(campoCVV);

        
        setLayout(new BorderLayout());
        add(areaResumen, BorderLayout.NORTH);
        add(panelFormulario, BorderLayout.CENTER);
        
        JButton botonConfirmarPago = new JButton("Confirmar Pago");
        botonConfirmarPago.addActionListener(e -> {
            String nombre = campoNombre.getText();
            String numeroTarjeta = campoNumeroTarjeta.getText();
            String fechaCaducidad = campoFechaCaducidad.getText();
            String cvv = campoCVV.getText();

            // se comprueba si esran llenos
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de realizar el pago ", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (numeroTarjeta.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de realizar el pago ", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (fechaCaducidad.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de realizar el pago ", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (cvv.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de realizar el pago ", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "Pago realizado y confirmado.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(botonConfirmarPago, BorderLayout.PAGE_END);
    }

    public static void main(String[] args) {
            VentanaConfirmacionPago ventana = new VentanaConfirmacionPago("Asiento-1, Asiento-2", 80.0);
            ventana.setVisible(true);
        };
    }

