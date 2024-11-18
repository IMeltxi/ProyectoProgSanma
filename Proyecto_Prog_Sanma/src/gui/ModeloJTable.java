package gui;

import java.util.ArrayList;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Usuario;
import domain.Usuario.tipoSocio;


public class ModeloJTable extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] header = { "","TipoSocio","Nombre", "Apellido", "Telefono", "Fecha de Nacimiento", "Correo", "Numero de Socio"};
	private List<Usuario> listaUsuarios = new ArrayList<>();
	
	public ModeloJTable (List<Usuario> listaUsuarios) {
		this.listaUsuarios=listaUsuarios;
	}	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return header[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaUsuarios.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return header.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: {
			return listaUsuarios.get(rowIndex).getTiposocio();
		}
		case 1: {
			return listaUsuarios.get(rowIndex).getNombre();
		}case 2: {
			return listaUsuarios.get(rowIndex).getApellido();
		}case 3: {
			return listaUsuarios.get(rowIndex).getTlf();
		}case 4: {
			return listaUsuarios.get(rowIndex).getFechNacStr();
		}case 5: {
			return listaUsuarios.get(rowIndex).getEmail();
		}case 6: {
			return listaUsuarios.get(rowIndex).getNumeroSocio();
		}
		
		}
		return null;
	}
}
