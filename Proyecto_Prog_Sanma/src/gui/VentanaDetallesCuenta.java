package gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import domain.Admin;
import domain.Partido;
import domain.Usuario;
import gui.VentanaAdmin.ModeloTabla;

public class VentanaDetallesCuenta extends JFrame{
    private JLabel nombreLbl;
    private JLabel emailLbl;
    private JLabel telefonoLbl;
    private Admin admin;

    public VentanaDetallesCuenta(Usuario usuario) {
    	
        setTitle("Detalles de la Cuenta");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panelDatosUsuario = new JPanel();
        panelDatosUsuario.setLayout(new BorderLayout());
        panelDatosUsuario.setBorder(BorderFactory.createTitledBorder("Información del Usuario"));
        nombreLbl = new JLabel("Nombre: " + usuario.getNombre());
        emailLbl = new JLabel("Email: " + usuario.getEmail());
        telefonoLbl = new JLabel("Teléfono: " + usuario.getTlf());

        panelDatosUsuario.add(nombreLbl);
        panelDatosUsuario.add(emailLbl);
        panelDatosUsuario.add(telefonoLbl);
        

        add(panelDatosUsuario,BorderLayout.NORTH);
        
        JPanel panelHistorialCompras = new JPanel();
        panelHistorialCompras.setLayout(new BorderLayout());
        panelHistorialCompras.setBorder(BorderFactory.createTitledBorder("Información del Usuario"));
        
        List<Usuario> compras = admin.getUsuarios();//
        ModeloTabla modelo = new ModeloTabla(compras);
        JTable tabla = new JTable(modelo);

        tabla.setDefaultRenderer(Object.class, new RenderTablas());
        JScrollPane scrollPane = new JScrollPane(tabla);
        panelHistorialCompras.add(scrollPane, BorderLayout.CENTER);
    }
	class ModeloTablaCompras extends AbstractTableModel {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private final String[] header = { "TipoSocio", "Nombre", "Apellido", "Telefono", 
	                                           "Fecha de Nacimiento", "Correo", "Numero de Socio" };
	    private List<Partido> compras;

	    public ModeloTablaCompras(List<Partido> compras) {
	        this.compras = compras;
	    }

	    public void setCompras(List<Partido> compras) {
			// TODO Auto-generated method stub
	    	this.compras=compras;
		}

		@Override
	    public int getRowCount() {
	        return compras.size();
	    }

	    @Override
	    public int getColumnCount() {
	        return header.length;
	    }

	    @Override
	    public String getColumnName(int column) {
	        return header[column];
	    }

	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
//	        Partido compras = this.compras.get(rowIndex);
//	        switch (columnIndex) {
//	            case 0: return usuario.getTiposocio();
//	            case 1: return usuario.getNombre();
//	            case 2: return usuario.getApellido();
//	            case 3: return usuario.getTlf();
//	            case 4: return usuario.getFechNac();
//	            case 5: return usuario.getEmail();
//	            case 6: return usuario.getNumeroSocio();
//	            default: return null;
//	        }
	    	return null;
	    }

	    @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return false; // Las celdas no son editables
	    }
	}
	

    public static void main(String[] args) {
     
    	
        	 VentanaDetallesCuenta ventana = new VentanaDetallesCuenta(null);
             ventana.setVisible(true);
    }
    }

