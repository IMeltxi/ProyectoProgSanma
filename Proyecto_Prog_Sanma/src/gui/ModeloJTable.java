package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Usuario;

public class ModeloJTable extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] headers = { "Nombre", "Apellido", "Telefono", "Fecha de Nacimiento", "Correo", "Numero de Socio"};
	List<Usuario> listaUsuarios = new ArrayList<>();
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
	return null;
	}

}
