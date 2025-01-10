package gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * El codigo necesitado para realizar el metodo que carga y guarda para diferenciar cada partido
 * ha sido realizado con ChatGPT-4
 */
public class VentanaAsientos extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private List<Asiento> asientos = new ArrayList<>();
    private final String nomFich = "ficheros/";

    private final ImageIcon asientoLibreIcon = redimensionarImagen(new ImageIcon("Imagenes/ImagenesAsientos/AsientoLibre.png"), 100, 100);
    private final ImageIcon asientoOcupadoIcon = redimensionarImagen(new ImageIcon("Imagenes/ImagenesAsientos/AsientoOcupado.png"), 100, 100);

    public VentanaAsientos(String partido, String lado) throws IOException {
        setTitle("Selección de Asientos - " + partido + " (" + lado + ")");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cargarAsientosDesdeCSV(partido, lado);

        panel = new JPanel();
        panel.setLayout(new GridLayout(11, 17));

        mostrarAsientos();

        JPanel pBotones = new JPanel();
        pBotones.setLayout(new FlowLayout());
        pBotones.setBorder(null);

        JButton guardarBtn = new JButton("Comprar entradas");
        guardarBtn.addActionListener(e -> {
            boolean hayAsientosSeleccionados = asientos.stream().anyMatch(asiento -> asiento.getEstado().equals("Ocupado"));

            if (!hayAsientosSeleccionados) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un asiento.");
            } else {
                try {
                    guardarAsientosEnCSV(partido, lado);
                    JOptionPane.showMessageDialog(null, "Entradas Compradas!");
                    dispose();
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Error al comprar las entradas");
                }
            }
        });

        JButton atrasBtn = new JButton("Atrás");
        atrasBtn.addActionListener(e -> {
            VentanaEntradas ventanaEntradas = new VentanaEntradas();
            ventanaEntradas.setVisible(true);
            dispose();
        });

        add(panel, BorderLayout.CENTER);
        pBotones.add(guardarBtn);
        pBotones.add(atrasBtn);
        add(pBotones, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void cargarAsientosDesdeCSV(String partido, String lado) throws IOException {
        asientos.clear();
        String rutaArchivo = nomFich + partido + "_" + lado + "_Asientos.csv";
        File f = new File(rutaArchivo);

        if (f.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            int fila = 0;

            while ((linea = br.readLine()) != null) {
                String[] estados = linea.split(";");
                for (int columna = 0; columna < estados.length; columna++) {
                    asientos.add(new Asiento(fila, columna, estados[columna]));
                }
                fila++;
            }
            br.close();
        } else {
            generarAsientosIniciales();
        }
    }

    private void generarAsientosIniciales() {
        int totalFilas = 11;
        int totalColumnas = 17;

        for (int fila = 0; fila < totalFilas; fila++) {
            if (fila == 3 || fila == 8) {
                for (int col = 0; col < totalColumnas; col++) {
                    asientos.add(new Asiento(fila, col, "Pasillo"));
                }
            } else {
                for (int col = 0; col < totalColumnas; col++) {
                    if (col == 8) {
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
            JLabel lAsiento;

            if (asiento.getEstado().equals("Escalera") || asiento.getEstado().equals("Pasillo")) {
                lAsiento = new JLabel(asiento.getEstado(), (int) CENTER_ALIGNMENT);
                lAsiento.setBackground(Color.LIGHT_GRAY);
                lAsiento.setOpaque(true);
            } else {
                lAsiento = new JLabel(asiento.getEstado().equals("Libre") ? asientoLibreIcon : asientoOcupadoIcon);
                lAsiento.setHorizontalAlignment(JLabel.CENTER);
                lAsiento.setVerticalAlignment(JLabel.CENTER);

                lAsiento.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if (asiento.getEstado().equals("Libre")) {
                            asiento.setEstado("Ocupado");
                            lAsiento.setIcon(asientoOcupadoIcon);
                        } else if (asiento.getEstado().equals("Ocupado")) {
                            asiento.setEstado("Libre");
                            lAsiento.setIcon(asientoLibreIcon);
                        }
                    }
                });
            }

            panel.add(lAsiento);
        }
        panel.revalidate();
        panel.repaint();
    }

    private void guardarAsientosEnCSV(String partido, String lado) throws IOException {
        String rutaArchivo = nomFich + partido + "_" + lado + "_Asientos.csv";
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
    

    
//    public static void main(String[] args) {
//    	
//        // Crear y mostrar la ventana.
//        javax.swing.SwingUtilities.invokeLater(() -> {
//            try {
//                String partido = "Athletic_vs_RealSociedad";
//                String lado = "Norte";
//
//                new VentanaAsientos(partido, lado).setVisible(true);
//            } catch (IOException e) {
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Error al cargar los asientos.");
//            }
//        });
//    }
}
