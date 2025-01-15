package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaHistorialCompras extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3149893019431349781L;
	private JTable tablaHistorial;
    private JScrollPane scrollPane;

    public VentanaHistorialCompras(ArrayList<String[]> historialCompras) {
        setTitle("Historial de Compras");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

       
        String[] columnas = {"Fecha del Partido", "Rival", "Asiento(s)", "Precio Total"};
        String[][] datos = new String[historialCompras.size()][4];
        
        for (int i = 0; i < historialCompras.size(); i++) {
            datos[i] = historialCompras.get(i);
        }

        tablaHistorial = new JTable(datos, columnas);
        scrollPane = new JScrollPane(tablaHistorial);


        add(scrollPane, BorderLayout.CENTER);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        // Datos de ejemplo para el historial de compras
        ArrayList<String[]> historialCompras = new ArrayList<>();
        historialCompras.add(new String[]{"2024-11-01", "Real Madrid", "A12, A13", "80€"});
        historialCompras.add(new String[]{"2024-10-15", "FC Barcelona", "B5, B6", "90€"});
        
        // Crear y mostrar la ventana
        new VentanaHistorialCompras(historialCompras);
    }
}
