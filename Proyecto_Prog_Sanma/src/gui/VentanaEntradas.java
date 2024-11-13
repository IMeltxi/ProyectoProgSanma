package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import domain.Entradas;
import domain.Usuario;

import javax.swing.JList;
import javax.swing.Icon;
import javax.swing.BoxLayout;

public class VentanaEntradas extends JFrame {
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
            new PartidoCombo("Athletic vs Real Madrid", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/RealMadrid.png")),
            new PartidoCombo("Athletic vs Barcelona", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Barça.png")),
            new PartidoCombo("Athletic vs Real Sociedad", 
            		redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/RealSociedad.png")),
            new PartidoCombo("Athletic vs Alaves", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Alaves.png")),
            new PartidoCombo("Athletic vs Atletico de Madrid", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AtleticoMadrid.png")),
            new PartidoCombo("Athletic vs Betis", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Betis.png")),
            new PartidoCombo("Athletic vs Celta", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Celta.png")),
            new PartidoCombo("Athletic vs RCD Espanyol", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Espanyol.png")),
            new PartidoCombo("Athletic vs Getafe", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Getafe.png")),
            new PartidoCombo("Athletic vs Girona", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Girona.png")),
            new PartidoCombo("Athletic vs UD Las Palmas", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/LasPalmas.png")),
            new PartidoCombo("Athletic vs Leganes", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Leganes.png")),
            new PartidoCombo("Athletic vs RCD Mallorca", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Mallorca.png")),
            new PartidoCombo("Athletic vs Osasuna", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Osasuna.png")),
            new PartidoCombo("Athletic vs Rayo Vallecano", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/RayoVallecano.png")),
            new PartidoCombo("Athletic vs Sevilla", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Sevilla.png")),
            new PartidoCombo("Athletic vs Valencia CF", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Valencia.png")),
            new PartidoCombo("Athletic vs Valladolid", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Valladolid.png")),
            new PartidoCombo("Athletic vs Villareal", 
                    redimensionarIcono("Imagenes/ImagenesEquipos/AthleticClub.png"), 
                    redimensionarIcono("Imagenes/ImagenesEquipos/Villareal.png")),
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

        bComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = cNombre.getText();
                PartidoCombo partido = (PartidoCombo) cbPartido.getSelectedItem();
                String socio = (String) cbTipoSocio.getSelectedItem();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error! Nombre Necesario.");
                } else {
                    JOptionPane.showMessageDialog(null, "Compra Exitosa para " + partido.getNombrePartido() + "!");
                }
            }
        });
    }

    // Método para poner el tamaño del icono
    private Icon redimensionarIcono(String rIcono) {
        ImageIcon iconoOriginal = new ImageIcon(rIcono);
        Image imagen = iconoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
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
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        	JPanel panel = new JPanel();
//        	JPanel panelIzq = new JPanel();
//            JPanel panelCen = new JPanel();
//            JPanel panelDer= new JPanel();
            
//            panelIzq.setLayout(new FlowLayout(FlowLayout.LEFT));
//            panelCen.setLayout(new FlowLayout(FlowLayout.CENTER));
//            panelDer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        	panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            if (value instanceof PartidoCombo) {
                PartidoCombo partido = (PartidoCombo) value;
                
                JLabel labelEquipo1 = new JLabel(partido.getIconoEquipo1());
                JLabel labelTexto = new JLabel(partido.getNombrePartido());
                JLabel labelEquipo2 = new JLabel(partido.getIconoEquipo2());

                panel.add(labelEquipo1); 
                panel.add(labelTexto);   
                panel.add(labelEquipo2); 
               
            }
            
            if (isSelected) {
//                panelIzq.setBackground(Color.RED);
//                panelCen.setBackground(Color.RED);
//                panelDer.setBackground(Color.RED);
            	panel.setBackground(Color.RED);
            } else {
//            	panelIzq.getBackground();
//                panelCen.getBackground();
//                panelDer.getBackground();
            	panel.getBackground();
            }

            return panel;
        }	
    }
    
    

    public static void main(String[] args) {
            VentanaEntradas ventana = new VentanaEntradas();
            ventana.setVisible(true);
        };
    }
