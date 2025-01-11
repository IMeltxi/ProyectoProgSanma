package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import domain.Admin;
import domain.Usuario;
import domain.Usuario.tipoSocio;
/**
 * El codigo necesitado para realizar el panel desplegable al añadir un usuario ha sido 
 * realizado con ChatGPT-4
 */
public class VentanaAdmin extends JFrame {
    private Admin admin;
    private JTable tabla;
    private JPanel panelLateral; // Panel para añadir socios
    private JTextField txtNombre, txtApellido, txtTelefono, txtFechaNacimiento, txtCorreo,txtContrasenia;
    private JComboBox<String> cbTipoSocio;

    public VentanaAdmin(Admin admin) {
        this.admin = admin;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Ventana Administrador");
        admin.cargarUsuarios(); // Cargar usuarios desde el fichero
        setLayout(new BorderLayout());

        // Panel con el texto "APARTADO ADMINISTRADOR"
        JPanel panelSuperior = new JPanel();
        JLabel texto = new JLabel("APARTADO ADMINISTRADOR");
        texto.setFont(new Font("Arial", Font.BOLD, 16));
        panelSuperior.setBorder(new MatteBorder(0, 0, 6, 0, Color.BLACK)); // Borde en la parte inferior de grosor 6
        panelSuperior.add(texto);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel contenido central
        JPanel panelCont = new JPanel(new BorderLayout());
        add(panelCont, BorderLayout.CENTER);

        // Panel superior del contenido (para el ComboBox)
        JPanel panelSup = new JPanel();
        String[] tipoSocios = {"Cualquiera", "SocioMensual", "Socio", "VIP", "GAZTEABONO"};
        ComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(tipoSocios);
        JComboBox<String> cBTipoSocio = new JComboBox<>(comboBoxModel);

        // Añadir un ActionListener para filtrar los usuarios al cambiar la selección
        cBTipoSocio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) cBTipoSocio.getSelectedItem();
                seleccion = seleccion.toUpperCase();
                List<Usuario> usuariosFiltrados;

                if ("CUALQUIERA".equals(seleccion)) {
                    usuariosFiltrados = admin.getUsuarios();
                } else {
                    usuariosFiltrados = admin.visualizarUsuariosPorTipo(tipoSocio.valueOf(seleccion));
                }

                ((ModeloTabla) tabla.getModel()).setUsuarios(usuariosFiltrados);
                ((ModeloTabla) tabla.getModel()).fireTableDataChanged();
            }
        });

        panelSup.add(cBTipoSocio);
        panelCont.add(panelSup, BorderLayout.NORTH);

        // Panel inferior del contenido (para los botones)
        JPanel panelInf = new JPanel();
        JButton botonAñadir = new JButton("Añadir socios");
        JButton botonEliminar = new JButton("Eliminar socios");

        botonAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelLateral();
            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarSocioSeleccionado();
            }
        });

        panelInf.add(botonAñadir);
        panelInf.add(botonEliminar);
        panelCont.add(panelInf, BorderLayout.SOUTH);

        // Panel central del contenido (para la tabla)
        JPanel panelCent = new JPanel(new BorderLayout());
        List<Usuario> usuarios = admin.getUsuarios();
        ModeloTabla modelo = new ModeloTabla(usuarios);
        tabla = new JTable(modelo);

        tabla.setDefaultRenderer(Object.class, new RenderJTable());
        JScrollPane scrollPane = new JScrollPane(tabla);
        panelCent.add(scrollPane, BorderLayout.CENTER);
        panelCont.add(panelCent, BorderLayout.CENTER);

        // Configurar el panel lateral
        configurarPanelLateral();
        add(panelLateral, BorderLayout.EAST);

        setVisible(true);
    }

    private void configurarPanelLateral() {
        panelLateral = new JPanel();
        panelLateral.setLayout(new GridLayout(8, 2, 10, 10));
        panelLateral.setPreferredSize(new Dimension(300, 0));
        panelLateral.setVisible(false);

        panelLateral.add(new JLabel("Tipo de Socio:"));
        cbTipoSocio = new JComboBox<>(new String[]{"SocioMensual", "Socio", "VIP", "GAZTEABONO"});
        panelLateral.add(cbTipoSocio);

        panelLateral.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelLateral.add(txtNombre);

        panelLateral.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panelLateral.add(txtApellido);

        panelLateral.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelLateral.add(txtTelefono);

        panelLateral.add(new JLabel("Fecha de Nacimiento (YYYY-MM-DD):"));
        txtFechaNacimiento = new JTextField();
        panelLateral.add(txtFechaNacimiento);

        panelLateral.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelLateral.add(txtCorreo);
        
        panelLateral.add(new JLabel("Contraseña:"));
        txtContrasenia = new JTextField();
        panelLateral.add(txtContrasenia);
        

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                añadirSocio();
            }
        });
        panelLateral.add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocultarPanelLateral();
            }
        });
        panelLateral.add(btnCancelar);
    }

    private void mostrarPanelLateral() {
        panelLateral.setVisible(true);
        revalidate();
        repaint();
    }

    private void ocultarPanelLateral() {
        panelLateral.setVisible(false);
        revalidate();
        repaint();
    }

    private void añadirSocio() {
        try {
            String tipoSocioStr = (String) cbTipoSocio.getSelectedItem();
            Usuario.tipoSocio tipoSocio = Usuario.tipoSocio.valueOf(tipoSocioStr.toUpperCase());
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String fechaNacimiento = txtFechaNacimiento.getText().trim();
            String correo = txtCorreo.getText().trim();

            if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || fechaNacimiento.isEmpty() || correo.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben estar llenos.");
            }
            for(Usuario u:admin.getUsuarios()) {
            	if(!(u.getEmail().equals(correo))) {
            		Usuario nuevoSocio = new Usuario(tipoSocio, nombre, apellido, telefono, fechaNacimiento, correo, "");
                    nuevoSocio.setNumeroSocio(admin.getUsuarios().size());
                    admin.añadirUsuarios(nuevoSocio);
            	}else
            		JOptionPane.showMessageDialog(this, "Este email ya esta asociado a un socio", "Error", JOptionPane.ERROR_MESSAGE);
            	
            }
            

            ((ModeloTabla) tabla.getModel()).setUsuarios(admin.getUsuarios());
            ((ModeloTabla) tabla.getModel()).fireTableDataChanged();

            JOptionPane.showMessageDialog(this, "Socio añadido correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            ocultarPanelLateral();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al añadir socio", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarSocioSeleccionado() {
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ModeloTabla modelo = (ModeloTabla) tabla.getModel();
        Usuario usuarioSeleccionado = modelo.usuarios.get(filaSeleccionada);

        String nombreApellido = usuarioSeleccionado.getNombre() + " " + usuarioSeleccionado.getApellido();
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar a " + nombreApellido + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        boolean eliminado = admin.eliminarUsuarioAdmin(usuarioSeleccionado.getNumeroSocio());

        if (eliminado) {
            modelo.usuarios.remove(filaSeleccionada);
            modelo.fireTableDataChanged();

            JOptionPane.showMessageDialog(this, nombreApellido + " eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el usuario. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	
//METODOS PARA LA TABLA
	//MODELO DE LA TABLA
	class ModeloTabla extends AbstractTableModel {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private final String[] header = { "TipoSocio", "Nombre", "Apellido", "Telefono", 
	                                           "Fecha de Nacimiento", "Correo", "Numero de Socio" };
	    private List<Usuario> usuarios;

	    public ModeloTabla(List<Usuario> usuarios) {
	        this.usuarios = usuarios;
	    }

	    public void setUsuarios(List<Usuario> usuarios) {
			// TODO Auto-generated method stub
	    	this.usuarios=usuarios;
		}

		@Override
	    public int getRowCount() {
	        return usuarios.size();
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
	        Usuario usuario = usuarios.get(rowIndex);
	        switch (columnIndex) {
	            case 0: return usuario.getTiposocio();
	            case 1: return usuario.getNombre();
	            case 2: return usuario.getApellido();
	            case 3: return usuario.getTlf();
	            case 4: return usuario.getFechNac();
	            case 5: return usuario.getEmail();
	            case 6: return usuario.getNumeroSocio();
	            default: return null;
	        }
	    }

	    @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return false; // Las celdas no son editables
	    }
	}
	//RENDERER DE LA TABLA
	private class RenderJTable extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	            int row, int column) {
	        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        
	        // Cambiar el color de fondo en líneas pares a rojo
	        if (row % 2 == 0) cellComponent.setBackground(Color.red);
	            else cellComponent.setBackground(Color.white);
	        
	        return cellComponent;
	    }
	}

	public static void main(String[] args) {
		Admin admin = new Admin();
		new VentanaAdmin(admin);
	}
}