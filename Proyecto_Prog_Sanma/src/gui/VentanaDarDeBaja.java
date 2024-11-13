package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Admin;

public class VentanaDarDeBaja extends JFrame {

		public VentanaDarDeBaja(){
			//Configuracion basica
			setTitle("Dar de Baja");
	        setExtendedState(JFrame.MAXIMIZED_BOTH);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			
			//Panel principal
			JPanel panelPrincipal = new JPanel(new BorderLayout());
			
			//Panel para datos del socio
			JPanel panelDatosSocio = new JPanel();
				//DNI
			JLabel etiquetaDNI= new JLabel("DNI:");
			JTextField TextoDNI = new JTextField(20);	
				//Numero de socio
			JLabel etiquetaNumeroSocio= new JLabel("Numero de socio:");
			JTextField TextoNumeroSocio = new JTextField(20);
				//Nombre del socio
			JLabel etiquetaNombreSocio= new JLabel("Nombre del socio:");
			JTextField TextoNombreSocio = new JTextField(20);
			//añadimos los elementos al panel
			panelDatosSocio.add(etiquetaDNI);
			panelDatosSocio.add(TextoDNI);
			panelDatosSocio.add(etiquetaNumeroSocio);
			panelDatosSocio.add(TextoNumeroSocio);
			panelDatosSocio.add(etiquetaNombreSocio);
			panelDatosSocio.add(TextoNombreSocio);

			
			//panel informacion motivo de la baja
			JPanel infobajaPanel = new JPanel();
				//Informacion
			JLabel etiquetainformacion = new JLabel("Motivo de Baja(opcional):");
			JTextField informacionTexto = new JTextField(40);
			
			//añadimos los elementos al panel
			infobajaPanel.add(etiquetainformacion);
			infobajaPanel.add(informacionTexto);
			
			//Panel usuario y contraseña para verificar que la identidad de de darse de baja
			JPanel PanelUsuario = new JPanel();
				//nombre de usuario
			JLabel UsuarioEtiqueta = new JLabel("Nombre de Usuario:");
			JTextField UsuarioTexto = new JTextField(10);
				//contraseña usuario
			JLabel ContraseñaEtiqueta = new JLabel("Contraseña");
			JTextField ContraseñaTexto = new JTextField(10);
			//Boton darse de baja
			JButton DardeBajaBoton = new JButton("Darse de Baja");
			
			//añadimos los elementos al panel
			PanelUsuario.add(UsuarioEtiqueta);
			PanelUsuario.add(UsuarioTexto);
			PanelUsuario.add(ContraseñaEtiqueta);
			PanelUsuario.add(ContraseñaTexto);
			PanelUsuario.add(DardeBajaBoton);
			
			
			//añadimos los paneles al panel principal
			panelPrincipal.add(panelDatosSocio, BorderLayout.NORTH);
			panelPrincipal.add(infobajaPanel, BorderLayout.CENTER);
			panelPrincipal.add(PanelUsuario, BorderLayout.SOUTH);

			add(panelPrincipal);
			setVisible(true);
			
	        // Acción del botón para verificar usuario y contraseña
			DardeBajaBoton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});

			}
		
	    public static void main(String[] args) {
	        new VentanaDarDeBaja();
	    }
}
