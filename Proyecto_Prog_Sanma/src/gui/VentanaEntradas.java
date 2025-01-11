package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import domain.Entradas;
import javax.swing.JList;
import javax.swing.Icon;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import domain.Admin;

public class VentanaEntradas extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField cNombre;
    private JButton bComprar, bCancelar;
    private JComboBox<PartidoCombo> cbPartido;
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
        
        
        PartidoCombo[] partidos = {
        	new PartidoCombo("Seleccione un partido", null, null),
        	new PartidoCombo("Athletic vs Getafe CF", 
        		redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        		redimensionarIcono("Imagenes/ImagenesEquipos/Getafe.png")),
        	new PartidoCombo("Athletic vs Valencia", 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/Valencia.png")),
        	new PartidoCombo("Athletic vs Atletico de Madrid", 
                redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                redimensionarIcono("Imagenes/ImagenesEquipos/AtleticoMadrid.png")),
        	new PartidoCombo("Athletic vs RC Celta de Vigo", 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/Celta.png")),
        	new PartidoCombo("Athletic vs Sevilla FC", 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/Sevilla.png")),
        	new PartidoCombo("Athletic vs RCD Espanyol", 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/Espanyol.png")),
        	new PartidoCombo("Athletic vs Betis", 
                redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                redimensionarIcono("Imagenes/ImagenesEquipos/Betis.png")),
        	new PartidoCombo("Athletic vs Real Sociedad", 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/RealSociedad.png")),
        	new PartidoCombo("Athletic vs Real Madrid", 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/RealMadrid.png")),
        	new PartidoCombo("Athletic vs Villareal", 
                redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                redimensionarIcono("Imagenes/ImagenesEquipos/Villareal.png")),
        	new PartidoCombo("Athletic vs CD Leganés", 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/Leganes.png")),
        	new PartidoCombo("Athletic vs Girona FC", 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/Girona.png")),
        	new PartidoCombo("Athletic vs Real Valladolid CF", 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/Valladolid.png")),
        	new PartidoCombo("Athletic vs RCD Mallorca", 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/Mallorca.png")),
        	new PartidoCombo("Athletic vs CA Osasuna", 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        	    redimensionarIcono("Imagenes/ImagenesEquipos/Osasuna.png")),
        	new PartidoCombo("Athletic vs Rayo Vallecano", 
        		redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        		redimensionarIcono("Imagenes/ImagenesEquipos/RayoVallecano.png")),
        	new PartidoCombo("Athletic vs UD Las Palmas", 
        		redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        		redimensionarIcono("Imagenes/ImagenesEquipos/LasPalmas.png")),
        	new PartidoCombo("Athletic vs Deportivo Alavés", 
        		redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        		redimensionarIcono("Imagenes/ImagenesEquipos/Alaves.png")),
        	new PartidoCombo("Athletic vs FC Barcelona", 
        		redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
        		redimensionarIcono("Imagenes/ImagenesEquipos/Barça.png")),
        };

        cbPartido = new JComboBox<>(partidos);
        cbPartido.setRenderer(new ComboBoxRenderer());

        pPartido.add(lPartido);
        pPartido.add(cbPartido);

        JPanel pTipoSocio = new JPanel();
        pTipoSocio.setLayout(new FlowLayout());
        pTipoSocio.setBorder(BorderFactory.createTitledBorder("Información del tipo de Entrada"));

        JLabel lTipoSocio = new JLabel("Selecciona el tipo de Entrada:");
        cbTipoSocio = new JComboBox<>();
        Entradas entradas = new Entradas();
        for (Entradas.TipoEntrada tipo : entradas.getTipoEntradas()) {
        	cbTipoSocio.addItem(tipo.toString());
        }
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
        
        
        
        JPanel pZonaEstadio = new JPanel();
        pZonaEstadio.setLayout(new BorderLayout());
        pZonaEstadio.setBorder(BorderFactory.createTitledBorder("Zona del Estadio"));

        // Panel para imágenes
        JPanel pImagenesGradas = new JPanel();
        pImagenesGradas.setLayout(new GridLayout(3,3));
        JLabel imagenNorte = new JLabel(redimensionarIcono2("Imagenes/ImagenesGradas/GradaNorte.png"));
        JLabel imagenSur = new JLabel(redimensionarIcono2("Imagenes/ImagenesGradas/GradaSur.png"));
        JLabel imagenCentro = new JLabel(redimensionarIcono2("Imagenes/ImagenesGradas/AthleticLuz.png"));
        JLabel imagenEste = new JLabel(redimensionarIcono2("Imagenes/ImagenesGradas/GradaEste.png"));
        JLabel imagenOeste = new JLabel(redimensionarIcono2("Imagenes/ImagenesGradas/GradaOeste.png"));
        
        
        pImagenesGradas.add(new JLabel()); 
        pImagenesGradas.add(imagenNorte);
        pImagenesGradas.add(new JLabel());
        pImagenesGradas.add(imagenOeste); 
        pImagenesGradas.add(imagenCentro); 
        pImagenesGradas.add(imagenEste); 
        pImagenesGradas.add(new JLabel()); 
        pImagenesGradas.add(imagenSur); 
        pImagenesGradas.add(new JLabel()); 

        // Panel para botones de selección
        JPanel pOpciones = new JPanel();
        pOpciones.setLayout(new FlowLayout());

        ButtonGroup grupoOpciones = new ButtonGroup();
        JRadioButton opcionNorte = new JRadioButton("Grada Norte");
        JRadioButton opcionSur = new JRadioButton("Grada Sur");
        JRadioButton opcionEste = new JRadioButton("Grada Este");
        JRadioButton opcionOeste = new JRadioButton("Grada Oeste");

        grupoOpciones.add(opcionNorte);
        grupoOpciones.add(opcionSur);
        grupoOpciones.add(opcionEste);
        grupoOpciones.add(opcionOeste);

        pOpciones.add(opcionNorte);
        pOpciones.add(opcionSur);
        pOpciones.add(opcionEste);
        pOpciones.add(opcionOeste);

        // Añadir las imágenes y los botones al panel de zona de estadio
        pZonaEstadio.add(pImagenesGradas, BorderLayout.CENTER);
        pZonaEstadio.add(pOpciones, BorderLayout.SOUTH);
        
     // Añadir funcionalidad a los botones
        opcionNorte.addActionListener(e -> {
            imagenNorte.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaNorte.png"));
            imagenSur.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaSurBN.png"));
            imagenEste.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaEsteBN.png"));
            imagenOeste.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaOesteBN.png"));
        });

        opcionSur.addActionListener(e -> {
        	imagenNorte.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaNorteBN.png"));
            imagenSur.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaSur.png"));
            imagenEste.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaEsteBN.png"));
            imagenOeste.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaOesteBN.png"));
        });

        opcionEste.addActionListener(e -> {
        	imagenNorte.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaNorteBN.png"));
            imagenSur.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaSurBN.png"));
            imagenEste.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaEste.png"));
            imagenOeste.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaOesteBN.png"));
        });

        opcionOeste.addActionListener(e -> {
        	imagenNorte.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaNorteBN.png"));
            imagenSur.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaSurBN.png"));
            imagenEste.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaEsteBN.png"));
            imagenOeste.setIcon(redimensionarIcono2("Imagenes/ImagenesGradas/GradaOeste.png"));
        });
        
    
        
        
        JPanel pCentral = new JPanel();
        pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
        pCentral.add(pPartido);      
        pCentral.add(pZonaEstadio);  

        add(pCentral, BorderLayout.CENTER);

        add(panelInfo, BorderLayout.NORTH);
        add(pTipoSocio, BorderLayout.WEST);
        add(pBotones, BorderLayout.SOUTH);

        bComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = cNombre.getText();
                PartidoCombo partido = (PartidoCombo) cbPartido.getSelectedItem();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error! Nombre Necesario.","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if ("Seleccione un partido".equals(partido.getNombrePartido())) {
                    JOptionPane.showMessageDialog(null, "Error! Seleccione un partido válido.","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (grupoOpciones.getSelection() == null) {
                	JOptionPane.showMessageDialog(null, "Error! seleccione una zona del estadio.","Error", JOptionPane.ERROR_MESSAGE);
                	return;
                }
                
                String lado = "";
                for (AbstractButton button : Collections.list(grupoOpciones.getElements())) {
                    if (button.isSelected()) {
                        lado = button.getText();
                        break;
                    }
                }
                JOptionPane.showMessageDialog(null, "Compra Exitosa para " + partido.getNombrePartido() + "! Selecciona el asiento deseado.");
                VentanaAsientos ventanaAsientos = null;
                try {
                    ventanaAsientos = new VentanaAsientos(partido.getNombrePartido(),lado);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (ventanaAsientos != null) {
                    ventanaAsientos.setVisible(true);
                    dispose();
                }
            }
        });
    }
    

    private Icon redimensionarIcono(String rIcono) {
        ImageIcon iconoOriginal = new ImageIcon(rIcono);
        Image imagen = iconoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(imagen);
    }
    
    private Icon redimensionarIcono2(String rIcono) {
        ImageIcon iconoOriginal = new ImageIcon(rIcono);
        Image imagen = iconoOriginal.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        return new ImageIcon(imagen);
    }

    // Clase para que aparezcan los iconos en la JComboBox
    class PartidoCombo {
        private String nombrePartido;
        private Icon iconoEquipo1;
        private Icon iconoEquipo2;

        public PartidoCombo(String nombrePartido, Icon iconoEquipo1, Icon iconoEquipo2) {
            this.nombrePartido = nombrePartido;
            this.iconoEquipo1 = iconoEquipo1;
            this.iconoEquipo2 = iconoEquipo2;
        }

        public String getNombrePartido() {
            return nombrePartido;
        }

        public Icon getIconoEquipo1() {
            return iconoEquipo1;
        }

        public Icon getIconoEquipo2() {
            return iconoEquipo2;
        }

        @Override
        public String toString() {
            return nombrePartido;
        }
    }


    class ComboBoxRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        	JPanel panel = new JPanel();
        	
        	
        	setLayout(new BorderLayout());
            if (value instanceof PartidoCombo) {
                PartidoCombo partido = (PartidoCombo) value;
                 
                JLabel labelEquipo1 = new JLabel(partido.getIconoEquipo1());
                JLabel labelTexto = new JLabel(partido.getNombrePartido());
                JLabel labelEquipo2 = new JLabel(partido.getIconoEquipo2());

            	add(labelEquipo1, BorderLayout.EAST);
            	add(labelTexto, BorderLayout.CENTER);
            	add(labelEquipo2, BorderLayout.SOUTH);
            	

                panel.add(labelEquipo1); 
                panel.add(labelTexto);   
                panel.add(labelEquipo2); 
               
            }
            
            if (isSelected) {

            	panel.setBackground(Color.RED);
            } else {

            	panel.setBackground(Color.WHITE);
            }

            return panel;
        }	
    }
    

    public static void main(String[] args) {
            VentanaEntradas ventana = new VentanaEntradas();
            ventana.setVisible(true);
        };
    }