package gui;

import domain.Admin;
import domain.Compras;
import domain.Usuario;
import domain.Usuario.tipoSocio;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaDetallesCuenta extends JFrame {
    private JLabel nombreLbl;
    private JLabel emailLbl;
    private JLabel telefonoLbl;
    private Admin admin;

    public VentanaDetallesCuenta(Usuario usuario) {
        setTitle("Detalles de la Cuenta");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        // Panel para mostrar los datos del usuario
        JPanel panelDatosUsuario = new JPanel();
        panelDatosUsuario.setLayout(new BorderLayout());
        panelDatosUsuario.setBorder(BorderFactory.createTitledBorder("Información del Usuario"));
        
        nombreLbl = new JLabel("Nombre: " + usuario.getNombre());
        emailLbl = new JLabel("Email: " + usuario.getEmail());
        telefonoLbl = new JLabel("Teléfono: " + usuario.getTlf());
        
        panelDatosUsuario.add(nombreLbl, BorderLayout.NORTH);
        panelDatosUsuario.add(emailLbl, BorderLayout.CENTER);
        panelDatosUsuario.add(telefonoLbl, BorderLayout.SOUTH);

        add(panelDatosUsuario, BorderLayout.NORTH);
        
        // Obtener las compras del usuario desde el objeto Admin
        List<Compras> comprasUsuario = admin.obtenerComprasPorUsuario(usuario);
        
        // Crear la tabla con las compras filtradas para el usuario
        ModeloTablaCompras modelo = new ModeloTablaCompras(comprasUsuario);
        JTable tabla = new JTable(modelo);
        tabla.setDefaultRenderer(Object.class, new RenderTablas());
        
        JScrollPane scrollPane = new JScrollPane(tabla);
        JPanel panelHistorialCompras = new JPanel();
        panelHistorialCompras.setLayout(new BorderLayout());
        panelHistorialCompras.setBorder(BorderFactory.createTitledBorder("Historial de Compras"));
        panelHistorialCompras.add(scrollPane, BorderLayout.CENTER);
        
        add(panelHistorialCompras, BorderLayout.CENTER);
        
        setVisible(true);
    }

    // Clase ModeloTablaCompras para mostrar los datos en la tabla
    class ModeloTablaCompras extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private final String[] header = { "Partido", "Lado", "Asientos" };
        private List<Compras> compras;

        public ModeloTablaCompras(List<Compras> compras) {
            this.compras = compras;
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
            Compras compra = compras.get(rowIndex);
            switch (columnIndex) {
                case 0: return compra.getPartido();  // Mostrar el nombre del partido
                case 1: return compra.getLado();     // Mostrar el lado
                case 2: return compra.getListaAsientos();
               // case 2: return String.join(", ", compra.getListaAsientos()); // Mostrar los asientos
                default: return null;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false; // Las celdas no son editables
        }
    }

    public static void main(String[] args) {
        // Crear algunos datos de ejemplo
        Usuario usuario = new Usuario(tipoSocio.SOCIO, "Juan", "Perez", "123456789", "1990-01-01", "juan@correo.com", "password");
      
        // Crear Admin y agregar compras (esto debería ser previamente en la aplicación)
        Admin admin = new Admin();
        ArrayList<String> asientos = new ArrayList<>();
        
        // Agregar algunos asientos de ejemplo al ArrayList
        asientos.add("Fila: 1, Asiento: 6");
        asientos.add("Fila: 2, Asiento: 5");
        asientos.add("Fila: 3, Asiento: 4");
        asientos.add("Fila: 5, Asiento: 8");
        
        List<Compras> compras = new ArrayList<>();
        compras.add(new Compras(usuario, "Athletic vs Real Sociedad", "Norte", asientos));
        compras.add(new Compras(usuario, "Real Madrid vs Barcelona", "Sur", asientos));

        admin.setListaCompras(compras);

        // Crear la ventana con las compras de "Juan"
        new VentanaDetallesCuenta(usuario);
    }
}
