package gui;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import domain.Usuario;
import domain.Usuario.tipoSocio;


public class VentanaAdmin extends JFrame {
	public VentanaAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Ventana Administrador");
		setLayout(new BorderLayout());
		
		//Panel con el texto "APARTADO ADMINISTRADOR"
		JPanel panelSuperior = new JPanel();
		JLabel texto = new JLabel("APARTADO ADMINISTRADOR");
		texto.setFont(new Font("Arial", Font.BOLD, 16));
		panelSuperior.setBorder(new MatteBorder(0, 0, 6, 0, Color.BLACK)); //Borde en la parte inferior de grosor 6
		panelSuperior.add(texto);
		add(panelSuperior,BorderLayout.CENTER);
		
		
		//Panel con el contenido de la ventana
		JPanel panelContenido = new JPanel();
		add(panelContenido,BorderLayout.CENTER);
		
		//Panel izquierdo del contenido
		JPanel panelIzq = new JPanel();
		panelIzq.setBackground(Color.RED);
		panelContenido.add(panelIzq,BorderLayout.WEST);
		
		//Panel superior del panel izquierdo
		JPanel panelIzqSup = new JPanel();
		String[] tipoSocios = {"Cualquiera", "SocioMensual", "Socio", "VIP","GAZTEABONO"};
		ComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(tipoSocios);
        JComboBox<String> cBTipoSocio = new JComboBox<>(comboBoxModel);
		panelIzqSup.add(cBTipoSocio);
		
		panelIzq.add(panelIzqSup);
		
		//Panel con el contenido principal del panel izquierdo
		JPanel panelIzqCont = new JPanel();
		
		/**
		 * Usuarios de ejemplo para ver el funcionamiento de la tabla
		 */
		String[] header = { "","TipoSocio","Nombre", "Apellido", "Telefono", "Fecha de Nacimiento", "Correo", "Numero de Socio"};
		List<domain.Usuario> usuarios = new ArrayList<>();
		for(int i=0;i==5;i++){
			usuarios.add(new Usuario(tipoSocio.GAZTEABONO, "nombre"+i, "apellido"+i, "telf"+i, null, "correo"+i, "contra"+i, i));
		}
		ModeloJTable modeloTabla = new ModeloJTable(usuarios);
		//JTable tablaUsuarios = new JTable();
		JTable tablaUsuarios  = new JTable(modeloTabla);
		tablaUsuarios.setDefaultRenderer(Object.class,  new RenderJTable());
		panelIzqCont.add(new JScrollPane(tablaUsuarios),BorderLayout.CENTER);
		
		panelIzqCont.add(tablaUsuarios);
		panelIzq.add(panelIzqCont);
		
		
		
		
		//Panel inferior del panel izquierdo
		JPanel panelIzqInf = new JPanel();
		JButton botonEliminarUsuario = new JButton("Eliminar Usuario");
		JButton botonAñadirUsuario = new JButton("Añadir usuario");
		panelIzqInf.add(botonAñadirUsuario);
		panelIzqInf.add(botonEliminarUsuario);
		
		panelIzq.add(panelIzqInf);
		
		
		
		
		//Apartado derecho del contenido
		JPanel panelDer = new JPanel();
		panelDer.setBackground(Color.YELLOW);
		
		
		
		
		
		
				
	
	
		
		setVisible(true);
		
		
	}
	public static void main(String[] args) {
		new VentanaAdmin();
	}
}