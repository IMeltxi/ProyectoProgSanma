package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import javax.swing.border.Border;
import javax.swing.border.MatteBorder;


public class VentanaAdmin extends JFrame {
	public VentanaAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Ventana Administrador");
		setLayout(new BorderLayout());
		
		JPanel panelSuperior = new JPanel();
		JLabel texto = new JLabel("APARTADO ADMINISTRADOR");
		texto.setFont(new Font("Arial", Font.BOLD, 16));
		
		panelSuperior.setBorder(new MatteBorder(0, 0, 6, 0, Color.BLACK)); //Borde en la parte inferior de grosor 6
		panelSuperior.add(texto);
		
		
		JPanel panelIzq = new JPanel();
		panelIzq.setBackground(Color.RED);
		JPanel panelIzqSup = new JPanel();
		JPanel panelIzqInf = new JPanel();
		
		
		
		/**
		 * Falta combobox
		 */
        
		
		JTable tablaUsuarios  = new JTable();
	
		
		
		JPanel panelDer = new JPanel();
		panelDer.setBackground(Color.YELLOW);
		
		
		add(panelSuperior,BorderLayout.NORTH);
		
		panelIzq.add(panelIzqInf,BorderLayout.SOUTH);
		panelIzq.add(panelIzqSup,BorderLayout.NORTH);
		add(panelIzq,BorderLayout.WEST);
		
		add(panelDer,BorderLayout.EAST);
		setVisible(true);
		
		
	}
	public static void main(String[] args) {
		new VentanaAdmin();
	}
}













