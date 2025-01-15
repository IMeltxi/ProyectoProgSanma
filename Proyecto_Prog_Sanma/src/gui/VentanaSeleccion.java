package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaSeleccion extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -475211810647273896L;

	public VentanaSeleccion() {
    	
        setTitle("Seleccionar Acción");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        //mensaje
        JPanel panelMensaje = new JPanel();
        JLabel mensaje = new JLabel("¿Qué te gustaría hacer?");
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        panelMensaje.add(mensaje);
        add(panelMensaje, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        JButton btnEntradas = new JButton("Comprar Entradas");
        JButton btnDetallesCuenta = new JButton("Detalles de la Cuenta");

        // Acción para el botón de "Comprar Entradas"
        btnEntradas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaEntradas ventanaEntradas = new VentanaEntradas(null);
                ventanaEntradas.setVisible(true);
                dispose(); 
            }
        });

        // boton de "DetallesCuenta"

//        btnDetallesCuenta.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                VentanaDetallesCuenta ventanaDetalles = new VentanaDetallesCuenta(); --> Da error (comento pa que pueda compilar)
//                ventanaDetalles.setVisible(true);
//                dispose(); 
//            }
//        });

        panelBotones.add(btnEntradas);
        panelBotones.add(btnDetallesCuenta);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        
   	 VentanaSeleccion ventana = new VentanaSeleccion();
        ventana.setVisible(true);
}
}
