package gui;

import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import domain.Usuario;
import domain.Admin;
public class VentanaHacermeSocio extends JFrame {
	private Admin admin; // Referencia al administrador para actualizar los usuarios
    private Usuario usuario; // Usuario actual que está iniciando la sesión
    public VentanaHacermeSocio() {
        this.admin = admin;
        this.usuario = usuario;
        // Configuración de la ventana
        setTitle("Hacerme Socio");
        setSize(800, 600); // Tamaño de la ventana más grande
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelprincipal = new JPanel();

        // Tabla con los distintos tipos de socios
        String[] header = {"Nombre", "Info", "Precio", "Hazte Socio"};
        Object[][] datos = {
                {"VIP", "Acceso al palco", "1000€", ""},
                {"GAZTEABONO", "Solo para jóvenes", "500€", ""},
                {"SOCIO", "Básico", "750€", ""}
        };

        DefaultTableModel model = new DefaultTableModel(datos, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo la columna de acción es editable
                return column == 3;
            }
        };

        JTable tabla = new JTable(model);
        tabla.setRowHeight(50); // Incrementar la altura de las filas
        tabla.getColumn("Hazte Socio").setPreferredWidth(300); // Ancho mayor para los botones

        // Asignar renderizador y editor personalizado para la columna "Hazte Socio"
        tabla.getColumn("Hazte Socio").setCellRenderer(new ButtonColumnRenderer());
        tabla.getColumn("Hazte Socio").setCellEditor(new ButtonColumnEditor());

        // Agregar la tabla al panel principal dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabla);
        panelprincipal.add(scrollPane);
        add(panelprincipal);
        setVisible(true);
    }

       //hago el renderer
    class ButtonColumnRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = new JButton(getButtonLabel(row));
            return button;
        }

        // Método para las labels
        private String getButtonLabel(int row) {
            switch (row) {
                case 0:
                    return "Hazte VIP";
                case 1:
                    return "Solicitar GazteAbono";
                case 2:
                    return "Hazte Socio";
                default:
                    return "Acción";
            }
        }
    }

    // Editor para convertir las labels en botones ayudado con una ia
    class ButtonColumnEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton boton;

        public ButtonColumnEditor() {
            boton = new JButton();

            // Añadir el ActionListener para diferenciar los botones
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    darAccion(boton.getText());
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            boton.setText(getButtonLabel(row));
            return boton;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        // Método para definir el texto del botón según la fila
        private String getButtonLabel(int row) {
            switch (row) {
                case 0:
                    return "Hazte VIP";
                case 1:
                    return "Solicitar GazteAbono";
                case 2:
                    return "Hazte Socio";
                default:
                    return "";
            }
        }

        private void darAccion(String action) {
            switch (action) {
                case "Hazte VIP":
                    abrirVentanaVIP();
                    break;
                case "Solicitar GazteAbono":
                    abrirGazteAbono();
                    break;
                case "Hazte Socio":
                    abrirSocioBasico();
                    break;
                default:
                    System.out.println("Acción no reconocida: " + action);
            }
        }

        // Métodos para abrir las ventanas
        private void abrirVentanaVIP() {
            VentanaVIP ventanavip = new VentanaVIP(admin, usuario);
            ventanavip.setVisible(true);
        }

        private void abrirGazteAbono() {
            VentanaGazteAbono gazteabono = new VentanaGazteAbono();
            gazteabono.setVisible(true);
        }

        private void abrirSocioBasico() {
            VentanaSocioBasico ventanabasico = new VentanaSocioBasico();
            ventanabasico.setVisible(true);
        }
    }

    // Método principal para probar la ventana
    public static void main(String[] args) {
        new VentanaHacermeSocio();
    }
}
