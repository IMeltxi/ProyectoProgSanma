package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaEntradas extends JFrame{
	private JTextField cNombre;
	private JButton bComprar, bCancelar;
	protected JPanel pNorte,pSur,pCentro,pEste,pOeste;
	
	
	private JComboBox<String> cbPartido;
	private JComboBox<String> cbTipoSocio;
	
	
	
	public VentanaEntradas() {
		setTitle("Gestión de Entradas de San Mamés");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		
		
		JPanel panelInfo = new JPanel();
		
		panelInfo.setLayout(new FlowLayout());
		panelInfo.setBorder(BorderFactory.createTitledBorder("Información del Usuario"));
		
		
		JLabel lNombre = new JLabel("Nombre: ");
		cNombre = new JTextField(15);
		panelInfo.add(lNombre);
		panelInfo.add(cNombre);
		
		
		JPanel pPartido = new JPanel();
		pPartido.setLayout(new FlowLayout());
		pPartido.setBorder(BorderFactory.createTitledBorder("Información del Partido"));
		
		JLabel lPartido = new JLabel("Seleccionar Partido: ");
		cbPartido= new JComboBox<>(new String[] 
				{"Athletic vs Madrid", "Athletic vs Barça", "Athletic vs real Suciedad"});
		pPartido.add(lPartido);
		pPartido.add(cbPartido);
		
		
		JPanel pTipoSocio = new JPanel();
		pTipoSocio.setLayout(new FlowLayout());
		pTipoSocio.setBorder(BorderFactory.createTitledBorder("Información del tipo de Socio"));
		
		JLabel lTipoSocio = new JLabel("Selecciona el tipo de Socio:");
		cbTipoSocio = new JComboBox<>(new String[] 
				{"Socio Normal", "Socio VIP", "Socio Jubilado", "Socio Gazte Abono"});
		pTipoSocio.add(lTipoSocio);
		pTipoSocio.add(cbTipoSocio);
		
		
		JPanel pBotones = new JPanel();
		pBotones.setLayout(new FlowLayout());
		pBotones.setBorder(null);
		
		bComprar = new JButton("Comprar Entrada");
		bCancelar = new JButton("Cancelar");
		pBotones.add(bComprar);
		pBotones.add(bCancelar);
		
				
        add(panelInfo, BorderLayout.NORTH);
        add(pPartido, BorderLayout.CENTER);
        add(pTipoSocio, BorderLayout.WEST);
        add(pBotones, BorderLayout.SOUTH);
        
        
        bComprar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = cNombre.getText();
				String partido = (String) cbPartido.getSelectedItem();
				String socio = (String) cbTipoSocio.getSelectedItem();
				
				if (!nombre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Compra Existosa!");
				}else {
					JOptionPane.showMessageDialog(null, "Campos sin rellenar");
				}
				
				
			}
		});

	}
	
	public static void main(String[] args) {
            VentanaEntradas ventana = new VentanaEntradas();
            ventana.setVisible(true);
	};
}
