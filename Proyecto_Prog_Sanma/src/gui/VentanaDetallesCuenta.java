package gui;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaDetallesCuenta extends JFrame {

    public VentanaDetallesCuenta() {
    	
        setTitle("Detalles de la Cuenta");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para mostrar los detalles de la cuenta
        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new GridLayout(5, 1, 10, 10)); 

        // detalles del usuario
        JLabel lblNombre = new JLabel("Nombre: Usuario Ejemplo");
        JLabel lblCorreo = new JLabel("Correo: usuario@ejemplo.com");
        JLabel lblMembresia = new JLabel("Membresía: Premium");
        JLabel lblFechaRegistro = new JLabel("Fecha de Registro: 01/01/2023");
        JLabel lblUltimaActividad = new JLabel("Última Actividad: 10/11/2024");

        //labels
        panelDetalles.add(lblNombre);
        panelDetalles.add(lblCorreo);
        panelDetalles.add(lblMembresia);
        panelDetalles.add(lblFechaRegistro);
        panelDetalles.add(lblUltimaActividad);

        add(panelDetalles, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
     
        	 VentanaDetallesCuenta ventana = new VentanaDetallesCuenta();
             ventana.setVisible(true);
    }
    }

