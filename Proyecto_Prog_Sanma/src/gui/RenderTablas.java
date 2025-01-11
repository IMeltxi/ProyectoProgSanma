package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderTablas extends DefaultTableCellRenderer {
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

