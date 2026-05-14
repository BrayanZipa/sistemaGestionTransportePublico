package co.edu.poligran.paradigmas.frontend.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

public class GuiUtils {
	
    // ── Paleta ─────────────────────────────────────────

    public static final Color C_PRIMARIO = new Color(18, 78, 160);

    public static final Color C_SECUNDARIO = new Color(238, 244, 255);
    
    public static final Color C_GRIS = Color.GRAY;

    public static final Color C_SUPERFICIE = Color.WHITE;

    public static final Color C_EXITO = new Color(34, 139, 34);

    public static final Color C_PELIGRO = new Color(200, 40, 40);

    public static final Color C_ADVERTENCIA = new Color(180, 120, 0);

    public static final Color C_TEXTO = new Color(25, 25, 40);

    public static final Color C_BORDE = new Color(190, 210, 235);

    // ── Tipografía ────────────────────────────────────

    public static final Font F_TITULO = new Font("Segoe UI", Font.BOLD, 20);

    public static final Font F_SECCION = new Font("Segoe UI", Font.BOLD, 13);

    public static final Font F_LABEL = new Font("Segoe UI", Font.BOLD, 12);

    public static final Font F_CAMPO = new Font("Segoe UI", Font.PLAIN, 12);

    public static final Font F_BTN = new Font("Segoe UI", Font.BOLD, 12);

    public static final Font F_TAB = new Font("Segoe UI", Font.BOLD, 13);

    // ── Formatos ──────────────────────────────────────

    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    
    // ── Métodos para componentes ──────────────────────────────────────
  
    /**
     * Crea un JPanel con borde titulado usando la paleta de colores del sistema.
     * 
     * @param titulo texto del borde del panel
     * @return JPanel configurado con borde titulado
     */
    public static JPanel createPanel(String titulo) {
        JPanel p = new JPanel();
        p.setBackground(C_SUPERFICIE);
        
        TitledBorder tb = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(C_BORDE, 1, true),
            titulo, 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            F_SECCION, 
            C_PRIMARIO
        );
        
        p.setBorder(BorderFactory.createCompoundBorder(tb, new EmptyBorder(6, 10, 8, 10)));
        return p;
    }
    
    /**
     * Crea un objeto GridBagConstraints con márgenes y relleno horizontal por defecto.
     * 
     * @return GridBagConstraints con configuración base
     */
    public static GridBagConstraints createGbc() {
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.fill = GridBagConstraints.HORIZONTAL;
        return g;
    }
    
    /**
     * Crea un JTextField estilizado con la tipografía y borde del sistema.
     * 
     * @param cols número de columnas del campo
     * @return JTextField configurado
     */
    public static JTextField createTextField(int cols) {
        JTextField f = new JTextField(cols);
        f.setFont(F_CAMPO);
        f.setBorder(
        	BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(C_BORDE), 
	            new EmptyBorder(3, 6, 3, 6)
            )
        );
        return f;
    }
    
    /**
     * Crea un JCheckBox estilizado con el fondo y tipografía del sistema.
     * 
     * @param texto texto junto al check box
     * @return JCheckBox configurado
     */
    public static JCheckBox createCheckBox(String texto) {
        JCheckBox chk = new JCheckBox(texto);
        chk.setBackground(C_SUPERFICIE);
        chk.setFont(F_CAMPO);
        return chk;
    }
    
    /**
     * Crea un JSpinner para selección de fecha, inicializado con un desplazamiento
     * desde la fecha actual.
     * 
     * @param diasDesdeHoy desplazamiento en días desde hoy (negativo para pasado, cero para hoy)
     * @return JSpinner con editor de fecha en formato dd/MM/yyyy
     */
    public static JSpinner createDateField(int diasDesdeHoy) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, diasDesdeHoy);
        SpinnerDateModel m = new SpinnerDateModel(cal.getTime(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner sp = new JSpinner(m);
        sp.setEditor(new JSpinner.DateEditor(sp, "dd/MM/yyyy"));
        sp.setFont(F_CAMPO);
        return sp;
    }
    
    /**
     * Crea un JLabel estilizado con la tipografía y color de texto del sistema.
     * 
     * @param texto texto de la etiqueta
     * @return JLabel configurado
     */
    public static JLabel createLabel(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(F_LABEL); 
        l.setForeground(C_TEXTO);
        return l;
    }
    
    /**
     * Agrega una fila de formulario con una etiqueta y un campo al panel usando GridBagLayout.
     * 
     * @param panel panel contenedor
     * @param g restricciones GridBagConstraints (se modifican internamente)
     * @param row índice de la fila
     * @param textoLabel texto de la etiqueta
     * @param campo componente del campo
     */
    public static void createFormRow(JPanel panel, GridBagConstraints g, int row, String textoLabel, JComponent campo) {
	    g.gridx = 0;
	    g.gridy = row;
	    g.gridwidth = 1;
	    g.weightx = 0;

	    panel.add(createLabel(textoLabel), g);

	    g.gridx = 1;
	    g.weightx = 1;

	    panel.add(campo, g);
	}
    
    /**
     * Crea un JPanel con FlowLayout centrado que contiene los botones especificados.
     * 
     * @param btns botones a incluir en la fila
     * @return JPanel con los botones agregados
     */
    public static JPanel createButtonRow(JButton... btns) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 4));
        p.setBackground(C_SUPERFICIE);
        for (JButton b : btns) p.add(b);
        return p;
    }
    
    /**
     * Crea un JButton estilizado con color de fondo, texto blanco, efecto hover y cursor de mano.
     * 
     * @param texto texto del botón
     * @param fondo color de fondo del botón
     * @param al ActionListener asociado al botón
     * @return JButton configurado
     */
    public static JButton createBtn(String texto, Color fondo, java.awt.event.ActionListener al) {
        JButton b = new JButton(texto);
        b.setFont(F_BTN); b.setBackground(fondo); b.setForeground(Color.WHITE);
        b.setFocusPainted(false); b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        b.addActionListener(al);
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) { b.setBackground(fondo.darker()); }
            @Override public void mouseExited (java.awt.event.MouseEvent e) { b.setBackground(fondo); }
        });
        return b;
    }
    
    // ── Métodos para modales ──────────────────────────────────────
    
    /**
     * Muestra un diálogo modal de información.
     * 
     * @param parent componente padre del diálogo
     * @param mensaje mensaje informativo a mostrar
     */
    public static void showInfoMessage(java.awt.Component parent, String mensaje) {
	    JOptionPane.showMessageDialog(
	        parent,
	        mensaje,
	        "Información",
	        JOptionPane.INFORMATION_MESSAGE
	    );
    }
    
    /**
     * Muestra un diálogo modal de error.
     * 
     * @param parent componente padre del diálogo
     * @param mensaje mensaje de error a mostrar
     */
    public static void showErrorMessage(java.awt.Component parent, String mensaje) {
	    JOptionPane.showMessageDialog(
	        parent,
	        mensaje,
	        "Error",
	        JOptionPane.ERROR_MESSAGE
	    );
	}
    
    /**
     * Muestra un diálogo modal de confirmación con opciones Sí/No.
     * 
     * @param parent componente padre del diálogo
     * @param mensaje mensaje de confirmación
     * @return true si el usuario seleccionó Sí, false en caso contrario
     */
    public static boolean confirmMessage(java.awt.Component parent, String mensaje) {
	    int opcion = JOptionPane.showConfirmDialog(
	        parent,
	        mensaje,
	        "Confirmar",
	        JOptionPane.YES_NO_OPTION,
	        JOptionPane.WARNING_MESSAGE
	    );

	    return opcion == JOptionPane.YES_OPTION;
	}
    
    // ── Método para resaltar una fila ──────────────────────────────────────
    
    /**
     * Busca y selecciona la fila de una tabla cuyo valor en la columna indicada coincida exactamente 
     * con el texto dado, además resalta la vista hasta la fila.
     * 
     * @param tabla   tabla donde se realizará la selección
     * @param modelo  modelo de datos de la tabla
     * @param valor   texto a buscar
     * @param columna índice de la columna donde buscar
     */
    public static void highlightRow(JTable tabla, TableModel modelo, String valor, int columna) {
	    for (int i = 0; i < modelo.getRowCount(); i++) {
	    	String actual = String.valueOf(modelo.getValueAt(i, columna)).trim();
	    	
	    	if (valor.trim().equalsIgnoreCase(actual)) {
	            tabla.setRowSelectionInterval(i, i);
	            tabla.scrollRectToVisible(tabla.getCellRect(i, 0, true));
	            return;
	        }
	    }
	}
}
