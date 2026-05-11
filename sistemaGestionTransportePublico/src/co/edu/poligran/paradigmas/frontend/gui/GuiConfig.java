package co.edu.poligran.paradigmas.frontend.gui;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;

public class GuiConfig {
    // ── Paleta ─────────────────────────────────────────

    public static final Color C_PRIMARIO = new Color(18, 78, 160);

    public static final Color C_SECUNDARIO = new Color(238, 244, 255);

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
}
