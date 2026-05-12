package co.edu.poligran.paradigmas.frontend.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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
    
    public static JPanel createButtonRow(JButton... btns) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 4));
        p.setBackground(C_SUPERFICIE);
        for (JButton b : btns) p.add(b);
        return p;
    }
    
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
}
