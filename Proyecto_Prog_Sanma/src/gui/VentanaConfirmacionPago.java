package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaConfirmacionPago extends JFrame {
    private JTextField campoNombre;
    private JTextField campoNumeroTarjeta;
    private JTextField campoFechaCaducidad;
    private JTextField campoCVV;
    private JTextArea areaResumen;

    public VentanaConfirmacionPago(String resumenAsientos, double total) {
        setTitle("Confirmación de Pago");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra esta ventana, no toda la app
        setLocationRelativeTo(null);

        // Panel principal con layout vertical
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        // Resumen de asientos y total
        areaResumen = new JTextArea("Resumen de Asientos:\n" + resumenAsientos + "\n\nTotal: €" + total);
        areaResumen.setEditable(false);
        areaResumen.setBackground(getBackground()); // Fondo igual al de la ventana
        panelPrincipal.add(new JScrollPane(areaResumen));

        // Formulario de pago
        panelPrincipal.add(new JLabel("Nombre del Titular:"));
        campoNombre = new JTextField(20);
        panelPrincipal.add(campoNombre);

        panelPrincipal.add(new JLabel("Número de Tarjeta:"));
        campoNumeroTarjeta = new JTextField(20);
        panelPrincipal.add(campoNumeroTarjeta);

        panelPrincipal.add(new JLabel("Fecha de Caducidad (MM/YY):"));
        campoFechaCaducidad = new JTextField(5);
        panelPrincipal.add(campoFechaCaducidad);

        panelPrincipal.add(new JLabel("CVV:"));
        campoCVV = new JTextField(3);
        panelPrincipal.add(campoCVV);

        // Botón de confirmación de compra
        JButton btnConfirmar = new JButton("Confirmar Compra");
        btnConfirmar.addActionListener(new ConfirmarPagoListener());
        panelPrincipal.add(btnConfirmar);

        add(panelPrincipal);
    }

    private class ConfirmarPagoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = campoNombre.getText();
            String numeroTarjeta = campoNumeroTarjeta.getText();
            String fechaCaducidad = campoFechaCaducidad.getText();
            String cvv = campoCVV.getText();

            // Validación simple de campos
            if (nombre.isEmpty() || numeroTarjeta.isEmpty() || fechaCaducidad.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Mensaje de confirmación
            JOptionPane.showMessageDialog(null, "Compra realizada con éxito. ¡Disfruta del partido!", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            
            // Aquí podrías añadir lógica para almacenar la compra en el historial del usuario
            dispose(); // Cierra la ventana de confirmación de pago
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaConfirmacionPago ventana = new VentanaConfirmacionPago("Asiento (1,1), Asiento (1,2)", 80.0);
            ventana.setVisible(true);
        });
    }
}
