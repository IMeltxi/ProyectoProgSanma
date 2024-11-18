package gui;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
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
		
		JPanel panelSuperior = new JPanel();
		JLabel texto = new JLabel("APARTADO ADMINISTRADOR");
		texto.setFont(new Font("Arial", Font.BOLD, 16));
		
		panelSuperior.setBorder(new MatteBorder(0, 0, 6, 0, Color.BLACK)); //Borde en la parte inferior de grosor 6
		panelSuperior.add(texto);
		
		JPanel panelContenido = new JPanel();
		
		
		JPanel panelIzq = new JPanel();
		panelIzq.setBackground(Color.RED);
		JPanel panelIzqSup = new JPanel();
		JPanel panelIzqCont = new JPanel();
		JPanel panelIzqInf = new JPanel();
		
		
		String[] tipoSocios = {"Cualquiera", "SocioMensual", "Socio", "VIP","GAZTEABONO"};
		ComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(tipoSocios);
        JComboBox<String> cBTipoSocio = new JComboBox<>(comboBoxModel);
		panelIzqSup.add(cBTipoSocio);
        
		
		 
		/**
		 * Usuarios de ejemplo para ver el funcionamiento de la tabla
		 */
		String[] header = { "","TipoSocio","Nombre", "Apellido", "Telefono", "Fecha de Nacimiento", "Correo", "Numero de Socio"};
		List<domain.Usuario> usuarios = new ArrayList<>();
		for(int i=0;i==5;i++){
			usuarios.add(new Usuario(tipoSocio.GAZTEABONO, "nombre"+i, "apellido"+i, "telf"+i, "fnato"+i, "correo"+i, "contra"+i, i));
		}
		//ModeloJTable modeloTabla = new ModeloJTable(usuarios);
		JTable tablaUsuarios = new JTable();//JTable tablaUsuarios  = new JTable(modeloTabla);
		tablaUsuarios.setDefaultRenderer(Object.class,  new RenderJTable());
		panelIzqInf.add(new JScrollPane(tablaUsuarios),BorderLayout.CENTER);
		/**
		 * Añadir a la tabla un JScrollPane (barra de desplazamiento) para poder visualizarla entera
		 */
		panelIzqInf.add(tablaUsuarios);
		JPanel panelDer = new JPanel();
		panelDer.setBackground(Color.YELLOW);
		
		//Panel Superior
		add(panelSuperior,BorderLayout.NORTH);
		
		
		//panelDer.add();
		
		//Apartado contenido de la izquierda (tablausuarios)
		panelIzq.add(panelIzqSup,BorderLayout.NORTH); //Combo box de filtrado para la tabla
		panelIzq.add(panelIzqCont,BorderLayout.CENTER);//Tabla usuarios
		panelIzq.add(panelIzqInf,BorderLayout.SOUTH);//Botones de accion
		panelContenido.add(panelIzq,BorderLayout.WEST);
		add(panelContenido,BorderLayout.CENTER);
		
		
		
		
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













