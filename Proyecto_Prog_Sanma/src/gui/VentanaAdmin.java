package gui;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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

import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import domain.Admin;
import domain.Usuario;
import domain.Usuario.tipoSocio;


public class VentanaAdmin extends JFrame {
	private Admin admin;
	private JTable tabla;
	
	public VentanaAdmin(Admin admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Ventana Administrador");
        admin.cargarUsuarios(); // Cargar usuarios desde el fichero
		setLayout(new BorderLayout());
		
		//Panel con el texto "APARTADO ADMINISTRADOR"
		JPanel panelSuperior = new JPanel();
		JLabel texto = new JLabel("APARTADO ADMINISTRADOR");
		texto.setFont(new Font("Arial", Font.BOLD, 16));
		panelSuperior.setBorder(new MatteBorder(0, 0, 6, 0, Color.BLACK)); //Borde en la parte inferior de grosor 6
		panelSuperior.add(texto);
		add(panelSuperior,BorderLayout.NORTH);
		
		// Panel contenido
		JPanel panelCont = new JPanel(new BorderLayout()); // Usar BorderLayout para organizar los subpaneles
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
		            // Mostrar todos los usuarios
		            usuariosFiltrados = admin.getUsuarios();
		        } else {
		            // Filtrar usuarios por tipo de socio
		            usuariosFiltrados = admin.visualizarUsuariosPorTipo(tipoSocio.valueOf(seleccion));
		        }

		        // Actualizar el modelo de la tabla con los usuarios filtrados
		        ((ModeloTabla) tabla.getModel()).setUsuarios(usuariosFiltrados);
		        ((ModeloTabla) tabla.getModel()).fireTableDataChanged(); // Notificar cambios al modelo
		    }
		});
		
		panelSup.add(cBTipoSocio);
		panelCont.add(panelSup, BorderLayout.NORTH); // Colocar en la parte superior

		// Panel inferior del contenido (para los botones)
		JPanel panelInf = new JPanel();
		JButton botonAñadir = new JButton("Añadir socios");
		JButton botonEliminar = new JButton("Eliminar socios");
		botonAñadir.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("botonAñadir pulsado");
		    }
		});
		botonEliminar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Obtener la fila seleccionada
		        int filaSeleccionada = tabla.getSelectedRow();
		        
		        if (filaSeleccionada == -1) {
		            // No se seleccionó ninguna fila
		            JOptionPane.showMessageDialog(VentanaAdmin.this, 
		                                          "Por favor, selecciona un usuario para eliminar.", 
		                                          "Error", 
		                                          JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        // Obtener el modelo de la tabla
		        ModeloTabla modelo = (ModeloTabla) tabla.getModel();
		        //Obtener nombre y apellido
		        String nombreApellido = (String) modelo.getValueAt(filaSeleccionada, 1)+ " " + modelo.getValueAt(filaSeleccionada, 2);
		        // Confirmación antes de eliminar
		        int confirmacion = JOptionPane.showConfirmDialog(
		                VentanaAdmin.this,
		                "¿Estás seguro de que quieres eliminar a "+nombreApellido+ " ?",
		                "Confirmar eliminación",
		                JOptionPane.YES_NO_OPTION);

		        if (confirmacion != JOptionPane.YES_OPTION) {
		            JOptionPane.showMessageDialog(
		                    VentanaAdmin.this, 
		                    "Operación cancelada.", 
		                    "Información", 
		                    JOptionPane.INFORMATION_MESSAGE);
		            return; // Salir si el usuario no confirma
		        }
		        
		        

		        // Obtener el número de socio del usuario seleccionado
		        int numeroSocio = (int) modelo.getValueAt(filaSeleccionada, 6);
		        
		        // Eliminar el usuario del administrador
		        boolean eliminado = admin.eliminarUsuarioAdmin(numeroSocio);

		        if (eliminado) {
		            // Actualizar la lista de usuarios en el modelo de la tabla
		            modelo.setUsuarios(admin.getUsuarios());
		            modelo.fireTableDataChanged(); // Notificar cambios en los datos

		            JOptionPane.showMessageDialog(VentanaAdmin.this, 
		                                          nombreApellido+" eliminado correctamente.", 
		                                          "Éxito", 
		                                          JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(VentanaAdmin.this, 
		                                          "No se pudo eliminar el usuario. Inténtalo de nuevo.", 
		                                          "Error", 
		                                          JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		panelInf.add(botonAñadir);
		panelInf.add(botonEliminar);
		panelCont.add(panelInf, BorderLayout.SOUTH); // Colocar en la parte inferior

		// Panel central del contenido (para la tabla)
		JPanel panelCent = new JPanel(new BorderLayout());
		
		
		List<Usuario> usuarios = admin.getUsuarios();
//		usuarios.add(new Usuario(Usuario.tipoSocio.GAZTEABONO, "Juan", "Pérez", "123456789", "1990-01-01", "juan@gmail.com", "1234", 1));
//		usuarios.add(new Usuario(Usuario.tipoSocio.SOCIO, "Ana", "Gómez", "987654321", "1985-02-02", "ana@gmail.com", "abcd", 2));
//		usuarios.add(new Usuario(Usuario.tipoSocio.VIP, "Luis", "Martínez", "456123789", "1975-03-03", "luis@gmail.com", "5678", 3));

		ModeloTabla modelo = new ModeloTabla(usuarios);
		tabla = new JTable(modelo);

		// Aplicar el render personalizado
		tabla.setDefaultRenderer(Object.class, new RenderJTable());
		JScrollPane scrollPane = new JScrollPane(tabla);
		panelCent.add(scrollPane, BorderLayout.CENTER);
		panelCont.add(panelCent, BorderLayout.CENTER); // Colocar en el centro

        setVisible(true);
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

	


