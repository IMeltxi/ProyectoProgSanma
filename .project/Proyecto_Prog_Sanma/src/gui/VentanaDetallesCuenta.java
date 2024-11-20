package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import domain.Usuario;

public class VentanaDetallesCuenta extends JFrame{
    private JLabel nombreLbl;
    private JLabel emailLbl;
    private JLabel telefonoLbl;

    public VentanaDetallesCuenta(Usuario usuario) {
        setTitle("Detalles de la Cuenta");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nombreLbl = new JLabel("Nombre: " + usuario.getNombre());
        emailLbl = new JLabel("Email: " + usuario.getEmail());
        telefonoLbl = new JLabel("Teléfono: " + usuario.getTlf());

        JPanel panel = new JPanel();
        panel.add(nombreLbl);
        panel.add(emailLbl);
        panel.add(telefonoLbl);

        add(panel);
    }


    public static void main(String[] args) {
     
    	
        	 VentanaDetallesCuenta ventana = new VentanaDetallesCuenta(null);
             ventana.setVisible(true);
    }
    }

