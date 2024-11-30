package gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaAsientos extends JFrame {
    private JPanel panel;
    private List<Asiento> asientos = new ArrayList<>();
    private final String AsientosSanMames = "ficheros/AsientosSanMames.csv";

    private final ImageIcon asientoLibreIcon = redimensionarImagen(new ImageIcon("Imagenes/ImagenesAsientos/AsientoLibre.png"), 100, 100);
    private final ImageIcon asientoOcupadoIcon = redimensionarImagen(new ImageIcon("Imagenes/ImagenesAsientos/AsientoOcupado.png"), 100, 100);

    public VentanaAsientos() throws IOException {
        setTitle("Selección de Asientos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cargarAsientosDesdeCSV();

        
        panel = new JPanel();
        panel.setLayout(new GridLayout(11, 17));

        
        
        mostrarAsientos();

        JButton guardarBtn = new JButton("Comprar entradas");
        guardarBtn.addActionListener(e -> {
            try {
                guardarAsientosEnCSV(AsientosSanMames);
                JOptionPane.showMessageDialog(this, "Entradas Compradas!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al comprar entradas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        

        add(panel, BorderLayout.CENTER);
        add(guardarBtn, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    
    

    private void cargarAsientosDesdeCSV() {
        int totalFilas = 11;
        int totalColumnas = 17;

        for (int fila = 0; fila < totalFilas; fila++) {
            if (fila == 3 || fila == 8) {
                for (int col = 0; col < totalColumnas; col++) {
                    asientos.add(new Asiento(fila, col, "Pasillo"));                   
                }
            } else {
                for (int col = 0; col < totalColumnas; col++) {
                    if (col % 9 == 8) { 

                        asientos.add(new Asiento(fila, col, "Escalera"));	
                    } else {
                        asientos.add(new Asiento(fila, col, "Libre"));
                    }
                }
            }
        }
    }
    
    
    

    private void mostrarAsientos() {
        panel.removeAll();
        for (Asiento asiento : asientos) {
            JLabel asientoLabel;

            if (asiento.getEstado().equals("Escalera")) {
                asientoLabel = new JLabel("Escalera", (int) CENTER_ALIGNMENT);
                asientoLabel.setBackground(Color.LIGHT_GRAY);
                asientoLabel.setOpaque(true);
            } else if (asiento.getEstado().equals("Pasillo")) {
            	asientoLabel = new JLabel("Pasillo", (int) CENTER_ALIGNMENT);
            	asientoLabel.setBackground(Color.LIGHT_GRAY);
            	asientoLabel.setOpaque(true);            	
            }else {
                asientoLabel = new JLabel(asiento.getEstado().equals("Libre") ? asientoLibreIcon : asientoOcupadoIcon);
                asientoLabel.setHorizontalAlignment(JLabel.CENTER);
                asientoLabel.setVerticalAlignment(JLabel.CENTER);

                asientoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if (asiento.getEstado().equals("Libre")) {
                            asiento.setEstado("Ocupado");
                            asientoLabel.setIcon(asientoOcupadoIcon);
                        } else if (asiento.getEstado().equals("Ocupado")) {
                            asiento.setEstado("Libre");
                            asientoLabel.setIcon(asientoLibreIcon);
                        }
                    }
                });
            }

            panel.add(asientoLabel);
        }
        panel.revalidate();
        panel.repaint();
    }

    
    
    private void guardarAsientosEnCSV(String rutaArchivo) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
        int currentRow = -1;

        for (Asiento asiento : asientos) {
            if (asiento.getFila() != currentRow) {
                if (currentRow != -1) bw.newLine();
                currentRow = asiento.getFila();
            }
            bw.write((asiento.getColumna() > 0 ? ";" : "") + asiento.getEstado());
        }
        bw.close();
    }

    
    
    private ImageIcon redimensionarImagen(ImageIcon icono, int ancho, int alto) {
        Image img = icono.getImage(); 
        Image imgRedimensionada = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imgRedimensionada); 
    }
    
    

    class Asiento {
        private int fila;
        private int columna;
        private String estado;

        public Asiento(int fila, int columna, String estado) {
            this.fila = fila;
            this.columna = columna;
            this.estado = estado;
        }

        public int getFila() {
            return fila;
        }

        public int getColumna() {
            return columna;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }
    
    

    public static void main(String[] args) throws IOException {
        VentanaAsientos ventana = new VentanaAsientos();
        ventana.setVisible(true);
    }
}




